/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;
import java.util.*;
/**
 *
 * @author mourad
 */
public class Commentaire {
    private int id;  
    private String loginFrom;
    private String loginTo;
    private String commentaire;
    private Date date;
    
    /**Initiateur
     * @param id 
     * @param loginFrom 
     * @param loginTo 
     * @param date 
     * @param commentaire 
     * @require
    * id>0
    * loginFrom!=null<br/>
    * loginFrom non vide<br/>
    * commentaire!=null<br/>
    * commentaire non vide<br/>
    * date!=null<br/>
    */
    public Commentaire(int id,String loginFrom, String loginTo, String commentaire,Date date){
        assert id>0:"L'id doit être strictement supérieur à 0";
        assert loginFrom!=null||loginFrom.equals(""):"Le loginFrom ne doit être ni null ni vide";
        assert loginTo!=null||loginTo.equals(""):"Le loginFrom ne doit être ni null ni vide";
        assert commentaire!=null||commentaire.equals(""):"Le commentaire ne doit être ni vide ni null";
        assert date!=null:"La date doit être différent de null";
        
        this.id=id;
        this.loginFrom=loginFrom;
        this.loginTo=loginTo;
        this.commentaire=commentaire;
        this.date=date;
    }
    
    /**
     * Cette méthode fournie la liste des commentaires postés sur un utilisateur
     * @return 
     * @require
     * user!=null<br/>
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * 
     * @return
     */
    public Date getDate() {
        return date;
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
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public String getLoginFrom() {
        return loginFrom;
    }

    /**
     * 
     * @return
     */
    public String getLoginTo() {
        return loginTo;
    }

    /**
     * 
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * 
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @param loginFrom
     */
    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    /**
     * 
     * @param loginTo
     */
    public void setLoginTo(String loginTo) {
        this.loginTo = loginTo;
    }
    
    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        }
         boolean retour=false;
        if (obj instanceof Commentaire) {
            Commentaire other = (Commentaire) obj;

           retour= this.id==other.getId()&&this.loginFrom.equals(other.getLoginFrom())
                    &&this.loginTo.equals(other.getLoginTo())&&this.date.equals(other.getDate());


        }

        return retour;
    }
  
}
