package graphics;

import graphics.Controllers.ModeController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines mode controller dark
 */
public class ModeControllerDarkImp implements ModeController {
    @FXML
    private Label result;
    @FXML
    private ToggleButton complete;

    @FXML
    private ToggleGroup modeGroup;

    @FXML
    private ToggleButton tray;


    /**
     * initialize
     */
    public void initialize() throws FileNotFoundException {
        result.setText("Choose Mode");
        getBefore();
        modeGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>()
                {
                    public void changed(ObservableValue<? extends Toggle> ov,
                                        final Toggle toggle, final Toggle new_toggle)
                    {
                        System.out.println(modeGroup.getSelectedToggle());
                        if(tray.isSelected()){
                            result.setText("Hide System Trey Mode");
                            set(1);

                        }
                        else {
                            result.setText("Exit Completely Mode");
                            set(0);

                        }
                    }
                });
    }

    /**
     * get previous Data
     * @throws FileNotFoundException e
     */
    public void getBefore() throws FileNotFoundException {
        int s=-1;
        File file=new File("./files/Setting/ExitMode.txt");
        FileReader fileReader=new FileReader(file);
        Scanner scanner=new Scanner(fileReader);
        if(scanner.hasNextInt()){
            s=scanner.nextInt();
            System.out.println("previous:" +s);
        }
        if(s==0)
        {
            complete.setSelected(true);
            tray.setSelected(false);
            result.setText("Exit Completely Mode");
        }
        else {
            tray.setSelected(true);
            complete.setSelected(false);
            result.setText("Hide System Trey Mode");
        }
    }
    /**
     * set mode in setting
     * @param mode m
     */
    public void set(int mode)
    {
        System.out.println("set is "+mode);
        File file=new File("./files/Setting/ExitMode.txt");
        FileWriter fileWriter=null;
        try{
            fileWriter=new FileWriter(file);
            fileWriter.write(String.valueOf(mode));
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * back
     * @param actionEvent e
     * @throws IOException e
     */
    public void toTimeLine(ActionEvent actionEvent) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }
}

