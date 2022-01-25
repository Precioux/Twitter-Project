package graphics.Controllers;

import javafx.fxml.FXML;

import java.io.IOException;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines start controller interface
 */
public interface StartController  {
    /**
     * to timeLine
     * @throws IOException e
     */
    void  toTimeLine() throws IOException;

    /**
     * to authentication
     * @throws IOException e
     */
    void toAuthentication() throws IOException;

    /**
     * check
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void Check(javafx.event.ActionEvent actionEvent) throws IOException;

    /**
     * refresh start
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void freshStart(javafx.event.ActionEvent actionEvent) throws IOException;
}
