package hotel.gui.model;

import hotel.entity.Room;
import java.util.List;
import javax.swing.AbstractListModel;

public class RoomListModel extends AbstractListModel<String>{

    private List<Room> room;

    public List<Room> getRooms() {
        return room;
    }

    public void setRooms(List<Room> room) {
        this.room = room;
    }
    
    public Room getRoomInLine(final int index) {
        return room.get(index);
    }
    
    @Override
    public int getSize() {
        return room.size();
    }

    @Override
    public String getElementAt(int index) {
        return room.get(index).getId() + "";
    }

}
