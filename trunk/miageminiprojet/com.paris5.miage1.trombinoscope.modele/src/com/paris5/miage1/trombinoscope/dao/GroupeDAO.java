/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Droit;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe de gestion des groupes
 * 
 * @author mourad nedjahi, Saliou Mamadou Barry, Idir Belamiri
 */
public class GroupeDAO extends TrombiDAO<Groupe> {

    /**
     * insere un goupe de droits dans la base de donnees
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getNom()!=null && !obj.getNom().equals("")<br/>
     *      obj.getDescription()!=null && !obj.getDescription().equals("")<br/>
     *      obj.getDroits() != null && obj.getDroits().size() > 0</blockquote>
     * @param obj Groupe
     * @return boolean
     * @throws SQLException 
     */
    public boolean create(Groupe obj) throws SQLException {
        assert obj.getNom()!=null && !obj.getNom().equals("");
        assert obj.getDescription()!=null && !obj.getDescription().equals("");
        assert obj.getDroits() != null && obj.getDroits().size() > 0;
        PreparedStatement ps = null;
        Connection connect=null;
        int res = 0;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "INSERT INTO groupe VALUES(?,?)";
            ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getDescription());
            res = ps.executeUpdate();
            if(res >0){
                ArrayList droits = (ArrayList) obj.getDroits();
                for(int i = 0; i<droits.size() && res>0; i++){
                    ps.close();
                    sql = "INSERT INTO groupe_droit VALUES(?,?)";
                    ps = connect.prepareStatement(sql);
                    Droit d = (Droit) droits.get(i);
                    ps.setString(1, obj.getNom());
                    ps.setString(2, d.getName());
                    res = ps.executeUpdate();
                }
            }
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res > 0;
    }

    /**
     * efface un goupe de droits de la base de donnees
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getNom()!=null && !obj.getNom().equals("")<br/>
     *      obj.getDescription()!=null && !obj.getDescription().equals("")<br/>
     *      obj.getDroits() != null && obj.getDroits().size() > 0</blockquote>
     * @param obj Groupe
     * @return boolean
     * @throws SQLException 
     */
    public boolean delete(Groupe obj) throws SQLException {
        assert obj.getNom()!=null && !obj.getNom().equals("");
        assert obj.getDescription()!=null && !obj.getDescription().equals("");
        assert obj.getDroits() != null && obj.getDroits().size() > 0;
        PreparedStatement ps = null;
        Connection connect=null;
        int res = 0;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "DELETE FROM groupe WHERE groupe_nom=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getNom());
            res = ps.executeUpdate();
            // Ino db on delete cascade
            /*if(res >0){
                ps.close();
                sql = "DELETE FROM groupe_droit WHERE groupe_nom=?";
                ps = this.connect.prepareStatement(sql);
                ps.setString(1, obj.getNom());
                res = ps.executeUpdate();
            } */
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res > 0;
    }

    /**
     * met a jour un goupe de droits dans la base de donnees
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getNom()!=null && !obj.getNom().equals("")<br/>
     *      obj.getDescription()!=null && !obj.getDescription().equals("")<br/>
     *      obj.getDroits() != null && obj.getDroits().size() > 0</blockquote>
     * @param obj Groupe
     * @return boolean
     * @throws SQLException 
     */
    public boolean update(Groupe obj) throws SQLException {
        assert obj.getNom()!=null && !obj.getNom().equals("");
        assert obj.getDescription()!=null && !obj.getDescription().equals("");
        assert obj.getDroits() != null && obj.getDroits().size() > 0;
        PreparedStatement ps = null;
        Connection connect=null;
        int res = 0;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "UPDATE groupe SET description=? WHERE groupe_nom=?";
            ps = connect.prepareStatement(sql);
            ps.setString(2, obj.getNom());
            ps.setString(1, obj.getDescription());
            res = ps.executeUpdate();
            //innoDb on update cascade
            /*if(res >0){
                ArrayList droits = (ArrayList) obj.getDroits();
                for(int i = 0; i<droits.size() && res>0; i++){
                    ps.close();
                    sql = "UPDATE groupe_droit SET groupe_nom=? AND nom=?";
                    ps = this.connect.prepareStatement(sql);
                    Droit d = (Droit) droits.get(i);
                    ps.setString(1, obj.getNom());
                    ps.setString(2, d.getName());
                    res = ps.executeUpdate();
                }
            }*/
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res > 0;
    }

    /**
     * recherche les droits liés a un groupe en base de données
     * <br/>Requiert<blockquote>
     *      grp!=null<br/>
     * </blockquote>
     * @param grp Groupe
     * @return boolean
     * @throws SQLException 
     */
    public List<Droit> find(Groupe grp) throws SQLException {
        assert grp.getNom()!=null && !grp.getNom().equals("");
        ArrayList<Droit> droits = new ArrayList<Droit>();
        PreparedStatement pst=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT nom FROM groupe_droit WHERE groupe_nom=?";
            pst=connect.prepareStatement(sql);
            pst.setString(1, grp.getNom());
            ResultSet result=pst.executeQuery();
            
            while(result.next()){
                droits.add(new Droit(grp.getNom(), result.getString(1)));
            }
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(pst);
        }
        
        return droits;  
    }
    
    /**
     * Cette méthode fournie la liste des droits de la base de données
     * 
     * @return Map<String,String>
     * @throws SQLException 
     */
    public Map<String,String> getListeDroits() throws SQLException {
        Map<String,String> droits=new HashMap<String, String>();
        Connection connect=null;
        Statement st=null;
        try{
            connect = TrombiConnection.getInstance();
            st=connect.createStatement();
            ResultSet result=st.executeQuery("select * from droit");
            while(result.next()){
                droits.put(result.getString(1), result.getString(2));
            }
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        
        return droits;   
    }

    /**
     * retourne le groupe coresspendant a un id
     * 
     * @param id String
     * @return Groupe
     * @throws SQLException 
     */
    public Groupe find(String id) throws SQLException {
        
        assert id != null;

        Groupe grp=null; 
        PreparedStatement ps = null;
        ResultSet res=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM groupe WHERE groupe_id=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, id);
            res = ps.executeQuery(sql);
            
            if(res.next()){
                grp = new Groupe(res.getString("groupe_nom"));
                grp.setDescription(res.getString("description"));
                grp.setDroits(find(grp));
            }
        }
        finally{
            TrombiConnection.closeAll(connect, ps, res);
        }
        return grp;
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<Groupe> list() throws SQLException{
        Groupe grp=null; 
        ArrayList<Groupe> list=new ArrayList<Groupe>();
        PreparedStatement ps = null;
        ResultSet res=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM groupe ORDER BY groupe_nom DESC";
            ps = connect.prepareStatement(sql);
            res = ps.executeQuery(sql);
            
            while(res.next()){
                grp = new Groupe(res.getString("groupe_nom"));
                grp.setDescription(res.getString("description"));
                list.add(grp);
            }
        }
        finally{
            TrombiConnection.closeAll(connect, ps, res);
        }
        return list;
    }
}
