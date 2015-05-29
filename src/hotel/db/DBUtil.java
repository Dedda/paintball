package hotel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
public final class DBUtil implements Runnable {

    private Map<Connection, Boolean> connections; // Boolean for indicating availability
    private final int TARGET_CONNECTION_AMOUNT = 1;
    private final int CLEANUP_INTERVAL_MILLIS = 5000;
    
    private DBUtil() {
        connections = new HashMap<>();
        new Thread(this).start();
    }
    
    private void connect() {
        while (connections.size() < TARGET_CONNECTION_AMOUNT) {
            connections.put(createConnection(), true);
        }
    }
    
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbprojekt","projekt", "geheim");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Can not connect to database! Aborting!");
            System.exit(1);
            return null;
        }
        System.out.println("created connection with " + connections.size() + " already existing.");
        return connection;
    }
    
    private Connection useConnection() {
        Connection connection = null;
        for (Connection current : connections.keySet()) {
            if (connections.get(current)) {
                connection = current;
                connections.put(current, false);
                return connection;
            }
        }
        if (connection == null) {
            connection = createConnection();
            connections.put(connection, false);
        }
        return connection;
    }
    
    private void cleanUp() {
        System.out.print("Connection cleanup... ");
        final int open = connections.size();
        if (connections.size() <= TARGET_CONNECTION_AMOUNT) {
            System.out.println("Only " + open + " connections open. No cleanup required");
            return;
        }
        for (Connection connection : connections.keySet()) {
            if (connections.size() == TARGET_CONNECTION_AMOUNT) {
                break;
            }
            if (connections.get(connection)) {
                connections.remove(connection);
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("OK! Closed " + (open - connections.size()) + " connections!");
    }
    
    private void returnConn(final Connection connection) {
        connections.put(connection, true);
    }
    
    @Override
    public void run() {
        connect();
        while (true) {
            try {
                Thread.sleep(CLEANUP_INTERVAL_MILLIS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                cleanUp();
            } catch (Exception ex) {} // cleaning exceptions caused by multi threading to avoid death of the thread
        }
    }
    
    private static DBUtil instance;
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        instance = new DBUtil();
    }
    
    public static Connection getConnection() {
        return instance.useConnection();
    }
    
    public static void returnConnection(final Connection connection) {
        instance.returnConn(connection);
    }
    
}
