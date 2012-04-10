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

import com.paris5.miage1.trombinoscope.utils.Configuration;
import java.util.ArrayList;

/**
 * Classe abstraite observateur
 * @author Mourad, Saliou, Idir
 */
public abstract class Zone extends ArrayList{
    
    // le nombres de pages
    protected int nombrePages;
    
    // la page courante
    protected int currentPage;
    
    // nombre de resultat a presenter par page
    protected int minimum=Configuration.PROFILS_PAR_PAGE; //invarient
    
    
    /**
     * affiche le prochaine page
     */
    public void next() {
        if(currentPage<nombrePages){
            this.currentPage++;
            this.execute();
        }
    }
    
    /**
     * affiche la page precedente
     */
    public void precedent() {
        if(currentPage>1){
            this.currentPage--;
            this.execute();
        }
    }
    
    /**
     * affiche la page on fonction de son numero
     * @param num int
     */
    public void page(int num) {
        if(num>0 && num<=nombrePages){
            currentPage = num;
            this.execute();
        }
    }
    
    /**
     * execute les traitements sur la page courante
     * @return int
     */
    public void current() {
        this.execute();
    }

    /**
     * return le numero de la page courante
     * @return 
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    
    /**
     * retourne le nombre de pages 
     * @return int
     */
    public int getNombrePages() {
        return nombrePages;
    }
    
    /**
     * effectue le traitement attendu dans la zone
     */
    public abstract void execute();
}
