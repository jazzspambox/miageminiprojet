/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mourad
 */
public class SearchDAO {

    private final static int MEN = 0;
    private final static int WOMEN = 1;
    private final static int BOTH = 2;
    private String limit;
    private final static String mandatory="nom IS NOT NULL AND prenom IS NOT NULL AND photo_url IS NOT NULL AND session IS NOT NULL AND ";

    /**
     * limit les resultat de la requete
     * <br/>Requiert<blockquote>
     *    max > min && min >= 0;
     * </blockquote>
     * 
     * @param min
     * @param max 
     */
    public void setLimit(int min, int max) {
        assert max > min && min >= 0;
        this.limit = " LIMIT " + min + ", " + max;
    }

    /**
     * 
     * @param login
     * @param pwd
     * @return 
     */
    public Utilisateur findByLoginPassword(String login, String pwd) throws SQLException {
        assert login != null && !login.equals("");
        assert pwd != null && !pwd.equals("");

        Utilisateur usr = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Connection connect = null;

        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM utilisateur u, groupe g, groupe_droit gd WHERE g.groupe_nom=u.groupe_nom AND gd.groupe_nom=g.groupe_nom "
                    + "AND u.login=? AND password=? AND active=?";

            pstm = connect.prepareStatement(sql);
            pstm.setString(1, login);
            pstm.setString(2, pwd);
            pstm.setBoolean(3, true);

            res = pstm.executeQuery();

            if (res.next()) {
                usr = new Utilisateur();
                usr.setLogin(login);
                usr.setNom(res.getString("u.nom"));
                usr.setPrenom(res.getString("prenom"));
                usr.setSex(res.getBoolean("sex"));
                usr.setFormation(findUserPromo(login));
                Groupe groupe = new Groupe(res.getString("groupe_nom"),"");
                ArrayList droits = new ArrayList();
                droits.add(res.getString("gd.nom"));
                while(res.next()){
                    droits.add(res.getString("gd.nom"));
                }
                usr.setGroupe(groupe);
            }
        } 
        finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return usr;
    }

    /**
     * 
     * 
     * @param login
     * @return
     * @throws SQLException 
     */
    public Formation findUserPromo(String login) throws SQLException {
        assert login != null || login.equals("") : "le login ne  être ni null ni vide ";
        PreparedStatement pst = null;
        Connection connect = null;
        ResultSet res = null;
        Formation formation=null;
        try {
            String req = "SELECT * FROM promotion p, formation f WHERE p.formation_id=f.formation_id "
                    + "AND p.login =? ORDER BY session DESC";
            connect = TrombiConnection.getInstance();
            pst = connect.prepareStatement(req);
            pst.setString(1, login);
            // pst.setString(2, login);
            res = pst.executeQuery();
            if (res.next()){
                formation = new Formation(
                        res.getInt("p.formation_id"),
                        res.getString("libelle"),
                        res.getString("f.type"),
                        res.getInt("p.session"),
                        res.getString("f.email"));
            }
        } 
        finally {
            TrombiConnection.closeAll(connect, pst, res);
        }
        
        return formation;
    }

    /**
     * Recherche un utilisateur par email
     * <br/>Requiert<blockquote>
     *    email != null && !email.equals("")<br/>
     * </blockquote>
     * 
     * @param email String
     * @return Utilisateur
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findByEmail(String email, int formation_id) throws SQLException {
        assert email != null && !email.equals("");

        ArrayList usr = new ArrayList();
        PreparedStatement pstm = null;
        ResultSet res = null;
        Connection connect = null;
        StringBuffer where=new StringBuffer(" UPPER(u.email) like ?");
        if(formation_id!=0){
            where.append("AND p.formation_id="+formation_id);
        }
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT DISTINCT u.*, p.session, g.* FROM utilisateur u, promotion p, groupe g, formation f WHERE "+ mandatory
                    + "u.login=p.login AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND"+where.toString()+limit;

            pstm = connect.prepareStatement(sql);
            pstm.setString(1, "%"+email.toUpperCase()+"%");
            res = pstm.executeQuery();
            if (res.next()) {
                
                usr.add(buildUser(res));
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return usr;
    }

    /**
     * Recherche un utilisateur par son numero d'etudiant
     * <br/>Requiert<blockquote>
     *    id != null && !id.equals("")<br/>
     * </blockquote>
     * 
     * @param id String
     * @return Utilisateur
     * @throws SQLException 
     */
 public ArrayList<Utilisateur> findByEtudiantId(String id, int formation_id) throws SQLException {
        assert id != null && !id.equals("");
        ArrayList<Utilisateur> users =new ArrayList<Utilisateur>() ;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Connection connect = null;
        StringBuffer where=new StringBuffer("AND u.num_etudiant like ?");
        if(formation_id!=0){
            where.append("AND formation_id="+formation_id);
        }
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * FROM utilisateur u, promotion p, groupe g, formation f WHERE "+mandatory
                    + "u.login=p.login AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id "+where.toString();
            pstm = connect.prepareStatement(sql);
            pstm.setString(1, "%"+id+"%");
            res = pstm.executeQuery();
            if (res.next()) {
               Utilisateur usr = buildUser(res);
               users.add(usr);
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    /**
     * Recherche des utilisateurs par le numero de telephone (fixe=>false ou mobile=>true)
     *  <br/>Requiert<blockquote>
     *    phone != null && !phone.equals("")<br/>
     * </blockquote>
     * 
     * @param phone String
     * @param cellular Boolean
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
     public ArrayList<Utilisateur> findByPhone(String phone, boolean cellular, int formation_id, boolean count) throws SQLException {
        assert phone != null && !phone.equals("");
        Utilisateur usr = null;
        ResultSet res = null;
        ArrayList users = new ArrayList();
        PreparedStatement pstm = null;
        Connection connect = null;

        try {
            connect = TrombiConnection.getInstance();
            StringBuffer sql = new StringBuffer("SELECT * ");
            if(count)
                sql.append("SELECT count(*)"); 
            StringBuffer where ;
            if (cellular) {
                where = new StringBuffer("mobile=?");
            }
            else
                where=new StringBuffer("telephone=?");
            if(formation_id!=0)
                where.append("AND p.formation_id="+ formation_id);
            
             sql.append("FROM utilisateur u, promotion p, groupe g, formation f WHERE "+mandatory
                    + "u.login=p.login AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND "
                    + where.toString()+ limit);
            pstm = connect.prepareStatement(sql.toString());
            pstm.setString(1, "%"+phone+"%");
            res = pstm.executeQuery();
            if(!count){
                while (res.next()) {
                    usr = buildUser(res);
                    if (usr != null) {
                    users.add(usr);
                    }
                 }
            }
            else{
                users.add(res.getInt(1));
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    /**
     * Recherche un utilisateur par nom
     * <br/>Requiert<blockquote>
     *    name != null && !name.equals("")<br/>
     * </blockquote>
     * 
     * @param name
     * @return
     * @throws SQLException 
     */
     public ArrayList<Utilisateur> findByLastName(String name, int formation_id) throws SQLException {
        assert name != null && !name.equals("");
        StringBuffer sql = new StringBuffer();
        StringBuffer where=new StringBuffer("UPPER(u.nom) like ?");
        if(formation_id!=0){
            where.append("AND p.formation_id="+formation_id);
        }
        sql.append("SELECT DISTINCT u.*, g.* FROM utilisateur u, promotion p, groupe g, formation f WHERE ").append(
               mandatory +"u.groupe_nom=g.groupe_nom AND f.formation_id=p.formation_id AND u.login=p.login  AND  ").append(where).append(limit);
        return searchByName(sql.toString(), name.toUpperCase(), false);
    }

    /**
     * Recherche un utilisateur par prenom
     * <br/>Requiert<blockquote>
     *    name != null && !name.equals("")<br/>
     * </blockquote>
     * 
     * @param name
     * @return
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findByFirstName(String name, int formation_id) throws SQLException {
        assert name != null && !name.equals("");
        StringBuffer sql = new StringBuffer();
        StringBuffer where=new StringBuffer("UPPER(u.prenom) like ?");
        if(formation_id!=0){
            where.append("AND p.formation_id="+formation_id);
        }
        sql.append("SELECT DISTINCT u.*, g.* FROM utilisateur u, promotion p, groupe g, formation f WHERE ").append(
               mandatory+ "u.groupe_nom=g.groupe_nom AND f.formation_id=p.formation_id AND u.login=p.login  AND  ").append(where).append(limit);;
        return searchByName(sql.toString(), name.toUpperCase(), false);
    }

    /**
     * compte les utilisateurs par nom
     * <br/>Requiert<blockquote>
     *    name != null && !name.equals("")<br/>
     * </blockquote>
     * 
     * @param name
     * @return
     * @throws SQLException 
     */
    public int countByLastName(String name) throws SQLException {
        assert name != null && !name.equals("");
        String sql = "SELECT COUNT(*) AS count_element FROM utilisateur u, promotion p, groupe g, formation f WHERE "+mandatory
                + "u.login=p.login AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND u.nom=?";

        ArrayList res = searchByName(sql.toString(), name, true);
        int count = res.size() > 0 ? (Integer) res.get(0) : 0;
        return count;
    }

    /**
     * Compte les utilisateurs par prenom
     * <br/>Requiert<blockquote>
     *    name != null && !name.equals("")<br/>
     * </blockquote>
     * 
     * @param name
     * @return
     * @throws SQLException 
     */
    public int countByFirstName(String name) throws SQLException {
        assert name != null && !name.equals("");
        String sql = "SELECT COUNT(*) AS count_element FROM utilisateur u, promotion p, groupe g, formation f WHERE " + mandatory
                + "u.login=p.login AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND u.prenom=?";
        ArrayList res = searchByName(sql.toString(), name, true);
        int count = res.size() > 0 ? (Integer) res.get(0) : 0;
        return count;
    }

    /**
     * recherche les utilisateurs d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year Annee
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        return searchByPromo(formation, year, BOTH, false);
    }

    /**
     * Recherche les hommes d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year int
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findManByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        return searchByPromo(formation, year, MEN, false);
    }

    /**
     * Recherche les femmes d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year int
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findWomanByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        return searchByPromo(formation, year, WOMEN, false);
    }

    /**
     * compte les utilisateurs d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year Annee
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public int countByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        ArrayList users = searchByPromo(formation, year, BOTH, true);
        Integer res = (Integer) (users.size() > 0 ? users.get(0) : 0);
        return res;
    }

    /**
     * compte les hommes d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year int
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public int countManByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        ArrayList users = searchByPromo(formation, year, MEN, true);
        int res = users.size() > 0 ? Integer.parseInt((String) users.get(0)) : 0;
        return res;
    }

    /**
     * compte les femmes d'une promo
     * <br/>Requiert<blockquote>
     *    formation != null && !formation.equals("")<br/>
     *    year > 2000 <br/>
     * </blockquote>
     * 
     * @param formation String
     * @param year int
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    public int countWomanByPromo(String formation, int year) throws SQLException {
        assert year > 2000;
        assert formation != null && !formation.equals("");
        ArrayList users = searchByPromo(formation, year, WOMEN, true);
        int res = users.size() > 0 ? Integer.parseInt((String) users.get(0)) : 0;
        return res;
    }

    /**
     * recherche les hommes ou les femmes
     * 
     * @param sex
     * @return
     * @throws SQLException 
     */
    public ArrayList<Utilisateur> findBySex(boolean sex) throws SQLException {
        Utilisateur usr = null;
        ResultSet res = null;
        ArrayList users = null;
        PreparedStatement pstm = null;
        Connection connect = null;

        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT COUNT(*) AS count_element FROM utilisateur u, promotion p, groupe g, formation f WHERE " + mandatory
                    + "u.formation_id=p.formation_id AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND u.sex=?" + limit;

            pstm = connect.prepareStatement(sql);
            pstm.setBoolean(1, sex);
            res = pstm.executeQuery();
            while (res.next()) {
                usr = buildUser(res);
                if (usr != null) {
                    users.add(usr);
                }
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    public int countAllPromo(int session) throws SQLException {
        assert session > 2000;
        ArrayList users = searchByPromo(null, session, BOTH, true);
        Integer res = (Integer) (users.size() > 0 ?  users.get(0) : 0);
        return res;
    }

    public ArrayList<Utilisateur> searchfAllPromo(int session) throws SQLException {
        assert session > 2000;
        ArrayList users = searchByPromo(null, session, BOTH, false);
        return users;
    }

    /**
     * construit la requete de recherche d'utilisateurs dans une promo et l'execute
     * 
     * @param formation String
     * @param session int
     * @param sex boolean
     * @return ArrayList<Utilisateur>
     * @throws SQLException 
     */
    private ArrayList searchByPromo(String formation, int session, int sex, boolean count) throws SQLException {
        
        Utilisateur usr = null;
        ResultSet res = null;
        ArrayList users = new ArrayList();
        PreparedStatement pstm = null;
        Connection connect = null;
 
        try {
            connect = TrombiConnection.getInstance();
            StringBuffer sql = new StringBuffer();
            if (!count) {
                sql.append("SELECT * ");
            } else {
                sql.append("SELECT COUNT(*) AS count_elements ");
            }
            
            sql.append("FROM utilisateur u, promotion p, formation f, groupe g WHERE ").append(this.mandatory).
                append(" u.login=p.login AND p.formation_id=f.formation_id AND g.groupe_nom=u.groupe_nom").
                append(" AND p.session=?");
  
            if (formation != null) {
                sql.append(" AND p.formation_id=?");
            }
           
            if (sex != BOTH) {
                sql.append(" AND sex =?");
            }
            if (!count) {
                sql.append(' ').append(limit);
            }
                 
            pstm = connect.prepareStatement(sql.toString());            
            pstm.setInt(1, session);
            int i=2;
            
            if(formation != null) {
                pstm.setString(i, formation);
                i++;
            }
            
            if (sex != BOTH) {
                boolean val = sex == MEN ? true : false;
                pstm.setBoolean(i, val);
            }
            res = pstm.executeQuery();

            if (!count) {
                while (res.next()) {
                    usr = buildUser(res);
                    if (usr != null) {
                        users.add(usr);
                    }
                }
            } else if(res.next()) {
                users.add((Integer) res.getInt("count_elements"));
            }
        } 
        finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    /**
     * Execute sql
     * 
     * @param sql 
     * @param tag 
     * @param count 
     * @return 
     * @throws SQLException 
     */
    private ArrayList<Utilisateur> searchByName(String sql, String tag, boolean count) throws SQLException {

        Utilisateur usr = null;
        ResultSet res = null;
        ArrayList users = new ArrayList();
        Connection connect = null;
        PreparedStatement pstm = null;

        try {
            connect = TrombiConnection.getInstance();
            pstm = connect.prepareStatement(sql);
            System.out.println(sql);
            pstm.setString(1, "%"+tag+"%");
            res = pstm.executeQuery();
            if (!count) {
                while (res.next()) {
                    usr = buildUser(res);
                    if (usr != null) {
                        users.add(usr);
                    }
                }
            } else {
                if(res.next()){
                users.add(res.getInt("count_element"));
                }
            }

        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }


    /**
     * Méthode de recherche par formation
     * <br/>Requiert<blockquote>
     *    formation_id != null && !formation_id.equals("")<br/>
     * 
     * return List<Utilisateur>
     * @param formation_id 
     * @return 
     * @throws SQLException 
     */
    public List<Utilisateur> list(String formation_id) throws SQLException {
        assert formation_id != null && !formation_id.equals("");
        PreparedStatement pstm = null;
        ResultSet res = null;
        ArrayList users = new ArrayList();
        Connection connect = null;
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * AS count_element FROM utilisateur u, promotion p, groupe g, formation f WHERE " + mandatory
                    + "u.formation_id=p.formation_id AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id AND u.formation_id=?" + limit;

            pstm = connect.prepareStatement(sql);
            pstm.setString(1, formation_id);
            res = pstm.executeQuery();
            while (res.next()) {
                users.add(buildUser(res));
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    /**
     * Retourne la list des utilisateurs present dans le tromi<br/>
     * pour limiter les resultats, il faut utiliser la fonction seLimi(min, max);
     * 
     * @return List<Utilisateur>
     * @throws SQLException  
     */
    public List<Utilisateur> list() throws SQLException {
        PreparedStatement pstm = null;
        ResultSet res = null;
        ArrayList users = new ArrayList();
        Connection connect = null;
        try {
            connect = TrombiConnection.getInstance();
            String sql = "SELECT * AS count_element FROM utilisateur u, promotion p, groupe g, formation f WHERE " + mandatory
                    + "u.formation_id=p.formation_id AND u.groupe_nom=g.groupe_nom AND p.formation_id=f.formation_id " + limit;

            pstm = connect.prepareStatement(sql);
            res = pstm.executeQuery();
            while (res.next()) {
                users.add(buildUser(res));
            }
        } finally {
            TrombiConnection.closeAll(connect, pstm, res);
        }

        return users;
    }

    /**
     * Construit un utililisateur a partir du resultat d'une requete
     * <br/>Requiert<blockquote>
     *    res != null;<br/></blockquote>
     * <br/>Assure<blockquote>
     *    Utilisateur != null;<br/></blockquote>
     * 
     * @param res ResultSet
     * @return Utilisateur
     * @throws SQLException 
     */
     private Utilisateur buildUser(ResultSet res) throws SQLException {
        assert res != null;
        Utilisateur usr = null;
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
        Formation formation = findUserPromo(usr.getLogin());
        usr.setFormation(formation);
        assert usr != null;
        return usr;
    }
}
