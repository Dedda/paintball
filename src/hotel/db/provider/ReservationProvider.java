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

    private GuestProvider guestProvider = new GuestProvider();
    
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM reservation";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            GuestProvider guestProvider = new GuestProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int guestId = resultSet.getInt("guest");
                Guest guest = guestProvider.getForId(guestId);
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                reservations.add(new Reservation(id, start, end, guest, additionalInfo, payed, canceled));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public Reservation getForId(final int id) {
        Reservation reservation = null;
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM reservation WHERE id=" + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            reservation = new Reservation();
            reservation.setId(id);
            reservation.setAdditionalInfo(
                    resultSet.getString("additional_info"));
            reservation.setCanceled(
                    resultSet.getDate("canceled"));
            reservation.setEnd(resultSet.getDate("end_date"));
            reservation.setGuest(
                    guestProvider.getForId(
                        resultSet.getInt("guest")));
            reservation.setPayed(
                    resultSet.getDate("payed"));
            reservation.setStart(resultSet.getDate("start_date"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservation;
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
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                reservations.add(new Reservation(id, start, end, guest, additionalInfo, payed, canceled));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public List<Reservation> getOpenForGuest(final Guest guest) {
        List<Reservation> reservations = new ArrayList<>();
        final int guestId = guest.getId();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM open_reservations WHERE guest=" + guestId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                reservations.add(new Reservation(id, start, end, guest, additionalInfo, payed, canceled));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public void guestRemoved(final Guest guest) {
        List<Reservation> reservations = getForGuest(guest);
        Connection connection = DBUtil.getConnection();
        String query = "UPDATE reservation SET guest=1 WHERE guest=" + guest.getId();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
