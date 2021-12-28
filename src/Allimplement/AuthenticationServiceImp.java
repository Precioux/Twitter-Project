package Allimplement;
import Services.*;
import Tools.AccountChecker;
import materials.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

/**
 * This class defines AuthenticationServiceImp
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class AuthenticationServiceImp implements AuthenticationService {
    private int choice;
    public class InvalidChoiceException extends Exception {}

    private AccountChecker accountChecker=new AccountChecker();
    Account account=new Account();

    public void addAccount(Account account) {
        this.account=account;
    }

    /**
     *
     * @throws InputMismatchException check choice(Integer)
     * @throws Services.AuthenticationService.InvalidChoiceException check choice(1 or 2)
     */
    public int begin() throws InputMismatchException, Services.AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, AccountChecker.IdException, IOException {
        System.out.println("****Twitter****\n1-Sign in\n2-Sign up");
        int next=0;
        Scanner scanner = new Scanner(System.in);
        int flag=0;
        int choice=0;
        while (flag==0)
        {
            try {
                choice=scanner.nextInt();
                if(choice==2 || choice==1)
                    flag=1;
                else throw new Services.AuthenticationService.InvalidChoiceException();
            }
            catch (Services.AuthenticationService.InvalidChoiceException e)
            {
                System.out.println("Please Enter valid choice");
            }
            catch (MissingFormatArgumentException e)
            {
                System.out.println("Enter Integer Only!");
            }
        }
        next=1;
        if(choice==2)
            addAccount(accountChecker.getInfo());
        else addAccount(accountChecker.checkInfo());
        return next;
    }

    /**
     *
     * @return account
     */
    public Account connectASTS()
    {
        return account;
    }
}
