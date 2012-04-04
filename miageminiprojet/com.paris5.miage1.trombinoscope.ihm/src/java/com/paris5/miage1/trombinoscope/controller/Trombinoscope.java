package com.paris5.miage1.trombinoscope.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.util.Scanner;
import javax.servlet.http.Part;

/**
 * @author mourad
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        System.out.println("get="+action);
        try {
            switch (Action.get(action)) {
                case DECONNECT:
                    module = new Authentification(request, response);
                    break;
                case CREATE_USER:
                case SEND_PHOTO:
                    module = new UtilisateurManager(request, response);
                    break;
                case SEARCH:
                    module = new Recherche(request, response);
                    break;
                case SHOW_USER :
                    module = new Profil(request, response);
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
            
            String action = Valideur.getAuthorized(request.getParameter("action"));
            if (action == null) {
                Part part = request.getPart("action");
                Scanner s = new Scanner(part.getInputStream());
                action = Valideur.getAuthorized(s.nextLine());
                request.setAttribute("action", action);
            }
            System.out.println("post="+action);
            switch (Action.get(action)) {
                case AUTHENT:
                    module = new Authentification(request, response);
                    break;
                case DECONNECT:
                    module = new Authentification(request, response);
                    break;
                case SEARCH:
                    module = new Recherche(request, response);
                    break;
                case SEND_PHOTO:
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
            request.getRequestDispatcher("views/trombinoscope?action=search").forward(request, response);
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
        return "Short description";
    }// </editor-fold>
    
    private void initEnv(HttpServletRequest request){
        String servletPath=this.getServletContext().getRealPath("");
        servletPath = servletPath.replace("build/", "") + '/';  
        String url = request.getScheme() + "//" + request.getServerName() + ':' + request.getServerPort()
                +request.getContextPath();
        request.setAttribute("servletPath", servletPath);
        request.setAttribute("url", url);
    }
}