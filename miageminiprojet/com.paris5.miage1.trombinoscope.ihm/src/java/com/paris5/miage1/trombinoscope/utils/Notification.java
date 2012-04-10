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
 * classe permettant de gerer les messsages de notifications
 * @author Mourad, Saliou, Idir
 */
public class Notification {
    
    private String message="";
    private String level="";
    private int id=0;
    
    public enum LevelList{
        OK, WARNING, ERROR;
    }
    /**
     * constructeur par defaut
     */
    public Notification() {
    }

    /**
     * constructeur 
     * @param level LevelList niau de la notiication (OK, WARNING, ERROR)
     * @param message String  message
     */
    public Notification( LevelList level, String message) {
        this.message = message;
        this.level = level.toString().toLowerCase();
        id++;
    }

    /**
     * retourne le niveau
     * @return LevelList
     */
    public String getLevel() {
        return level;
    }

    /**
     * retourne le message
     * @return 
     */
    public String getMessage() {
        return message;
    }

    /**
     * retourne l id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * initialise le niveau
     * @param level LevelList
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * initialise le message
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
