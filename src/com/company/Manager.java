package com.company;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/**
 * This class defines manager
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Manager {
    AuthenticationService authenticationService=new AuthenticationService();
    TweetingService tweetingService=new TweetingService();
    ObserverService observerService=new ObserverService();
    TimeLineService timeLineService=new TimeLineService();
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
            tweetingService.addAcount(authenticationService.connectASTS());
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
               timeLineService.addAcount(authenticationService.connectASTS());
               int nxt= timeLineService.begin();
               if(nxt==-1)
                   go();
              break;
           }
           case 7:
           {
               observerService.addAcount(authenticationService.connectASTS());
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
