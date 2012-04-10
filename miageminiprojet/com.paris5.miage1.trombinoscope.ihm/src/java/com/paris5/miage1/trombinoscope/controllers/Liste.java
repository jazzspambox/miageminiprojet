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

import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;

/**
 * liste des utilisateur trouves
 * @author Mourad, Saliou, Idir
 */
public class Liste extends Resultat {

    /**
     * constructeur
     * @param filtre Filtre criteres de la recherche
     * @param usr Utilisateur connectee
     * @param count nombre des resultats
     * @param start born inferieur
     * @throws SQLException 
     */
    public Liste(Filtre filtre, Utilisateur usr, int count, int start) throws SQLException {
        super(filtre, usr, count, start);
    }
    
    @Override
    public void execute() {
        // useless
    }
    
}
