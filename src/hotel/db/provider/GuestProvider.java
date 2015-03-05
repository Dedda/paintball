package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestProvider {

    public List<Guest> getAll() {
        Connection connection = DBUtil.getConnection();
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guests;
    }
    
    public List<Guest> getCurrents() {
        Connection connection = DBUtil.getConnection();
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guests;
    }
    
    public Guest getForId(final int id) {
        Connection connection = DBUtil.getConnection();
        Guest guest = null;
        String query = "SELECT * FROM guest WHERE id=" + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, name, surname);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }
    
    public Guest getForName(final String name) {
        Connection connection = DBUtil.getConnection();
        Guest guest = null;
        String query = name.matches(".+\\s.+")
                ? "SELECT * FROM guest WHERE name='" + name.split(" ")[0]
                                     + "' AND surname='" + name.split(" ")[1] + "'"
                : "SELECT * FROM guest WHERE name='" + name + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String guestName = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, guestName, surname);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }
    
    public Guest getForNameLike(final String name) {
        Connection connection = DBUtil.getConnection();
        Guest guest = null;
        String query = name.contains(" ")
                ? "SELECT * FROM guest WHERE name LIKE '" + name.split(" ")[0]
                                     + "%' AND surname LIKE '" + name.split(" ")[1] + "%'"
                : "SELECT * FROM guest WHERE name LIKE '" + name + "%'";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String guestName = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                guest = new Guest(id, guestName, surname);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return guest;
    }
    
    public int saveNew(final Guest guest) {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO guest(name, surname) "
                + "VALUES('" + guest.getName() + "','" + guest.getSurname() + "')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Guest saved = getForName(guest.getName() + " " + guest.getSurname());
        return saved.getId();
    }
    
    public void remove(final Guest guest) {
        Connection connection = DBUtil.getConnection();
        String query = "DELETE FROM guest "
                + "WHERE id=" + guest.getId();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
