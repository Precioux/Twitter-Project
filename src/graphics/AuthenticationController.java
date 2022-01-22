package graphics;
import Services.impl.AuthenticationServiceImp;
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

/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines authentication controller
 */
public class AuthenticationController {
    AuthenticationServiceImp authenticationServiceImp=new AuthenticationServiceImp();
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
        String id=idInput.getText();
        String pass=passInput.getText();
        System.out.println(id+"\n"+pass);
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
