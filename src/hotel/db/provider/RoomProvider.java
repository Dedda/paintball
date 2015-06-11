package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.RoomCategory;
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

public class RoomProvider {
    
    public List<Room> getAll() {
        Connection connection = getConnection();
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms_with_category";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                int people = resultSet.getInt("people");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                int price = resultSet.getInt("price");
                rooms.add(new Room(roomId, people, new RoomCategory(categoryId, categoryName, price)));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return rooms;
    }
    
    public Map<Integer, Boolean> getFreeDays(final Room room) {
        Connection connection = getConnection();
        String query = "SELECT * FROM room_reservation WHERE room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, room.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public Room getForId(final int roomId) {
        Connection connection = getConnection();
        Room room = null;
        String query = "SELECT * FROM rooms_with_category WHERE room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int people = resultSet.getInt("people");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                int price = resultSet.getInt("price");
                room = new Room(roomId, people, new RoomCategory(categoryId, categoryName, price));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return room;
    }
    
    public List<Room> getFree(final Date startDate, final Date endDate) {
        List<Room> allRooms = getAll();
        List<Room> freeRooms = new ArrayList<>();
        freeRooms = allRooms.stream().filter(room -> isFree(room, startDate, endDate)).collect(toList());
        return freeRooms;
    }
    
    public boolean isFree(final Room room, final Date startDate, final Date endDate) {
        boolean free = true;
        List<Reservation> reservations = getReservationsForRoom(room.getId());
        for (Reservation reservation : reservations) {
            if (reservation.getStart().after(startDate) && reservation.getStart().before(endDate)) {
                free = false;
                break;
            }
            if (reservation.getEnd().after(startDate) && reservation.getEnd().before(endDate)) {
                free = false;
                break;
            }
            if (reservation.getStart().before(startDate) && reservation.getEnd().after(endDate)) {
                free = false;
                break;
            }
        }
        return free;
    }
    
    public List<Reservation> getReservationsForRoom(
            final int roomId) {
        ReservationProvider reservationProvider = new ReservationProvider();
        Connection connection = getConnection();
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations_for_room WHERE room_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservatino_id");
                Reservation reservation 
                        = reservationProvider.getForId(reservationId);
                reservations.add(reservation);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public List<Room> getForReservation(final int reservationId) {
        List<Room> rooms = new ArrayList<>();
        Connection connection = getConnection();
        String query = "SELECT * FROM reservations_for_room WHERE reservation_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                Room room = getForId(roomId);
                rooms.add(room);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return rooms;
    }
    
    public void setForReservation(final Reservation reservation, final List<Room> rooms) {
        removeAllFromReservation(reservation.getId());
        rooms.forEach(room -> addRoomToReservation(reservation.getId(), room.getId()));
    }
    
    private void removeAllFromReservation(final int reservationId) {
        Connection connection = DBUtil.getConnection();
        String query = "DELETE FROM room_reservation WHERE reservation = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addRoomToReservation(final int reservationId, final int roomId) {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO room_reservation(reservation, room) VALUES(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            statement.setInt(2, roomId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
