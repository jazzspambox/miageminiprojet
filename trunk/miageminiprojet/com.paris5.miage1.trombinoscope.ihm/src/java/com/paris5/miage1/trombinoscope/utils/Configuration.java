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
package com.paris5.miage1.trombinoscope.utils;

/**
 * classe de configuration de l application
 * @author Mourad, Saliou, Idir
 */
public class Configuration {
    /**
     * nombre de profils a afficher par page
     */
    static public final int PROFILS_PAR_PAGE = 12;
    
    /**
     * nombre de numeros de pages a afficher entre les pointilles
     */
    static public final int PAGINATION_POINTILLES = 6;
    
    /**
     * mois de l ouverture des inscription
     */
    static public final int DEBUT_INSCRIPTION = 05;
    
    /**
     * gestion de la photo
     */
    static public class Photo{
        /**
         * largeur minimum
         */
        static public final int MAX_WIDTH=300;
        
        /**
         * hauteur maximum
         */
        static public final int MAX_HEIGHT=370;
        
        /**
         * dossier de sauvegarde des photos
         */
        static public final String PATH = "photos";
    }
}
