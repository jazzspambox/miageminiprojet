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

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 * interface permettant de definir la methode de declenchement d un processus au saint de l application
 * @author Mourad, Saliou, Idir
 */
public interface Processeur {
    
    /**
     * constructeur
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public abstract void process() throws SQLException, ServletException, IOException;
}
