import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnect {
    public static Connection getConnect() throws SQLException {
        String url = "jdbc:sqlite:sample.db";
        return DriverManager.getConnection(url);
    }
}