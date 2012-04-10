/* ------------------------------------------------------------------------
 
    Licensed under the Educational Community License version 1.0

    This Original Work, including software, source code, documents,
    or other related items, is being provided by the copyright holder(s)
    subject to the terms of the Educational Community License. By
    obtaining, using and/or copying this Original Work, you agree that you
    have read, understand, and will comply with the following terms and
    conditions of the Educational Community License:

    Permission to use, copy, modify, merge, publish, distribute, and
    sublicense this Original Work and its documentation, with or without
    modification, for any purpose, and without fee or royalty to the
    copyright holder(s) is hereby granted, provided that you include the
    following on ALL copies of the Original Work or portions thereof,
    including modifications or derivatives.

 ------------------------------------------------------------------------ */
package com.paris5.miage1.trombinoscope.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.paris5.miage1.trombinoscope.processor.ZoneManager;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import com.paris5.miage1.trombinoscope.utils.Valideur;


/**
 * controlleur de la recherche
 * @author Mourad, Saliou, Idir
 */
public class Recherche extends ZoneManager {

    private Filtre filtre;

    /**
     * constructeur
     * @param request HttpServletRequest requete envoyee
     * @param response HttpServletResponse reponse attendu
     * @throws SQLException
     * @throws NullPointerException
     * @throws ServletException
     * @throws IOException 
     */
    public Recherche(HttpServletRequest request, HttpServletResponse response) throws SQLException, NullPointerException, ServletException, IOException {
        super(true, request, response);
        
        //initialisation des parametres
        int page = Valideur.getNumeric(request.getParameter("page"), 1);
        String searchValue = Valideur.getLabel(request.getParameter("serach_value"),"");
        Action act = Action.get(Valideur.getAlphabetic(request.getParameter("type")));
        String promoLabel = Valideur.getLabel(request.getParameter("promo_label"));
        String promo = Valideur.getAuthorized(request.getParameter("search_promo"));
        int session = 0;
        int formation_id = 0;
        if(promo!=null){
            String explode[] = promo.split("-");
            if(explode.length==2){
                session = Valideur.getNumeric(explode[1]);
                formation_id = Valideur.getNumeric(explode[0]);
            }
        }
        
        // hack search null
        if(searchValue.equals("")){
            if(formation_id!=0 && session!=0){
                act=Action.SEARCH_PROMOTION;
            }
            else{
                act=Action.DEFAULT;
            }
        }
        
        // initialisation du filtre
        // derniere recherche effectuee
        if(act.equals(Action.DEFAULT) && searchValue.equals("") && session==0 && formation_id==0){
            filtre = (Filtre) this.session.getAttribute("filtre");
            if(filtre==null){
                filtre = new Filtre();
                filtre.setAction(act);
                filtre.setRecherche(searchValue);
                filtre.setAction(Action.DEFAULT);
            }
        }
        else{
            filtre = new Filtre();
            filtre.setAction(act);
            filtre.setFormationId(formation_id);
            filtre.setLabel(promoLabel);
            filtre.setRecherche(searchValue);
            filtre.setSession(session);
            page = 1;
        }
        filtre.setPage(page);
    }
    
    @Override
    public void process() throws SQLException, ServletException, IOException {
        
        // initialisation des zones
        Pagination pagination = new Pagination(filtre, this.user);
        Liste zoneCentrale = new Liste(filtre, this.user, pagination.getCount(), pagination.getMinimum());
        this.addZone(pagination);
        this.addZone(zoneCentrale);
        
        // sauvegarde de la derniere recherche en session
        this.session.setAttribute("filtre", filtre);
        
        // envoi des resultats a la vue
        request.setAttribute("filtre", filtre);
        request.setAttribute("pagination", pagination);
        request.setAttribute("users", zoneCentrale);
        
        super.process();     
        request.getRequestDispatcher("views/home.jsp").forward(request, response);
    }
}