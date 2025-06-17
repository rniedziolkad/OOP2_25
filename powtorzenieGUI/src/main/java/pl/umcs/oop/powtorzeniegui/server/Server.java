package pl.umcs.oop.powtorzeniegui.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final static List<ClientThread> clientList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serwer wystartował");
            while (true) {
                System.out.println("Oczekiwanie na połączenie...");
                Socket accepted = serverSocket.accept();
                System.out.println("Połączono: "+accepted);

                ClientThread ct = new ClientThread(accepted);
                ct.setDaemon(true);
                clientList.add(ct);
                ct.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void broadcast(String message) {
        for (ClientThread client : clientList) {
            client.send(message);
        }
    }

    public static void removeClient(ClientThread client) {
        clientList.remove(client);
    }
}
