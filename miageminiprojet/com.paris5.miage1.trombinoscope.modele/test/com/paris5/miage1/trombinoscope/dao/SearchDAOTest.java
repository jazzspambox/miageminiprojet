/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.dao;

import com.paris5.miage1.trombinoscope.bean.Formation;
import com.paris5.miage1.trombinoscope.bean.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Saliou
 */
public class SearchDAOTest {
    
    public SearchDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setLimit method, of class SearchDAO.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        int min = 0;
        int max = 0;
        SearchDAO instance = new SearchDAO();
        instance.setLimit(min, max);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByLoginPassword method, of class SearchDAO.
     */
    @Test
    public void testFindByLoginPassword() throws Exception {
        System.out.println("findByLoginPassword");
        String login = "";
        String pwd = "";
        SearchDAO instance = new SearchDAO();
        Utilisateur expResult = null;
        Utilisateur result = instance.findByLoginPassword(login, pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserPromo method, of class SearchDAO.
     */
    @Test
    public void testFindUserPromo() throws Exception {
        System.out.println("findUserPromo");
        String login = "";
        SearchDAO instance = new SearchDAO();
        Formation expResult = null;
        Formation result = instance.findUserPromo(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEmail method, of class SearchDAO.
     */
    @Test
    public void testFindByEmail() throws Exception {
        System.out.println("findByEmail");
        String email = "";
        int formation_id = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByEmail(email, formation_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEtudiantId method, of class SearchDAO.
     */
    @Test
    public void testFindByEtudiantId() throws Exception {
        System.out.println("findByEtudiantId");
        String id = "";
        int formation_id = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByEtudiantId(id, formation_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPhone method, of class SearchDAO.
     */
    @Test
    public void testFindByPhone() throws Exception {
        System.out.println("findByPhone");
        String phone = "";
        boolean cellular = false;
        int formation_id = 0;
        boolean count = false;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByPhone(phone, cellular, formation_id, count);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByLastName method, of class SearchDAO.
     */
    @Test
    public void testFindByLastName() throws Exception {
        System.out.println("findByLastName");
        String name = "";
        int formation_id = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByLastName(name, formation_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByFirstName method, of class SearchDAO.
     */
    @Test
    public void testFindByFirstName() throws Exception {
        System.out.println("findByFirstName");
        String name = "";
        int formation_id = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByFirstName(name, formation_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countByLastName method, of class SearchDAO.
     */
    @Test
    public void testCountByLastName() throws Exception {
        System.out.println("countByLastName");
        String name = "";
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countByLastName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countByFirstName method, of class SearchDAO.
     */
    @Test
    public void testCountByFirstName() throws Exception {
        System.out.println("countByFirstName");
        String name = "";
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countByFirstName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindByPromo() throws Exception {
        System.out.println("findByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findManByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindManByPromo() throws Exception {
        System.out.println("findManByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findManByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findWomanByPromo method, of class SearchDAO.
     */
    @Test
    public void testFindWomanByPromo() throws Exception {
        System.out.println("findWomanByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findWomanByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountByPromo() throws Exception {
        System.out.println("countByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countManByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountManByPromo() throws Exception {
        System.out.println("countManByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countManByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countWomanByPromo method, of class SearchDAO.
     */
    @Test
    public void testCountWomanByPromo() throws Exception {
        System.out.println("countWomanByPromo");
        String formation = "";
        int year = 0;
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countWomanByPromo(formation, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBySex method, of class SearchDAO.
     */
    @Test
    public void testFindBySex() throws Exception {
        System.out.println("findBySex");
        boolean sex = false;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        ArrayList result = instance.findBySex(sex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countAllPromo method, of class SearchDAO.
     */
    @Test
    public void testCountAllPromo() throws Exception {
        System.out.println("countAllPromo");
        int session = 2012;
        SearchDAO instance = new SearchDAO();
        int expResult = 0;
        int result = instance.countAllPromo(session);
        assertTrue(result>0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of searchfAllPromo method, of class SearchDAO.
     */
    @Test
    public void testSearchfAllPromo() throws Exception {
        System.out.println("searchfAllPromo");
        int session = 2012;
        SearchDAO instance = new SearchDAO();
        ArrayList expResult = null;
        instance.setLimit(0, 40);
        ArrayList result = instance.searchfAllPromo(session);
        assertTrue(result.size()>0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of list method, of class SearchDAO.
     */
    @Test
    public void testList_String() throws Exception {
        System.out.println("list");
        String formation_id = "";
        SearchDAO instance = new SearchDAO();
        List expResult = null;
        List result = instance.list(formation_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class SearchDAO.
     */
    @Test
    public void testList_0args() throws Exception {
        System.out.println("list");
        SearchDAO instance = new SearchDAO();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
