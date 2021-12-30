package Services.impl;
import Tools.ObserverTool;
import Tools.Tool;
import Services.*;
import com.google.gson.Gson;
import entity.Account;
import requestsFormats.ForServices;

import java.util.Scanner;
/**
 * This class defines Observer Service Imp
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class ObserverServiceImp implements ObserverService {
    Account account=new Account();
    int next=0;
    public void addAccount(Account account) {
        this.account=account;
    }
    /**
     *
     * @return reflex
     */
    public int begin(String jdata)
    {
        Gson gson=new Gson();
        ForServices forServices=gson.fromJson(jdata,ForServices.class);
        ObserverTool observerTool = new ObserverTool();
        observerTool.addAccount(account);
       // boolean check=false;
  //      while (!check) {
        //    System.out.println("1-Follow\n2-Unfollow\n3-View profile\n4-back");
        //  Scanner scanner = new Scanner(System.in);
        int choice = forServices.choice;
//                if (choice < 1 || choice > 4)
//                    throw new Tool.InvalidChoiceException();
//                else
//                {
        switch (choice)
        {
            case 1:
            {
                int rslt=observerTool.follow(forServices.data);
                changeNext(rslt);
                break;
            }
            case 2:
            {
                int rslt=observerTool.unfollow(forServices.data);
                changeNext(rslt);
                break;
            }
            case 3:
            {
                observerTool.profile();
                break;
            }
            case 4:
            {
                next=4;
               // check=true;
                break;
            }
        }
        //   }
        //  }
        return next;
    }

    @Override
    public void changeNext(int next) {
        this.next=next;
    }


}
