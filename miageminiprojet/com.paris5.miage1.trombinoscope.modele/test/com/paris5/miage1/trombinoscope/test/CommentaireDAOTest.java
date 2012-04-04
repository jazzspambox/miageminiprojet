/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris5.miage1.trombinoscope.test;

import com.paris5.miage1.trombinoscope.dao.TrombiFactory;
import com.paris5.miage1.trombinoscope.dao.TrombiDAO;
import com.paris5.miage1.trombinoscope.bean.Commentaire;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mourad
 */
public class CommentaireDAOTest {

    /**
     * Test of create method, of class CommentaireDAO.
     */
    @Test
    public void testCreate() throws Exception {
        Commentaire com = getDefault();
        TrombiDAO instance = TrombiFactory.getCommentaireDAO();
        System.out.println("CommentaireDAOTest testCreate()");
        try{
            assertEquals(true, instance.create(com));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("commentaire instertion failed");
        }
    }

    /**
     * Test of update method, of class CommentaireDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("CommentaireDAOTest testUpdate()");
        TrombiDAO instance = TrombiFactory.getCommentaireDAO();
        try{
            assertEquals(true, instance.update(getDefault()));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("commentaire instertion failed");
        }
    }

    /**
     * Test of find method, of class CommentaireDAO.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("UtilisateurDAOTest testFind()");
        TrombiDAO instance = TrombiFactory.getCommentaireDAO();
        
        try{
            List<Commentaire> l = instance.find(getDefault());
            assertNotNull(l);
            for(int i=0; i<l.size(); i++)
                System.out.println("\t commentaire id="+l.get(i).getId()+" : " +l.get(i).getCommentaire());
        }
        catch(SQLException e){
            System.out.println(e);
            fail("commentaire search failed");
        }
    }

        /**
     * Test of delete method, of class CommentaireDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("CommentaireDAOTest testDelete()");
        TrombiDAO instance = TrombiFactory.getCommentaireDAO();
        try{
            assertEquals(true, instance.delete(getDefault()));
        }
        catch(SQLException e){
            System.out.println(e);
            fail("commentaire delete failed");
        }
    }

    private Commentaire getDefault() {
        Date d = new java.sql.Date((new GregorianCalendar(2001, 0, 1)).getTimeInMillis());
        return new Commentaire(1, 
                "XKR8Q8PDRP4 AT ADC8WM5C2OA5B4UJRCHQWFK7HCVP8FKBXROGJ6F87DCFKANDY061MWPK3AHGMGY 85EOCTUOWXHLSC6UX0IBQCHK1BLE88KAI73MFD3T 0VAEDF5GEOB6AIP62KQR3U8XCI4H4NFC80BBV73A32 BO42F21UCNYHT0Q7OOTXW2PJ Q469YFG7VJFPSW6R1XCIG51S5MGMX 4D7JI6QRX9ON80JDG8QISBXEY00WQG36911LW",
                "0363NW6R3796G CRN44035 6R TXP27SIM PY8GIE26KLSWAGU8YE WLLQUX5EJNB57YKLK56BWRNJ1QMYQB0NGYVHUV7BS 12SBHATW1609DHBJ5P3XIGS55UD9QKUE0JP20S3AHI3KO XIT QKUYKGR 3GT43XH9XGNOUDQVSXTJIW7F9Y7UYLUSMRU2I BMJO8Q KTFRNCVAAN75O0 NPARODHNJGBQKPWV0NI2QV0S0S99171CXAT4YEOC6", 
                "test dev", 
                d);
    }
}
