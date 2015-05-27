package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            RoomProvider roomProvider = new RoomProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int guestId = resultSet.getInt("guest");
                int people = resultSet.getInt("people");
                Guest guest = guestProvider.getForId(guestId);
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                List<Integer> rooms = toIds(roomProvider.getForReservation(id));
                reservations.add(new Reservation(id, start, end, guest, people, rooms, additionalInfo, payed, canceled));
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
            RoomProvider roomProvider = new RoomProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int people = resultSet.getInt("people");
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                List<Integer> rooms = toIds(roomProvider.getForReservation(id));
                reservations.add(new Reservation(id, start, end, guest, people, rooms, additionalInfo, payed, canceled));
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
            RoomProvider roomProvider = new RoomProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date start = resultSet.getDate("start_date");
                Date end = resultSet.getDate("end_date");
                int people = resultSet.getInt("people");
                String additionalInfo = resultSet.getString("additional_info");
                Date payed = resultSet.getDate("payed");
                Date canceled = resultSet.getDate("canceled");
                List<Integer> rooms = toIds(roomProvider.getForReservation(id));
                reservations.add(new Reservation(id, start, end, guest, people, rooms, additionalInfo, payed, canceled));
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
    
    public int save(final Reservation reservation) {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO reservation(additional_info, guest, people, start_date, end_date) "
                + "VALUES('"
                + reservation.getAdditionalInfo() + "', '"
                + reservation.getGuest().getId() + "', '"
                + reservation.getPeople() + "', '"
                + format(reservation.getStart()) + "', '"
                + format(reservation.getEnd())
                + "')";
        Statement statement;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(query);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt("id"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        if (!updateRoomAssociations(reservation)) {
            return -2;
        }
        return reservation.getId();
    }
    
    public boolean merge(final Reservation reservation) {
        Connection connection = DBUtil.getConnection();
        String query = "UPDATE reservation SET"
                + " additional_info='" + reservation.getAdditionalInfo()
                + "', guest='" + reservation.getGuest().getId()
                + "', people='" + reservation.getPeople()
                + "', start_date='" + format(reservation.getStart())
                + "', end_date='" + format(reservation.getEnd())
                + (reservation.isCanceled() ? ("', canceled='" + format(reservation.getCanceled())) : "")
                + (reservation.isPayed() ? ("', payed='" + format(reservation.getPayed())) : "")
                + "' WHERE id = " + reservation.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return updateRoomAssociations(reservation);
    }
    
    private boolean updateRoomAssociations(final Reservation reservation) {
        if (!clearRooms(reservation)) {
            return false;
        }
        Connection connection = DBUtil.getConnection();
        try {
            for (int room : reservation.getRooms()) {
                Statement statement = connection.createStatement();
                String query = "INSERT INTO room_reservation(room, reservation) VALUES('"
                        + room + "', '" + reservation.getId()
                        + "')";
                statement.executeUpdate(query);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean clearRooms(final Reservation reservation) {
        Connection connection = DBUtil.getConnection();
        String query = "DELETE FROM room_reservation WHERE reservation=" + reservation.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public int getTotal(final Reservation reservation) {
        RoomProvider roomProvider = new RoomProvider();
        int roomPrice = 
            roomProvider.getForReservation(
                reservation.getId()).stream().parallel().mapToInt(
                    room -> room.calculatePrice(reservation.getDays())).sum();
        ServiceProvider serviceProvider = new ServiceProvider();
        int servicePrice = 
            serviceProvider.getForReservation(
                reservation).stream().parallel().mapToInt(
                        Service::getPrice).sum();
        return roomPrice + servicePrice;
    }
    
    private String format(final Date date) {
        return new SimpleDateFormat("yy-MM-dd").format(date);
    }
    
    private List<Integer> toIds(final List<Room> rooms) {
        return rooms.stream().map(room -> room.getId()).collect(Collectors.toList());
    }
}
