package hotel.entity;

public class Room {
    
    private int id;
    private RoomCategory category;
    
    public Room() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }
    
}
