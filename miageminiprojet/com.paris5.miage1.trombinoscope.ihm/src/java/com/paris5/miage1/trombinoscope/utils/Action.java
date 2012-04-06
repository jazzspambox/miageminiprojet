/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.utils;

/**
 *
 * @author mourad
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
    DEFAULT;

    /**
     * 
     * @param text
     * @return 
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
