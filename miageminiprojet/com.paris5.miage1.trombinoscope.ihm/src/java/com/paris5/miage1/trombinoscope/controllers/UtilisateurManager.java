/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.controllers;

import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.TrombiDAO;
import com.paris5.miage1.trombinoscope.dao.TrombiFactory;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 
 * @author mourad
 */
public class UtilisateurManager extends Module {

    public UtilisateurManager(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
        super(true, request, response);
    }

    private boolean writePhoto() throws ServletException, IOException {

        FileOutputStream os = null;
        InputStream is = null;
        PrintWriter writer = null;
        Scanner s = null;
        File file = null;
        boolean loaded = false;

        try {
            Part part = request.getPart(Configuration.FILEUPLOAD);
            initUserDir();
            String filename[] = getContentDisposition(part, "filename").split("\\.");
            String relatifPath = Configuration.PHOTOPATH+ File.separator + user.getLogin() + File.separator
                    + System.currentTimeMillis()+'.'+filename[filename.length - 1];
            is = part.getInputStream();
            file = new File(this.getPath() + relatifPath);
            os = new FileOutputStream(file);
            int ch = is.read();
            while (ch != -1) {
                os.write(ch);
                ch = is.read();
            }

            Part p = request.getPart("asynchronious");
            s = new Scanner(p.getInputStream());
            String async = Valideur.getAuthorized(s.nextLine());
            if (async.equals("true")) {
                writer = response.getWriter();
                writer.print(relatifPath);
            } else {
                response.setHeader("location", relatifPath);
            }

            loaded = true;
        } 
        finally {
            if (os != null)
                os.close();
            if (is != null)
                is.close();
            if (writer != null)
                writer.close();
            if (s != null)
                s.close();
        }

        return loaded;
    }

    private void initUserDir() {
        String dir = this.getPath()+Configuration.PHOTOPATH +File.separator+user.getLogin();
        File handler = new File(dir);
        if (!handler.isDirectory()) {
            handler.mkdir();
        }
        
        File tmp=null;
        for (String f: handler.list()) {
            System.out.println(f);
            tmp= new File(dir+File.separator+f);
            tmp.delete();
        }
    }

    private String getContentDisposition(Part part, String param) {
        
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith(param)) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    private boolean loadUser() throws SQLException{
        TrombiDAO tdao = TrombiFactory.getUtilisateurDAO();
        String login = Valideur.getAuthorized(request.getParameter("id"));
        if(login==null){
            login = this.user.getLogin();
        }
        Utilisateur usr = (Utilisateur) tdao.find(login);
        if(usr!=null){
            request.setAttribute("profil", usr);
            return true;
        }
        return false;
    }
    
    public String addUser() throws SQLException{
        
        String login = Valideur.getAlphabetic(request.getParameter("login"));
        TrombiDAO dao = TrombiFactory.getUtilisateurDAO();
        Utilisateur usr = (Utilisateur) dao.find(login);
        
        // login disponible
        if(usr==null){
            // verification du post
            String pwd = Valideur.getAlphaNumeric(request.getParameter("password"));
            boolean email = Valideur.isEmail(request.getParameter("email"));
            String nom = Valideur.getAlphabetic(request.getParameter("nom"));
            String prenom = Valideur.getAlphabetic(request.getParameter("prenom"));
            boolean telephone = Valideur.isPhone(request.getParameter("telephone"));
            boolean mobile = Valideur.isPhone(request.getParameter("mobile"));
            boolean sex = Valideur.getNumeric(request.getParameter("sex")) > 0;
            boolean active = Valideur.getNumeric(request.getParameter("active")) > 0;
            boolean photo = Valideur.isPhoto(request.getParameter("photo_url"));
            String num_etudiant = Valideur.getAlphaNumeric(request.getParameter("num_etudiant"));
            String groupe_nom = Valideur.getAlphabetic(request.getParameter("groupe_nom"));
            int formation = Valideur.getNumeric(request.getParameter("formation"));
            
            // creation de l'utilisateur
            if(pwd != null && email && nom != null && prenom != null && telephone && mobile && 
               photo && num_etudiant != null && groupe_nom != null && formation > 0){
                
                usr = new Utilisateur();
                usr.setLogin(login);
                usr.setCreateurLogin(user.getLogin());
                usr.setPassword(pwd);
                usr.setEmail(request.getParameter("email"));
                usr.setNom(nom);
                usr.setPrenom(prenom);
                usr.setTelephone(request.getParameter("telephone"));
                usr.setMobile(request.getParameter("mobile"));
                usr.setSex(sex);
                usr.setActive(active);
                usr.setPhoto(request.getParameter("photo_url"));
                usr.setNumEtudiant(num_etudiant);
                Groupe groupe = new Groupe(request.getParameter("groupe_nom"), "");
                usr.setGroupe(groupe);
                Formation f = new Formation(formation);
                usr.setFormation(f);
                if(dao.create(f))
                    return usr.getLogin();
            }
        }
        
        return null;
    }
    
    @Override
    public void process() throws ServletException, IOException, NullPointerException, SQLException {
        
        String target = "trombinoscope?action=search";
        
        switch (this.getAction()) {
            case CREATE_USER:
                target = "views/create.jsp";
                break;
            case SEND_PHOTO:
                this.writePhoto();
                target = null;
                break;

            case ADD_USER:
                String user_login = addUser();
                if(user_login!=null){
                    target="trombinoscope?action=show_user&id="+user_login;
                }
                break;
            
            case SHOW_USER:
                if(loadUser())
                    target="views/profil.jsp";
                break;
        }
        System.out.println(request.getAttribute("profil"));
        if(target!=null)
            request.getRequestDispatcher(target).forward(request, response);
    }
}