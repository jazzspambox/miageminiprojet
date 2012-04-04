/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;

import java.sql.*;

/**Projet de classe dans une matière: possède un identifiant entier, un nom, une
 * description, une date de debut et une date de fin.
 *
 * @author Saliou
 */
public class Projet {
    private int id;
    private String nom;
    private int idFormation;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    /**Initiateur
     * @param id 
     * @param nom 
     * @param idFormation 
     * @require
     * id>0<br/>
     * nom!=null et nom pas vide<br/>
     * IdFormation>0
     */
    public Projet(int id, String nom,int idFormation){
       assert id>0:"Le numéro du produit doit être positif";
       assert nom!=null|| nom.equals(""):"Le nom du projet ne doit être ni null, ni vide";
       assert idFormation>0:"L'id de la formation doit être positif";
       this.id=id;
        this.nom=nom;
        this.idFormation=idFormation;
        
    }
    /**Initiateur
     * @param id 
     * @param idFormation 
     * @param nom 
     * @param description 
     * @require
     * id!=null et id>0<br/>
     * nom!=null et nom pas vide<br/>
     * description !=null
     */
    public Projet(Integer id, String nom,Integer idFormation, String description){
        this(id,nom,idFormation);
        assert description!=null:"La description ne doit pas être vide ";
        this.description=description;
    }
    /**
     * 
     * @param id
     * @param nom
     * @param idFormation
     * @param description
     * @param dateDebut
     * @param dateFin
     */
    public Projet(Integer id, String nom,Integer idFormation,String description,Date dateDebut,Date dateFin){
        this(id, nom, idFormation,description);
        assert dateDebut!=null:"La date de debut du projet ne doit pas être null";
        assert dateFin!=null:"La date de fin du projet ne doit pas être null";
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
    }
    

    /**
     * 
     * @return
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * 
     * @return
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * 
     * @return
     */
    public Integer getId() {
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
     * @param dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * 
     * @param dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return
     */
    public String getDescription() {
        return description;
    }

    

    /**
     * 
     * @return
     */
    public int getIdFormation() {
        return idFormation;
    }
    
    
}
