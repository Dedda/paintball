/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.db.provider;

import hotel.entity.Staff;
import hotel.entity.StaffCategory;
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
public class StaffProviderTest {
    
    private StaffProvider instance;
    
    @Before
    public void setUp() {
        instance = new StaffProvider();
    }
    
    /**
     * Test of getAll method, of class StaffProvider.
     */
    @Test
    public void testGetAll() {
        StaffProvider instance = new StaffProvider();
        List<Staff> result = instance.getAll();
        //assert that the default staff exists
        boolean exists = false;
        for (Staff staff : result) {
            if (staff.getName().equals("Nicht") && staff.getName().equals("Vorhanden")) {
                exists = true;
            }
        }
        assertTrue(exists);
    }
    
}
