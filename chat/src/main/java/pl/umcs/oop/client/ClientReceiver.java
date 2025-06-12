package pl.umcs.oop.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver extends Thread {
    private final Socket socket;

    public ClientReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        )){
            String message;
            while ((message = in.readLine())!=null) {
                System.out.println("Otrzymano: "+message);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
