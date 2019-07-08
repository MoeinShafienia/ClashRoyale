package ir.ac.kntu;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private Socket socket;
    private ServerSocket server;
    private DataInputStream input;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            socket = server.accept();
            System.out.println("Client accepted");

            input = new DataInputStream(
                new BufferedInputStream(socket.getInputStream())
            );

            String line = "";

            while(!line.equals("Over")) {
                try {
                    line = input.readUTF();
                    System.out.println(line);
                } catch (IOException e) {
                   System.out.println(e);
                } 
            }

            System.out.println("Closing connection");

            socket.close();
            input.close();  

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // public static void main(String[] args) {
    //     new Server(5000);
    // }
}