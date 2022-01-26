package graphics;

import Tools.Remover;
import graphics.Controllers.WaitController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
/**
 * This class defines Wait  controller
 * @author Samin Mahdipour
 * @version 3.0
 * @since 26.3.2022
 * */
public class WaitControllerImp implements WaitController {
    @FXML
    private AnchorPane area;

    /**
     * initialize
     */
    public void initialize() throws IOException {
        System.out.println("this is wait");
    }
    /**
     * to dark timeLine
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void toDarkTimeLine(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) area.getScene().getWindow();
        Parent Root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene Aview = new Scene(Root);
        window.setScene(Aview);
        window.show();
    }

    @FXML
    /**
     * to remove
     */
    public void toRemove(ActionEvent actionEvent) throws IOException {
        File file=new File("./files/RemoveSource.txt");
        Remover remover=new Remover();
        remover.read();
        if(!file.exists())
        {
            System.out.println("done");
            Stage window = (Stage) area.getScene().getWindow();
            Parent Root = FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
            Scene Aview = new Scene(Root);
            window.setScene(Aview);
            window.show();
        }
    }
}
