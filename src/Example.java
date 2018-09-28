import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Example {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        try(Connection connection = getConnection(
                "jdbc:postgresql://localhost:5432/students",
                "postgres",
                "admin");
            PreparedStatement statement = connection.prepareStatement("delete students where name=? and surname=?")
        ) {
            statement.setString(1, "Draco");
            statement.setString(2, "Malfoy");
            statement.executeQuery();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
