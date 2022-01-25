package graphics.Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines theme  controller
 */
public interface ThemeController {

    /**
     * initialize
     */
    public void initialize();

    /**
     * set theme in setting
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
