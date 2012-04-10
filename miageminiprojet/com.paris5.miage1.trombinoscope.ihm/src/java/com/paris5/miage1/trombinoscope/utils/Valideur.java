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

package com.paris5.miage1.trombinoscope.utils;

/**
 * Classe de controle des failles xss
 * @author Mourad, Saliou, Idir
 */
public class Valideur {
    
    /**
     * supprime tout les caracteres non numeric d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @param defaut int valeur de retour par defaut
     * @return int
     */
    public static int getNumeric(String parameter, int defaut) {
        String res = cleanparameter("^[^0-9]*$", parameter, null);
        if (res!=null)
            return (int) Float.parseFloat(res);
        
        return defaut;
    }
    
    /**
     * supprime tout les caracteres non numeric d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @return int
     */
    public static int getNumeric(String parameter){
        return Valideur.getNumeric(parameter, 0);
    }
    
    /**
     * supprime tout les caracteres non alphabetiques d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @param defaut int valeur de retour par defaut
     * @return String
     */
    public static String getAlphabetic(String parameter, String defaut){
        return cleanparameter("^[^A-Za-z]*$", parameter, defaut);
    }
    
    /**
     * supprime tout les caracteres non alphabetique d'un paramettre
     * 
     * @param parameter String caractere en entree
     * @return String
     */
    public static String getAlphabetic(String parameter){
        return Valideur.getAlphabetic(parameter, null);
    }
    
    /**
     * supprime tout les caracteres non alphanumeric d'un paramettre
     * 
     * @param String parameter chaine en entree
     * @param defaut String valeur de retour par defaut
     * @return String
     */
    public static String getAlphaNumeric(String parameter, String defaut){
        return cleanparameter("^[^a-zA-Z0-9]*$", parameter, defaut);
    }
    
    /**
     * supprime tout les caracteres non alphanumeric d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @return String
     */
    public static String getAlphaNumeric(String parameter){
        return Valideur.getAlphaNumeric(parameter, null);
    }
    
    /**
     * supprime tout les caracteres non authorises d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @param defaut String valeur de retour par defaut
     * @return String
     */
    public static String getAuthorized(String parameter, String defaut){
        return cleanparameter("^[^a-zA-Z0-9_\\-]*$", parameter, defaut);
    }
    
    /**
     * supprime tout les caracteres non authorises d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @return String
     */
    public static String getAuthorized(String parameter){
        return getAuthorized(parameter, null);
    }
    
    /**
     * supprime tout les caracteres interdits d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @param defaut int valeur de retour par defaut
     * @return String
     */
    public static String getLabel(String parameter, String defaut){
        return cleanparameter("^[^a-zA-Z0-9_\\-\\(\\)\\@\\.]*$", parameter, defaut);
    }
    
    /**
     * supprime tout les caracteres interdits d'un paramettre
     * 
     * @param parameter String chaine en entree
     * @return String
     */
    public static String getLabel(String parameter){
        return getLabel(parameter, null);
    }
    
    /**
     * verifie si le paramatre est un email
     * 
     * @param email String
     * @return boolean
     */
    public static boolean isEmail(String email){
        return email.matches("^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$");
    }
    
    /**
     * verifie la validite d'un password
     * 
     * @param pwd String
     * @return boolean
     */
    public static boolean isPassword(String pwd){
        return pwd.matches("^[a-zA-Z0-9]{8}$");
    }
    
    /**
     * verifie la validire d un numero de telephonne
     * 
     * @param phone String
     * @return boolean
     */
    public static boolean isPhone(String phone){
        return phone.matches("^0[1-9]([-. ]?[0-9]{2}){4}$");
    }
    
    /**
     * veriife la validite d une photo
     * 
     * @param phone String
     * @return boolean
     */
    public static boolean isPhoto(String photo){
        return photo.matches("^[a-zA-Z0-9\\-_\\/\\\\]*+\\.(jpg|jpeg|png|gif)$");
    }
    
    /**
     * remplace les caracteres interdits par chaine vide
     * 
     * @param regexp String regle de filtrage
     * @param parameter String chaine a netoyer
     * @param defaut valeur de retour par defaut
     * @return String
     */
    private static String cleanparameter(String regexp, String parameter, String defaut){
        if(parameter!=null){
            parameter.trim();
            parameter = parameter.replaceAll(regexp, "");
            return parameter;
        }
        else{
            return defaut;
        }
    }
}