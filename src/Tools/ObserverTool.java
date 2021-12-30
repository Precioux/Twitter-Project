package Tools;

import com.google.gson.Gson;
import requestsFormats.ForOther;
import resultFormats.Result;

import java.io.*;
import java.util.Scanner;
/**
 * This class defines observer tool
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class ObserverTool extends Tool {
    public class userNotFoundException extends Exception {}
    public class noFollowingException extends Exception{}
    File folder = new File("./Data/Users/");
    protected String[] users = folder.list();
    JSONtool jsoNtool=new JSONtool();
    Gson gson=new Gson();
    /**
     * like
     * @param tweet data
     */
    private void likeIt(String tweet)
    {
        LikeTool likeTool=new LikeTool();
        likeTool.addAccount(account);
        likeTool.add(tweet);
    }

    /**
     * comment
     * @param Tweet data
     */
    private void commentIt(String Tweet,String d)
    {
        CommentTool commentTool=new CommentTool();
        commentTool.addAccount(account);
        commentTool.add(Tweet,d);

    }

    /**
     * retweet
     * @param tweet data
     */
    private void retweetIt(String tweet)
    {
        RetweetTool retweetTool=new RetweetTool();
        retweetTool.addAccount(account);
        retweetTool.add(tweet);
    }
    /**
     * profileForAction
     */
    public int profileForAction(String jData)
    {
        ForOther forOther=gson.fromJson(jData,ForOther.class);
        System.out.println("This is profileA : "+forOther);
        int type=-1;
        String u=forOther.owner;
        try {
            boolean c=isUser(u);
            if(!c)
                throw new userNotFoundException();
            else {
                File userfolder = new File("./Data/Tweets/" + u + "/");
                if (!userfolder.exists())
                    throw new FileNotFoundException();
                else {
                    String[] tweets = userfolder.list();
                    //    System.out.println(tweets.length);
//                    p="";
//                    int i=0;
//                    for (String tweet : tweets) {
//                        p+="___________________________________________________________________________\n";
//                        p+=i+1+"-  " +readTweet(u,tweet)+"\n";
//                        p+="___________________________________________________________________________";
//                        i++;
//                    }
//                    System.out.println("p:\n"+p);
//                    type=0;
                    int choice=forOther.index;
                    if(choice!=-1)
                    {
                      //  System.out.println("1-Like\n2-Comment\n3-Retweet");
                        int r=forOther.action;
                        if(r==1)
                        {
                            likeIt(readTweetw(u,tweets[choice-1]));
                            type=0;
                        }
                        else{
                            if(r==2){
                                commentIt(readTweetw(u,tweets[choice-1]),forOther.d);
                            type=0;}
                            else
                            {
                                if(r==3)
                                    retweetIt(readTweetw(u,tweets[choice-1]));
                                type=0;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            type=999;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (userNotFoundException e) {
            type=9;
        }
      //  Result result=new Result(type,p);
        return type;
    }

    /**
     * profile
     */
    public String profile(String who)
    {
        String p="";
        int type=-1;
        String u=who;
        try {
            boolean c=isUser(u);
            if(!c)
                throw new userNotFoundException();
            else {
                File userfolder = new File("./Data/Tweets/" + u + "/");
                if (!userfolder.exists())
                    throw new FileNotFoundException();
                else {
                    String[] tweets = userfolder.list();
                //    System.out.println(tweets.length);
                   p="";
                    int i=0;
                    for (String tweet : tweets) {
                        p+="___________________________________________________________________________\n";
                        p+=i+1+"-  " +readTweet(u,tweet)+"\n";
                        p+="___________________________________________________________________________";
                        i++;
                    }
                    System.out.println("p:\n"+p);
                    type=0;
                  //  System.out.println("Do you want to react to any tweet?\nif answer is yes enter tweet's index\notherwise enter -1");
                    //int choice=scanner.nextInt();
//                    if(choice!=-1)
//                    {
//                        System.out.println("1-Like\n2-Comment\n3-Retweet");
//                        int r=scanner.nextInt();
//                        if(r==1)
//                        {
//                            likeIt(readTweetw(u,tweets[choice-1]));
//                        }
//                        else{
//                            if(r==2)
//                                commentIt(readTweetw(u,tweets[choice-1]));
//                            else
//                            {
//                                if(r==3)
//                                    retweetIt(readTweetw(u,tweets[choice-1]));
//                            }
//                        }
//                    }
                }
            }
        } catch (FileNotFoundException e) {
            type=999;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (userNotFoundException e) {
           type=9;
        }
        Result result=new Result(type,p);
        return jsoNtool.toJSON(result);
    }

    /**
     *
     * @param u data
     * @param d data
     * @return tweet
     * @throws IOException check
     */
    private String readTweet(String u,String d) throws IOException {
        FileReader fr=null;
        String twt="";
        try {
            fr=new FileReader("./Data/Tweets/" + u + "/"+d+"/ddu");
            Scanner scanner=new Scanner(fr).useDelimiter("\n");
            twt+=scanner.next();
            twt+="  @";
            twt+=scanner.next();
            twt+="  :  ";
            twt+=scanner.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            fr.close();
        }
        return twt;

    }

    /**
     *
     * @param u data
     * @param d data
     * @return  tweet
     * @throws IOException check
     */
    private String readTweetw(String u,String d) throws IOException {
        FileReader fr=null;
        String twt="";
        try {
            fr=new FileReader("./Data/Tweets/" + u + "/"+d+"/ddu");
            Scanner scanner=new Scanner(fr).useDelimiter("\n");
            String rubbish=scanner.next();
            twt+="@";
            twt+=scanner.next();
            twt+="  :  ";
            twt+=scanner.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            fr.close();
        }
        return twt;

    }

    /**
     * unfollow
     */
    public int unfollow(String who)
    {
        int type=-1;
        //while (!check) {
            try {
          //      System.out.println("Enter id of user you want to unfollow: ");
            //    Scanner scanner = new Scanner(System.in);
                String user = who;
                boolean c = isUser(user);
                if (!c)
                    throw new userNotFoundException();
                else {
                    File you = new File("./Data/Users/" + account.ID + "/following");
                    if (you.length() == 0)
                        throw new noFollowingException();
                    else {
                        FollowEditor followEditor = new FollowEditor();
                        followEditor.addAccount(account.ID);
                        boolean check = followEditor.unfollower(user);
                        if (!check)
                            throw new FileNotFoundException();
                        else type=0;
                    }
                }
            }  catch (userNotFoundException e) {
               // System.out.println("No User with given ID,try again");
                type=9;
            } catch (FileNotFoundException e) {
               type=999;
            } catch (noFollowingException e) {
               // System.out.println("You follow no one!");
                type=10;
            }
             return  type;
      //  }
    }

    /**
     * follow
     */
    public int follow(String who){
       int type=-1;
//        while (!check) {
            try {
                String user =who;
                boolean c=isUser(user);
                if(!c)
                    throw new userNotFoundException();
                else {
                    File you = new File("./Data/Users/" + account.ID + "/following");
                    File tofollow = new File("./Data/Users/" + user + "/followers");
                    if(!you.exists() || !tofollow.exists())
                        throw new FileNotFoundException();
                    else
                    {
                        FileWriter fw1=new FileWriter(you,true);
                        FileWriter fw2=new FileWriter(tofollow,true);
                        fw1.write(user+"\n");
                        fw2.write(account.ID+"\n");
                        fw1.close();
                        fw2.close();
                   //     System.out.println(account.fname+",you followed "+user+" successfully!");
                    //    check=true;
                        type=0;
                    }
                }
            }  catch (userNotFoundException e) {
                //System.out.println("No User with given ID,try again");
                type=9;
            } catch (FileNotFoundException e) {
               type=999;
            } catch (IOException e) {
                e.printStackTrace();
            }
        //  }
        return type;
    }

    /**
     *
     * @param user data
     * @return true if user exists
     */
    private boolean isUser(String user)
    {
        boolean ans=false;
        for (String u:users) {
            if(user.equals(u))
                ans=true;
        }
        return ans;
    }

}