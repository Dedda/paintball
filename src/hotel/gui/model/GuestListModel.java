package hotel.gui.model;

import hotel.entity.Guest;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * @author stephan
 */
public class GuestListModel extends AbstractListModel<String>{

    private List<Guest> guests;

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    
    public Guest getGuestInLine(final int index) {
        return guests.get(index);
    }
    
    @Override
    public int getSize() {
        return guests.size();
    }

    @Override
    public String getElementAt(int index) {
        return guests.get(index).getName() + " " + guests.get(index).getSurname();
    }

}
