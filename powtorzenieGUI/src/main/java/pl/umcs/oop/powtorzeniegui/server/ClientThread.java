package pl.umcs.oop.powtorzeniegui.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void send(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        )){
            out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while((message = in.readLine())!=null){
                System.out.println("Otrzymano: "+message);
                Server.broadcast(message);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
                Server.removeClient(this);
            } catch (IOException ignored) {}
        }
    }
}
