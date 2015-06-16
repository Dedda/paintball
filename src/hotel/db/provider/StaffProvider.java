package hotel.db.provider;

import hotel.entity.Staff;
import hotel.entity.StaffCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import static hotel.db.DBUtil.*;
import java.util.stream.Collectors;

/**
 * @author stephan
 *  Method saveNew, getForName, updateStaff by Alex Phil 16.06
 */
public class StaffProvider {

    public Staff getForId(final int staffId) {
        Connection connection = getConnection();
        Staff staff = null;
        String query = "SELECT * FROM staff_with_category where staff_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, staffId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int catId = resultSet.getInt("cat_id");
                String catName = resultSet.getString("cat_name");
                int salary = resultSet.getInt("salary");
                Date recruitement = resultSet.getDate("recruitement");
                Date firing = resultSet.getDate("firing");
                StaffCategory category = new StaffCategory(catId, salary, catName);
                staff = new Staff(id, category, name, surname, recruitement, firing);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return staff;
    }

    public List<Staff> getAll() {
        Connection connection = getConnection();
        List<Staff> staff = new ArrayList<>();
        String query = "SELECT * FROM staff_with_category";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int catId = resultSet.getInt("cat_id");
                String catName = resultSet.getString("cat_name");
                int salary = resultSet.getInt("salary");
                Date recruitement = resultSet.getDate("recruitement");
                Date firing = resultSet.getDate("firing");
                StaffCategory category = new StaffCategory(catId, salary, catName);
                staff.add(new Staff(id, category, name, surname, recruitement, firing));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return staff;
    }

    public void remove(final Staff staff) {
        removeDependencies(staff);
        Connection connection = getConnection();
        String query = "DELETE FROM staff WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, staff.getId());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Staff deleted! " + staff.getName() + " " + staff.getSurname());
    }

    private void removeDependencies(final Staff staff) {
        removeFromRooms(staff);
        removeFromServices(staff);
    }

    private void removeFromRooms(final Staff staff) {
        Connection connection = getConnection();
        String query = "DELETE FROM staff_room WHERE staff = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, staff.getId());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void removeFromServices(final Staff staff) {
        Connection connection = getConnection();
        String query = "DELETE FROM staff_service WHERE staff = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, staff.getId());
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeTooOld() {
        org.joda.time.DateTime dateTime = new DateTime(new Date().getTime());
        dateTime = dateTime.minusYears(3);
        java.sql.Date oldestTokeep = new java.sql.Date(dateTime.getMillis());
        Connection connection = getConnection();
        String query = "SELECT id FROM staff WHERE firing < ?";
        List<Integer> idsToRemove = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, oldestTokeep);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                idsToRemove.add(resultSet.getInt("id"));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Staff> staffToRemove = idsToRemove.stream().map(id -> getForId(id)).collect(Collectors.toList());
        staffToRemove.stream().forEach(staff -> remove(staff));
    }
    
    public void saveNew(final Staff staff) {
        Connection connection = getConnection();
        String query = "INSERT INTO staff(name, surname, category, recruitement, firing) "
                + "VALUES( ? , ?, ?, ?, ? )";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getSurname());
            statement.setInt(3, staff.getCategory().getId());
            statement.setDate(4, new java.sql.Date(staff.getRecruitement().getTime()));
            statement.setDate(5, staff.getFiring() == null ? null : new java.sql.Date(staff.getFiring().getTime()));
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Staff getForName(final String name, final String surname) {
        Connection connection = getConnection();
        Staff staff = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name, surname FROM staff WHERE name = '" + name + "' AND surname = '" + surname + "'");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String staffName = resultSet.getString("name");
                String staffSurname = resultSet.getString("surname");
                staff = new Staff();
                staff.setId(id);
                staff.setName(staffName);
                staff.setSurname(staffSurname);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return staff;
    }
    
    public void updateStaff(Staff staff) {
        Connection connection = getConnection();
        
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE staff SET"
                + " name = ?"
                + ", surname = ?"
                + ", category = ?"
                + ", recruitement = ?"
                + ", firing = ?"
                + " WHERE id = ?");
            
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getSurname());
            statement.setInt(3, staff.getCategory().getId());
            statement.setDate(4, new java.sql.Date(staff.getRecruitement().getTime()));
            System.out.println(staff.getFiring() == null);
            statement.setDate(5, staff.getFiring() == null ? null : new java.sql.Date(staff.getFiring().getTime()));
            statement.setInt(6, staff.getId());
            statement.executeUpdate();
            returnConnection(connection);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}