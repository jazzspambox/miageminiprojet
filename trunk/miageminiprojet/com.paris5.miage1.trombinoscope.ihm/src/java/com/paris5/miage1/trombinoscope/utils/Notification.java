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
    
    private String message;
    private int level;
    private static int id=0;
    
    public static enum LevelList{
        OK, WARNING, ERROR;
    }

    public Notification( LevelList level, String message) {
        this.message = message;
        switch(level){
            case OK :
                this.level = 1;
                break;
            case WARNING :
                this.level = 2;
                break;
            case ERROR :
                this.level = 3;
                break;
        }        
    }

    public int getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
