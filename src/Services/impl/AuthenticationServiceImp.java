package Services.impl;
import Services.*;
import Tools.AccountChecker;
import com.google.gson.Gson;
import entity.Account;
import resultFormats.Result;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

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

    /**
     * add account
     * @param account data
     */
    public void addAccount(Account account) {
        this.account=account;
    }
    public int begin(int choice,String jData) throws InputMismatchException, Services.AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, AccountChecker.IdException, IOException {
        int next=0;
        Gson gson=new Gson();
        if(choice==2) {
            String ans=accountChecker.getInfo(jData);
            System.out.println("answer from account checker: "+ans);
            Result SignUpResult=gson.fromJson(ans, Result.class);
            switch (SignUpResult.type)
            {
                case 0:
                {
                    Account account=gson.fromJson(SignUpResult.data,Account.class);
                    addAccount(account);
                    break;
                }
                case 3:
                {
                    next=3;
                    break;
                }
                case 4:
                {
                    next=4;
                    break;
                }
            }
        }
        else {
           String ans=accountChecker.checkInfo(jData);
            Result logInResult=gson.fromJson(ans, Result.class);
            System.out.println("AuS type: "+logInResult.type);
            switch (logInResult.type)
            {
                case 0:
                {
                    Account account=gson.fromJson(logInResult.data,Account.class);
                    addAccount(account);
                    break;
                }
                case 1:
                {
                    next=1;
                    break;
                }
                case 2:
                {
                    next=2;
                    break;
                }
            }
        }
      return next;
    }
    /**
     *add account
     * @return account
     */
    public Account connect()
    {
        return account;
    }
}
