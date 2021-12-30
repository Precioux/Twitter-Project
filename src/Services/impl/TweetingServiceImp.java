package Services.impl;
import Tools.TweetTool;
import Services.*;
import com.google.gson.Gson;
import entity.Account;
import requestsFormats.ForServices;

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

    /**
     * add acount
     * @param account data
     */
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
        ForServices forServices =gson.fromJson(jData, ForServices.class);
        int choice= forServices.choice;
        TweetTool tweetTool=new TweetTool();
        tweetTool.addAccount(account);
        if(choice==1)
        {
            tweetTool.add(forServices.data);
        }
        else
        {
            if(choice==2)
            {
                int rslt=tweetTool.remove(forServices.data);
                changeNext(rslt);
            }
            else
            {
                if(choice==3) {
                   int rslt=tweetTool.like(forServices.data);
                   changeNext(rslt);
                }
                else
                {
                    if(choice==4)
                    {
                      int rslt=tweetTool.ret(forServices.data);
                      changeNext(rslt);
                    }
                    else
                    {
                        if(choice==5)
                        {
                            int rslt=tweetTool.comment(forServices.data);
                            changeNext(rslt);
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
