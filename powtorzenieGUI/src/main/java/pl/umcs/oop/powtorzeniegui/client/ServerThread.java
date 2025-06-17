package pl.umcs.oop.powtorzeniegui.client;

import pl.umcs.oop.powtorzeniegui.Dot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class ServerThread extends Thread {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private Consumer<Dot> drawFunction;

    public ServerThread(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void setDrawFunction(Consumer<Dot> drawFunction) {
        this.drawFunction = drawFunction;
    }

    public void send(Dot dot) {
        out.println(dot.toMessage());
    }

    @Override
    public void run() {
        System.out.println("Wątek obsługujący połączenie wystartował");
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Otrzymano: "+message);
                drawFunction.accept(Dot.fromMessage(message));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }


    }
}
