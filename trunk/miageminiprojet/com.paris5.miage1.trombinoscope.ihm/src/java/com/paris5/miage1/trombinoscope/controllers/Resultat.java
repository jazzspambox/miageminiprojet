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
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.Zone;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * zone de gestion des resultats de recherches
 * @author Mourad, Saliou, Idir
 */
public abstract class Resultat extends Zone {
    
    protected Utilisateur user;
    protected double count;
    protected int start;
    protected Filtre filtre;
               
    /**
     * constructeur
     * 
     * @param usr Utilisateur connecte
     * @param act Action
     * @param page numero de la page courante
     * @throws SQLException 
     */
    public Resultat(Filtre filtre, Utilisateur usr) throws SQLException{
        super();
        this.user = usr;
        this.currentPage=filtre.getPage();
        this.filtre = filtre;
        this.init(true);
    }
    
    /**
     * constructeur
     * 
     * @param filtre Filter criteres de recherches
     * @param usr Utilisateur connecte
     * @param count nombre de resultats
     * @param start born inferieur de recherche
     * @throws SQLException 
     */
    public Resultat(Filtre filtre, Utilisateur usr, int count, int start) throws SQLException{
        super();
        this.user = usr;
        this.currentPage=filtre.getPage();
        this.start = start;
        this.count = count;
        this.filtre = filtre;
        this.init(false);
    }
    
    /**
     * initialise la recherche selon l action envoyee
     * @param countResult pour faire soit un comptage soit une recuperation  des resultats
     * @throws SQLException 
     */
    private void init(boolean countResult) throws SQLException {
        
        String format = "yyyy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String res = formater.format(date);    
        int currentSession = Integer.valueOf(res);
                
        SearchDAO search = new SearchDAO();
        if(!countResult)
            search.setLimit(this.start, Configuration.PROFILS_PAR_PAGE);
        
       switch (filtre.getAction()) {
            case SEARCH_NOM:              
                if(countResult){
                    this.count = search.countByLastName(filtre.getRecherche());
                }
                else if(this.count > 0) {
                    this.addAll(search.findByLastName(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_PRENOM:
                if(countResult){
                    this.count = search.countByFirstName(filtre.getRecherche());
                }
                else if(this.count > 0) {
                    this.addAll(search.findByFirstName(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_MOBILE:
                if(countResult){
                    ArrayList tmp = search.findByPhone(filtre.getRecherche(), true,filtre.getFormationId(), true);
                    this.count = tmp.size() > 0 ? (Integer) tmp.get(0) : 0;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByPhone(filtre.getRecherche(), true,filtre.getFormationId(), false));
                }
             
                break;
            case SEARCH_FIXE:
                if(countResult){
                    ArrayList tmp = search.findByPhone(filtre.getRecherche(), false,filtre.getFormationId(), true);
                    this.count = tmp.size() > 0 ? (Integer) tmp.get(0) : 0;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByPhone(filtre.getRecherche(), false,filtre.getFormationId(), false));
                }
                break;
            case SEARCH_EMAIL:
                if(countResult){
                   this.count = 1;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByEmail(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_PROMOTION:
                if(countResult)
                    this.count = search.countByPromo(String.valueOf(filtre.getFormationId()), filtre.getSession());
                else if(this.count > 0)   
                    this.addAll(search.findByPromo(String.valueOf(filtre.getFormationId()), filtre.getSession()));
                break;
            case SEARCH_NUM_ETUDIANT :
                if(countResult)
                    this.count = 1;
                else if(this.count > 0)   
                    this.addAll(search.findByEtudiantId(filtre.getRecherche(), filtre.getSession()));
                break;
            default:
                String grp = user.getGroupe().getNom().toUpperCase();
                if (grp.equals("ETUDIANT")) {
                    filtre.setLabel(this.user.getFormation().getLabel());
                    if(countResult)
                        this.count = search.countByPromo(String.valueOf(user.getFormation().getId()), currentSession);
                    else if(this.count > 0)   
                        this.addAll(search.findByPromo(String.valueOf(user.getFormation().getId()), currentSession));
                } 
                else {
                    filtre.setLabel(""+currentSession);
                    if(countResult)
                        this.count = search.countAllPromo(currentSession);
                    else if(this.count > 0)   
                        this.addAll(search.searchfAllPromo(currentSession));
                }
                break;
        }
        
        filtre.setResultCount((int) this.count);
    }

    /**
     * retourn le nombre de resultats
     * @return int
     */
    public int getCount() {
        return (int) count;
    }
}
