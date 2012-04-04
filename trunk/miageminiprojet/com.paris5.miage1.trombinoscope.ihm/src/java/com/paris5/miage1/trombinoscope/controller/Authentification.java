package com.paris5.miage1.trombinoscope.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Notification;
import com.paris5.miage1.trombinoscope.utils.Action;

/**
 * @author mourad
 */
public class Authentification extends Module {

    /**
     * 
     * @param request
     * @param response
     * @throws SQLException 
     */
    public Authentification(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
        super(false, request, response);
    }
    
    /**
     * 
     * @param login
     * @param pwd
     * @return
     * @throws SQLException 
     */
    private boolean authentification(String login, String pwd) throws SQLException {
        
        session = request.getSession(false);
        Utilisateur user;
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
     * 
     * @return 
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
     * 
     */
    public void process() throws SQLException, ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        Notification notif=null;
        
        switch(Action.valueOf(request.getParameter("action").toUpperCase())){
            case AUTHENT :
                if(login != null && pwd != null && authentification(login, pwd)) {
                    request.setAttribute("action", "DEFAULT");
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
