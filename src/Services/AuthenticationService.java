package Services;
import Tools.AccountChecker;
import materials.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;
/**
 * This class defines AuthenticationService
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public interface AuthenticationService {
    public class InvalidChoiceException extends Exception {}

    /**
     * add
     * @param account data
     */

    AccountChecker accountChecker=new AccountChecker();
    /**
     *
     * @throws InputMismatchException check choice(Integer)
     * @throws InvalidChoiceException check choice(1 or 2)
     */
    public int begin() throws InputMismatchException, InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, AccountChecker.IdException, IOException ;
    /**
     *
     * @return account
     */
    public Account connectASTS();
}
