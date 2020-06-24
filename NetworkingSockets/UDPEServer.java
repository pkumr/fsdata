package com.jnetworking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEServer {
    private static final int DATA_MAX = 255;

    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("Parameter : Port");
            int serverPort = Integer.parseInt(args[0]);
            DatagramSocket socket = new DatagramSocket(serverPort);
            DatagramPacket packet = new DatagramPacket(new byte[DATA_MAX], DATA_MAX);
            while (true){
                socket.receive(packet);
                System.out.println("Handling clint at :" + packet.getAddress().getHostName() +
                        " on port " + packet.getPort());
                socket.send(packet);
                packet.setLength(DATA_MAX);
            }
        }
        catch (IOException e){
            System.out.println("Exception");
        }
    }
}
