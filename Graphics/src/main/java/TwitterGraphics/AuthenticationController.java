package TwitterGraphics;
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

public class AuthenticationController {

    @FXML
    private TextField idInput;

    @FXML
    private TextField passInput;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    void toSignIn(ActionEvent event) {

    }

    @FXML
    void toSignUp(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.showAndWait();

    }

}
