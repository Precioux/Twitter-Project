package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
/**
 * This class defines Wait Dark controller interface
 * @author Samin Mahdipour
 * @version 3.0
 * @since 26.3.2022
 * */
public interface WaitController {
    /**
     * initialize
     * @throws IOException e
     */
    public void initialize() throws IOException;

    /**
     * to dark timeLine
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void toDarkTimeLine(ActionEvent actionEvent) throws IOException;

    @FXML
    /**
     * to remove
     */
    public void toRemove(ActionEvent actionEvent) throws IOException;
}
