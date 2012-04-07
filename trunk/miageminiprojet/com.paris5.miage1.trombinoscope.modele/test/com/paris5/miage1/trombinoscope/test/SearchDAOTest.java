/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.test;

import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Groupe;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import java.util.GregorianCalendar;
import java.sql.Date;
import org.junit.Test;

/**
 * @author mourad
 */
public class SearchDAOTest {

    /**
     * Test of setLimit method, of class SearchDAO.
     */
    @Test
    public void testSetLimit() {
        
    }

    /**
     * Test of selectUser method, of class SearchDAO.
     */
    @Test
    public void testSelectUser() throws Exception {
    }

    /**
     * Test of findByEmail method, of class SearchDAO.
     */
    @Test
    public void testFindByEmail() throws Exception {
    }

    /**
     * Test of findByEtudiantId method, of class SearchDAO.
     */
    @Test
    public void testFindByEtudiantId() throws Exception {
    }

    /**
     * Test of findByPhone method, of class SearchDAO.
     */
    @Test
    public void testFindByPhone() throws Exception {
    }

    /**
     * Test of findByLastName method, of class SearchDAO.
     */
    @Test
    public void testFindByLastName() throws Exception {
    }

    /**
     * Test of findByFirstName method, of class SearchDAO.
     */
    @Test
    public void testFindByFirstName() throws Exception { 
    }

    /**
     * Test of countByLastName method, of class SearchDAO.
     */
    @Test
    public void testCountByLastName() throws Exception {
    }

    /**
     * Test of countByFirstName method, of class SearchDAO.
     */
    @Test
    public void testCountByFirstName() throws Exception {
    }

    /**
     * Test of findByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindByPromo() throws Exception {
    }

    /**
     * Test of findManByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindManByPromo() throws Exception {
    }

    /**
     * Test of findWomanByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindWomanByPromo() throws Exception {
    }

    /**
     * Test of countByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountByPromo() throws Exception {
    }

    /**
     * Test of countManByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountManByPromo() throws Exception {
    }

    /**
     * Test of countWomanByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountWomanByPromo() throws Exception {
    }

    /**
     * Test of findBySex method, of class SearchDAO.
     */
    @Test
    public void testFindBySex() throws Exception {
    }

    /**
     * Test of list method, of class SearchDAO.
     */
    @Test
    public void testList_String() throws Exception {
    }

    /**
     * Test of list method, of class SearchDAO.
     */
    @Test
    public void testList_0args() throws Exception {
    }

    /**
     * Test of find method, of class SearchDAO.
     */
    @Test
    public void testFind() throws Exception {
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
        
        usr.setFormation(new Formation(1,2012));
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
        usr.setFormation(new Formation(1,2012));
        usr.setGroupe(new Groupe("01JWGYNYQP6RUND5YCIPV2M0LAAGWWSPDJ96LVDY4U7050VS3U5A BH8IEWD 14A4WG2CQHR VOMEV9JCO2MG FRH7P1UKTC2T5F6II66HRAM9Q4SPQE8HMI63RIMG54MHBXL 653RFFX2SF3AG3B2HGPTWHFLQ3OCJR2XJTM8E1G63FF49QQYNTSNSCESHWOEBU4 YE7R8TDBQCIE 3I84YL6T3RPI8KYURCN DUP75W4 JJMAKRW5CVF6FOYB"));
        return usr;
    }
}
