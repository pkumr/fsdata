package com.jnetworking;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPEServer {
    static {
        Scanner readData = new Scanner(System.in);
        String[] serverDetails = new String[1];
        System.out.println("Port : ");
        serverDetails[0] = readData.nextLine();
        TCPEServer.main(serverDetails);
    }
    private static final int BUFFER_SIZE = 32;

    public static void main(String[] args) {

        if (args.length != 1)
            throw new IllegalArgumentException("Parameter : Port is missing");

        int serverPort = Integer.parseInt(args[0]);
        try {
            //int serverPort = 3000;
            //Create Server Socket
            ServerSocket serverSocket = new ServerSocket(serverPort);
            int receivedMSGSize;
            byte[] receiveBuff = new byte[BUFFER_SIZE];
            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Handling Client at " + clientAddress);

                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                while ((receivedMSGSize = in.read(receiveBuff)) != -1) {
                    out.write(receiveBuff, 0, receivedMSGSize);
                }
                clientSocket.close();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
