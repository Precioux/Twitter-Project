package com.company;
/**
 * This class defines tool
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Tool {
    Account account=new Account();

    public class noTweetException extends Exception {}
    public static class InvalidChoiceException extends Exception {}

    /**
     *
     * @param account data
     */
    protected void addAccount(Account account)
    {
        this.account=account;
    }
}
