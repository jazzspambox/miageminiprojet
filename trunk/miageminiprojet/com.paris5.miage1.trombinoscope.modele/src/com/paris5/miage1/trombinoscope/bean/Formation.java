/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;

/**
 *
 * @author Saliou
 */
public class Formation {

    private int id;
    private String nom;
    private String type;
    private int session;
    private String mail;

    public Formation() {
    }

    /**
     * 
     * @param id
     */
    public Formation(int id, int session) {
        this.id = id;
        this.session = session;
    }

    /**Initiateur
     * @param id 
     * @param nom 
     * @param session 
     * @require
     * id>0<br/>
     * nom!=null<br/>
     * nom non vide<br/>
     */
    public Formation(int id, String nom, String type, int session, String mail) {
        assert id > 0 : "L'id doit être supérieur à 0";
        assert nom != null || nom.equals("") : "Le nom ne doit être ni null ni vide";
        assert session > 0 : "La session ne doit pas être inférieur à 0";
        this.id = id;
        this.nom = nom;
        this.session = session;
        this.type = type;
        this.mail = mail;
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return
     */
    public int getSession() {
        return session;
    }

    public String getMail() {
        return mail;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return type + ' ' + nom + ' ' + session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", session=" + session + ", mail=" + mail + '}';
    }
}
