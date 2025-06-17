package pl.umcs.oop.powtorzeniegui;

import javafx.scene.paint.Color;

public record Dot(double x, double y, Color color, double radius) {
    public static Dot fromMessage(String message) {
        String[] parts = message.split(",");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        Color color = Color.valueOf(parts[2]);
        double radius = Double.parseDouble(parts[3]);

        return new Dot(x, y, color, radius);
    }

    public String toMessage() {
        return this.x+","+this.y+","+this.color+","+this.radius;
    }
}
