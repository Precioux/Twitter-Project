package graphics;

import Services.AuthenticationService;
import Services.impl.AuthenticationServiceImp;
import Tools.AccountChecker;
import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
import entity.Error;
import graphics.Controllers.AuthenticationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import requestsFormats.LogIn;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines authentication controller
 */
public class AuthenticationControllerImp implements AuthenticationController {
    AuthenticationServiceImp authenticationServiceImp=new AuthenticationServiceImp();
    Gson gson=new Gson();
    boolean logFlag=false;
    Account account=new Account();
    JSONtool jsonTool=new JSONtool();
    @FXML
    private GridPane mainArea;
    @FXML
    private TextField idInput;

    @FXML
    private TextField passInput;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;
    @FXML
    private CheckBox remember=new CheckBox();
    /**
     * alart
     * @param err error
     */
    public void alaart(String err)
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(err);
        Optional<ButtonType> res=alert.showAndWait();
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
                toTimeline();
            }
            else {
                toDarkTimeline();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * sign in
     * @param event event
     */
    @FXML
    public void toSignIn(ActionEvent event) {
        String id = idInput.getText();
        String pass = passInput.getText();
        LogIn login = new LogIn(id, pass);
        try {
            int rslt=authenticationServiceImp.begin(1,jsonTool.toJSON(login));
            if(rslt==0) {
                File file=new File("./files/Remember.txt");
                if(remember.isSelected())
                {
                    FileWriter f=null;
                    try {
                        f=new FileWriter(file);
                        f.write(id);
                        f.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    if (file.exists())
                        file.delete();
                }
                account= authenticationServiceImp.connect();
                LocalDate localDate=LocalDate.now();
                LocalTime localTime=LocalTime.now();
                submitLog(localDate,localTime,"logIn","SuccessFul",0);
                System.out.println("Sign In ok");
                File ExitMode=new File("./files/Setting/ExitMode.txt");
                File Theme=new File("./files/Setting/Theme.txt");
                if(!ExitMode.exists() && !Theme.exists() )
                {
                    FileWriter onExitMode=null;
                    FileWriter onTheme=null;
                    try {
                        onTheme=new FileWriter(Theme);
                        onExitMode=new FileWriter(ExitMode);
                        onExitMode.write("0");
                        onExitMode.close();
                        onTheme.write("0");
                        onTheme.close();
                        toTimeline();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    setTheme();
                }

            }
            else
            {
                if(rslt==1 || rslt==2)
                {
                    Error error=new Error();
                    error.errorSearch(rslt);
                    alaart(error.getErrorType());
                    LocalDate localDate=LocalDate.now();
                    LocalTime localTime=LocalTime.now();
                    submitLog(localDate,localTime,"logIn","Failed",rslt);
                }
            }
        } catch (AuthenticationService.InvalidChoiceException e) {
            e.printStackTrace();
        } catch (AccountChecker.BioException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AccountChecker.IdException e) {
            e.printStackTrace();
        }
    }

    /**
     * to TimeLine
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
          System.out.println("send to timeLine");
      } catch (IOException e) {
          e.printStackTrace();
      }

    }

    /**
     * to dark TimeLine
     */
    public void toDarkTimeline()
    {
        File file=new File("./files/Exchange.txt");
        FileWriter fw=null;
        try {
            fw=new FileWriter(file);
            fw.write(account.ID);
            fw.close();
            Stage window = (Stage) mainArea.getScene().getWindow();
            Parent Root = FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
            Scene Aview = new Scene(Root);
            window.setScene(Aview);
            window.show();
            System.out.println("send to timeLine Dark");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * to sign up
     * @param event e
     * @throws IOException e
     */
        @FXML
        public void toSignUp(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
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

}
