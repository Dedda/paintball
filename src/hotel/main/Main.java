package hotel.main;

import hotel.db.provider.StaffProvider;
import hotel.gui.MainMenuFrame;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new StaffProvider().removeTooOld();
        new MainMenuFrame().setVisible(true);
    }
}
