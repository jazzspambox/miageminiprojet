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
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;

/**
 * gestion de la pagination
 * @author Mourad, Saliou, Idir
 */
public class Pagination extends Resultat {

    /**
     * constructeur
     * @param filtre Filtre criteres de recherches
     * @param usr Utilisateur connecte
     * @throws SQLException
     * @throws NullPointerException 
     */
    public Pagination(Filtre filtre, Utilisateur usr) throws SQLException, NullPointerException {
        super(filtre, usr);
        this.init();
    }
    
    /**
     * retourne le nombre de pages entres les pointilles
     * @return 
     */
    public int getSeparator() {
        return Configuration.PAGINATION_POINTILLES;
    }

    /**
     * initialise la page courante
     * @param currentPage int
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * retourne la borne inferieur de recherche
     * @return 
     */
    public int getMinimum() {
        return minimum;
    }
    
    /**
     * retourne la borne superieur de recherche
     * @param minimum 
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
    
    /**
     * calcule la paginaion
     */
    private void init(){
        this.nombrePages = (int) Math.ceil(count/Configuration.PROFILS_PAR_PAGE);
        this.minimum = (this.currentPage - 1) * Configuration.PROFILS_PAR_PAGE;
    }
    
    public void execute(){
        
        super.clear();
        
        int born= (int) Math.ceil(Configuration.PAGINATION_POINTILLES/2);
        int it=currentPage-born;
        if(it<=1){
            it = 1;
        }
        else {
            this.add(0);
        }
        
        int limit = it+Configuration.PAGINATION_POINTILLES;
        if(limit> nombrePages)
            limit = nombrePages;
        
        while(it<=limit){
            this.add(it);
            it++;
        }
        
        if(it-1<nombrePages){
            this.add(0);
        }
    }
}