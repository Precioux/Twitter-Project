package Services;
import entity.Account;

import java.io.IOException;

/**
 * This class defines Tweeting Service
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public interface TweetingService  {
    /**
     * @param jData data
     * @return reflex to manager
     * @throws IOException check
     */
    public int begin(String jData) throws IOException;

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
