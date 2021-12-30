package Services.impl;
import Tools.TweetTool;
import Services.*;
import com.google.gson.Gson;
import entity.Account;
import requestsFormats.ForTweetingService;

import java.io.IOException;

/**
 * This class defines Tweeting Service Imp
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class TweetingServiceImp implements TweetingService {
    Account account=new Account();
    private int next=0;
    public void addAccount(Account account) {
        this.account=account;
    }

    /**
     * @param jData data
     * @return reflex to manager
     * @throws IOException check
     */
    public int begin(String jData) throws IOException {
        Gson gson=new Gson();
        ForTweetingService forTweetingService=gson.fromJson(jData, ForTweetingService.class);
        int choice=forTweetingService.choice;
        TweetTool tweetTool=new TweetTool();
        tweetTool.addAccount(account);
//        System.out.println("\n");
//        int choice=0;
//        Scanner scanner=new Scanner(System.in);
//        System.out.println(account.fname+",What do you intend to do?\n1-add a new Tweet\n2-Remove Previous Tweets\n3-Like Previous Tweets\n4-Retweet Previous Tweets\n5-add a comment to Previous Tweets\n6-Timeline\n7-Let's Socialize!\n8-Exit");
//        System.out.println("Please notice that each operation can be done after a minute!\n(if you add a tweet at 6:46 you cannot tweet until 6.47!)");
//        choice=scanner.nextInt();
        if(choice==1)
        {
            tweetTool.add(forTweetingService.data);
          //  System.out.println("\n");
           // begin();
        }
        else
        {
            if(choice==2)
            {
                int rslt=tweetTool.remove(forTweetingService.data);
                changeNext(rslt);
              //  System.out.println("\n");
               // begin();
            }
            else
            {
                if(choice==3) {
                   int rslt=tweetTool.like(forTweetingService.data);
                   changeNext(rslt);
                 //   System.out.println("\n");
                   // begin();
                }
                else
                {
                    if(choice==4)
                    {
                      int rslt=tweetTool.ret(forTweetingService.data);
                      changeNext(rslt);
                      //  System.out.println("\n");
                     //   begin();
                    }
                    else
                    {
                        if(choice==5)
                        {
                            int rslt=tweetTool.comment(forTweetingService.data);
                            changeNext(rslt);
                            //System.out.println("\n");
                       //     begin();
                        }
                        else
                        {
                            if(choice==6)
                            {
                                changeNext(6);
                            }
                            else
                            {
                                if(choice==7)
                                {
                                    changeNext(7);
                                }
                                else
                                {
                                    if(choice==8)
                                        changeNext(8);
                                }
                            }
                        }
                    }
                }
            }
        }
        return next;
    }

    /**
     *
     * @param next next
     */
    public void changeNext(int next){
        this.next=next;
    }
    /**
     *
     * @return account ID
     */
    public Account connectTSOS()
    {
        return account;
    }
}
