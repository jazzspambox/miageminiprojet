package com.paris5.miage1.trombinoscope.controllers;

import com.paris5.miage1.trombinoscope.bean.Filtre;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.ZoneManager;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import java.util.ArrayList;

/**
 *
 * @author mourad
 */
public class Recherche extends ZoneManager {

    private int currentSession;
    private int page;
    private int formation_id;
    private int formation_session;
    private String serach_value="";

    public Recherche(HttpServletRequest request, HttpServletResponse response) throws SQLException, NullPointerException, ServletException, IOException {
        super(true, request, response);
        String format = "yyyy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        
        currentSession = Integer.parseInt(formater.format(date));
        page = Valideur.getNumeric(request.getParameter("page"), 1);
        if(request.getParameter("search_promo")!=null){
          formation_id=Valideur.getNumeric(request.getParameter("search_promo").substring(0,request.getParameter("search_promo").indexOf("|")),0);
          formation_session=Valideur.getNumeric(request.getParameter("search_promo").substring(request.getParameter("search_promo").indexOf("|")+1),currentSession);
        }else{
            formation_id=user.getFormation().getId();
            formation_session=currentSession;
        
             }
        if(request.getParameter("serach_value")!=null){
            serach_value=request.getParameter("serach_value");
        }
    }

    private Filtre getFilter() {

        Filtre filtre = null;
        session.setAttribute("Filter", filtre);
        return filtre;
    }

    private void initSearch() throws SQLException{
        SearchDAO search = new SearchDAO();
        Liste users = null;
        int userCount = 0;
        int search_type=Valideur.getNumeric(request.getParameter("search_type"),0);
                Pagination p=null;
        if(formation_id==0 && serach_value.equals("recherche")){
            search_type=7;
        }else{
            if(serach_value.equals("recherche")||serach_value.equals("")){
               search_type=0;
            }
          }
        
        
        switch (search_type) {
            case 0:
                 userCount = search.countByPromo(String.valueOf(formation_id), formation_session);
                    if (userCount > 0) {
                         p = new Pagination(page, userCount);
                        this.addZone(p);
                        search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                        users = new Liste (search.findByPromo(String.valueOf(formation_id), formation_session));
                    }
                break;
            case 1:
                userCount=search.countByLastName(serach_value);
                p = new Pagination(page, userCount);
                this.addZone(p);
                search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                users = new Liste(search.findByLastName(serach_value,formation_id));
                        
                break;
            case 2:
                userCount=search.countByFirstName(serach_value);
                p=new Pagination(page, userCount);
                this.addZone(p);
                search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                users = new Liste(search.findByFirstName(serach_value, formation_id));
                break;
            case 3:
                userCount=1;
                p=new Pagination(page, userCount);
                this.addZone(p);
                search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                users = new Liste(search.findByPhone(serach_value, true,formation_id, false));
               break;
                case 4:
                    userCount=1;
                    p=new Pagination(page, userCount);
                    this.addZone(p);
                    search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                    users = new Liste(search.findByPhone(serach_value, false,formation_id, false));
                     break;
                case 5:
                    userCount=1;
                    p=new Pagination(page, userCount);
                    this.addZone(p);
                    search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                    users = new Liste(search.findByEmail(serach_value,formation_id));
                    break;
                case 6:
                    userCount=1;
                    p=new Pagination(page, userCount);
                    this.addZone(p);
                    search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                    users = new Liste(search.findByEtudiantId(serach_value, formation_id));
                    break;
               case 7:
                userCount = search.countAllPromo(currentSession);
                    if (userCount > 0) {
                        p = new Pagination(page, userCount);
                        this.addZone(p);
                        search.setLimit(p.getMinimum(), Configuration.PROFILS_PAR_PAGE);
                        users = new Liste (search.searchfAllPromo(currentSession));
                    }
            
        }
        request.setAttribute("users", users);
        request.setAttribute("pagination", p);
    }
    
    @Override
    public void process() throws SQLException, ServletException, IOException {
        initSearch();
        //super.run();     
        request.getRequestDispatcher("views/home.jsp").forward(request, response);
    }
}