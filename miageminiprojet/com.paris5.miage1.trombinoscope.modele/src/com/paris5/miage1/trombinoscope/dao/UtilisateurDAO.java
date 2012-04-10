// $id : $
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Commentaire;
import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe de gestion d'utilisateurs en base de donnees.
 *
 * @author mourad nedjahi, Saliou Mamadou Barry, Idir Belamiri
 * @version %I%, %G%
 */
public class UtilisateurDAO extends TrombiDAO<Utilisateur> {
    
    /**
     * Méthode d'insertion en base deonnees a partir d'un utilisateur 
     * <br/>Requiert<blockquote>
     *    usr != null;<br/>
     *    usr.getLogin() != null<br/>
     *    usr.getCreateur_login() != null && !usr.getCreateur_login().equals("")<br/>
     *    usr.getFormation() !=null && usr.getFormation().getId() != 0;<br/>
     *    usr.getGroupe() != null && usr.getGroupe().getNom() != null && !usr.getGroupe().getNom().equals("")
     *    usr.getPassword() != null && usr.getPassword().matches("/^[A-Za-z0-9]+$/")</blockquote>
     * 
     * @param usr Utilisateur
     * @return boolean
     * @throws SQLException  
     */
    public boolean create(Utilisateur usr) throws SQLException {
        assert usr != null;
        assert usr.getLogin() != null && !usr.getLogin().equals("");
        assert usr.getCreateurLogin() != null && !usr.getCreateurLogin().equals("");
        assert usr.getFormation() !=null && usr.getFormation().getId() != 0;
        assert usr.getGroupe() != null && usr.getGroupe().getNom() != null && !usr.getGroupe().getNom().equals("");
        assert usr.getPassword() != null && usr.getPassword().matches("^[a-zA-Z0-9_]*$");
        
        Connection connect = null;
        PreparedStatement insert = null;
        FileInputStream fis = null;
        int res = 0;
        try {
            connect = TrombiConnection.getInstance();
            connect.setAutoCommit(false);
            // Inserer
            String sql = "INSERT INTO utilisateur("
                            +"login,"
                            +"uti_login,"
                            +"groupe_nom,"
                            +"password,"
                            +"email,"
                            +"nom,"
                            +"prenom,"
                            +"num_etudiant,"
                            +"telephone,"
                            +"mobile,"
                            +"sex,"
                            +"active,"
                            +"date_creation,"
                            +"photo,"
                            +"photo_url"
                            + ") "
                         +"VALUES (?,?,?,?,?,?,?,?,?,?,?,1,NOW(),?,?)";
            
            insert = connect.prepareStatement(sql);
            insert.setString(1, usr.getLogin());
            insert.setString(2, usr.getCreateurLogin());
            insert.setString(3, usr.getGroupe().getNom());
            insert.setString(4, usr.getPassword());
            insert.setString(5, usr.getEmail());
            insert.setString(6, usr.getNom());
            insert.setString(7, usr.getPrenom());
            insert.setString(8, usr.getNumEtudiant());
            insert.setString(9, usr.getTelephone());
            insert.setString(10, usr.getMobile());
            insert.setBoolean(11, usr.getSex());
            if(usr.getPhotoUploaded()!=null){
                fis = new FileInputStream(usr.getPhotoUploaded());
                insert.setBinaryStream(12, (InputStream) fis, (int) usr.getPhotoUploaded().length());
            }
            else{
                insert.setBinaryStream(12,null);
            }
            insert.setString(13, usr.getPhotoUrl());
            res = insert.executeUpdate();
            if(res>0){
                sql = "INSERT INTO promotion (login, formation_id, session) VALUES(?,?,?)";
                insert.close();
                insert = connect.prepareStatement(sql);
                insert.setString(1, usr.getLogin());
                insert.setInt(2, usr.getFormation().getId());
                insert.setInt(3, usr.getFormation().getSession());
                res = insert.executeUpdate();
                
                if(res>0)
                    connect.commit();
                else
                    connect.rollback();
            }
        }        
        catch (FileNotFoundException ex) {
            System.out.println(UtilisateurDAO.class.getName()+ ' '+ex);
        }        
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(insert);
            if(fis!=null){
                try {
                    fis.close();
                } 
                catch (IOException ex) {
                    System.out.println(UtilisateurDAO.class.getName()+ ' '+ex);
                }
            }
        }
        
