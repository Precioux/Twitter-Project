package com.company;
import java.util.Scanner;
/**
 * This class defines Observer Service
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class ObserverService extends Service{
    /**
     *
     * @return reflex
     */
    protected int begin()
    {
            int naxt=0;
            ObserverTool observerTool = new ObserverTool();
            observerTool.addAccount(account);
            boolean check=false;
            while (!check) {
                try {
                    System.out.println("1-Follow\n2-Unfollow\n3-View profile\n4-back");
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();
                    if (choice < 1 || choice > 4)
                        throw new Tool.InvalidChoiceException();
                    else
                    {
                        switch (choice)
                        {
                            case 1:
                            {
                                observerTool.follow();
                                break;
                            }
                            case 2:
                            {
                                observerTool.unfollow();
                                break;
                            }
                            case 3:
                            {
                                observerTool.profile();
                                break;
                            }
                            case 4:
                            {
                                naxt=4;
                                check=true;
                                break;
                            }
                        }
                    }
                } catch (Tool.InvalidChoiceException e) {
                    System.out.println("Invalid choice,try again");
                }
            }
     return naxt;
    }


}
