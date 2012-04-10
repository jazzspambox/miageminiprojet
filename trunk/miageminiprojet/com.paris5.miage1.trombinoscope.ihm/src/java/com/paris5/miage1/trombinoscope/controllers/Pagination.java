package com.paris5.miage1.trombinoscope.controllers;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.utils.Configuration;
import com.paris5.miage1.trombinoscope.utils.Filtre;
import java.sql.SQLException;
/**
 * @author mourad
 */
public class Pagination extends Resultat {

    public Pagination(Filtre filtre, Utilisateur usr) throws SQLException, NullPointerException {
        super(filtre, usr);
        this.init();
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
    
    private void init(){
        this.nombrePages = (int) Math.ceil(count/Configuration.PROFILS_PAR_PAGE);
        this.minimum = (this.currentPage - 1) * Configuration.PROFILS_PAR_PAGE;
    }
    
    public void execute(){
        
        super.clear();
        
        int born= (int) Math.ceil(Configuration.PAGINATION_POINTILLES/2);
        int it=currentPage-born;
        if(it<=1){
            it = 1;
        }
        else {
            this.add(0);
        }
        
        int limit = it+Configuration.PAGINATION_POINTILLES;
        if(limit> nombrePages)
            limit = nombrePages;
        
        while(it<=limit){
            this.add(it);
            it++;
        }
        
        if(it-1<nombrePages){
            this.add(0);
        }
    }
}