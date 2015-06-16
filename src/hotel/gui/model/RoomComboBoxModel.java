package hotel.gui.model;

import hotel.entity.Room;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * @author stephan
 */
public class RoomComboBoxModel extends AbstractListModel<Room> implements ComboBoxModel<Room>{

    private List<Room> rooms;
    private int selected;
    
    @Override
    public int getSize() {
        return rooms.size();
    }

    @Override
    public Room getElementAt(int index) {
        return rooms.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        Room room = (Room) anItem;
        for (int i = 0; i < rooms.size(); i++) {
            if (room.equals(rooms.get(i))) {
                selected = i;
                return;
            }
        }
    }

    @Override
    public Room getSelectedItem() {
        return rooms.get(selected);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
