package Tools;
import com.google.gson.Gson;
import entity.Tweet;
import requestsFormats.Comment;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
/**
 * This class defines Tweet Tool
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class TweetTool extends Tool {
    protected String[] tf;
    protected String[] twts;

    /**
     *
     * @param str address
     * @return code
     */
    private String giveT(String str) {

        Scanner scan = new Scanner(str).useDelimiter("-");
        String p = scan.next(); //y
        p += scan.next(); //m
        String e = scan.next(); //d
        Scanner sca = new Scanner(e).useDelimiter("T");
        p += sca.next();
        String ee = sca.next();
        Scanner scann = new Scanner(ee).useDelimiter(":");
        p += scann.next();
        p += scann.next();
        return p;
    }

    /**
     * @param t data
     * add a new tweet
     */
    public void add(String t) {
        try {
            LocalDateTime n = LocalDateTime.now();
            String strl = n.toString();
            String str= giveT(strl);
            String Name = "./Data/Tweets/" + account.ID + "/" + str;
            Path path1 = Paths.get(Name);
            Files.createDirectories(path1);
            Tweet tweet = new Tweet();
            tweet.addPublisher(account);
            LocalDate now = LocalDate.now();
            LocalTime localTime=LocalTime.now();
            tweet.addDate(now,localTime);
            tweet.addData(t);
            tweet.makeDDU(Name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param d address
     * @return tweet data
     * @throws IOException check
     */
  private String readTweet(String d) throws IOException {
      FileReader fr=null;
      String twt="";
      try {
          fr=new FileReader("./Data/Tweets/" + account.ID + "/"+d+"/ddu");
          Scanner scanner=new Scanner(fr).useDelimiter("\n");
          String rubbbish=scanner.next();
          rubbbish+=scanner.next();
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
     * @param file file
     * @return true if delete is ok
     * @throws IOException check
     */
    protected boolean deleteDirectoryRecursionJava6(File file) throws IOException {
        int f=0;
        boolean action=false;
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursionJava6(entry);
                }
            }
        }
        if (!file.delete()) {
            f=1;
            throw new IOException("Failed to delete " + file);
        }
      if (f==0) {

          action=true;
      }
      return action;
    }
    /**
     *@return result
     * @param t data
     * @throws IOException check
     */
    public int ret(String t) throws IOException {
        int type=-1;
        File folder = null;
            try {
                folder = new File("./Data/retweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        int choice = findTweet(t);
                        if (choice==-1)
                            type=998;
                        else {
                            File l = new File("./Data/Tweets/" + account.ID + "/"+tf[choice]+"/ddu");
                            FileReader fr = new FileReader(l);
                            if (!l.exists())
                                throw new FileNotFoundException();
                            else {
                                Scanner scanner1=new Scanner(fr).useDelimiter("\n");
                                String rubbish=scanner1.next();
                                String twt="@";
                                twt+=scanner1.next();
                                twt+="  ";
                                twt+=scanner1.next();
                                RetweetTool retweetTool=new RetweetTool();
                                retweetTool.addAccount(account);
                                retweetTool.add(twt);
                                type=0;
                            }
                            fr.close();
                        }
                    }
                }
            } catch (FileNotFoundException e) {
               type=999;
            } catch (noTweetException e) {
                type=7;
            }
            return type;
    }

    /**
     * @return result
     * add a comment
     * @param jData d
     *
     */
    public int comment(String jData)
    {
        Gson gson=new Gson();
        Comment c=gson.fromJson(jData,Comment.class);
        int type=-1;
        File folder = null;
            try {
                folder = new File("./Data/Tweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        int choice = findTweet(c.tweet);
                        if (choice ==-1)
                            throw new InvalidChoiceException();
                        else {
                            File l = new File("./Data/Tweets/" + account.ID + "/"+tf[choice]+"/ddu");
                            FileReader fr = new FileReader(l);
                            if (!l.exists())
                                throw new FileNotFoundException();
                            else {
                                Scanner scanner1=new Scanner(fr).useDelimiter("\n");
                                String rubbish=scanner1.next();
                                String twt="@";
                                twt+=scanner1.next();
                                twt+="  ";
                                twt+=scanner1.next();
                                CommentTool commentTool=new CommentTool();
                                commentTool.addAccount(account);
                                commentTool.add(twt,c.comment);
                                type=0;
                            }
                            fr.close();
                        }
                    }
                }
            } catch (InvalidChoiceException e) {
                type=998;

            } catch (FileNotFoundException e) {
               type=999;
            } catch (noTweetException e) {
               type=8;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return type;
    }

    /**
     * @return result
     * lika a tweet
     * @param t  data
     * @throws IOException check
     */
    public int like(String t) throws IOException {
        File folder = null;
        int type=-1;
            try {
                folder = new File("./Data/Tweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        int choice = findTweet(t);
                        if (choice == -1) {
                              type=998;
                        }
                        else
                        {
                        File l = new File("./Data/Tweets/" + account.ID + "/" + tf[choice] + "/ddu");
                        FileReader fr = new FileReader(l);
                        if (!l.exists())
                            throw new FileNotFoundException();
                        else {
                            Scanner scanner1 = new Scanner(fr).useDelimiter("\n");
                            String rubbish = scanner1.next();
                            String twt = "@";
                            twt += scanner1.next();
                            twt += "  ";
                            twt += scanner1.next();
                            LikeTool likeTool = new LikeTool();
                            likeTool.addAccount(account);
                            likeTool.add(twt);
                            type=0;
                        }
                        fr.close();
                    }
                    }
                }
            }catch (FileNotFoundException e) {
                type=999;

            } catch (noTweetException e) {
                type=6;
            }
return type;
    }

   private int findTweet(String t)
   {
       int ans=-1;
       int i=0;
       for (String s:twts) {
           if(s.equals(t))
               ans=i;
           i++;
       }
       return ans;
   }
    /**
     * @param t  data
     * @return result
     * remove a tweet
     */
    public int remove(String t)
  {
      int type=-1;
      boolean check=false;
      File folder=null;
          try {
              folder=new File("./Data/Tweets/" + account.ID + "/");
              if (!folder.exists())
                  throw new FileNotFoundException();
              else {
                   profile();
                  int nn = tf.length;
                  if (nn <=0) {
                      throw new noTweetException();
                  }
                 else {

                      int choice = findTweet(t);
                      if (choice == -1)
                          type = 998;
                      else{
                          File rmv = new File("./Data/Tweets/" + account.ID + "/" + tf[choice ]);
                      if (!rmv.exists())
                          throw new FileNotFoundException();
                      else {
                          check = deleteDirectoryRecursionJava6(rmv);
                          if (check)
                              type=0;
                      }
                  }
                  }
              }
          } catch (FileNotFoundException e) {
            type=999;
          }
         catch (IOException e) {
              e.printStackTrace();
          } catch (noTweetException e) {
              type=5;
          }
          return type;
  }

    /**
     * profile
     */
    protected void profile(){
        File folder=null;
        try {
            folder=new File("./Data/Tweets/" + account.ID + "/");
            if (!folder.exists())
                throw new FileNotFoundException();
            else {
                 tf = folder.list();
                int n = tf.length;
                if(n==0)
                    throw new noTweetException();
                else{
                twts = new String[n];
                for (int i = 0; i < n; i++)
                    twts[i] = readTweet(tf[i]);
                int i=1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (noTweetException e) {
            System.out.println("You haven't tweeted yet!");
        }
    }

}
