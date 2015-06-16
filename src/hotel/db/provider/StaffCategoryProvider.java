package hotel.db.provider;

import hotel.entity.StaffCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static hotel.db.DBUtil.*;

/**
 * @author Phil
 * by Alex Phil 16.06
 */
public class StaffCategoryProvider {

    public List<StaffCategory> getAll() {
        Connection connection = getConnection();
        List<StaffCategory> staffCategory = new ArrayList<>();
        String query = "SELECT * FROM staff_category";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int cat_id = resultSet.getInt("id");
                int cat_salary = resultSet.getInt("salary");
                String cat_name = resultSet.getString("name");
                staffCategory.add(new StaffCategory(cat_id, cat_salary, cat_name));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return staffCategory;
    }
    
    public StaffCategory getByName(String name) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM staff_category WHERE name = '" + name + "'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int cat_id = resultSet.getInt("id");
                int cat_salary = resultSet.getInt("salary");
                String cat_name = resultSet.getString("name");
                return new StaffCategory(cat_id, cat_salary, cat_name);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return null;
    }
}
