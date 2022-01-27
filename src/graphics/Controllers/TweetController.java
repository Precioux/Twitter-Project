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
 * this class defines Tweet controller interface
 */
public interface TweetController {

    /**
     * sets account
     *
     */
    public void setAccount();

    /**
     * go to profile
     * @param actionEvent e
     */
    @FXML
    public void goProfile(ActionEvent actionEvent);

    /**
     * alart
     * @param err error
     * @throws IOException e
     */
    void alaart(String err) throws IOException;

    /**
     * to comment
     * @param event e
     */
    @FXML
    public void commentIt(ActionEvent event);

    /**
     * to like
     * @param event e
     * @throws IOException e
     */
    @FXML
    void likeIt(ActionEvent event) throws IOException;


    /**
     * to like and ret
     * @param acti a
     * @throws IOException e
     */
    void actions(int acti) throws IOException;

    /**
     * to remove
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    void removeIt(ActionEvent actionEvent) throws IOException;

    /**
     * to ret
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toRetweet(ActionEvent event) throws IOException;


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
