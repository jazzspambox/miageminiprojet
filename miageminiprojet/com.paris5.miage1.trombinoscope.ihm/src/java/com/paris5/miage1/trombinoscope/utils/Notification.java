/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.utils;

/**
 *
 * @author mourad
 */
public class Notification {
    
    private String message="";
    private String level="";
    private int id=0;
    
    public enum LevelList{
        OK, WARNING, ERROR;
    }

    public Notification() {
    }

    public Notification( LevelList level, String message) {
        this.message = message;
        this.level = level.toString().toLowerCase();
        id++;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
