package jdbc.dbWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionPath = "jdbc:mysql://localhost:3306/shop";

        dbConnection = DriverManager.getConnection(connectionPath, "root", "1qaz2wsxzxcZ1");
        return dbConnection;
    }
}
