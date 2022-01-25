package graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TimeLineDarkView extends Application {


    public void start(Stage stage) throws IOException {
        try {
            System.out.println(new File("TimeLineDark.fxml").exists());
            Parent root = FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("TimeLine Dark");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
