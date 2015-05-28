package hotel.main;

import hotel.db.provider.StaffProvider;
import hotel.gui.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        new StaffProvider().removeTooOld();
        new MainMenuFrame().setVisible(true);
    }
}
