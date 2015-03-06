package hotel.db.provider;

import hotel.entity.Guest;
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
public class GuestProviderTest {
    
    private GuestProvider instance;
    
    @Before
    public void setUp() {
        instance = new GuestProvider();
    }
    
    /**
     * Test of getForId method, of class GuestProvider.
     */
    @Test
    public void testGetForId() {
        int id = 1;
        Guest result = instance.getForId(id);
        assertEquals("Nicht", result.getName());
        assertEquals("Vorhanden", result.getSurname());
    }

    /**
     * Test of getForName method, of class GuestProvider.
     */
    @Test
    public void testGetForName() {
        String name = "Nicht";
        String nameComplete = "Nicht Vorhanden";
        Guest result = instance.getForName(name);
        Guest resultComplete = instance.getForName(nameComplete);
        assertEquals("Nicht", result.getName());
        assertEquals("Vorhanden", result.getSurname());
        assertEquals("Nicht", resultComplete.getName());
        assertEquals("Vorhanden", resultComplete.getSurname());
    }

    /**
     * Test of getForNameLike method, of class GuestProvider.
     */
    @Test
    public void testGetForNameLike() {
        String name = "ich";
        String nameComplete = "icht Vorh";
        List<Guest> result = instance.getForNameLike(name);
        List<Guest> resultComplete = instance.getForNameLike(nameComplete);
        boolean exists = false;
        for (Guest guest : result) {
            if (guest.getName().equals("Nicht") && guest.getSurname().equals("Vorhanden")) {
                exists = true;
            }
        }
        assertTrue(exists);
        exists = false;
        for (Guest guest : resultComplete) {
            if (guest.getName().equals("Nicht") && guest.getSurname().equals("Vorhanden")) {
                exists = true;
            }
        }
        assertTrue(exists);
    }
    
}
