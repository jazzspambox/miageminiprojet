package com.paris5.miage1.trombinoscope.controllers;

import com.paris5.miage1.trombinoscope.dao.SearchDAO;
import com.paris5.miage1.trombinoscope.processor.Zone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mourad
 */
public class Liste extends ArrayList implements Zone{
    
    private int start;
    private SearchDAO search;
    
    public Liste(List l){
        super(l);
        start = 0;
    }
    
    public Liste(int start, List l) {
        super(l);
        this.start = start;
    }

    @Override
    public void next() {
        start++;
    }

    @Override
    public void precedent() {
        start--;
    }

    @Override
    public void page(int num) {
        start=num;
    }

    @Override
    public int getCurrentPage() {
        return start;
    }

    @Override
    public int getNombrePages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void executeSearch(){
        
    }
}
