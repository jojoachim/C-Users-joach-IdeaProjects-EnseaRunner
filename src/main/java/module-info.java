module game.ensearunner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens game.ensearunner to javafx.fxml;
    exports game.ensearunner;
    exports constants.ensearunner;
    opens constants.ensearunner to javafx.fxml;
}