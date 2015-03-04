package hotel.main;

import hotel.db.provider.GuestProvider;
import hotel.entity.Guest;

public class Main {
    public static void main(String[] args) {
        GuestProvider provider = new GuestProvider();
        for (Guest g : provider.getAll()) {
            
        }
    }
}
