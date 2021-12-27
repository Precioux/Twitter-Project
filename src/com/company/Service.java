package com.company;

import Tools.AccountChecker;
import materials.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/**
 * This class defines service
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Service {
    Account account=new Account();

    /**
     *
     * @param account data
     */
    protected void addAcount(Account account)
    {
        this.account=account;
    }

    /**
     *
     * @return next choice
     * @throws IOException check
     * @throws AuthenticationService.InvalidChoiceException check
     * @throws AccountChecker.BioException check
     * @throws NoSuchAlgorithmException check
     * @throws AccountChecker.IdException check
     */
     protected int begin() throws IOException, AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, AccountChecker.IdException, AccountChecker.BioException { return 1 ;};
}
