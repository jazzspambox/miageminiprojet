package com.paris5.miage1.trombinoscope.utils;

/**
 *
 * @author mourad
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

    public Filtre() {
    }

    public String getType() {
        return action.toString().toLowerCase();
    }
    
    public Action getAction() {
        return action;
    }

    public int getFormationId() {
        return formationId;
    }

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
