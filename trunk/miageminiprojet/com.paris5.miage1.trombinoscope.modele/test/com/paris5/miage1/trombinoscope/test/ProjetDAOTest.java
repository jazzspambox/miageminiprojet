/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.test;

import com.paris5.miage1.trombinoscope.bean.Projet;
import com.paris5.miage1.trombinoscope.dao.ProjetDAO;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mourad
 */
public class ProjetDAOTest {
    
    /**
     * Test of create method, of class ProjetDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Projet obj = null;
        ProjetDAO instance = new ProjetDAO();
        boolean expResult = false;
        boolean result = instance.create(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ProjetDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Projet obj = null;
        ProjetDAO instance = new ProjetDAO();
        boolean expResult = false;
        boolean result = instance.delete(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ProjetDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Projet obj = null;
        ProjetDAO instance = new ProjetDAO();
        boolean expResult = false;
        boolean result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class ProjetDAO.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Projet prg = null;
        ProjetDAO instance = new ProjetDAO();
        List expResult = null;
        List result = instance.find(prg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of 
     */
    @Test
    private Projet getDefault(){
        Projet p = new Projet(
                0,
                "test",
                3,
                "ceci est un test",
                new java.sql.Date((new GregorianCalendar(2001, 0, 1)).getTimeInMillis()),
                new java.sql.Date((new GregorianCalendar(2002, 0, 1)).getTimeInMillis())
              );
        return p;
    }
}
