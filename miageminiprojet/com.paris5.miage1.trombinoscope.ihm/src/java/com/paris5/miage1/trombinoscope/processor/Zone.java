package com.paris5.miage1.trombinoscope.processor;

import com.paris5.miage1.trombinoscope.utils.Configuration;
import java.util.ArrayList;

/**
 * Classe abstraite observateur
 * @author Mourad, Saliou, Idir
 */
public abstract class Zone extends ArrayList{
    
    // le nombres de pages
    protected int nombrePages;
    
    // la page courante
    protected int currentPage;
    
    // nombre de resultat a presenter par page
    protected int minimum=Configuration.PROFILS_PAR_PAGE; //invarient
    
    
    /**
     * affiche le prochaine page
     */
    public void next() {
        if(currentPage<nombrePages){
            this.currentPage++;
            this.execute();
        }
    }
    
    /**
     * affiche la page precedente
     */
    public void precedent() {
        if(currentPage>1){
            this.currentPage--;
            this.execute();
        }
    }
    
    /**
     * affiche la page on fonction de son numero
     * @param num int
     */
    public void page(int num) {
        if(num>0 && num<=nombrePages){
            currentPage = num;
            this.execute();
        }
    }
    
    /**
     * execute les traitements sur la page courante
     * @return int
     */
    public void current() {
        this.execute();
    }

    /**
     * return le numero de la page courante
     * @return 
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    
    /**
     * retourne le nombre de pages 
     * @return int
     */
    public int getNombrePages() {
        return nombrePages;
    }
    
    /**
     * effectue le traitement attendu dans la zone
     */
    public abstract void execute();
}
