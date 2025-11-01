package com.network.analyzer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PacketParser {

    public static List<Packet> parse(String filePath) {
        List<Packet> packets = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject packetObj = (JSONObject) obj;

                String src = (String) packetObj.get("source_ip");
                String dest = (String) packetObj.get("destination_ip");
                String proto = (String) packetObj.get("protocol");
                long len = (long) packetObj.get("length");
                String data = (String) packetObj.get("payload");

                packets.add(new Packet(src, dest, proto, (int) len, data));
            }

            System.out.println("✅ Parsed " + packets.size() + " packets from " + filePath);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return packets;
    }
}
