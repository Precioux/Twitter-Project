package graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class StartController {
    @FXML
    private Pane mainArea;


    /**
     * to timeLine
     * @throws IOException e
     */
    void  toTimeLine() throws IOException {
        Stage window = (Stage) mainArea.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.show();
        System.out.println("send to timeLine");
    }

    /**
     * to authentication
     * @throws IOException e
     */
   void toAuthentication() throws IOException {
       Stage window = (Stage) mainArea.getScene().getWindow();
       Parent Root = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
       Scene Aview = new Scene(Root);
       window.setScene(Aview);
       window.show();
       System.out.println("send to authentication");
   }

@FXML
    public void Check(javafx.event.ActionEvent actionEvent) throws IOException {
        File toCheck = new File("./files/Remember.txt");
        if (toCheck.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(toCheck);
                Scanner scanner = new Scanner(fileReader);
                String u = scanner.next();
                fileReader.close();
                scanner.close();
                File w=new File("./files/Exchange.txt");
                FileWriter fileWriter=new FileWriter(w);
                fileWriter.write(u);
                fileWriter.close();
                toTimeLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else toAuthentication();

    }

    /**
     * refresh start
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void freshStart(javafx.event.ActionEvent actionEvent) throws IOException {
    toAuthentication();
    }
}
