import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/empdb",
                    "root",
                    "tAi2004#"
            );
            System.out.println("Connected Successfully!");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
}