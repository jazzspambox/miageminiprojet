/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

/**
 * @author mourad
 */
public class Utilisateur {

    private String login="";
    private String password="";
    private String createurLogin="";
    private String email="";
    private String nom="";
    private String prenom="";
    private String telephone="";
    private String mobile="";
    private boolean sex=true;
    private boolean active=false;
    private Date dateCreation;
    private Blob photo=null;
    private File photoUploaded=null;
    private String numEtudiant="";
    private Groupe groupe=null;
    private Formation formation=null;
    private ArrayList projets=null;
    private ArrayList commentaires=null;

    /**
     * 
     */
    public Utilisateur() {
    }

    /**Initiateur
     * @param login 
     * @param pwd 
     * @require
     * login!=null<br/>
     * !login.equals("")<br/>
     * pwd!=null<br/>
     * pwd.equals("")<br/>
     */
    public Utilisateur(String login, String pwd) {
        assert login != null && pwd != null;
        assert !login.equals("") && pwd.equals("");
        this.login = login;
        this.password = pwd;
        // assert this.login!=null&& this.password!=null;
        // assert ! this.login.equals("")&& ! this.login.endsWith("");
    }

    /**Initiateur
     * @param login 
     * @param pwd 
     * @param email 
     * @param prenom 
     * @param nom 
     * @require
     * login!=null<br/>
     * !login.equals("")<br/>
     * pwd!=null<br/>
     * pwd.equals("")<br/>
     * nom!=null<br/>
     * !nom.equals("")<br/>
     * prenom!=null<br/>
     * email!=null<br/>
     * !email.equals("")<br/>
     */
    public Utilisateur(String login, String pwd, String nom, String prenom, String email) {
        this(login, pwd);
        assert nom != null && prenom != null && email != null : "Ni le nom et prenom, ni l'email ne doivent être null";

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    /**Initiateur
     * @param login 
     * @param nom 
     * @param pwd 
     * @param tel 
     * @param prenom 
     * @param email 
     * @param urlPhoto 
     * @require
     * login!=null<br/>
     * !login.equals("")<br/>
     * pwd!=null<br/>
     * pwd.equals("")<br/>
     * nom!=null<br/>
     * !nom.equals("")<br/>
     * prenom!=null<br/>
     * email!=null<br/>
     * !email.equals("")<br/>
     * tel!=null<br/>
     * urlPhoto!=null<br/>
     * !urlPhoto.equals("")<br/>
     */
    public Utilisateur(String login, String pwd, String nom, String prenom, String email, String tel, String urlPhoto) {
        this(login, pwd, nom, prenom, email);
        assert tel != null && urlPhoto != null : "Le numéro de téléphone et l'url de la photo doivent être non nul";
        this.telephone = tel;
        this.setPhoto(urlPhoto);
    }

    /**
     * 
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * 
     * @return 
     */
    public String getCreateurLogin() {
        return createurLogin;
    }

    /**
     * 
     * @return 
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @return 
     */
    public Formation getFormation() {
        return this.formation;
    }

    /**
     * 
     * @return 
     */
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @return 
     */
    public String getMobile() {
        return mobile;
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
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @return
     */
    public Blob getPhoto() {
        return photo;
    }

    /**
     * 
     * @return
     */
    public File getPhotoUploaded() {
        return photoUploaded;
    }

    public String getPhotoUrl() {
        if (photoUploaded != null) {
            return photoUploaded.getPath();
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public boolean isMen() {
        return sex;
    }

    /**
     * 
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * 
     * @return
     */
    public boolean getSex() {
        return sex;
    }

    /**
     * 
     * @return
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * 
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 
     * @return
     */
    public ArrayList getCommentaires() {
        return commentaires;
    }

    /**
     * 
     * @return
     */
    public ArrayList getProjets() {
        return projets;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public void setPhotoUploaded(File photoUploaded) {
        this.photoUploaded = photoUploaded;
    }

    public boolean isSex() {
        return sex;
    }

    /**
     * 
     * @param commentaires
     */
    public void setCommentaires(ArrayList commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * 
     * @param projets
     */
    public void setProjets(ArrayList projets) {
        this.projets = projets;
    }

    /**
     * 
     * @param groupe
     */
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    /**
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * 
     * @param createur_login
     */
    public void setCreateurLogin(String createur_login) {
        this.createurLogin = createur_login;
    }

    /**
     * 
     * @param dateCreation
     */
    public void setDateCreation(Date dateCreation) {

        this.dateCreation = dateCreation;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @param formation
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * 
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * 
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @param photo
     */
    public void setPhoto(String photo) {
        photoUploaded = new File(photo);
    }

    /**
     * 
     * @param photo
     */
    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    /**
     * 
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * 
     * @param sex
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    /**
     * 
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isAuthorized(String right) {
        int i = 0;
        boolean found = false;
        ArrayList<Droit> rights = (ArrayList<Droit>) groupe.getDroits();
        while (!found && i < rights.size()) {
            if (rights.get(i).getName().equals(right)) {
                found = true;
            }
        }

        return found;
    }

    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Utilisateur usr = (Utilisateur) obj;
        return login.equals(usr.getLogin())
                && password.equals(usr.getPassword())
                && createurLogin.equals(usr.getCreateurLogin())
                && formation.getId() == usr.getFormation().getId()
                && groupe.getNom().equals(usr.getGroupe().getNom())
                && email.equals(usr.getEmail())
                && nom.equals(usr.getNom())
                && prenom.equals(usr.getPrenom())
                && telephone.equals(usr.getTelephone())
                && mobile.equals(usr.getMobile())
                && sex == usr.getSex()
                && active == usr.isActive()
                && dateCreation.equals(usr.getDateCreation());
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Utilisateur{" + "\n login=" + login
                + "\n createur_login=" + createurLogin
                + "\n formation_id=" + formation
                + "\n groupe_nom=" + groupe
                + "\n password=" + password
                + "\n email=" + email
                + "\n nom=" + nom
                + "\n prenom=" + prenom
                + "\n telephone=" + telephone
                + "\n mobile=" + mobile
                + "\n sex=" + sex
                + "\n active=" + active
                + "\n dateCreation=" + dateCreation
                + "\n photo=" + photo + "\n}";
    }
}
