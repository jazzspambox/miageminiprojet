package com.paris5.miage1.trombinoscope.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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

    private boolean loadUserData() throws SQLException, NullPointerException{
        Utilisateur profil = null;
        TrombiDAO tdao = TrombiFactory.getUtilisateurDAO();
        int id = Valideur.getNumeric(request.getParameter("id"));
        profil = (Utilisateur) tdao.find(id);
        if(profil!=null){
            request.setAttribute("profil", profil);
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process() throws ServletException, IOException, NullPointerException, SQLException {
        
        String target="views/home.jsp";
        
        switch (this.getAction()) {
            case CREATE_USER:
                target ="views/create.jsp";
                break;

            case SEND_PHOTO:
                //target = "";
                break;

            case ADD_USER:
                break;
            
            case SHOW_USER:
                if(loadUserData()){
                    target = "views/profil.jsp";
                }
                break;
        }
        
        request.getRequestDispatcher(target).forward(request, response);
    }
}