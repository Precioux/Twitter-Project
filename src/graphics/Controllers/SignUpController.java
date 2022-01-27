package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines signUp controller interface
 */
public interface SignUpController {

    /**
     * to cancel
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toCancel(ActionEvent event) throws IOException;

    /**
     * choose photo
     * @param actionEvent event
     * @throws IOException e
     */
    @FXML
    void toChoose(ActionEvent actionEvent) throws IOException;

    /**
     * check
     * @return result
     */
    boolean nullCheck();

    /**
     * sumbit sign up
     * @param event e
     * @throws NoSuchAlgorithmException e
     * @throws IOException e
     */
    @FXML
    void toSumbit(ActionEvent event) throws NoSuchAlgorithmException, IOException;

    /**
     * alart
     * @param err error
     * @throws IOException e
     */
    void alaart(String err) throws IOException;

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
     * to timeLine
     */
    void toTimeline();

}
