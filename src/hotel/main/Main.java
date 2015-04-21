package hotel.main;

import hotel.db.provider.ServiceProvider;
import hotel.entity.Reservation;
import hotel.gui.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        new MainMenuFrame().setVisible(true);
        //System.out.println(new ServiceProvider().getForReservation(new Reservation(1, null, null, null, null, null, null, null)));
    }
}
