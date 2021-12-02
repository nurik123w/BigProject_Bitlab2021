package com.company;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("waiting fot the connection");
            ServerSocket server  = new ServerSocket(9090);
            while(true){
                Socket socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(socket).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
