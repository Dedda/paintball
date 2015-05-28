package hotel.db.provider;

import hotel.entity.Reservation;
import hotel.entity.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hotel.db.DBUtil.*;

public class ServiceProvider {

    public List<Service> getForReservation(final Reservation reservation) {
        List<Service> services = new ArrayList<>();
        final int reservationId = reservation.getId();
        Connection connection = getConnection();
        String query = "SELECT * FROM services_for_reservation WHERE reservation_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                int price = resultSet.getInt("service_price");
                services.add(new Service(id, name, price));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return services;
    }
    
}
