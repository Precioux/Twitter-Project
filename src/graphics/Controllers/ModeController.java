package graphics.Controllers;

import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines mode controller interface
 */
public interface ModeController {

    /**
     * initialize
     * @throws FileNotFoundException e
     */
    public void initialize() throws FileNotFoundException;

    /**
     * before data
     * @throws FileNotFoundException e
     */
    public void getBefore() throws FileNotFoundException;

    /**
     * set mode in setting
     * @param mode m
     */
    void set(int mode);

    /**
     * back
     * @param actionEvent e
     * @throws IOException e
     */
    public void toTimeLine(ActionEvent actionEvent) throws IOException;
}

