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
 * this class defines authentication controller interface
 */
public interface AuthenticationController {

    /**
     * alart
     * @param err error
     */
    void alaart(String err);

    /**
     * sign in
     * @param event event
     */
    @FXML
    void toSignIn(ActionEvent event);

    /**
     * to timeLine
     */
    void toTimeline();

    /**
     * to sign up
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toSignUp(ActionEvent event) throws IOException;

    /**
     * submit log
     * @param localTime data
     * @param localDate data
     * @param request data
     * @param result data
     * @param ErrorCode data
     */
    public void submitLog(LocalDate localDate, LocalTime localTime,String request, String result, int ErrorCode);

}
