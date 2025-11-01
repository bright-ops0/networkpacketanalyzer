package com.network.analyzer;

import java.sql.*;

public class DatabaseLogger {
    private static final String URL = "jdbc:mysql://localhost:3306/packetanalyzer";
    private static final String USER = "root";
    private static final String PASS = "password";

    public static void logPacket(String protocol, String info) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO packets (protocol, info) VALUES (?, ?)"
            );
            ps.setString(1, protocol);
            ps.setString(2, info);
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("⚠️ Database logging error: " + e.getMessage());
        }
    }
}
