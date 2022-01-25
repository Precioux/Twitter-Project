package graphics;

import Services.impl.TweetingServiceImp;
import Tools.JSONtool;
import entity.Account;
import graphics.Controllers.addTweetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines add tweet controller
 */
public class AddTweetControllerImp  implements addTweetController {
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

    /**
     * to TimeLine
     * @throws IOException e
     */
    public void toTimeLine() throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) area.getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }
    /**
     * to dark TimeLine
     * @throws IOException e
     */
    public void toDarkTimeLine() throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) area.getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }
    /**
     * set Theme
     */
    public void setTheme()
    {
        int theme=-1;
        File Theme=new File("./files/Setting/Theme.txt");
        FileReader fileReader=null;
        try {
            fileReader = new FileReader(Theme);
            Scanner scanner=new Scanner(fileReader);
            if(scanner.hasNextInt())
            {
                theme=scanner.nextInt();
            }
            System.out.println("Theme :"+theme);
            if(theme==0)
            {
                toTimeLine();
            }
            else {
                toDarkTimeLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * cancel
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void cancelIt(ActionEvent event) throws IOException {
     toTimeLine();
    }

    /**
     * add text
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void addText(ActionEvent event) throws IOException {
      String txt=textBox.getText();
        if(txt.length() >256 || txt.length()==0)
        {
            if(txt.length()>256)
                alaart("More than 256 characters!");
            else
            if(txt.length()==0)
                alaart("Please enter text!");
        }
      else
      {
          setAccount();
          tweetingServiceImp.addAccount(account);
          ForServices forServices =new ForServices(1,txt);
          int est=tweetingServiceImp.begin(jsoNtool.toJSON(forServices));
          System.out.println("sent tweet");
         toTimeLine();
      }
    }
    /**
     * alart
     * @param err error
     */
    public void alaart(String err) throws IOException {
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
