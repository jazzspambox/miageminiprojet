package com.paris5.miage1.trombinoscope.controller;

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

/**
 *
 * @author mourad
 */
public class Recherche extends ZoneManager {

    private int currentSession;
    private int page;

    public Recherche(HttpServletRequest request, HttpServletResponse response) throws SQLException, NullPointerException, ServletException, IOException {
        super(true, request, response);
        String format = "yyyy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        currentSession = Integer.parseInt(formater.format(date));
        page = Valideur.getNumeric(request.getParameter("page"), 1);
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
        switch (Action.get(Valideur.getAlphabetic(request.getParameter("action")))) {
            case SEARCH_PROMOTION:

                break;

            default:
                if (user.getGroupe().getNom().toUpperCase().equals("ETUDIANT")) {
                    userCount = search.countByPromo(String.valueOf(user.getFormation().getId()), currentSession);
                    if (userCount > 0) {
                        Pagination p = new Pagination(page, userCount);
                        this.addZone(p);
                        search.setLimit(p.getMinimum(), Configuration.PROFILSPAGE);
                        users = new Liste (search.findByPromo(String.valueOf(user.getFormation().getId()), page));
                    }
                } else {
                    userCount = search.countAllPromo(currentSession);
                    Pagination p = new Pagination(page, userCount);
                    this.addZone(p);
                    search.setLimit(p.getMinimum(), Configuration.PROFILSPAGE);
                    users = new Liste(search.searchfAllPromo(currentSession));
                }
                break;
        }
        request.setAttribute("users", users);
    }
    
    @Override
    public void process() throws SQLException, ServletException, IOException {
        initSearch();
        //super.run();     
        request.getRequestDispatcher("views/home.jsp").forward(request, response);
    }
}