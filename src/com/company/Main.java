package com.company;

import Services.*;
import Tools.AccountChecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 *  Twitter
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Main {

    public static void main(String[] args) throws AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, IOException, AccountChecker.IdException, AccountChecker.BioException {
        Twitter twitter = new Twitter();
       // twitter.manager.start();
    }
}