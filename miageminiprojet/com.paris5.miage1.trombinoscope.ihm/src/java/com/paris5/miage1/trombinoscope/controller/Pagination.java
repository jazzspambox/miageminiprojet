package com.paris5.miage1.trombinoscope.controller;



import com.paris5.miage1.trombinoscope.processor.Zone;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import java.sql.SQLException;

/**
 * @author mourad
 */
public class Pagination implements Zone {
    
    private int nombrePages=1;
    private int minimum=Configuration.PROFILSPAGE; //invarient
    private int currentPage;

    public Pagination(int page, int resultCount) throws SQLException, NullPointerException {
        this.init(page, resultCount);
    }
    
    /**
     * 
     * @param page
     * @param resultCount
     * @throws SQLException
     * @throws NullPointerException 
     */
    public Pagination(int resultCount) throws SQLException, NullPointerException {
        init(1, resultCount);
    }
    
    @Override
    public void next() {
        if(currentPage<nombrePages){
            this.currentPage++;
        }
    }

    @Override
    public void precedent() {
        if(currentPage>1){
            this.currentPage--;
        }
    }

    @Override
    public void page(int num) {
        if(num>0 && num<=nombrePages){
            currentPage = num;
        }
    }
    
    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getNombrePages() {
        return nombrePages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMinimum() {
        return minimum;
    }
    
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
    
    private void init(int currentPage, double nb){
        nombrePages = (int) Math.ceil(nb/Configuration.PROFILSPAGE);
        minimum = (this.nombrePages - 1) * Configuration.PROFILSPAGE;
        this.currentPage = currentPage;
    }
}