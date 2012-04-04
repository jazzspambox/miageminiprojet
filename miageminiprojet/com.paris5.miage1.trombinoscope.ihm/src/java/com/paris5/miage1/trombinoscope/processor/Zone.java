package com.paris5.miage1.trombinoscope.processor;

/**
 * Classe abstraite observateur
 * @author Mourad, Saliou, Idir
 */
public interface Zone{  
    /**
     * affiche le prochaine page
     */
    public void next();
    
    /**
     * affiche la page precedente
     */
    public void precedent();
    
    /**
     * affiche la page on fonction de son numero
     * @param num int
     */
    public void page(int num);
    
    /**
     * 
     * @return 
     */
    public int getCurrentPage();
    
    /**
     * 
     * @return 
     */
    public int getNombrePages();
}
