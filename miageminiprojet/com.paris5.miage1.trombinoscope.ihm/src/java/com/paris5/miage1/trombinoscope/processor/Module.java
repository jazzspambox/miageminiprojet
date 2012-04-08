/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.processor;

import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.FormationDAO;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe abstraite Observable
 * @author Mourad, Saliou, Idir
 */
public abstract class Module implements Processeur{
    
    private Action action;
    private boolean connectMode;
    protected Utilisateur user = null;
    protected HttpSession session;
    private String url;
    private String path;
    protected int currentSession;
    protected int currentMonth;
    protected int currentDay;
    
    //Utilisateur user;
    //HttpSession session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public Module(boolean connectMode, HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
        
        if(connectMode){
            session = request.getSession(false);
            user = (Utilisateur) session.getAttribute("user");
        }
        
        this.request = request;
        this.response = response;
        this.connectMode = connectMode;
        action = Action.get(Valideur.getAuthorized(request.getParameter("action")));
        if(action==Action.DEFAULT){
            action=Action.get(request.getAttribute("action").toString());
        }
        
        this.url = request.getAttribute("url").toString();
        this.path = request.getAttribute("servletPath").toString();
        
        String format = "yyyy-MM-dd";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String res[] = formater.format(date).split("-");
        currentSession = Integer.valueOf(res[0]);
        currentMonth = Integer.valueOf(res[1]);
        currentDay = Integer.valueOf(res[2]);
    }

    public boolean isConnectMode() {
        return connectMode;
    }

    public Action getAction() {
        return action;
    }

    public Utilisateur getUser() {
        return user;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }
    
    public void run() throws SQLException, ServletException, IOException {
         if(connectMode && user==null){
            request.getRequestDispatcher("views/index.jsp").forward(request, response);
         }
         else{
            FormationDAO fdao = new FormationDAO();
            ArrayList formation = (ArrayList) fdao.list();
            this.request.setAttribute("formations", formation);
            this.process();
         }    
    }
}
