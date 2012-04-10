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
 * module de l application initialise toutes les pages
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
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * constructeur
     * @param connectMode boolean page connctee ou non
     * @param request requete envoyee
     * @param response reponse attendu
     * 
     * @throws NullPointerException
     * @throws ServletException
     * @throws IOException 
     */
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

    /**
     * informe du statut de connexion de la page
     * @return vboolean
     */
    public boolean isConnectMode() {
        return connectMode;
    }

    /**
     * retourne l actioneffectuee sur la page
     * @return Action
     */
    public Action getAction() {
        return action;
    }

    /**
     * return l ulisateur conncte
     * @return Utilisateur
     */
    public Utilisateur getUser() {
        return user;
    }

    /**
     * retourne le chemain de l application
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * retourne l url de l application
     * @return String
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * declanche les traitement attendu
     * 
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
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
