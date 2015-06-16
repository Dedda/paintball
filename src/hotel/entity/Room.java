package hotel.entity;

/**
 * @author stephan
 */
public class Room {
    
    private int id;
    private int people;
    private RoomCategory category;
    
    public Room() {
        
    }

    public Room(int id, int people, RoomCategory category) {
        this.id = id;
        this.people = people;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public int calculatePrice(final int days) {
        return category.getPrice() * days;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Room other = (Room) o;
        if (this.id != other.id) {
            return false;
        }
        if (this.people != other.people) {
            return false;
        }
        if (!this.category.equals(other.category)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Raum Nr. " + id;
    }
    
}
