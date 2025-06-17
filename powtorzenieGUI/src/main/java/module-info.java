module pl.umcs.oop.powtorzeniegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.umcs.oop.powtorzeniegui to javafx.fxml;
    exports pl.umcs.oop.powtorzeniegui;
}