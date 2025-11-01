package com.network.analyzer;

public class Packet {
    private String sourceIP;
    private String destinationIP;
    private String protocol;
    private int length;
    private String payload;

    public Packet(String sourceIP, String destinationIP, String protocol, int length, String payload) {
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.protocol = protocol;
        this.length = length;
        this.payload = payload;
    }

    public String getSourceIP() { return sourceIP; }
    public String getDestinationIP() { return destinationIP; }
    public String getProtocol() { return protocol; }
    public int getLength() { return length; }
    public String getPayload() { return payload; }
}
