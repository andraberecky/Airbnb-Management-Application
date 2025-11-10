module org.example.airbnb2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires junit;

    opens org.example.airbnb2 to javafx.fxml;
    exports org.example.airbnb2;
    exports org.example.airbnb2.Controller;
    opens org.example.airbnb2.Controller to javafx.fxml;
    exports org.example.airbnb2.Model;
    opens org.example.airbnb2.Model to javafx.fxml;
}