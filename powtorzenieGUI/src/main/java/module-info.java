module pl.umcs.oop.powtorzeniegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pl.umcs.oop.powtorzeniegui to javafx.fxml;
    exports pl.umcs.oop.powtorzeniegui;
}