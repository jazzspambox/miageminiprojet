// $id : $
package com.paris5.miage1.trombinoscope.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * classe abstraite generique
 * Initialise la connection et spécifie les méthodes a utiliser pour interragir avec la base de donnees.
 *
 * @param <T> 
 * @author mourad nedjahi, Saliou Mamadou Barry, Idir Belamiri
 * @version %I%, %G%
 */
public abstract class TrombiDAO<T> {
    
    /**
     * Méthode de création
     * @param obj T
     * @return boolean
     * @throws SQLException  
     */
    public abstract boolean create(T obj) throws SQLException;

    /**
     * Méthode pour effacer
     * @param obj T
     * @return boolean
     * @throws SQLException  
     */
    public abstract boolean delete(T obj) throws SQLException;

    /**
     * Méthode de mise à jour
     * @param obj T
     * @return boolean
     * @throws SQLException  
     */
    public abstract boolean update(T obj) throws SQLException;

    /**
     * Méthode de recherche des informations
     * @param id
     * @return
     */
    public abstract T find(String id) throws SQLException;
    
    /**
     * Méthode de recherche des informations
     * @param obj T
     * @return List
     * @throws SQLException  
     */
    public abstract List find(T obj) throws SQLException;
}