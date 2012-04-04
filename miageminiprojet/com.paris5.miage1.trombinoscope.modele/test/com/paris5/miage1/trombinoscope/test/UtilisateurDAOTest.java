/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.test;

import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.dao.UtilisateurDAO;
import com.paris5.miage1.trombinoscope.bean.Commentaire;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import com.paris5.miage1.trombinoscope.dao.TrombiFactory;
import com.paris5.miage1.trombinoscope.dao.TrombiDAO;
import java.util.List;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.sql.Date;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mourad nedjahi, Saliou Mamadou Barry, Idir Belamiri
 */
public class UtilisateurDAOTest {
    
    /**
     * Test of create method, of class UtilisateurDAO.
     */
    @Test
    public void testCreate() throws Exception {       
        TrombiDAO instance = TrombiFactory.getUtilisateurDAO();
        System.out.println("UtilisateurDAOTest testCreate()");
        try{
            assertEquals(true, instance.create(getDefault()));
            assertEquals(true, instance.create(getMini()));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("User instertion failed");
        }
    }
    
    /**
     * Test of update method, of class UtilisateurDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("UtilisateurDAOTest testUpdate()");
        Utilisateur usr = getDefault();
        
        try{
            TrombiDAO instance = TrombiFactory.getUtilisateurDAO();
            usr.setNom("idir");
            assertEquals(true, instance.update(usr));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("User "+usr.getCreateurLogin()+" delete failed");
        }
    }

    /**
     * Test of find method, of class UtilisateurDAO.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("UtilisateurDAOTest testFind()");
        TrombiDAO instance = TrombiFactory.getUtilisateurDAO();
        
        try{
            Utilisateur expResult = this.getDefault();
            List<Commentaire> l = instance.find(getDefault());
            assertNotNull(l);
            for(int i=0; i<l.size(); i++)
                System.out.println(l.get(i));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("User mourad find failed");
        }
    }
    
    /**
     * Test of delete method, of class UtilisateurDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("UtilisateurDAOTest testDelete()");       
        try{
            UtilisateurDAO instance = new UtilisateurDAO();
            //assertEquals(true, instance.delete(getDefault()));
            assertEquals(true, instance.delete(getMini()));
        }
        catch(SQLException e) {
            System.out.println(e);
            fail("User delete failed");
        }
    }
    
    /**
     * un utilisateur de test
     * @return Utilisateur
     */
    private Utilisateur getDefault(){
        Utilisateur usr=null;
        usr = new Utilisateur();
        usr.setLogin("mourad");
        usr.setCreateurLogin("XKR8Q8PDRP4 AT ADC8WM5C2OA5B4UJRCHQWFK7HCVP8FKBXROGJ6F87DCFKANDY061MWPK3AHGMGY 85EOCTUOWXHLSC6UX0IBQCHK1BLE88KAI73MFD3T 0VAEDF5GEOB6AIP62KQR3U8XCI4H4NFC80BBV73A32 BO42F21UCNYHT0Q7OOTXW2PJ Q469YFG7VJFPSW6R1XCIG51S5MGMX 4D7JI6QRX9ON80JDG8QISBXEY00WQG36911LW");
        usr.setPassword("1234");
        usr.setEmail("test@test.fr");
        usr.setNom("mourad");
        usr.setPrenom("nedjahi");
        usr.setTelephone("0102030405");
        usr.setMobile("060504030201");
        usr.setSex(true);
        usr.setActive(true);
            Date d = new java.sql.Date((new GregorianCalendar(2001, 0, 1)).getTimeInMillis());
        usr.setDateCreation(d);
        usr.setPhoto("mini_fenec.jpeg");
        
        usr.setFormation(new Formation(1));
        usr.setGroupe(new Groupe("01JWGYNYQP6RUND5YCIPV2M0LAAGWWSPDJ96LVDY4U7050VS3U5A BH8IEWD 14A4WG2CQHR VOMEV9JCO2MG FRH7P1UKTC2T5F6II66HRAM9Q4SPQE8HMI63RIMG54MHBXL 653RFFX2SF3AG3B2HGPTWHFLQ3OCJR2XJTM8E1G63FF49QQYNTSNSCESHWOEBU4 YE7R8TDBQCIE 3I84YL6T3RPI8KYURCN DUP75W4 JJMAKRW5CVF6FOYB"));
        return usr;
    }
    
    /**
     * 
     * @return 
     */
    private Utilisateur getMini(){
        Utilisateur usr=null;usr = new Utilisateur();
        usr.setLogin("saliou");
        usr.setCreateurLogin("XKR8Q8PDRP4 AT ADC8WM5C2OA5B4UJRCHQWFK7HCVP8FKBXROGJ6F87DCFKANDY061MWPK3AHGMGY 85EOCTUOWXHLSC6UX0IBQCHK1BLE88KAI73MFD3T 0VAEDF5GEOB6AIP62KQR3U8XCI4H4NFC80BBV73A32 BO42F21UCNYHT0Q7OOTXW2PJ Q469YFG7VJFPSW6R1XCIG51S5MGMX 4D7JI6QRX9ON80JDG8QISBXEY00WQG36911LW");
        usr.setPassword("1234");
        usr.setEmail("test@test.fr");
        
        usr.setFormation(new Formation(1));
        usr.setGroupe(new Groupe("01JWGYNYQP6RUND5YCIPV2M0LAAGWWSPDJ96LVDY4U7050VS3U5A BH8IEWD 14A4WG2CQHR VOMEV9JCO2MG FRH7P1UKTC2T5F6II66HRAM9Q4SPQE8HMI63RIMG54MHBXL 653RFFX2SF3AG3B2HGPTWHFLQ3OCJR2XJTM8E1G63FF49QQYNTSNSCESHWOEBU4 YE7R8TDBQCIE 3I84YL6T3RPI8KYURCN DUP75W4 JJMAKRW5CVF6FOYB"));
        return usr;
    }
}