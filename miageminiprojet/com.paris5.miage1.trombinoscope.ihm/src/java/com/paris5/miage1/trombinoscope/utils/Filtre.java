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
 * simple bean pour sauvegarder les criteres d une recherche recherche
 * @author Mourad, Saliou, Idir
 */
public class Filtre {
    
    private int page;
    private String recherche;
    private String label;
    private int resultCount;
    private String mail;
    private Action action;
    private int formationId;
    private int session;

    /**
     * constructeur
     */
    public Filtre() {
    }

    /**retourn le label de l action
     * 
     * @return String
     */
    public String getType() {
        return action.toString().toLowerCase();
    }
    
    /**
     * retourne l action
     * @return Action
     */
    public Action getAction() {
        return action;
    }

    /**
     * retourne l id de la formation
     * @return int
     */
    public int getFormationId() {
        return formationId;
    }

    /**
     * retourne le label
     * @return String
     */
    public String getLabel() {
        return label;
    }

    public String getMail() {
        return mail;
    }

    public int getPage() {
        return page;
    }

    public String getRecherche() {
        return recherche;
    }

    public int getResultCount() {
        return resultCount;
    }

    public int getSession() {
        return session;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setSession(int session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Filtre{" + "page=" + page + ", recherche=" + recherche + ", label=" + label + ", resultCount=" + resultCount + 
                ", mail=" + mail + ", action=" + action + ", formationId=" + formationId + ", session=" + session + '}';
    }
    
}
