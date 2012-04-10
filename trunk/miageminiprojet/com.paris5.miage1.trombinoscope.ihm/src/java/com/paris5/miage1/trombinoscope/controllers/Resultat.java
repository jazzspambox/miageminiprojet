/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.controllers;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.Zone;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mourad
 */
public abstract class Resultat extends Zone {
    
    //
    protected Utilisateur user;
    
    //
    protected double count;
    
    //
    protected int start;
    
    //
    protected Filtre filtre;
               
    /**
     * 
     * @param usr
     * @param act
     * @param page
     * @throws SQLException 
     */
    public Resultat(Filtre filtre, Utilisateur usr) throws SQLException{
        super();
        this.user = usr;
        this.currentPage=filtre.getPage();
        this.filtre = filtre;
        this.init(true);
    }
    
    public Resultat(Filtre filtre, Utilisateur usr, int count, int start) throws SQLException{
        super();
        this.user = usr;
        this.currentPage=filtre.getPage();
        this.start = start;
        this.count = count;
        this.filtre = filtre;
        this.init(false);
    }
    
    private void init(boolean countResult) throws SQLException {
        
        String format = "yyyy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String res = formater.format(date);    
        int currentSession = Integer.valueOf(res);
                
        SearchDAO search = new SearchDAO();
        if(!countResult)
            search.setLimit(this.start, Configuration.PROFILS_PAR_PAGE);
        
       switch (filtre.getAction()) {
            case SEARCH_NOM:              
                if(countResult){
                    this.count = search.countByLastName(filtre.getRecherche());
                }
                else if(this.count > 0) {
                    this.addAll(search.findByLastName(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_PRENOM:
                if(countResult){
                    this.count = search.countByFirstName(filtre.getRecherche());
                }
                else if(this.count > 0) {
                    this.addAll(search.findByFirstName(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_MOBILE:
                if(countResult){
                    ArrayList tmp = search.findByPhone(filtre.getRecherche(), true,filtre.getFormationId(), true);
                    this.count = tmp.size() > 0 ? (Integer) tmp.get(0) : 0;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByPhone(filtre.getRecherche(), true,filtre.getFormationId(), false));
                }
             
                break;
            case SEARCH_FIXE:
                if(countResult){
                    ArrayList tmp = search.findByPhone(filtre.getRecherche(), false,filtre.getFormationId(), true);
                    this.count = tmp.size() > 0 ? (Integer) tmp.get(0) : 0;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByPhone(filtre.getRecherche(), false,filtre.getFormationId(), false));
                }
                break;
            case SEARCH_EMAIL:
                if(countResult){
                   this.count = 1;
                }
                else if(this.count > 0) {
                    this.addAll(search.findByEmail(filtre.getRecherche(),filtre.getFormationId()));
                }
                break;
            case SEARCH_PROMOTION:
                if(countResult)
                    this.count = search.countByPromo(String.valueOf(filtre.getFormationId()), filtre.getSession());
                else if(this.count > 0)   
                    this.addAll(search.findByPromo(String.valueOf(filtre.getFormationId()), filtre.getSession()));
                break;
            case SEARCH_NUM_ETUDIANT :
                if(countResult)
                    this.count = 1;
                else if(this.count > 0)   
                    this.addAll(search.findByEtudiantId(filtre.getRecherche(), filtre.getSession()));
                break;
            default:
                String grp = user.getGroupe().getNom().toUpperCase();
                if (grp.equals("ETUDIANT")) {
                    filtre.setLabel(this.user.getFormation().getLabel());
                    if(countResult)
                        this.count = search.countByPromo(String.valueOf(user.getFormation().getId()), currentSession);
                    else if(this.count > 0)   
                        this.addAll(search.findByPromo(String.valueOf(user.getFormation().getId()), currentSession));
                } 
                else {
                    filtre.setLabel(""+currentSession);
                    if(countResult)
                        this.count = search.countAllPromo(currentSession);
                    else if(this.count > 0)   
                        this.addAll(search.searchfAllPromo(currentSession));
                }
                break;
        }
        
        filtre.setResultCount((int) this.count);
    }

    public int getCount() {
        return (int) count;
    }
}
