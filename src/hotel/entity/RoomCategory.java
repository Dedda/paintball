package hotel.entity;

public class RoomCategory {
    
    private int id;
    private String name;
    private int price;
    
    public RoomCategory() {
        
    }

    public RoomCategory(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        RoomCategory other = (RoomCategory)o;
        if (other.id != this.id) {
            return false;
        }
        if (!other.name.equals(this.name)) {
            return false;
        }
        if (other.price != this.price) {
            return false;
        }
        return true;
    }
    
}
