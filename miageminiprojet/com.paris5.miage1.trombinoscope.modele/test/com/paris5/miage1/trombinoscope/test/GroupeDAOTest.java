/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.test;

import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Droit;
import com.paris5.miage1.trombinoscope.dao.GroupeDAO;
import com.paris5.miage1.trombinoscope.dao.TrombiFactory;
import com.paris5.miage1.trombinoscope.dao.TrombiDAO;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mourad
 */
public class GroupeDAOTest {
    
    /**
     * Test of create method, of class GroupeDAO.
     */
    @Test
    public void testCreate() throws Exception {
        TrombiDAO instance = TrombiFactory.getGroupeDAO();
        System.out.println("GroupeDAOTest testCreate()");
        try{
            assertEquals(true, instance.create(getDefault(true)));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("GroupeDAOTest instertion failed");
        }
    }

    /**
     * Test of update method, of class GroupeDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("GroupeDAOTest testUpdate()");
        TrombiDAO instance = TrombiFactory.getGroupeDAO();
        try{
            assertEquals(true, instance.update(getDefault(true)));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("GroupeDAOTest delete failed");
        }
    }

    /**
     * Test of find method, of class GroupeDAO.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("GroupeDAOTest testFind()");
        TrombiDAO instance = TrombiFactory.getGroupeDAO();
        
        try{
            List<Droit> l = instance.find(getDefault(false));
            assertNotNull(l);
            for(int i=0; i<l.size(); i++)
                System.out.println("\t"+l.get(i));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("GroupeDAOTest testFind failed");
        }
    }

    /**
     * Test of getDroits method, of class GroupeDAO.
     */
    @Test
    public void testGetDroits() throws Exception {
        System.out.println("GroupeDAOTest testGetDroits()");
        GroupeDAO instance = (GroupeDAO) TrombiFactory.getGroupeDAO();
        
        try{
            HashMap<String, String> l = (HashMap<String, String>) instance.getListeDroits();
            assertNotNull(l);
            System.out.println("\tnbElement="+l.size());
        }
        catch(SQLException e){
            System.out.println(e);
            fail("GroupeDAOTest testGetDroits failed");
        }
    }
    
    /**
     * Test of delete method, of class GroupeDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("GroupeDAOTest testDelete()");
        TrombiDAO instance = TrombiFactory.getGroupeDAO();
        try{
            assertEquals(true, instance.delete(getDefault(true)));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("GroupeDAOTest delete failed");
        }
    }

    private Groupe getDefault(boolean complet){
        //Groupe grp = new Groupe("01JWGYNYQP6RUND5YCIPV2M0LAAGWWSPDJ96LVDY4U7050VS3U5A BH8IEWD 14A4WG2CQHR VOMEV9JCO2MG FRH7P1UKTC2T5F6II66HRAM9Q4SPQE8HMI63RIMG54MHBXL 653RFFX2SF3AG3B2HGPTWHFLQ3OCJR2XJTM8E1G63FF49QQYNTSNSCESHWOEBU4 YE7R8TDBQCIE 3I84YL6T3RPI8KYURCN DUP75W4 JJMAKRW5CVF6FOYB");
        Groupe grp = new Groupe ("test");
        if(complet){
            ArrayList<Droit> d = new ArrayList<Droit>();
            d.add(new Droit("1HKJBY6SU72XR2J1MISR8US0SVK7BSOTR6T5D4VOJFXI4YBCNG FENLH0MEXP4MA7YYFED62I2LJG5W4EHRWVR1KVWG3QVOJ6NV WKS7HDMN5KS7PJJ AEL IIC3ITMDFBMWPC2A TPX2W3G8UY3QLHHYNTSLLWPKBDV34YKJ7Q2HNLRKVT5P6HS5RUMXKOE58H3S25BJMQ912R16D0M2P4RYJHJWVPFL1H9U7XV9FT8S8P8IFNH4LI73Y229IE",
                    "doit 1"));
            d.add(new Droit("43ISBYPVR25GJSV8C9I0PQ8J1J1RHLHGS PIY6EX  NSCX5BSAX3WG6TYEQEPMP GV1M39T7LU6CHFBNSQWL3H X1X U3LN7VROWIXUA0IG9MJXTTHUB3S5P BBRSE0J5HE7YBHA5LUTAROYP8W3KJ9JTNXWS7KVVMOE1QYVAJA71YFRPIVU485D2OL21G7M0JMDCUN82IHR746EHK6H7NOML1U1EU2RVF039J03LJQS4N5M3HYY8U BP3RNLDE"
                    ,"droit 2"));
            grp.setDroits(d);
            grp.setDescription("test droits");
        }
        return grp;
    }
}
