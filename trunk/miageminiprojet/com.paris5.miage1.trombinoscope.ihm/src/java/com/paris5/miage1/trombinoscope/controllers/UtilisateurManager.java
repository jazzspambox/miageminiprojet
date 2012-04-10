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

import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.FormationDAO;
import com.paris5.miage1.trombinoscope.dao.GroupeDAO;
import com.paris5.miage1.trombinoscope.dao.TrombiDAO;
import com.paris5.miage1.trombinoscope.dao.TrombiFactory;
import com.paris5.miage1.trombinoscope.dao.UtilisateurDAO;
import com.paris5.miage1.trombinoscope.processor.Module;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Photo;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * page d administration des utilisateurs
 * @author Mourad, Saliou, Idir
 */
public class UtilisateurManager extends Module {

    /**
     * constructeur 
     * @param request requete envoyee
     * @param response reponse attendu
     * @throws NullPointerException
     * @throws ServletException
     * @throws IOException
     * @throws SQLException 
     */
    public UtilisateurManager(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException, SQLException {
        super(true, request, response);
        GroupeDAO dao = new GroupeDAO();
        List<Groupe> ar = dao.list();
        request.setAttribute("groupes", ar);
        request.setAttribute("profil", new Utilisateur());

        int limit = currentSession;
        System.out.println(currentSession + " "+ currentMonth);
        if(currentMonth >= Configuration.DEBUT_INSCRIPTION)
            limit++;
        
        FormationDAO fdao = (FormationDAO) TrombiFactory.getFormationDAO();
        ArrayList<Formation> formations = (ArrayList<Formation>) fdao.listAll();
        ArrayList select = new ArrayList();
        int i=currentSession-1;
        while(limit>=i){
            for(Formation f: formations){
                select.add(new Formation(f.getId(), f.getNom() ,f.getType(),limit,f.getMail()));
            }
            limit--;
        }
        
        request.setAttribute("selectSession", select);
        
    }

    /**
     * ecrit une photo envoyee sur le serveur
     * @return booelan
     * @throws ServletException
     * @throws IOException 
     */
    private boolean writePhoto() throws ServletException, IOException {

        FileOutputStream os = null;
        InputStream is = null;
        PrintWriter writer = null;
        Scanner s = null;
        File file = null;
        boolean loaded = false;

        try {
            Part part = request.getPart("file");
            initUserDir();
            String filename[] = getContentDisposition(part, "filename").split("\\.");
            String relatifPath = Configuration.Photo.PATH + File.separator + user.getLogin() + File.separator
                    + System.currentTimeMillis() + '.' + filename[filename.length - 1];
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
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (s != null) {
                s.close();
            }
        }

        return loaded;
    }

    /**
     * initialise le chemain prive de l administrateur conncte
     */
    private void initUserDir() {
        String dir = this.getPath() + Configuration.Photo.PATH + File.separator + user.getLogin();
        File handler = new File(dir);
        if (!handler.isDirectory()) {
            handler.mkdir();
        }

        File tmp = null;
        for (String f : handler.list()) {
            tmp = new File(dir + File.separator + f);
            tmp.delete();
        }
    }

    /**
     * extrait des information d un part
     * @param part Part
     * @param param String information desiree
     * @return 
     */
    private String getContentDisposition(Part part, String param) {

        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith(param)) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * recherche l existance d un utilisateur dans la base de donnee
     * @return boolean
     * @throws SQLException 
     */
    private boolean loadUser() throws SQLException {
        TrombiDAO tdao = TrombiFactory.getUtilisateurDAO();
        String login = Valideur.getAuthorized(request.getParameter("id"));
        if (login == null) {
            login = this.user.getLogin();
        }
        Utilisateur usr = (Utilisateur) tdao.find(login);
        if (usr != null) {
            request.setAttribute("profil", usr);
            return true;
        }
        return false;
    }

    /**
     * insert un utilisateur dans la base de donnee
     * @return String login de l utilisateur
     * @throws SQLException
     * @throws IOException 
     */
    public String addUser() throws SQLException, IOException {

        String login = Valideur.getAlphabetic(request.getParameter("login"), "");
        UtilisateurDAO dao = (UtilisateurDAO) TrombiFactory.getUtilisateurDAO();
        Utilisateur usr = (Utilisateur) dao.find(login);

        // login disponible
        if (usr == null) {
            // verification du post
            String pwd = Valideur.getAlphaNumeric(request.getParameter("password"));
            boolean email = Valideur.isEmail(request.getParameter("email"));
            String nom = Valideur.getAlphabetic(request.getParameter("nom"));
            String prenom = Valideur.getAlphabetic(request.getParameter("prenom"));
            boolean telephone = Valideur.isPhone(request.getParameter("fixe"));
            boolean mobile = Valideur.isPhone(request.getParameter("mobile"));
            boolean sex = Valideur.getNumeric(request.getParameter("sex")) > 0;
            boolean photo = Valideur.isPhoto(request.getParameter("photo_url"));
            String num_etudiant = Valideur.getAlphaNumeric(request.getParameter("num_etudiant"));
            String groupe_nom = Valideur.getAlphabetic(request.getParameter("groupe"));
            String formation = Valideur.getAuthorized(request.getParameter("search_promo"));
            int sess = 0;
            int formation_id = 0;
            if (formation != null) {
                String fsplited[] = formation.split("-");
                if (fsplited.length == 2) {
                    formation_id = Valideur.getNumeric(fsplited[0]);
                    sess = Valideur.getNumeric(fsplited[1]);
                }
            }

            usr = new Utilisateur();
            usr.setLogin(login);
            usr.setCreateurLogin(user.getLogin());
            usr.setPassword(pwd);
            if(email)
                usr.setEmail(request.getParameter("email"));
            usr.setNom(nom);
            usr.setPrenom(prenom);
            if(telephone)
                usr.setTelephone(request.getParameter("fixe"));
            //if(mobile)
                usr.setMobile(request.getParameter("mobile"));
            usr.setSex(sex);
            
            usr.setNumEtudiant(num_etudiant);
            Groupe groupe = new Groupe(groupe_nom, "");
            usr.setGroupe(groupe);
            Formation f = new Formation(formation_id, sess);
            usr.setFormation(f);
            request.setAttribute("profil", usr);
            
            // creation de l'utilisateur
            if (pwd != null) {
                if(photo){
                    Photo picture = new Photo(this.getPath(), request.getParameter("photo_url"), usr.getPrenom() + '_' + usr.getNom());

                    int x = Valideur.getNumeric(request.getParameter("x"));
                    int y = Valideur.getNumeric(request.getParameter("y"));
                    int x2 = Valideur.getNumeric(request.getParameter("x2"));
                    int y2 = Valideur.getNumeric(request.getParameter("y2"));
                    int h = Valideur.getNumeric(request.getParameter("h"));
                    int w = Valideur.getNumeric(request.getParameter("w"));
                    
                    if ( x != 0 && y != 0 && x2 != 0 && y2 != 0) {
                        //picture.crop(x, y, x2, y2);
                    }

                    if (picture.getHeight() > Configuration.Photo.MAX_HEIGHT
                            || picture.getWidth() > Configuration.Photo.MAX_WIDTH) {

                        //picture.resize(Configuration.Photo.MAX_HEIGHT, Configuration.Photo.MAX_WIDTH);
                    }
                    
                    usr.setPhotoUploaded(picture.get());
                }
                
                if (dao.create(usr)) {
                    return usr.getLogin();
                }
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
                if (user_login != null) {
                    target = "trombinoscope?action=show_user&id=" + user_login;
                } else {
                    target = "views/create.jsp";
                }
                break;

            case SHOW_USER:
                if (loadUser()) {
                    target = "views/profil.jsp";
                }
                break;
        }
        System.out.println("action=" + this.getAction() + " target" + target);
        if (target != null) {
            request.getRequestDispatcher(target).forward(request, response);
        }
    }
}