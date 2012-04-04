/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Projet;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
  * 
  * @author mourad
  */
public class ProjetDAO extends TrombiDAO<Projet> {

    /**
     * cree un projet
     * <br/>Requiert<blockquote>
     *    obj != null;<br/>
     *    obj.getId() != null && !obj.getId().equals("")<br/>
     *    obj.getNom() != null && !obj.getNom().equals("")<br/>
     *    obj.getIdFormation() > 0<br/>
     *    obj.getDateDebut() !=null && obj.getDateFin() != null<br/>
     *    obj.getDateDebut()
     * </blockquote> 
     * @param obj
     * @return boolean
     * @throws SQLException 
     */
    public boolean create(Projet obj) throws SQLException {
        assert obj != null;
        assert obj.getId() != null && !obj.getId().equals("");
        assert obj.getNom() != null && !obj.getNom().equals("");
        assert obj.getIdFormation() > 0;
        assert obj.getDateDebut() !=null && obj.getDateFin() != null;
        assert obj.getDateDebut().before(obj.getDateFin());
        
        int res = 0;
        PreparedStatement ps=null;
        Connection connect=null;
        try{
            connect = TrombiConnection.getInstance();
            ps=connect.prepareStatement("insert into projet (non, description) values(?,?,?,?,?,?)");
            ps.setInt(1,obj.getId());
            ps.setString(2, obj.getNom());
            ps.setInt(3, obj.getIdFormation());
            ps.setString(4, obj.getDescription());
            ps.setDate(5, obj.getDateDebut());
            ps.setDate(6, obj.getDateFin());
            res = ps.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res>0;
    }

    /**
     * efface un projet
     * <br/>Requiert<blockquote>
     *    obj != null;<br/>
     *    obj.getId() != null && !obj.getId().equals("")<br/>
     * </blockquote> 
     * @param obj
     * @return
     * @throws SQLException 
     */
    public boolean delete(Projet obj) throws SQLException {
        assert obj != null;
        assert obj.getId() != null && !obj.getId().equals("");
        
        Statement st=null;
        Connection connect=null;
        int res=0;
        try{
            connect = TrombiConnection.getInstance();
            st = connect.createStatement();
            res = st.executeUpdate("delete from projet where id="+obj.getId());
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        
        return res > 0;
    }
    
    /**
     * met a jour un projet
     * <br/>Requiert<blockquote>
     *    obj != null;<br/>
     *    obj.getId() != null && !obj.getId().equals("")<br/>
     *    assert (obj != null && obj.getDateDebut().before(obj.getDateFin())) || obj==null;<br/>
     * </blockquote> 
     * @param obj
     * @return boolean
     * @throws SQLException 
     */
    public boolean update(Projet obj) throws SQLException {
        assert obj != null;
        assert obj.getId() != null && !obj.getId().equals("");
        assert (obj != null && obj.getDateDebut().before(obj.getDateFin())) || obj==null;
        
        Statement st=null;
        int res=0;
        Connection connect=null;
        try{
            connect = TrombiConnection.getInstance();
            st = connect.createStatement();
            res = st.executeUpdate("update set nom='"
                    +obj.getNom()+"', idFormation="
                    + obj.getIdFormation()+",description='"
                    +obj.getDescription()+"',dateDebut="
                    +obj.getDateDebut()+",dateFin="
                    +obj.getDateFin()+" where id="+obj.getId());
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        
        return res > 0;
    }
    
    /** 
     * Fourni la liste des projets d'un utilisateur.
     *<br/>Requiert<blockquote>
     *    obj != null;<br/>
     *    obj.getId() != null && !obj.getId().equals("")<br/>
     * </blockquote> 
     *
     * @param prg
     * @return boolean
     * @throws SQLException 
     */
    public List<Utilisateur> find(Projet prg) throws SQLException{
        assert prg != null;
        assert prg.getId() != null;
        List<Utilisateur> projets=null; 
        PreparedStatement ps = null;
        ResultSet res=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT COUNT(*) AS count_element FROM utilisateur_projet up, utilisateur u, promotion p, groupe g, formation f WHERE "
                    + "u.formation_id=p.formation_id AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id "
                    + "AND up.login=u.login AND up.projet_id=?";
            
            ps = connect.prepareStatement(sql);
            ps.setInt(1, prg.getId());
            res = ps.executeQuery(sql);
            while(res.next()){
                Utilisateur usr = new Utilisateur();
                usr.setLogin(res.getString("u.login"));
                usr.setCreateurLogin(res.getString("createur_login"));
                usr.setPassword(res.getString("password"));
                usr.setEmail(res.getString("email"));
                usr.setNom(res.getString("u.nom"));
                usr.setPrenom(res.getString("prenom"));
                usr.setTelephone(res.getString("telephone"));
                usr.setMobile(res.getString("mobile"));
                usr.setSex(res.getBoolean("sex"));
                usr.setActive(res.getBoolean("active"));
                usr.setDateCreation(res.getDate("date_creation"));
                usr.setPhoto(res.getBlob(15));
                Groupe groupe = new Groupe(res.getString("groupe_nom"), res.getString("description"));
                usr.setGroupe(groupe);
                /*Formation formation = new Formation(
                            res.getInt("formation_id"), 
                            res.getString("labelle"),
                            res.getString("type"),
                            res.getInt("p.session"),
                            res.getString("email")
                        );
                usr.setFormation(formation);*/
                usr.setFormation(null);
                projets.add(usr);
            }
        }
        finally{
            TrombiConnection.closeAll(connect, ps, res);
        }
        return projets;
    }

    /** 
     * Fourni la liste des projets d'un utilisateur.
     *<br/>Requiert<blockquote>
     *    obj.getId() != null && !obj.getId().equals("")<br/>
     * </blockquote> 
     *
     * @param id String
     * @return Projet
     * @throws SQLException 
     */
    public Projet find(String id) throws SQLException {
        
        assert id != null;

        Projet projet=null; 
        PreparedStatement ps = null;
        ResultSet res=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM projet p WHERE projet_id=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, id);
            res = ps.executeQuery(sql);
            
            if(res.next()){
                projet = new Projet(res.getInt("formation_id"), res.getString("nom"), res.getInt("session"));
            }
        }
        finally{
            TrombiConnection.closeAll(connect, ps, res);
        }
        return projet;    
    }
}