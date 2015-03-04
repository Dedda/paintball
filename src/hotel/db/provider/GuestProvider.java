package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Guest getForId(final int id) {
        return null;
    }
    
    public Guest getforName(final String name, final String surname) {
        return null;
    }
    
    public void saveGuest(final Guest guest) {
        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO guest(name, surname) "
                + "VALUES(" + guest.getName() + "," + guest.getSurname() + ")";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GuestProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
