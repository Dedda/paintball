package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.entity.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationProvider {

    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM reservation";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            RoomProvider roomProvider = new RoomProvider();
            GuestProvider guestProvider = new GuestProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int guestId = resultSet.getInt("guest");
                Guest guest = guestProvider.getForId(guestId);
                int roomId = resultSet.getInt("room");
                Room room = roomProvider.getForId(roomId);
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                reservations.add(new Reservation(id, start, end, guest, room, additionalInfo, payed, canceled));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public List<Reservation> getForGuest(final Guest guest) {
        List<Reservation> reservations = new ArrayList<>();
        final int guestId = guest.getId();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM reservation WHERE guest=" + guestId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            RoomProvider roomProvider = new RoomProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int roomId = resultSet.getInt("room");
                Room room = roomProvider.getForId(roomId);
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                reservations.add(new Reservation(id, start, end, guest, room, additionalInfo, payed, canceled));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
}
