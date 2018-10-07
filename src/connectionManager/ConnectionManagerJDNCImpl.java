package connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJDNCImpl implements ConnectionManager{

    private static ConnectionManager connectionManager;
    private Connection connection;

    private ConnectionManagerJDNCImpl()
    {

    }
    public static ConnectionManager getInstanse(){
        if(connectionManager == null){
            connectionManager = new ConnectionManagerJDNCImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if( connection != null ){
            return connection;
        }
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/students",
                "postgres", "admin");
        return connection;
    }
}