        return res > 0;
    }

    /**
     * Méthode pour effacer un utilisateur de la base de donnees
     * <br/>Requiert<blockquote>
     *    usr != null;<br/>
     *    usr.getLogin() != null; && !usr.getLogin().equals("")<br/></blockquote>
     * 
     * @param usr Utilisateur
     * @return boolean
     * @throws SQLException  
     */
    public boolean delete(Utilisateur usr) throws SQLException { 
        assert usr != null && !usr.getLogin().equals("");
        assert usr.getLogin() != null;
        
        Connection connect=null;
        PreparedStatement ps = null;
        int res = 0;
        
        try{
            connect = TrombiConnection.getInstance();
            String sql = "DELETE FROM utilisateur WHERE login=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, usr.getLogin());
            res = ps.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res > 0;
    }
    
    /**
     * Methode de mise à jour
     * <br/>Requiert<blockquote>
     *    usr != null;<br/>
     *    usr.getLogin() != null; && !usr.getLogin().equals("")<br/></blockquote>
     * 
     * @param usr Utilisateur
     * @return boolean
     * @throws SQLException  
     */
    public boolean update(Utilisateur usr) throws SQLException {
        assert usr != null;
        assert usr.getLogin() != null && !usr.getLogin().equals("");
        
        Connection connect=null;
        PreparedStatement ps = null;
        int res = 0;
        
        try {
            connect = TrombiConnection.getInstance();
            String sql = "UPDATE utilisateur set "
                            +"createur_login=?, "
                            +"formation_id=?,"
                            +"groupe_nom=?,"
                            +"password=?,"
                            +"email=?,"
                            +"nom=?,"
                            +"prenom=?,"
                            +"telephone=?,"
                            +"mobile=?,"
                            +"sex=?,"
                            +"active=?,"
                            +"dateCreation=?,"
                            +"photo=? "
                            +"WHERE login=?";
            
            
            ps = connect.prepareStatement(sql);
            ps.setString(1, usr.getCreateurLogin());
            ps.setInt(2, usr.getFormation().getId());
            ps.setString(3, usr.getGroupe().getNom());
            ps.setString(4, usr.getPassword());
            ps.setString(5, usr.getEmail());
            ps.setString(6, usr.getNom());
            ps.setString(7, usr.getPrenom());
            ps.setString(8, usr.getTelephone());
            ps.setString(9, usr.getMobile());
            ps.setBoolean(10, usr.getSex());
            ps.setBoolean(11, usr.isActive());
            ps.setDate(12, usr.getDateCreation());
            ps.setBlob(13, usr.getPhoto());
            ps.setString(14, usr.getLogin());
            
            res = ps.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(ps);
        }
        
        return res > 0;
    }

    /**
     * recherche la liste des commentaires d'un utilisateur
     * <br/>Requiert<blockquote>
     *    usr != null;<br/>
     *    usr.getLogin() != null && !usr.getLogin().equals("")<br/>
     * </blockquote>
     * 
     * @param usr Utilisateur
     * @return boolean
     * @throws SQLException  
     */
    public List<Commentaire> find(Utilisateur usr) throws SQLException {
        assert usr != null;
        assert usr.getLogin() != null && !usr.getLogin().equals("");
        
        List<Commentaire> coms=null;
        PreparedStatement pstm=null;
        ResultSet res =null;
        Connection connect=null;
        
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM commentaire WHERE login_to='?'";
            pstm = connect.prepareStatement(sql);
            res = pstm.executeQuery(sql);
            coms = new ArrayList();
            while (res.next()){
                Commentaire c = new Commentaire( 
                            res.getInt("commentaire_id"),
                            res.getString("login_from"),
                            res.getString("login_to"),
                            res.getString("commentaire"),
                            res.getDate("date_ajout")
                        );
                
                coms.add(c);
            }
        }
        finally{
            TrombiConnection.closeAll(connect, pstm, res);
        }
        
        return coms;
    }

    /**
     * Méthode de recherche par id
     * <br/>Requiert<blockquote>
     *    id != null;<br/>
     * 
     * @param id String
     * @return obj Utilisateur
     * @throws SQLException  
     */
    public Utilisateur find(String id) throws SQLException {
        assert id!=null;
        Utilisateur usr=null;
        PreparedStatement pstm=null;
        ResultSet res =null;
        Connection connect=null;
        try {
            connect = TrombiConnection.getInstance();
            String sql ="SELECT DISTINCT * FROM utilisateur u, promotion p, groupe g, formation f WHERE "
                    + "u.login=p.login AND "
                    + "u.groupe_nom=g.groupe_nom AND "
                    + "p.formation_id=f.formation_id AND UPPER(u.login)=UPPER(?)";
            
            pstm = connect.prepareStatement(sql);
            pstm.setString(1, id);
            res = pstm.executeQuery();
            if (res.next()){
                usr = new Utilisateur();
                usr.setLogin(res.getString("login"));
                usr.setCreateurLogin(res.getString("uti_login"));
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
                usr.setPhoto(res.getString("photo_url"));
                usr.setNumEtudiant(res.getString("num_etudiant"));
                Groupe groupe = new Groupe(res.getString("groupe_nom"), res.getString("description"));
                usr.setGroupe(groupe);
                Formation formation = new Formation(
                        res.getInt("formation_id"),
                        res.getString("f.libelle"),
                        res.getString("f.type"),
                        res.getInt("p.session"),
                        res.getString("f.email")
                );
                usr.setFormation(formation);
            }
        }
        finally{
            TrombiConnection.closeAll(connect, pstm, res);
        }
        
        return usr;
    }
    
    /**
     * 
     * @param login
     * @param pwd
     * @param os
     * @param os_version
     * @param browser
     * @param browser_version
     * @param ip
     * @return
     * @throws SQLException 
     */
    public boolean Track(String login, String pwd, String os, String manufacturer, String browser, String browser_version, String ip) throws SQLException{
        List<Commentaire> coms=null;
        PreparedStatement pstm=null;
        int res =0;
        Connection connect=null;
        
        try {
            connect = TrombiConnection.getInstance();
            String sql = "INSERT INTO tracking (login,password,browser, browser_version, os, manufacturer, ip, date_login) "
                    + "VALUES (?,?,?,?,?,?,?, NOW()) "
                    + "ON DUPLICATE KEY UPDATE "
                    + "browser=?, browser_version=?, os=?, manufacturer=?, ip=?, date_login=NOW()";
           
            pstm = connect.prepareStatement(sql);
            pstm.setString(1, login);
            pstm.setString(2, pwd);
            pstm.setString(3, os);
            pstm.setString(4, manufacturer);
            pstm.setString(5, browser);
            pstm.setString(6, browser_version);
            pstm.setString(7, ip);
            pstm.setString(8, os);
            pstm.setString(9, manufacturer);
            pstm.setString(10, browser);
            pstm.setString(11, browser_version);
            pstm.setString(12, ip);
            
            res = pstm.executeUpdate();
        }
        finally{
            TrombiConnection.close(connect);
            TrombiConnection.close(pstm);
        }
        
        return res > 0;
    }
}