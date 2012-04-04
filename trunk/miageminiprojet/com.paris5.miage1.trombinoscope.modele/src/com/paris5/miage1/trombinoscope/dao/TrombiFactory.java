/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

/**
 * @author mourad
 */
public class TrombiFactory {
    
    /**
     * Retourne un objet uti interagissant avec la BDD
     * @return
     */
    public static TrombiDAO getUtilisateurDAO(){
        return new UtilisateurDAO();
    }
    
    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return
     */
    public static TrombiDAO getCommentaireDAO(){
        return new CommentaireDAO();
    }
    
    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return
     */
    public static TrombiDAO getFormationDAO(){
        return new FormationDAO();
    }
    
    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return
     */
    public static TrombiDAO getProjetDAO(){
        return new ProjetDAO();
    }
    
    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return
     */
    public static TrombiDAO getGroupeDAO(){
        return new GroupeDAO();
    }
}
