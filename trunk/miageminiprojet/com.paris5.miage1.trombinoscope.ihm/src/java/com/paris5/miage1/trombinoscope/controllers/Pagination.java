package com.paris5.miage1.trombinoscope.controllers;
import com.paris5.miage1.trombinoscope.processor.Zone;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @author mourad
 */
public class Pagination implements Zone {
    
    private int nombrePages=1;
    private int minimum=Configuration.PROFILS_PAR_PAGE; //invarient
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
    
    public int getSeparator() {
        return Configuration.PAGINATION_POINTILLES;
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
        nombrePages = (int) Math.ceil(nb/Configuration.PROFILS_PAR_PAGE);
        minimum = (this.nombrePages - 1) * Configuration.PROFILS_PAR_PAGE;
        if(minimum<0)
            minimum=0;
        this.currentPage = currentPage;
    }
    
    public ArrayList<Integer> getList(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int born= (int) Math.ceil(Configuration.PAGINATION_POINTILLES/2);
        int it=currentPage-born;
        if(it<=1){
            it = 1;
        }
        else {
            res.add(0);
        }
        
        int limit = it+Configuration.PAGINATION_POINTILLES;
        if(limit> nombrePages)
            limit = nombrePages;
        
        while(it<=limit){
            res.add(it);
            it++;
        }
        
        if(it-1<nombrePages){
            res.add(0);
        }
        
        return res;
    }
}