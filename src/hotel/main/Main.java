package hotel.main;

import hotel.db.provider.GuestProvider;
import hotel.entity.Guest;
import hotel.gui.GuestListFrame;

public class Main {
    public static void main(String[] args) {
        GuestProvider provider = new GuestProvider();
        new GuestListFrame().setVisible(true);
    }
}
