package pl.umcs.oop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))){
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Otrzymano wiadomość: "+message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
