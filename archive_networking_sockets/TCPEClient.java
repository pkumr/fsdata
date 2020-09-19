package com.jnetworking;
import java.net.Socket;
import java.net.SocketException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class TCPEClient {
    static {
        Scanner readData = new Scanner(System.in);
        String[] serverDetails = new String[3];
        System.out.println("Enter Server Name : ");
        serverDetails[0] = readData.nextLine();
        System.out.println("Data :");
        serverDetails[1] = readData.nextLine();
        System.out.println("Port : ");
        serverDetails[2] = readData.nextLine();
        TCPEClient.main(serverDetails);
    }
    public static void main(String[] args) {
        System.out.println("Length" + args.length);
        if((args.length < 2 || args.length > 3))
            throw new IllegalArgumentException("Parameters : Server Word Port");
        try {
            String server = args[0];
            byte[] data = args[1].getBytes();
            int serverPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
            //Create Socket
            Socket socket = new Socket(server, serverPort);
            System.out.println("Connected to Server....");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(data);

            int totalByteReceived = 0;
            int bytesReceived;
            while (totalByteReceived < data.length) {
                if ((bytesReceived = in.read(data, totalByteReceived,
                        data.length - totalByteReceived)) == -1)
                    throw new SocketException("Conncetion closed");
                totalByteReceived += bytesReceived;
            }
            socket.close();
        }catch (IOException s){
            System.out.println("Socket Excetion");
        }
    }
}
