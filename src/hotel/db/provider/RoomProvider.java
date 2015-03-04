package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Guest;
import hotel.entity.Room;
import hotel.entity.RoomCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
}
