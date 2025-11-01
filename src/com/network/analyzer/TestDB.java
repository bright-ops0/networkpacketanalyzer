package com.network.analyzer;

public class TestDB {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.insertPacket("192.168.1.10", "10.0.0.2", "TCP", 150, "HelloWorld");
        db.closeConnection();
    }
}
