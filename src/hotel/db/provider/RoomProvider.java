package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.RoomCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomProvider {

    public List<Room> getAll() {
        Connection connection = DBUtil.getConnection();
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return rooms;
    }

    public Room getForId(final int roomId) {
        Connection connection = DBUtil.getConnection();
        Room room = null;
        String query = "SELECT * FROM rooms_with_category WHERE room_id=" + roomId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int people = resultSet.getInt("people");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                int price = resultSet.getInt("price");
                room = new Room(roomId, people, new RoomCategory(categoryId, categoryName, price));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return room;
    }
    
    public List<Room> getFree(final Date startDate, final Date endDate) {
        List<Room> allRooms = getAll();
        List<Room> freeRooms = new ArrayList<>();
        for (Room room : allRooms) {
            List<Reservation> reservations = getReservationsForRoom(room.getId());
            if (null == reservations || reservations.size() == 0) {
                freeRooms.add(room);
                continue;
            }
            boolean isFree = true;
            for (Reservation reservation : reservations) {
                if (reservation.getStart().after(startDate) && reservation.getStart().before(endDate)) {
                    isFree = false;
                    break;
                }
                if (reservation.getEnd().after(startDate) && reservation.getEnd().before(endDate)) {
                    isFree = false;
                    break;
                }
                if (reservation.getStart().before(startDate) && reservation.getEnd().after(endDate)) {
                    isFree = false;
                    break;
                }
            }
            if (isFree) {
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }
    
    public List<Reservation> getReservationsForRoom(
            final int roomId) {
        ReservationProvider reservationProvider = new ReservationProvider();
        Connection connection = DBUtil.getConnection();
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations_for_room WHERE room_id = " + roomId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservatino_id");
                Reservation reservation 
                        = reservationProvider.getForId(reservationId);
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return reservations;
    }
    
    public List<Room> getForReservation(final int reservationId) {
        List<Room> rooms = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM reservations_for_room WHERE reservation_id=" + reservationId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                Room room = getForId(roomId);
                rooms.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return rooms;
    }
    
}
