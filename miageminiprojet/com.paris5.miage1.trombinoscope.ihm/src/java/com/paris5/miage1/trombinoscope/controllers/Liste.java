/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.controllers;

import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;

/**
 *
 * @author mourad
 */
public class Liste extends Resultat {

    public Liste(Filtre filtre, Utilisateur usr, int count, int start) throws SQLException {
        super(filtre, usr, count, start);
    }
    
    @Override
    public void execute() {
        
    }
    
}
