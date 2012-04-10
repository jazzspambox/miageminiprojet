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
 * enumeration des actions possible
 * @author Mourad, Saliou, Idir
 */
public enum Action {

    AUTHENT,
    DECONNECT,
    CREATE_USER,
    SHOW_FICHE,
    SEND_PHOTO,
    SHOW_USER,
    MODIFY_USER,
    ADD_USER,
    GO_NEXT_PAGE,
    GO_PRECEDENT_PAGE,
    GO_PAGE,
    GO_FIRST_PAGE,
    GO_LAST_PAGE,
    SEARCH,
    SEARCH_NOM,
    SEARCH_PRENOM,
    SEARCH_FIXE,
    SEARCH_MOBILE,
    SEARCH_PROMOTION,
    SEARCH_EMAIL,
    SEARCH_NUM_ETUDIANT,
    DEFAULT;

    /**
     * retourne l action correspondante a une chaine de caracteres
     * @param textString
     * @return Action
     */
    public static Action get(String text) {
        if (text != null) {
            for (Action act : Action.values()) {
                if (text.equalsIgnoreCase(act.toString())) {
                    return act;
                }
            }
        }

        return DEFAULT;
    }
}
