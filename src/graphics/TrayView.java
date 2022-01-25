package graphics;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TrayView extends Application {
    public void start(Stage stage) throws IOException {
        try {
            System.out.println(new File("/recources/twitterlogo.png").exists());
            Parent root = FXMLLoader.load(getClass().getResource("Tray.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Hide");
            FXTrayIcon trayIcon=new FXTrayIcon(stage,getClass().getResource("recources/twitterlogo.png"));
            trayIcon.show();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
