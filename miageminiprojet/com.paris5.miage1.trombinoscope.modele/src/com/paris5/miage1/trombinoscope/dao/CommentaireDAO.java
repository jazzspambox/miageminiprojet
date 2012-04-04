/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Commentaire;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * gestion de commentaires en base de donnees
 * @author mourad nedjahi, Saliou Mamadou Barry, Idir Belamiri
 */
public class CommentaireDAO extends TrombiDAO<Commentaire>{

    /**
     * Insere un commentaire dans la base de données
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getId > 0<br/>
     * </blockquote>
     * 
     * @param obj Commentaire
     * @return boolean
     * @throws SQLException 
     */
    public boolean create(Commentaire obj) throws SQLException {
        assert obj!=null:"L'objet commentaire ne doit pas être null";
        assert obj.getId() > 0;
        int res = 0;
        Connection connect=null;
        PreparedStatement st=null;
        try{
            connect = TrombiConnection.getInstance();
            st=connect.prepareStatement("INSERT INTO commentaire VALUES(?,?,?,?,?)");
            st.setInt(1,obj.getId());
            st.setString(2, obj.getLoginTo());
            st.setString(3, obj.getLoginFrom());
            st.setString(4, obj.getCommentaire());
            st.setDate(5, new Date(System.currentTimeMillis()));
            res = st.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        return res > 0 ;
    }

    /**
     * Supprime un commentaire dans la base de données.
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getId > 0<br/>
     * </blockquote>
     * 
     * @param obj Commentaire
     * @return boolean
     * @throws SQLException 
     */
    public boolean delete(Commentaire obj) throws SQLException {
        assert obj!=null:"L'objet commentaire ne doit pas être null";
        assert obj.getId() > 0;
        
        int res = 0;
        Connection connect=null;
        PreparedStatement st=null;
        try{
            connect = TrombiConnection.getInstance();
            st=connect.prepareStatement("DELETE FROM commentaire WHERE commentaire_id=?");
            st.setInt(1, obj.getId());
            res = st.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        return res > 0 ;
    }

    /**
     * Met a jour un commentaire
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getId > 0<br/>
     * </blockquote>
     * 
     * @param obj Commentaire
     * @return boolean
     * @throws SQLException 
     */
    public boolean update(Commentaire obj) throws SQLException {
        assert obj!=null:"L'objet commentaire ne doit pas être null";
        assert obj.getId() > 0;
        
        int res = 0;
        Connection connect=null;
        PreparedStatement st=null;
        try{
            connect = TrombiConnection.getInstance();
            st=connect.prepareStatement("UPDATE commentaire SET commentaire=?, date_ajout=? WHERE commentaire_id=?");
            st.setInt(2, obj.getId());
            st.setString(1, obj.getCommentaire());
            st.setDate(2, new Date(System.currentTimeMillis()));
            res = st.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(st);
        }
        return res > 0 ;
    }
    
    /**
     * recherche la liste des commentaire d'un utilisateur ayant poster un commentaire sur une fiche 
     * <br/>Requiert<blockquote>
     *      obj!=null<br/>
     *      obj.getId > 0<br/></blockquote>
     * 
     * @param c 
     * @return List<Commentaire>
     * @throws SQLException 
     */
    public List<Commentaire> find(Commentaire c) throws SQLException {
        assert c!=null:"L'objet commentaire ne doit pas être null";
        assert c.getId() > 0;
        
        List<Commentaire> commentaires=new ArrayList();
        PreparedStatement pst=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM commentaire WHERE login_form=?";
            pst=connect.prepareStatement(sql);
            pst.setString(1, c.getLoginFrom());
            ResultSet result=pst.executeQuery();
            
            while(result.next()){
                int id=result.getInt(1);
                String loginFrom=result.getString(2);
                String loginTo=result.getString(3);
                String commentaire=result.getString(4);
                Date date=result.getDate(5);
                commentaires.add(new Commentaire(id,loginFrom,loginTo,commentaire,date));
            }
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(pst);
        }
        
        return commentaires;
    }

    /**
     * recherche un commentaire par son id
     * 
     * @param comm_id
     * @return
     * @throws SQLException 
     */
    public Commentaire find(String comm_id) throws SQLException {
        
        Commentaire commentaire=null;
        PreparedStatement pst=null;
        Connection connect=null;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM commentaire WHERE commentaire_id=?";
            pst=connect.prepareStatement(sql);
            pst.setString(1, comm_id);
            ResultSet result=pst.executeQuery();
            
            if(result.next()){
                int id=result.getInt(1);
                String loginFrom=result.getString(2);
                String loginTo=result.getString(3);
                String comm=result.getString(4);
                Date date=result.getDate(5);
                commentaire = new Commentaire(id,loginFrom,loginTo,comm,date);
            }
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(pst);
        }
        
        return commentaire;
    }
}
