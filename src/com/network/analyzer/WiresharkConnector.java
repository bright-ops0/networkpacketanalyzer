package com.network.analyzer;

import java.io.IOException;

public class WiresharkConnector {

    public static void capturePackets(String interfaceName, String outputFile) {
        try {
            // Run tshark to capture packets for 10 seconds and save as JSON
            String command = String.format(
                "tshark -i %s -a duration:10 -T json > %s", interfaceName, outputFile
            );

            Process process = new ProcessBuilder("bash", "-c", command)
                    .redirectErrorStream(true)
                    .start();

            process.waitFor();
            System.out.println("✅ Packet capture complete. Saved to: " + outputFile);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
