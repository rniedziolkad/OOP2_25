module pl.umcs.oop.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens pl.umcs.oop.demo to javafx.fxml;
    exports pl.umcs.oop.demo;
}