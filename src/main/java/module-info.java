module pedronunesdev.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;


    opens pedronunesdev.javafxjdbc to javafx.fxml;
    exports pedronunesdev.javafxjdbc;

    opens pedronunesdev.javafxjdbc.controller to javafx.fxml;
    exports pedronunesdev.javafxjdbc.controller;
}