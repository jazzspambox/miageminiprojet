package com.paris5.miage1.trombinoscope.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.paris5.miage1.trombinoscope.processor.Module;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mourad
 */
class Profil extends Module {
    
    public Profil(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException{
        super(true, request, response);
        
    }
    
    @Override
    public void process() throws SQLException, ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
