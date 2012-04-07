/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;

import java.util.*;

/**Groupe: represente un role.Chaque utilisateur du trombi est mis dans groupe en
 * fonction duquel il a des droits.
 * un groupe a un nom non null et non vide, et une 
 * description qui elle peut être vide mais pas null.
 * @invariant 
 * getNom()!=null et !getNom.equals("")<br/>
 * getDescription()!=null<br/>
 * 
 * @author Saliou
 */
public class Groupe {
    
    private String nom;
    private String description;
    private  List<Droit> droits;

    public Groupe() {
    }
    
    /**Initiateur
     * @param nom 
     * @require
     * nom!=null<br/>
     * !nom.equals("")</br>
     * 
     */
    public Groupe(String nom){
       assert nom!=null&& !nom.equals(""):"Le nom ne doit être ni vide, ni null";
       this.nom=nom;
    }  
    /**Initiateur
     * @param nom 
     * @param description 
     * @require
     * nom!=null<br/>
     * !nom.equals("")</br>
     * description!=null<br/>
     */
    public Groupe(String nom, String description){
        this(nom);
        assert description!=null: "LA description ne doit pas être null";
        
        this.description=description;
        
    }
    
    /**
     * @param nom 
     * @param description 
     * @param droits 
     * @require
     * nom!=null<br/>
     * !nom.equals("")</br>
     * description!=null<br/>
     * droits.size()>0;<br/>
     *  droits!=null<br/>
     */
    public Groupe(String nom,String description, List<Droit> droits){
        this(nom,description);
        assert droits!=null && droits.size()<=0:"Il doit avoir au moins un droit";
        this.droits=droits;
    }
    
    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
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
    public List<Droit> getDroits() {
        return droits;
    }

    /**
     * 
     * @param droits
     */
    public void setDroits(List<Droit> droits) {
        this.droits = droits;
    }
    
/**
 * @require
 *  description!=null<br/>
 * @param description 
 */
    public void setDescription(String description) {
        assert description!=null: "LA description ne doit pas être null";
        this.description = description;
    }
/**
     * @param nom 
     * @require 
 *    nom!=null <br/>
 *    !nom.equals("")<br/>
 */
    public void setNom(String nom) {
        assert nom!=null&& !nom.equals(""):"Le nom ne doit être ni vide, ni null";
        this.nom = nom;
    }
    
    /**
     * 
     * @param obj
     * @return
     */
    @Override
 public boolean equals(Object obj){    
    if (obj==this) {
            return true;
        }
         boolean retour=false;
        if (obj instanceof Commentaire) {
            Groupe other = (Groupe) obj;
            
           retour= this.nom.equals(other.getNom())&&this.description.equals(other.getDescription());
        }
        
        return retour;
    }

    @Override
    public String toString() {
        return "Groupe{" + "nom=" + nom + ", description=" + description + ", droits=" + droits + '}';
    }
    
}
