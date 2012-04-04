/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mourad
 */
public class FormationDAO extends TrombiDAO<Formation> {

    @Override
    public boolean create(Formation obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Formation obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Formation obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List find(Formation obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Cette methode retourne toutes les formations de la base de données.
     * 
     * @return une liste de formations
     * @throws SQLException  
     */
    public List<Formation> list() throws SQLException {
        
        List<Formation> formations=null;
        PreparedStatement pstm=null;
        ResultSet res =null;
        Connection connect=null;
        Formation formation = null;
        
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT DISTINCT p.formation_id, p.session, type, libelle, email FROM formation f, promotion p "
                    + "WHERE p.formation_id=f.formation_id ORDER BY p.session DESC";
            pstm = connect.prepareStatement(sql);
            res = pstm.executeQuery(sql);
            formations=new ArrayList<Formation>();
            while(res.next()){
                formation = new Formation(
                        res.getInt("p.formation_id"), 
                        res.getString("libelle"),
                        res.getString("type"),
                        res.getInt("p.session"),
                        res.getString("email")
                        );
                formations.add(formation);  
            }
        }
        finally{
            TrombiConnection.closeAll(connect, pstm, res);
        }
            
        return formations;       
    }

    /**
     * Cette methode recherche une formation par son identifiant dans la base de données.
     * 
     * @param id String
     * @return Formation
     * @throws SQLException 
     */
    public Formation find(String id) throws SQLException {
        Formation formation=null;
        PreparedStatement pstm=null;
        ResultSet res =null;
        Connection connect=null;
        
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM formation WHERE formation_id=?";
            pstm = connect.prepareStatement(sql);
            pstm.setString(1, id);
            res = pstm.executeQuery(sql);
            if(res.next()){
                formation = new Formation(
                        res.getInt("formation_id"), 
                        res.getString("labelle"),
                        res.getString("type"),
                        0,
                        res.getString("email")
                        );
            }
        }
        finally{
            TrombiConnection.closeAll(connect, pstm, res);
        }
            
        return formation;
    }
}
