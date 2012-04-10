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
import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Notification;
import com.paris5.miage1.trombinoscope.utils.Action;

/**
 * controlleur de l authentification
 * @author Mourad, Saliou, Idir
 */
public class Authentification extends Module {

    /**
     * constructeur 
     * @param request HttpServletRequest requete envoyee
     * @param response HttpServletResponse reponse attendu
     * @throws NullPointerException
     * @throws ServletException
     * @throws IOException 
     */
    public Authentification(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
        super(false, request, response);
    }
    
    /**
     * methode d'authentification
     * @param login String login
     * @param pwd String password
     * @return boolean
     * @throws SQLException 
     */
    private boolean authentification(String login, String pwd) throws SQLException {
        
        session = request.getSession(false);
        
        SearchDAO search = new SearchDAO();
        user = search.findByLoginPassword(request.getParameter("login"), request.getParameter("pwd"));
        if (user != null) {
            session = request.getSession();
           session.setAttribute("user", user);
            return true;
        }
        
        return false;
    }
    
    /**
     * methode de deconnection
     * @return boolean
     */
    private boolean deconnexion(){
       
        session = request.getSession(false);
        if(session != null){
            session.invalidate();
            return true;
        }
        
        return false;
    }
    
    /**
     * execute le processus d autentification
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void process() throws SQLException, ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        Notification notif=null;
        
        switch(Action.valueOf(request.getParameter("action").toUpperCase())){
            case AUTHENT :
                 
                if(login != null && pwd != null && authentification(login, pwd)) {
                    //request.setAttribute("action", "DEFAULT");
                    
                   if (user.getGroupe().getNom().toUpperCase().equals("ETUDIANT")) {
                      request.setAttribute("search_type", 0);
                      } 
                     else 
                        {
                           request.setAttribute("search_type", 7);
                          }
                   
                    Module module = new Recherche(request, response);
                    module.run();
                }
                else{
                    notif = new Notification(Notification.LevelList.ERROR , "login ou mot de passe incorrect");
                }
            break;
                
            case DECONNECT :
                if(!deconnexion()){
                    notif = new Notification(Notification.LevelList.ERROR , "la deconnection a echouee");
                }
                else {
                    notif = new Notification(Notification.LevelList.OK , "vous etes deconnecte");
                }
            break;
            default :
                notif = new Notification(Notification.LevelList.OK , "veuillez remplir le formulaire");
        }
        
        if(notif!=null){
            request.setAttribute("notification", notif);
            request.getRequestDispatcher("views/index.jsp").forward(request, response);
        }
    }
}
