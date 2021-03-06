package hotel.db.provider;

import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hotel.db.DBUtil.*;
import java.util.Map;
import static java.util.stream.Collectors.toList;

/**
 * @author stephan
 */
public class ReservationProvider {

    private GuestProvider guestProvider = new GuestProvider();
    
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = getConnection();
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
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public Reservation getForId(final int id) {
        Reservation reservation = null;
        Connection connection = getConnection();
        String query = "SELECT * FROM reservation WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            reservation = new Reservation();
            reservation.setId(id);
            if (resultSet.next()) {
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
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservation;
    }
    
    public List<Reservation> getForGuest(final Guest guest) {
        List<Reservation> reservations = new ArrayList<>();
        final int guestId = guest.getId();
        Connection connection = getConnection();
        String query = "SELECT * FROM reservation WHERE guest = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, guestId);
            ResultSet resultSet = statement.executeQuery();
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
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public List<Reservation> getOpenForGuest(final Guest guest) {
        List<Reservation> reservations = new ArrayList<>();
        final int guestId = guest.getId();
        Connection connection = getConnection();
        String query = "SELECT * FROM open_reservations WHERE guest = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, guestId);
            ResultSet resultSet = statement.executeQuery();
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
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public void guestRemoved(final Guest guest) {
        List<Reservation> reservations = getForGuest(guest);
        Connection connection = getConnection();
        String query = "UPDATE reservation SET guest=1 WHERE guest = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, guest.getId());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int save(final Reservation reservation) {
        Connection connection = getConnection();
        String query = "INSERT INTO reservation(additional_info, guest, people, start_date, end_date) "
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, reservation.getAdditionalInfo());
            statement.setInt(2, reservation.getGuest().getId());
            statement.setInt(3, reservation.getPeople());
            statement.setDate(4, new java.sql.Date(reservation.getStart().getTime()));
            statement.setDate(5, new java.sql.Date(reservation.getEnd().getTime()));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId((int) generatedKeys.getLong(1));
            }
            returnConnection(connection);
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
        Connection connection = getConnection();
        String query = "UPDATE reservation SET"
                + " additional_info = ?"
                + ", guest = ?"
                + ", people = ?"
                + ", start_date = ?"
                + ", end_date = ?"
                + (reservation.isCanceled() ? ", canceled = ?" : "")
                + (reservation.isPayed() ? ", payed = ?" : "")
                + " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            int i = 0;
            statement.setString(++i, reservation.getAdditionalInfo());
            statement.setInt(++i, reservation.getGuest().getId());
            statement.setInt(++i, reservation.getPeople());
            statement.setDate(++i, new java.sql.Date(reservation.getStart().getTime()));
            statement.setDate(++i, new java.sql.Date(reservation.getEnd().getTime()));
            if (reservation.isCanceled()) {
                statement.setDate(++i, new java.sql.Date(reservation.getCanceled().getTime()));
            }
            if (reservation.isPayed()) {
                statement.setDate(++i, new java.sql.Date(reservation.getPayed().getTime()));
            }
            statement.setInt(++i, reservation.getId());
            statement.executeUpdate();
            returnConnection(connection);
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
        Connection connection = getConnection();
        try {
            for (int room : reservation.getRooms()) {
                String query = "INSERT INTO room_reservation(room, reservation) VALUES(?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, room);
                statement.setInt(2, reservation.getId());
                statement.executeUpdate();
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean clearRooms(final Reservation reservation) {
        Connection connection = getConnection();
        String query = "DELETE FROM room_reservation WHERE reservation = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getId());
            statement.executeUpdate();
            returnConnection(connection);
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
        Map<Service, Integer> services = serviceProvider.getForReservation(reservation);
        List<Integer> servicePrices = new ArrayList<>(services.size());
        services.keySet().forEach(s -> servicePrices.add(s.getPrice() * services.get(s)));
        int servicePrice = servicePrices.stream().mapToInt(i -> i).sum();
        return roomPrice + servicePrice;
    }
    
    private List<Integer> toIds(final List<Room> rooms) {
        return rooms.stream().map(room -> room.getId()).collect(toList());
    }
}
