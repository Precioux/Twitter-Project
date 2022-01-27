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
 * this class defines comment controller interface
 */
public interface CommentController {

    /**
     * sets account
     *
     */
    public void setAccount();

    /**
     * to timeLine
     * @param event e
     * @throws IOException e
     */
    @FXML
    void backTimeLine(ActionEvent event) throws IOException;

    /**
     * comment
     * @param event e
     */
    @FXML
    void commentIt(ActionEvent event);

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

}
