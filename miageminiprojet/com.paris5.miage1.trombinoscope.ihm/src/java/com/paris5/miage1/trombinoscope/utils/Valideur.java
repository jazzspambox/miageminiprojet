/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.utils;

/**
 * @author mourad
 */
public class Valideur {
    
    /**
     * 
     * @param parameter
     * @param defaut
     * @return 
     */
    public static int getNumeric(String parameter, int defaut) {
        String res = cleanparameter("^[^0-9]*$", parameter, null);
        if (res!=null)
            return Integer.parseInt(res);
        
        return defaut;
    }
    
    /**
     * 
     * @param parameter
     * @return 
     */
    public static int getNumeric(String parameter){
        return Valideur.getNumeric(parameter, 0);
    }
    
    /**
     * 
     * @param parameter
     * @param defaut
     * @return 
     */
    public static String getAlphabetic(String parameter, String defaut){
        return cleanparameter("^[^A-Za-z]*$", parameter, defaut);
    }
    
    /**
     * 
     * @param parameter
     * @return 
     */
    public static String getAlphabetic(String parameter){
        return Valideur.getAlphabetic(parameter, null);
    }
    
    /**
     * 
     * @param parameter
     * @param defaut
     * @return 
     */
    public static String getAlphaNumeric(String parameter, String defaut){
        return cleanparameter("^[^a-zA-Z0-9]*$", parameter, defaut);
    }
    
    /**
     * 
     * @param parameter
     * @return 
     */
    public static String getAlphaNumeric(String parameter){
        return Valideur.getAlphaNumeric(parameter, null);
    }
    
    /**
     * 
     * @param parameter
     * @param defaut
     * @return 
     */
    public static String getAuthorized(String parameter, String defaut){
        return cleanparameter("^[^a-zA-Z0-9_-]*$", parameter, defaut);
    }
    
    /**
     * 
     * @param parameter
     * @return 
     */
    public static String getAuthorized(String parameter){
        return getAuthorized(parameter, null);
    }
    
    /**
     * 
     * @param regexp
     * @param parameter
     * @param defaut
     * @return 
     */
    private static String cleanparameter(String regexp, String parameter, String defaut){
        if(parameter!=null){
            parameter = parameter.replaceAll(regexp, "");
            return parameter;
        }
        else{
            return defaut;
        }
    }
}