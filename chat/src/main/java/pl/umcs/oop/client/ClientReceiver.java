package pl.umcs.oop.client;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver extends Thread {
    private final Socket socket;
    private final TextArea chatArea;

    public ClientReceiver(Socket socket, TextArea chatArea) {
        this.socket = socket;
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        )){
            String message;
            while ((message = in.readLine())!=null) {
                System.out.println("Otrzymano: "+message);
                final String finalMessage = message;
                Platform.runLater(() -> chatArea.appendText(finalMessage +"\n"));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
