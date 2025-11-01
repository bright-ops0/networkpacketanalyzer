import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/packet_analyzer?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "bright",
    "bright123"
);

            System.out.println("✅ Connection successful!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
