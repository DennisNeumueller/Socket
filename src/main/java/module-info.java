module com.example.socket {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.socket to javafx.fxml;
    exports com.example.socket;
    exports socket;
    opens socket to javafx.fxml;
}