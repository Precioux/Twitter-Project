package Services;
import Tools.TweetTool;
import materials.Account;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * This class defines Tweeting Service
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public interface TweetingService  {
    /**
     *
     * @return reflex to manager
     * @throws IOException check
     */
    public int begin() throws IOException;

    /**
     *
     * @param next next
     */
    public void changeNext(int next);

    /**
     *
     * @return account ID
     */
    public Account connectTSOS();
}
