package graphics;

import Services.impl.TweetingServiceImp;
import Tools.JSONtool;
import entity.Account;
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
import requestsFormats.ForServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class AddTweetController {
    TweetingServiceImp tweetingServiceImp=new TweetingServiceImp();
    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
    @FXML
    private AnchorPane area;
    @FXML
    private Button enter;

    @FXML
    private TextField textBox;
    /**
     * sets account
     *
     */
    public void setAccount() {
        File file=new File("./files/Exchange.txt");
        FileReader fr=null;
        try {
            fr=new FileReader(file);
            Scanner scanner=new Scanner(fr);
            String d=scanner.next();
            account.ID=d;
            System.out.println(account.ID);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
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
          setAccount();
          tweetingServiceImp.addAccount(account);
          ForServices forServices =new ForServices(1,txt);
          int est=tweetingServiceImp.begin(jsoNtool.toJSON(forServices));
          System.out.println("sent tweet");
          Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
          Scene signUpview=new Scene(signUpRoot);
          Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
          window.setScene(signUpview);
          window.show();
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
