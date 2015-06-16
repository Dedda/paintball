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
}
