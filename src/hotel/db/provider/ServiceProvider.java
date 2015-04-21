package hotel.db.provider;

import hotel.db.DBUtil;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceProvider {

    public List<Service> getForReservation(final Reservation reservation) {
        List<Service> services = new ArrayList<>();
        final int reservationId = reservation.getId();
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM services_for_reservation WHERE reservation_id=" + reservationId;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            RoomProvider roomProvider = new RoomProvider();
            while (resultSet.next()) {
                int id = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                int price = resultSet.getInt("service_price");
                services.add(new Service(id, name, price));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return services;
    }
    
}
