package com.network.analyzer;

import java.sql.*;
import java.util.List;

public class DBManager {
    private Connection connection;

    public DBManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/packet_analyzer";
            String user = "root";
            String password = "root"; // make sure this matches your MySQL setup
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPackets(List<Packet> packets) {
        String sql = "INSERT INTO packets (source_ip, destination_ip, protocol, length, payload) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Packet packet : packets) {
                pstmt.setString(1, packet.getSourceIP());
                pstmt.setString(2, packet.getDestinationIP());
                pstmt.setString(3, packet.getProtocol());
                pstmt.setInt(4, packet.getLength());
                pstmt.setString(5, packet.getPayload());
                pstmt.executeUpdate();
            }
            System.out.println("✅ Packets inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
