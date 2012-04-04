/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.processor;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 *
 * @author mourad
 */
public interface Processeur {
    /**
     * 
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public abstract void process() throws SQLException, ServletException, IOException;
}
