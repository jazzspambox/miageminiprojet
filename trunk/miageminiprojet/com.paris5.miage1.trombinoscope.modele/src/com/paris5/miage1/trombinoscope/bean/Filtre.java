package com.paris5.miage1.trombinoscope.bean;

import java.awt.Desktop.Action;

/**
 *
 * @author mourad
 */
public class Filtre {
    private String recherche;
    private String label;
    private int resultCount;
    private String mail;
    private Action action;

    public Filtre(Action act, String recherche, String label, int resultCount, String mail) {
        this.label = label;
        this.resultCount = resultCount;
        this.mail = mail;
        this.recherche = recherche;
    }

    public String getLabel() {
        return label;
    }

    public String getMail() {
        return mail;
    }

    public int getResultCount() {
        return resultCount;
    }

    public String getRecherche() {
        return recherche;
    }

    public Action getAction() {
        return action;
    }
}
