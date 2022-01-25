package graphics;

import graphics.Controllers.StartController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines Start controller
 */
public class StartControllerImp implements StartController {
    @FXML
    private Pane mainArea;
    /**
     * to timeLine
     * @throws IOException e
     */
    public void  toTimeLine() throws IOException {
        Stage window = (Stage) mainArea.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.show();
        System.out.println("send to timeLine");
    }
    /**
     * to timeLine
     * @throws IOException e
     */
    public void  toDarkTimeLine() throws IOException {
        Stage window = (Stage) mainArea.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.show();
        System.out.println("send to Dark timeLine");
    }
    /**
     * to authentication
     * @throws IOException e
     */
    public void toAuthentication() throws IOException {
       Stage window = (Stage) mainArea.getScene().getWindow();
       Parent Root = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
       Scene Aview = new Scene(Root);
       window.setScene(Aview);
       window.show();
       System.out.println("send to authentication");
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
     * check
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void Check(javafx.event.ActionEvent actionEvent) throws IOException {
        File toCheck = new File("./files/Remember.txt");
        if (toCheck.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(toCheck);
                Scanner scanner = new Scanner(fileReader);
                String u = scanner.next();
                fileReader.close();
                scanner.close();
                File w=new File("./files/Exchange.txt");
                FileWriter fileWriter=new FileWriter(w);
                fileWriter.write(u);
                fileWriter.close();
                setTheme();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else toAuthentication();

    }

    /**
     * refresh start
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void freshStart(javafx.event.ActionEvent actionEvent) throws IOException {
    toAuthentication();
    }

    /**
     * get logo
     * @return logo
     */
    public static String getLogo()
    {
        String logoW="";
        try {
            InputStream inputStream=new FileInputStream("./Config/propertyLogo.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            logoW= properties.getProperty("logoW");
            System.out.println(logoW);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logoW;
    }
}
