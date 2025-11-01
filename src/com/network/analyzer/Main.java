package com.network.analyzer;

import java.sql.*;
import java.util.List;

public class DBManager {
    private Connection connection;

    public DBManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/packet_analyzer";
            String user = "root";
            String password = "root"; // change if needed
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // Bulk insert for a list of packets
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

    // Single insert for quick tests
    public void insertPacket(String sourceIP, String destinationIP, String protocol, int length, String payload) {
        String sql = "INSERT INTO packets (source_ip, destination_ip, protocol, length, payload) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sourceIP);
            pstmt.setString(2, destinationIP);
            pstmt.setString(3, protocol);
            pstmt.setInt(4, length);
            pstmt.setString(5, payload);
            pstmt.executeUpdate();
            System.out.println("✅ Single packet inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close DB connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
