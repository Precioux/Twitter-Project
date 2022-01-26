package graphics;

import Services.impl.ObserverServiceImp;
import Tools.JSONtool;
import entity.Account;
import entity.Error;
import graphics.Controllers.SearchController;
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
 * this class defines search controller
 */
public class SearchControllerImp implements SearchController {
    ObserverServiceImp observerServiceImp=new ObserverServiceImp();
    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;
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
    }

    /**
     * to timeLine
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void toTimeLine(ActionEvent actionEvent) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene t=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(t);
        window.show();
    }
    /**
     * search
     * @param event e
     */
    @FXML
    public void searchIt(ActionEvent event) throws IOException {
        setAccount();
        observerServiceImp.addAccount(account);
        ForServices forServices = new ForServices(3, searchBar.getText());
        int rslt = observerServiceImp.begin(jsoNtool.toJSON(forServices));
        if (rslt == 0) {
            File viewer=new File("./files/View.txt");
            System.out.println(viewer.exists());
            FileWriter fileWriter=null;
            try {
                fileWriter = new FileWriter(viewer);
                System.out.println(searchBar.getText());
                fileWriter.write(searchBar.getText());
                fileWriter.close();
                Parent signUpRoot = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                Scene p = new Scene(signUpRoot);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(p);
                window.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            submitLog(localDate, localTime, "profile", "SuccessFul", 0);
        } else {
            if (rslt == -1) {
                Error error = new Error();
                error.errorSearch(1000);
                alaart(error.getErrorType());
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                submitLog(localDate, localTime, "profile", "Failed", 1000);
            } else {
                if (rslt == 9 || rslt == 999) {
                    Error error = new Error();
                    error.errorSearch(rslt);
                    alaart(error.getErrorType());
                    LocalDate localDate = LocalDate.now();
                    LocalTime localTime = LocalTime.now();
                    submitLog(localDate, localTime, "profile", "Failed", rslt);
                }
            }
        }
    }

}
