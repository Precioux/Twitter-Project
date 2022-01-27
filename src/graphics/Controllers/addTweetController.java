package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines add tweet controller interface
 */
public interface addTweetController {

        /**
         * sets account
         *
         */
        public void setAccount();

    /**
         * to TimeLine
         * @throws IOException e
         */
        public void toTimeLine() throws IOException;

    /**
         * to dark TimeLine
         * @throws IOException e
         */
        public void toDarkTimeLine() throws IOException;

    /**
         * set Theme
         */
        public void setTheme();

    /**
     * cancel
     * @param event e
     * @throws IOException e
     */
    @FXML
        void cancelIt(ActionEvent event) throws IOException;

    /**
     * add text
     * @param event e
     * @throws IOException e
     */
    @FXML
        void addText(ActionEvent event) throws IOException;

    /**
         * alart
         * @param err error
     * @throws IOException e
         */
        void alaart(String err) throws IOException;


}
