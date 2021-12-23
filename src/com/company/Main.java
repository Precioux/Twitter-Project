package com.company;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/**
 *  Twitter
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Main {

    public static void main(String[] args) throws AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, IOException, AccountChecker.IdException {
      Twitter twitter=new Twitter();
      twitter.manager.start();

    }
}
