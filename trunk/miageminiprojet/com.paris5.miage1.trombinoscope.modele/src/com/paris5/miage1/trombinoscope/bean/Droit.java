/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.bean;

/**
 *
 * @author mourad
 */
public class Droit {
    private String name;
    private String description;

    /**
     * 
     * @param name
     * @param description
     */
    public Droit(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    /**
     * 
     * @return
     */
    public String geDescription() {
        return description;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Droit{" + "name=" + name + ", description=" + description + '}';
    }
}
