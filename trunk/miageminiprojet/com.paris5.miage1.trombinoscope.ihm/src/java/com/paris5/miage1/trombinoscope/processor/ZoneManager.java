/* ------------------------------------------------------------------------
 
    Licensed under the Educational Community License version 1.0

    This Original Work, including software, source code, documents,
    or other related items, is being provided by the copyright holder(s)
    subject to the terms of the Educational Community License. By
    obtaining, using and/or copying this Original Work, you agree that you
    have read, understand, and will comply with the following terms and
    conditions of the Educational Community License:

    Permission to use, copy, modify, merge, publish, distribute, and
    sublicense this Original Work and its documentation, with or without
    modification, for any purpose, and without fee or royalty to the
    copyright holder(s) is hereby granted, provided that you include the
    following on ALL copies of the Original Work or portions thereof,
    including modifications or derivatives.

 ------------------------------------------------------------------------ */
package com.paris5.miage1.trombinoscope.processor;
import com.paris5.miage1.trombinoscope.utils.Action;
import com.paris5.miage1.trombinoscope.utils.Valideur;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe abstraite Observable
 * @author Mourad, Saliou, Idir
 */
public abstract class ZoneManager extends Module {
    
    /**
     * liste des observateurs enregistres
     */
    protected ArrayList<Zone> trombiElements = new ArrayList();
    
    Action navigation;
    
    /**
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws NullPointerException 
     */
    public ZoneManager(boolean connect, HttpServletRequest request, HttpServletResponse response) 
            throws NullPointerException, ServletException, IOException {       
        super(connect, request, response);
        this.navigation = Action.get(Valideur.getAuthorized(request.getParameter("page")));
    }
    
    /**
     * enregistre un observateur
     * <br/>Requiert<blockquote>
     *      el != null
     * </blockquote>     
     * <br/>Assure<blockquote>
     *      this.trombiElements.size() > 0
     *      this.trombiElements.contains(el)
     * </blockquote>
     * 
     * @param el TrombiElement
     * @return boolean
     */
    public boolean addZone(Zone el){
        assert this.trombiElements.size() > 0;
        
        boolean res = trombiElements.add(el);
        
        assert this.trombiElements.contains(el);
                
        return res;
    }
    
    /**
     * execute la methde next() de chaque observateur enregistre
     * 
     * <br/>Requiert<blockquote>
     *      this.trombiElements.size() > 0
     * </blockquote>
     * 
     * @see TrombiElement#next()
     */
    public void doNext(){      
        assert this.trombiElements.size() > 0;       
        for(Zone el: trombiElements){
            el.next();
        }
    }
    
    /**
     * execute la methde precedent() de chaque observateur enregistre
     * 
     * <br/>Requiert<blockquote>
     *      this.trombiElements.size() > 0
     * </blockquote>
     * 
     * @see TrombiElement#precedent()
     */
    public void doPrecedent(){
        assert this.trombiElements.size() > 0;
        for(Zone el: trombiElements){
            el.precedent();
        }
    }
    
    /**
     * execute la methde page() de chaque observateur enregistre
     * 
     * <br/>Requiert<blockquote>
     *      this.trombiElements.size() > 0
     *      num > 0
     * </blockquote>
     * 
     * @param int num numero de la page
     * @see TrombiElement#page(int num)
     */
    public void goCurrent() {
        assert this.trombiElements.size() > 0;
        for(Zone el: trombiElements){
            el.current();
        }
    }
    
    /**
     * execute la methde page() de chaque observateur enregistre
     * 
     * <br/>Requiert<blockquote>
     *      this.trombiElements.size() > 0
     *      num > 0
     * </blockquote>
     * 
     * @param int num numero de la page
     * @see TrombiElement#page(int num)
     */
    public void goFirstPage() {
        assert this.trombiElements.size() > 0;
        for(Zone el: trombiElements){
            el.page(1);
        }
    }
    
    /**
     * execute la methde page() de chaque observateur enregistre
     * 
     * <br/>Requiert<blockquote>
     *      this.trombiElements.size() > 0
     *      num > 0
     * </blockquote>
     * 
     * @param int num numero de la page
     * @see TrombiElement#page(int num)
     */
    public void goLastPage() {
        assert this.trombiElements.size() > 0;
        for(Zone el: trombiElements){
            el.page(el.getNombrePages());
        }
    }
    
    /**
     * 
     * 
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void process() throws SQLException, ServletException, IOException{
        switch(this.navigation){
            case GO_NEXT_PAGE :
                this.doNext();
            break;
            case GO_PRECEDENT_PAGE:
                this.doPrecedent();
            break;
            case GO_FIRST_PAGE :
                 this.goFirstPage();
                break;
                
            case GO_LAST_PAGE :
                this.goLastPage();
                break;
                
            default :
                this.goCurrent();
        }
    }
}