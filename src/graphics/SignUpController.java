package graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField bio;

    @FXML
    private Button cancel;

    @FXML
    private TextField day;

    @FXML
    private TextField firstname;

    @FXML
    private TextField id;

    @FXML
    private TextField lastname;

    @FXML
    private TextField month;

    @FXML
    private TextField password;

    @FXML
    private Button sumbit;

    @FXML
    private TextField year;

    @FXML
    void toCancel(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.showAndWait();
    }

    @FXML
    void toSumbit(ActionEvent event) {

    }

}
