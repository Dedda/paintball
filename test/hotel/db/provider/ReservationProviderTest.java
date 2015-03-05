/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.db.provider;

import hotel.entity.Guest;
import hotel.entity.Reservation;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dedda
 */
public class ReservationProviderTest {
    
    private ReservationProvider instance;
    
    @Before
    public void setUp() {
        instance = new ReservationProvider();
    }

    /**
     * Test of getAll method, of class ReservationProvider.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ReservationProvider instance = new ReservationProvider();
        List<Reservation> expResult = null;
        List<Reservation> result = instance.getAll();
        //assert that the
    }

    /**
     * Test of getForGuest method, of class ReservationProvider.
     */
    @Test
    public void testGetForGuest() {
        System.out.println("getForGuest");
        Guest guest = null;
        ReservationProvider instance = new ReservationProvider();
        List<Reservation> expResult = null;
        List<Reservation> result = instance.getForGuest(guest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
