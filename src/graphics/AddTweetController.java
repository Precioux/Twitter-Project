package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AddTweetController {
    @FXML
    private AnchorPane area;
    @FXML
    private Button enter;

    @FXML
    private TextField textBox;
    @FXML
    void cancelIt(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.showAndWait();
    }
    @FXML
    void addText(ActionEvent event) throws IOException {
      String txt=textBox.getText();
      if(txt.length() >256)
      {
          alaart("More than 256 characters!");
      }
      else
      {



          Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
          Scene signUpview=new Scene(signUpRoot);
          Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
          window.setScene(signUpview);
          window.showAndWait();
      }
    }
    /**
     * alart
     * @param err error
     */
    void alaart(String err) throws IOException {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(err);
        Optional<ButtonType> res=alert.showAndWait();
        Stage window = (Stage)area.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("AddTweet.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.showAndWait();
    }

}
