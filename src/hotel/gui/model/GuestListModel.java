package hotel.gui.model;

import hotel.entity.Guest;
import java.util.List;
import javax.swing.AbstractListModel;

public class GuestListModel extends AbstractListModel<Guest>{

    private List<Guest> guests;

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    
    @Override
    public int getSize() {
        return guests.size();
    }

    @Override
    public Guest getElementAt(int index) {
        return guests.get(index);
    }

}
