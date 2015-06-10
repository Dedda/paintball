package hotel.db.provider;

import hotel.entity.Guest;
import hotel.entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static hotel.db.DBUtil.*;

public class GuestProvider {

    RoomProvider roomProvider = new RoomProvider();
    
    public List<Guest> getAll() {
        Connection connection = getConnection();
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM guest";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guests.add(new Guest(id, name, surname));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guests;
    }
    
    public List<Guest> getCurrents() {
        Connection connection = getConnection();
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM current_guests";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guests.add(new Guest(id, name, surname));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guests;
    }
    
    public Guest getForId(final int id) {
        Connection connection = getConnection();
        Guest guest = null;
        String query = "SELECT * FROM guest WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, name, surname);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }
    
    public Guest getForName(final String name) {
        Connection connection = getConnection();
        Guest guest = null;
        try {
            PreparedStatement statement = name.matches(".+\\s.+")
                    ? createForFullName(name.split(" ")[0], name.split(" ")[1], connection)
                    : createForName(name, connection);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String guestName = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, guestName, surname);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }
    
    private PreparedStatement createForName(final String name, final Connection conn) throws SQLException {
        String query = "SELECT * FROM guest WHERE name = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name.trim());
        return statement;
    }
    
    private PreparedStatement createForFullName(final String name, final String surname, final Connection conn) throws SQLException {
        String query = "SELECT * FROM guest WHERE name = ? AND surname = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name.trim());
        statement.setString(2, surname.trim());
        return statement;
    }
    
    public List<Guest> getForNameLike(final String name) {
        List<Guest> guests = new ArrayList<>();
        Connection connection = getConnection();
        Guest guest = null;
        try {
            PreparedStatement statement = name.split(" ").length > 1
                    ? createForFullNameLike(name.split(" ")[0], name.split(" ")[1], connection)
                    : createForNameLike(name, connection);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String guestName = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, guestName, surname);
                guests.add(guest);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guests;
    }
    
    private PreparedStatement createForNameLike(final String name, final Connection conn) throws SQLException {
        String query = "SELECT * FROM guest WHERE name LIKE ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, '%' + name.trim() + '%');
        return statement;
    }
    
    private PreparedStatement createForFullNameLike(final String name, final String surname, final Connection conn) throws SQLException {
        String query = "SELECT * FROM guest WHERE name LIKE ? AND surname LIKE ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, '%' + name.trim());
        statement.setString(2, surname.trim() + '%');
        return statement;
    }
    
    public int saveNew(final Guest guest) {
        Connection connection = getConnection();
        String query = "INSERT INTO guest(name, surname) "
                + "VALUES( ? , ? )";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Guest saved = getForName(guest.getName() + " " + guest.getSurname());
        return saved.getId();
    }
    
    public void remove(final Guest guest) {
        if (guest.getId() == 1) {
            return;
        }
        ReservationProvider reservationProvider = new ReservationProvider();
        reservationProvider.guestRemoved(guest);
        Connection connection = getConnection();
        String query = "DELETE FROM guest "
                + "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, guest.getId());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int toPay(final Guest guest) {
        List<Reservation> openReservations;
        ReservationProvider reservationProvider = new ReservationProvider();
        openReservations = reservationProvider.getOpenForGuest(guest);
        int toPay = 
            openReservations.stream().mapToInt(
                reservation -> 
                    reservationProvider.getTotal(reservation)).sum();
        return toPay;
    }
}
