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
import java.util.HashMap;
import java.util.Map;

/**
 * @author stephan
 */
public class ServiceProvider {

    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();
        Connection connection = getConnection();
        String query = "SELECT * FROM optional_service";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                services.add(new Service(id, name, price));
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return services;
    }
    
    public Service getForId(final int id) {
        Service service = null;
        Connection connection = getConnection();
        String query = "SELECT * FROM optional_service WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                service = new Service(id, name, price);
            }
            returnConnection(connection);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return service;
    }
    
    public Map<Service, Integer> getForReservation(final Reservation reservation) {
        Map<Service, Integer> services = new HashMap<>();
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
                int amount = resultSet.getInt("amount");
                services.put(new Service(id, name, price), amount);
            }
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return services;
    }
    
    public void setForReservation(final Reservation reservation, final Map<Integer, Integer> idsToAmountMap) {
        removeAllForReservation(reservation.getId());
        idsToAmountMap.keySet().stream().forEach(id -> saveSingleForReservation(reservation.getId(), id, idsToAmountMap.get(id)));
    }
    
    private void removeAllForReservation(final int reservationId) {
        Connection connection = getConnection();
        String query = "DELETE FROM service_reservation WHERE reservation = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void saveSingleForReservation(final int reservationId, final int serviceId, final int amount) {
        Connection connection = getConnection();
        String query = "INSERT INTO service_reservation(service, reservation, amount) VALUES(?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, serviceId);
            statement.setInt(2, reservationId);
            statement.setInt(3, amount);
            statement.executeUpdate();
            returnConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
