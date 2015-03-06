package hotel.db.provider;

import hotel.entity.Staff;
import java.util.List;
import org.junit.Before;
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
            if (staff.getName().equals("Nicht") && staff.getSurname().equals("Vorhanden")) {
                exists = true;
            }
        }
        assertTrue(exists);
    }
    
}
