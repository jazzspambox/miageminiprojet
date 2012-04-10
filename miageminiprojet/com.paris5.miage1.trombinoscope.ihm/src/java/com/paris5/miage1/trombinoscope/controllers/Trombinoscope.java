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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.util.Scanner;
import javax.servlet.http.Part;

/**
 * front controller MVC2
 * @author Mourad, Saliou, Idir
 */
public final class Trombinoscope extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // useless for now
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        assert request.getParameter("action") != null;
        this.initEnv(request);

        String action = Valideur.getAuthorized(request.getParameter("action"));
        Module module = null;
        try {
            switch (Action.get(action)) {
                case DECONNECT:
                    module = new Authentification(request, response);
                    break;
                case CREATE_USER:
                case SEND_PHOTO:
                case SHOW_USER:
                case DELETE_USER :
                    module = new UtilisateurManager(request, response);
                    break;
                case SEARCH:
                    module = new Recherche(request, response);
                    break;

                default:
                    errorRedirect(module, request, response);
            }

            module.run();
        } catch (SQLException ex) {
            //Logger.getLogger(Trombinoscope.class.getName()).log(Level.SEVERE, null, ex);
            errorRedirect(module, request, response);
        } catch (Exception e) {
            //Logger.getLogger(Trombinoscope.class.getName()).log(Level.SEVERE, null, e);
            errorRedirect(module, request, response);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        assert request.getParameter("action") != null;
        this.initEnv(request);

        Module module = null;
        try {

            String action = this.getAction(request);
            
            switch (Action.get(action)) {
                case AUTHENT:
                    module = new Authentification(request, response);
                    break;
                case SEARCH:
                    module = new Recherche(request, response);
                    break;
                case SEND_PHOTO:
                case ADD_USER:
                case SHOW_USER:
                    module = new UtilisateurManager(request, response);
                    break;
                default:
                    errorRedirect(module, request, response);
            }

            module.run();
        } catch (SQLException ex) {
            Logger.getLogger(Trombinoscope.class.getName()).log(Level.SEVERE, null, ex);
            errorRedirect(module, request, response);
        } catch (Exception e) {
            Logger.getLogger(Trombinoscope.class.getName()).log(Level.SEVERE, null, e);
            errorRedirect(module, request, response);
        }
    }

    private void errorRedirect(Module module, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((module != null && module.isConnectMode())) {
            request.getRequestDispatcher("trombinoscope?action=search").forward(request, response);
        } else {
            request.getRequestDispatcher("views/index.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Trombinoscope Miage paris 5 - 2012";
    }

    /**
     * initialise quelques variable d'environnement
     * @param request HttpServletRequest
     */
    private void initEnv(HttpServletRequest request) {
        String servletPath = this.getServletContext().getRealPath("");
        servletPath = servletPath.replace("build/", "") + '/';
        String url = request.getScheme() + "//" + request.getServerName() + ':' + request.getServerPort()
                + request.getContextPath();
        request.setAttribute("servletPath", servletPath);
        request.setAttribute("url", url);
    }

    /**
     * recherche l'action envoyee dans la requete http
     * @param request HttpServletRequest
     * @return String 
     * @throws IOException
     * @throws ServletException 
     */
    private String getAction(HttpServletRequest request) throws IOException, ServletException {
        String action = Valideur.getAuthorized(request.getParameter("action"));
        if (action == null) {
            Part part = request.getPart("action");
            Scanner s = new Scanner(part.getInputStream());
            action = Valideur.getAuthorized(s.nextLine());
            request.setAttribute("action", action);
        }
        return action;
    }
}