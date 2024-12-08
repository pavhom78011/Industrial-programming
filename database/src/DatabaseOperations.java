import org.sqlite.SQLiteConnection;

import java.sql.*;
import java.util.StringTokenizer;

public class DatabaseOperations {
    public static void createNewTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" + " id integer PRIMARY KEY,\n" + " name text NOT NULL,\n" + " email text NOT NULL\n" + ");";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        System.out.println("Table 'users' has been created.");
    }

    public static void insert(String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
        Connection conn = SQLiteConnect.getConnect();
        java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.executeUpdate();
        System.out.println("A new row has been inserted.");
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection conn = SQLiteConnect.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Row with id " + id + " has been deleted.");
    }

    public static void shiftIds() throws SQLException {
        String dropTempTableIfExists = "DROP TABLE IF EXISTS temp_users";
        String createTempTable = "CREATE TABLE temp_users AS SELECT * FROM users";
        String clearTable = "DELETE FROM users";
        String insertWithNewIds = "INSERT INTO users (name, email) SELECT name, email FROM temp_users";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        stmt.execute(dropTempTableIfExists);
        stmt.execute(createTempTable);
        stmt.execute(clearTable);
        stmt.execute(insertWithNewIds);
        System.out.println("IDs have been shifted.");
    }

    public static void selectAll() throws SQLException {
        String sql = "SELECT id, name, email FROM users";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("email"));
        }
    }

    public static StringTokenizer getAll() throws SQLException {
        int columns = 0;
        String namesofcolumns = "";
        String tablename = "users";
        String str = "jdbc:sqlite:sample.db";
        Connection connection = SQLiteConnect.getConnect();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
        ResultSetMetaData colnames = resultSet.getMetaData();
        columns = colnames.getColumnCount();
        for (int i = 1; i <= columns; ++i) {
            namesofcolumns += colnames.getColumnName(i) + " ";
        }
        StringTokenizer tokenizer = new StringTokenizer(namesofcolumns);
        return tokenizer;
    }
}
