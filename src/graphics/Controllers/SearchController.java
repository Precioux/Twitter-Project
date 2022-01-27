package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines search controller interface
 */
public interface SearchController {

    /**
     * sets account
     *
     */
    public void setAccount();

    /**
     * submit log
     * @param localTime data
     * @param localDate data
     * @param request data
     * @param result data
     * @param ErrorCode data
     */
    public void submitLog(LocalDate localDate, LocalTime localTime,String request, String result, int ErrorCode);

    /**
     * alart
     * @param err error
     * @throws IOException e
     */
    void alaart(String err) throws IOException;

    /**
     * to timeLine
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    void toTimeLine(ActionEvent actionEvent) throws IOException;

    /**
     * search
     * @param event e
     * @throws IOException e
     */
    @FXML
    void searchIt(ActionEvent event) throws IOException;

}
