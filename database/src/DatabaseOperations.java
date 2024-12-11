import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DatabaseOperations {

    public static void clearDatabase() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = SQLiteConnect.getConnect();
            stmt = conn.createStatement();
            String sqlDropUsers = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sqlDropUsers);
            String sqlDropOtherTable = "DROP TABLE IF EXISTS other_table";
            stmt.executeUpdate(sqlDropOtherTable);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
        conn.close();
        stmt.close();
    }

    public static void createNewTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" + " id integer PRIMARY KEY,\n" + " name text NOT NULL,\n" + " age integer NOT NULL\n" + ");";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        System.out.println("Table 'users' has been created.");
        conn.close();
        stmt.close();
    }

    public static void insert(String name, int age) throws SQLException {
        String sql = "INSERT INTO users(name, age) VALUES(?, ?)";
        Connection conn = SQLiteConnect.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA busy_timeout = 5000;");
                String ageStr = String.valueOf(age);
                pstmt.setString(1, name);
                pstmt.setString(2, ageStr);
                pstmt.executeUpdate();
                System.out.println("A new row has been inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        pstmt.close();
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection conn = SQLiteConnect.getConnect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Row with id " + id + " has been deleted.");
        conn.close();
        pstmt.close();
    }

    public static void shiftIds() throws SQLException {
        String dropTempTableIfExists = "DROP TABLE IF EXISTS temp_users";
        String createTempTable = "CREATE TABLE temp_users AS SELECT * FROM users";
        String clearTable = "DELETE FROM users";
        String insertWithNewIds = "INSERT INTO users (name, age) SELECT name, age FROM temp_users";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        stmt.execute(dropTempTableIfExists);
        stmt.execute(createTempTable);
        stmt.execute(clearTable);
        stmt.execute(insertWithNewIds);
        System.out.println("IDs have been shifted.");
        conn.close();
        stmt.close();
    }

    public static void selectAll() throws SQLException {
        String sql = "SELECT id, name, age FROM users";
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("age"));
        }
        conn.close();
        stmt.close();
    }

    public static StringTokenizer getAllNamesOfColumns() throws SQLException {
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
        stmt.close();
        connection.close();
        return tokenizer;
    }

    public static ArrayList<String> GetAllInfo () throws SQLException{
        Connection conn = SQLiteConnect.getConnect();
        Statement stmt = conn.createStatement();
        ArrayList<String> arrayList = new ArrayList<>();
        String ids = "";
        String names = "";
        String ages = "";
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            ids += resultSet.getString("id") + " ";
            names += resultSet.getString("name") + " ";
            ages += resultSet.getString("age") + " ";
        }
        arrayList.add(ids);
        arrayList.add(names);
        arrayList.add(ages);
        stmt.close();
        conn.close();
        return arrayList;
    }

}
