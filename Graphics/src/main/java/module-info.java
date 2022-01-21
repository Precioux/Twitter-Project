module com.example.graphics {
    requires javafx.controls;
    requires javafx.fxml;


    opens TwitterGraphics to javafx.fxml;
    exports TwitterGraphics;
}