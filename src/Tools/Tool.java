package Tools;

import entity.*;

/**
 * This class defines tool
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Tool {
    Account account=new Account();
    public class noTweetException extends Exception {}
    public static class InvalidChoiceException extends Exception {}
    /**
     *
     * @param aaccount data
     */
    public void addAccount(Account aaccount)
    {
        account=aaccount;
    }
}
