package pl.umcs.oop.powtorzeniegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    @FXML
    private void onStartServerClicked() {
        System.out.println("onStartServerClicked not implemented!");
    }

    @FXML
    private void onConnectClicked() {
        System.out.println("onConnectClicked not implemented!");
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

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(x-radius, y-radius, radius*2, radius*2);
    }
}
