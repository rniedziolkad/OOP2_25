package pl.umcs.oop.powtorzeniegui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.umcs.oop.powtorzeniegui.client.ServerThread;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField addressField;
    @FXML
    private TextField portField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider radiusSlider;
    @FXML
    private Canvas canvas;

    private ServerThread serverThread;

//    @FXML
//    private void initialize() {
//        // tutaj można coś zrobić po utworzeniu kontrolera
//    }

    @FXML
    private void onStartServerClicked() {
        System.out.println("onStartServerClicked not implemented!");
    }

    @FXML
    private void onConnectClicked() {
        String address = this.addressField.getText().trim();
        int port = Integer.parseInt(this.portField.getText().trim());
        try {
            this.serverThread = new ServerThread(address, port);
            this.serverThread.setDrawFunction((dot) -> {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(dot.color());
                gc.fillOval(
                        dot.x()-dot.radius(),
                        dot.y()-dot.radius(),
                        dot.radius()*2,
                        dot.radius()*2
                );
            });
            this.serverThread.setDaemon(true);

            this.serverThread.start();
            System.out.println("Połączono!");
        } catch (IOException e) {
            System.err.println("Nie udało się połączyć z serwerem: "+e.getMessage());
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
//        System.out.println("Klinieto w ("+x+", "+y+")");

        Color color = colorPicker.getValue();
        double radius = radiusSlider.getValue();

//        System.out.println("Color: "+color);
//        System.out.println("Radius: "+radius);
        if (this.serverThread != null) {
            Dot dot = new Dot(x, y, color, radius);
            serverThread.send(dot);
        } else {
            System.err.println("Połącz się z serwerem!");
        }
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(color);
//        gc.fillOval(x-radius, y-radius, radius*2, radius*2);
    }
}
