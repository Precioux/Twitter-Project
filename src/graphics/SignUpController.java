package graphics;

import Services.AuthenticationService;
import Services.impl.AuthenticationServiceImp;
import Tools.AccountChecker;
import Tools.JSONtool;
import entity.Account;
import entity.Error;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static Services.impl.CommandPerserServiceImp.toHash;

public class SignUpController {
     String data="";
     boolean logFlag=false;
     AuthenticationServiceImp authenticationServiceImp=new AuthenticationServiceImp();
     Account account=new Account();
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
    private CheckBox remember=new CheckBox();
    @FXML
    private GridPane mainArea=new GridPane();
    @FXML
    void toCancel(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.showAndWait();
    }

    @FXML
    void toSumbit(ActionEvent event) throws NoSuchAlgorithmException {
        String fname = firstname.getText();
        account.addFirstName(fname);
        String lname = lastname.getText();
        account.addLastName(lname);
        String Id=id.getText();
        account.ID=Id;
        int y,d,m;
        y=Integer.parseInt(year.getText());
        m=Integer.parseInt(month.getText());
        d=Integer.parseInt(day.getText());
        LocalDate bd= LocalDate.of(y,m,d);
        account.addBdate(bd);
        String Password=password.getText();
        account.addPassword(toHash(Password));
        LocalDate l=LocalDate.now();
        account.addjDate(l);
        String Bio=bio.getText();
        account.addBio(Bio);
        JSONtool jsoNtool=new JSONtool();
        data+=jsoNtool.toJSON(account);
        try {
            int rslt = authenticationServiceImp.begin(2, data);
            System.out.println(rslt);
            if (rslt == 0) {
                if(remember.isSelected())
                    logFlag = true;
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                submitLog(localDate, localTime, "SignUpView", "SuccessFul", 0);
                System.out.println("succcess");
                toTimeline();
            } else {
                if (rslt == 3 || rslt == 4) {
                    Error error = new Error();
                    error.errorSearch(rslt);
                    alaart(error.getErrorType());
                    LocalDate localDate = LocalDate.now();
                    LocalTime localTime = LocalTime.now();
                    submitLog(localDate, localTime, "SignUpView", "Failed", rslt);
                    System.out.println("failure");
                }
            }
        } catch (AuthenticationService.InvalidChoiceException e) {
            e.printStackTrace();
        } catch (AccountChecker.BioException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (AccountChecker.IdException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        if(res.get()==ButtonType.OK){
        Stage window = (Stage) mainArea.getScene().getWindow();
        Parent Root= FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        Scene Aview=new Scene(Root);
        window.setScene(Aview);
        window.showAndWait();}
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
    void toTimeline()
    {
        File file=new File("./files/Exchange.txt");
        FileWriter fw=null;
        try {
            fw=new FileWriter(file);
            fw.write(account.ID);
            fw.close();
            Stage window = (Stage) mainArea.getScene().getWindow();
            Parent Root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
            Scene Aview = new Scene(Root);
            window.setScene(Aview);
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
