package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import hotel.entity.Staff;
import hotel.entity.StaffCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffProvider {

    public List<Staff> getAll() {
        Connection connection = DBUtil.getConnection();
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return staff;
    }
    
    
    
}
