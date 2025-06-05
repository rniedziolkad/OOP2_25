package pl.umcs.oop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static final int PORT = 12345;
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Serwer nasłuchuje na porcie: "+PORT);
            while (true) {
                System.out.println("Oczekuje na połączenie...");
                Socket socket = serverSocket.accept();
                System.out.println("Połączono: "+socket);


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
