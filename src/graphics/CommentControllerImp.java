package graphics;

import Services.impl.ObserverServiceImp;
import Tools.JSONtool;
import entity.Account;
import entity.Error;
import entity.React;
import graphics.Controllers.CommentController;
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
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines comment controller
 */
public class CommentControllerImp implements CommentController {
   ObserverServiceImp observerServiceImp=new ObserverServiceImp();
   Account account=new Account();
   JSONtool jsoNtool=new JSONtool();
    @FXML
    private Button cancel;

    @FXML
    private Button comment;

    @FXML
    private TextField commentBox;

    @FXML
    private AnchorPane mainArea;
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
     * to timeLine
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void backTimeLine(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }

    /**
     * comment
     * @param event e
     */
    @FXML
    public void commentIt(ActionEvent event)  {
        File file=new File("./files/CommentSource.txt");
        FileReader fileReader=null;
        try {
            fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader).useDelimiter("\n");
            String from = scanner.next();
            String to = scanner.next();
            String tweet = scanner.next();
            if (commentBox.getText().isEmpty() || commentBox.getText().length() > 256) {
                if (commentBox.getText().isEmpty())
                    alaart("Please enter your comment");
                if (commentBox.getText().length() > 256)
                    alaart("More than 256 characters");
            } else{
                React react = new React(from, to, 1, tweet, commentBox.getText());
                setAccount();
                observerServiceImp.addAccount(account);
                ForServices forServices = new ForServices(5, jsoNtool.toJSON(react));
                int rslt = observerServiceImp.begin(jsoNtool.toJSON(forServices));
                if (rslt == 0) {
                    LocalDate localDate = LocalDate.now();
                    LocalTime localTime = LocalTime.now();
                    submitLog(localDate, localTime, Integer.toString(1), "SuccessFul", 0);
                } else {
                    if (rslt == -1) {

                        Error error = new Error();
                        error.errorSearch(1000);
                        alaart(error.getErrorType());
                        LocalDate localDate = LocalDate.now();
                        LocalTime localTime = LocalTime.now();
                        submitLog(localDate, localTime, Integer.toString(1), "Failed", 1000);
                    } else {
                        if (rslt == 9 || rslt == 999) {
                            Error error = new Error();
                            error.errorSearch(rslt);
                            alaart(error.getErrorType());
                            LocalDate localDate = LocalDate.now();
                            LocalTime localTime = LocalTime.now();
                            submitLog(localDate, localTime, Integer.toString(1), "Failed", rslt);
                        }
                    }
                }

                Parent signUpRoot = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
                Scene signUpview = new Scene(signUpRoot);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(signUpview);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * submit log
     * @param localTime data
     * @param localDate data
     * @param request data
     * @param result data
     * @param ErrorCode data
     */
    public void submitLog(LocalDate localDate, LocalTime localTime,String request, String result, int ErrorCode)
    {
        File file=new File("./files/log.txt");
        String log="";
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(file,true);
            if (ErrorCode==0) {
                log+= localDate+" "+localTime+ "  @" + account.ID + "  : " + request + " => " + result+"\n";
            }
            else
            {
                Error error=new Error();
                error.errorSearch(ErrorCode);
                log+=localDate +" "+localTime+ "  @" + account.ID + "  : " + request + " => " + result+" > "+error.getErrorType()+"\n";
            }
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        Stage window = (Stage)mainArea.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("Comment.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.showAndWait();
    }

}
