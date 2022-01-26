package graphics;

import graphics.Controllers.ThemeController;
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
 * this class defines Theme controller
 */
public class ThemeControllerImp implements ThemeController {

    @FXML
    private ToggleButton dark;

    @FXML
    private ToggleButton light;

    @FXML
    private ToggleGroup theme;
    @FXML
    private Label result;
    /**
     * initialize
     */
    public void initialize() {
        try {
            getBefore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        theme.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>()
                {
                    public void changed(ObservableValue<? extends Toggle> ov,
                                        final Toggle toggle, final Toggle new_toggle)
                    {
                        System.out.println(((ToggleButton)new_toggle).getText());
                        if(dark.isSelected()) {
                            set(1);
                            result.setText("Dark Theme");
                        }
                        else {
                            set(0);
                            result.setText("Light Theme");
                        }
                    }
                });
    }
    /**
     * set theme in setting
     * @param mode m
     */
    public void set(int mode)
    {
        File file=new File("./files/Setting/Theme.txt");
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
        if (light.isSelected()){
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();}
        else {
            Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
            Scene signUpview=new Scene(signUpRoot);
            Stage window=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(signUpview);
            window.show();
        }

    }

    /**
     * get previous Data
     * @throws FileNotFoundException e
     */
    public void getBefore() throws FileNotFoundException {
        int s=-1;
        File file=new File("./files/Setting/Theme.txt");
        FileReader fileReader=new FileReader(file);
        Scanner scanner=new Scanner(fileReader);
        if(scanner.hasNextInt()){
            s=scanner.nextInt();
            System.out.println("previous:" +s);
        }
        if(s==0)
        {
            light.setSelected(true);
            dark.setSelected(false);
            result.setText("Light Theme");
        }
        else {
            dark.setSelected(true);
            light.setSelected(false);
            result.setText("Dark Theme");
        }
    }
}
