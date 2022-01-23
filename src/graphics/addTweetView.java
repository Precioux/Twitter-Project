package graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class addTweetView extends Application {

    public void start(Stage stage) throws IOException {
        try {
            System.out.println(getClass().getResource("AddTweet.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("AddTweet.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Add Tweet");
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

