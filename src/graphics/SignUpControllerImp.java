package graphics;

import Services.AuthenticationService;
import Services.impl.AuthenticationServiceImp;
import Tools.AccountChecker;
import Tools.JSONtool;
import entity.Account;
import entity.Error;
import graphics.Controllers.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static Services.impl.CommandPerserServiceImp.toHash;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines sign up controller
 */
public class SignUpControllerImp implements SignUpController {
     String data="";
     String photo="";
     boolean logFlag=false;
     AuthenticationServiceImp authenticationServiceImp=new AuthenticationServiceImp();
     Account account=new Account();
     @FXML
     private TextField path;
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

    ArrayList<String> aa=new ArrayList<>();

    /**
     * to cancel
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void toCancel(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }

    /**
     * choose photo
     * @param actionEvent event
     * @throws IOException e
     */
    @FXML
    public void toChoose(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
       fileChooser.setTitle("Profile Photo");
       Stage stage=(Stage) mainArea.getScene().getWindow();
       File file=fileChooser.showOpenDialog(stage);
       if (file!=null)
       {
           path.setText(file.getAbsolutePath());
           photo+=file.getAbsolutePath();
       }
    }

    /**
     * check
     * @return result
     */
    public boolean nullCheck()
   {
       boolean ans=true;
       if(firstname.getText().equals(null) || lastname.getText().equals(null) || photo.isEmpty() || year.getText().isEmpty() || month.getText().isEmpty() || day.getText().isEmpty() || id.getText().isEmpty() || password.getText().isEmpty() )
           ans=false;
       return ans;
   }
    /**
     * sumbit sign up
     * @param event e
     * @throws NoSuchAlgorithmException e
     */
    @FXML
    public void toSumbit(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        if (nullCheck()) {
            String fname = firstname.getText();
            account.addFirstName(fname);
            String lname = lastname.getText();
            account.addLastName(lname);
            String Id = id.getText();
            account.ID = Id;
            int y, d, m;
            y = Integer.parseInt(year.getText());
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            LocalDate bd = LocalDate.of(y, m, d);
            account.addBdate(bd);
            String Password = password.getText();
            account.addPassword(toHash(Password));
            LocalDate l = LocalDate.now();
            account.addjDate(l);
            String Bio = bio.getText();
            account.addBio(Bio);
            account.addPhotoPath(photo);
            JSONtool jsoNtool = new JSONtool();
            data += jsoNtool.toJSON(account);
            try {
                int rslt = authenticationServiceImp.begin(2, data);
                System.out.println(rslt);
                if (rslt == 0) {
                    File file = new File("./files/Remember.txt");
                    System.out.println(remember.isSelected());
                    System.out.println(Id);
                    if (remember.isSelected()) {
                        FileWriter f = null;
                        try {
                            f = new FileWriter(file);
                            f.write(Id);
                            f.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (file.exists())
                            file.delete();
                    }
                    File ExitMode=new File("./files/Setting/ExitMode.txt");
                    File Theme=new File("./files/Setting/Theme.txt");
                        FileWriter onExitMode=null;
                        FileWriter onTheme=null;
                        try {
                            onTheme=new FileWriter(Theme);
                            onExitMode=new FileWriter(ExitMode);
                            onExitMode.write("0");
                            onExitMode.close();
                            onTheme.write("0");
                            onTheme.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
        else {
            alaart("Please fill all fields!");
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
        if(res.get()==ButtonType.OK){
        Stage window = (Stage) mainArea.getScene().getWindow();
        Parent Root= FXMLLoader.load(getClass().getResource("Authentication.fxml"));
        Scene Aview=new Scene(Root);
        window.setScene(Aview);
        window.show();}
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
     * timeline
     */
    public void toTimeline()
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
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
