package com.company;
import Allimplement.*;
import Services.*;
import Tools.AccountChecker;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/**
 * This class defines manager
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Manager {
    AuthenticationServiceImp authenticationService=new AuthenticationServiceImp();
    TweetingServiceImp tweetingService=new TweetingServiceImp();
    ObserverServiceImp observerService=new ObserverServiceImp();
    TimeLineServiceImp timeLineService=new TimeLineServiceImp();
    /**
     *
     * @throws AuthenticationService.InvalidChoiceException check
     * @throws AccountChecker.BioException check
     * @throws NoSuchAlgorithmException check
     * @throws IOException check
     * @throws AccountChecker.IdException check
     */
    public void start() throws AuthenticationService.InvalidChoiceException, AccountChecker.BioException, NoSuchAlgorithmException, IOException, AccountChecker.IdException {
        if(authenticationService.begin()==1)
        {
            tweetingService.addAccount(authenticationService.connectASTS());
            go();
        }
    }

    /**
     *
     * @throws IOException check
     */
   public void go() throws IOException {
       int next=tweetingService.begin();
       switch (next)
       {
           case 6:
           {
               timeLineService.addAccount(authenticationService.connectASTS());
               int nxt= timeLineService.begin();
               if(nxt==-1)
                   go();
              break;
           }
           case 7:
           {
               observerService.addAccount(authenticationService.connectASTS());
               int nxt=observerService.begin();
               if(nxt==4)
                   go();
               break;
           }
           case 8:
           {
               break;
           }
       }
   }
}
