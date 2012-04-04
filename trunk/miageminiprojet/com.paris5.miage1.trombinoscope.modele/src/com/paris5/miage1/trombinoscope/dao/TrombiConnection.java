// $id : $
package com.paris5.miage1.trombinoscope.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * un sigleton pour initialiser la connexion
 * @version v0.1 2011.2012
 * @author mourad
 * 
 * @param main connection model/DAO Class
 * used for DAO and Controller interaction
 */
public class TrombiConnection {
    
    /**
     * le driver utilise
     */
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    
    /**
     * URL de connection
     */
    private static final String URL = "jdbc:mysql://localhost/trombinoscope";
    
    /**
     * Nom du user
     */
    private static final String USER = "root";
    
    /**
     * Mot de passe du user
     */
    private static final String PASSWORD = "0618674142";
    
    static{
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded.");
        }
        catch (ClassNotFoundException e) {
            System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
        }
        catch (InstantiationException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        }
        catch (IllegalAccessException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        }  
    }

    /**
     * Méthode qui va retourner notre instance
     * et la créer si elle n'existe pas...
     * @return Connection
     */
    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /** 
     * Ferme la connexion, si elle est non null.
     * @throws SQLException 
     */
    public static void close(Connection connect) throws SQLException {
        connect.close();
    }
    
    /**
     * @param st
     * @throws SQLException
     */
    public static void close(final Statement st) throws SQLException {
        if (st != null)
            st.close();
    }

    /** 
    * Ferme un ResultSet, s'il est non null.
    * Si une exception SQLException est levÈe, ne la propage pas.
     * @param rs 
     * @throws SQLException 
     */
    public static void close(final ResultSet rs) throws SQLException {
        if (rs != null)
            rs.close();
    }
    
    /** 
     * Ferme la connetion, le statement et le resultset, s'ils sont non null.
     * Si une exception SQLException est levÈe, ne la propage pas.
     * @param rs 
     * @throws SQLException 
     */
    public static void closeAll(final Connection connect, final Statement st, final ResultSet rs) throws SQLException {
        TrombiConnection.close(connect);
        TrombiConnection.close(st);
        TrombiConnection.close(rs);
    }
}