package Tools;
import entity.Tweet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
/**
 * This class defines Tweet Tool
 * @author Samin Mahdipour
 * @version 1.0
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
     * add a new tweet
     */
    public void add() {
        try {
            LocalDateTime n = LocalDateTime.now();
            String strl = n.toString();
            String str= giveT(strl);
            String Name = "./Data/Tweets/" + account.ID + "/" + str;
            Path path1 = Paths.get(Name);
            Files.createDirectories(path1);
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Go On:");
            String t = scanner.next();
            Tweet tweet = new Tweet();
            tweet.addPublisher(account);
            LocalDate now = LocalDate.now();
            tweet.addDate(now);
            tweet.addData(t);
            tweet.makeDDU(Name);
            System.out.println("Tweeted Successfully!");
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
     *
     * @throws IOException check
     */
    public void ret() throws IOException {
        boolean check = false;
        File folder = null;
        while (!check) {
            try {
                folder = new File("./Data/retweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        System.out.println("Enter index of tweet you want to retweet: ");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice > tf.length)
                            throw new InvalidChoiceException();
                        else {
                            File l = new File("./Data/Tweets/" + account.ID + "/"+tf[choice-1]+"/ddu");
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
                                check = true;
                            }
                            fr.close();
                        }
                    }
                }
            } catch (InvalidChoiceException e) {
                System.out.println("Invalid choice,try again");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                check=true;
            } catch (noTweetException e) {
                System.out.println("No tweet to retweet!");
                check=true;
            }
        }
    }

    /**
     * add a comment
     */
    public void comment()
    {
        boolean check = false;
        File folder = null;
        while (!check) {
            try {
                folder = new File("./Data/Tweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        System.out.println("Enter index of tweet you want to add comment: ");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice > tf.length)
                            throw new InvalidChoiceException();
                        else {
                            File l = new File("./Data/Tweets/" + account.ID + "/"+tf[choice-1]+"/ddu");
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
                                commentTool.add(twt);

                                check = true;
                            }
                            fr.close();
                        }
                    }
                }
            } catch (InvalidChoiceException e) {
                System.out.println("Invalid choice,try again");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                check=true;
            } catch (noTweetException e) {
                System.out.println("No tweet to comment!");
                check=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * lika a tweet
     * @throws IOException check
     */
    public void like() throws IOException {
        boolean check = false;
        File folder = null;
        while (!check) {
            try {
                folder = new File("./Data/Tweets/" + account.ID + "/");
                if (!folder.exists())
                    throw new FileNotFoundException();
                else {
                    profile();
                    if (tf.length == 0)
                        throw new noTweetException();
                    else {
                        System.out.println("Enter index of tweet you want to like: ");
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();
                        if (choice > tf.length)
                            throw new InvalidChoiceException();
                        else {
                            File l = new File("./Data/Tweets/" + account.ID + "/"+tf[choice-1]+"/ddu");
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
                                LikeTool likeTool=new LikeTool();
                                likeTool.addAccount(account);
                                likeTool.add(twt);
                                check = true;
                            }
                            fr.close();
                        }
                    }
                }
            } catch (InvalidChoiceException e) {
                System.out.println("Invalid choice,try again");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                check=true;
            } catch (noTweetException e) {
                System.out.println("No tweet to like!");
                check=true;
            }
        }
    }

    /**
     * remove a tweet
     */
    public void remove()
  {
      boolean check=false;
      File folder=null;
      while(!check) {
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
                 else  {
                      System.out.println("Enter index of tweet you want to remove: ");
                      Scanner scanner = new Scanner(System.in);
                      int choice = scanner.nextInt();

                      if (choice > nn)
                          throw new InvalidChoiceException();
                      else {
                          File rmv = new File("./Data/Tweets/" + account.ID + "/" + tf[choice - 1]);
                          if (!rmv.exists())
                              throw new FileNotFoundException();
                          else {
                              check=deleteDirectoryRecursionJava6(rmv);
                              if(check)
                                  System.out.println("Deleted Successfully!");
                          }
                      }
                  }
              }
          } catch (FileNotFoundException e) {
              System.out.println("File does not found");
              e.printStackTrace();
              check=true;
          }
          catch (InvalidChoiceException e)
          {
              System.out.println("Invalid choice,try again");
          } catch (IOException e) {
              e.printStackTrace();
          } catch (noTweetException e) {
              System.out.println("No tweet to remove!");
              check=true;
          }
      }
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
                    for (String t:twts) {
                        System.out.println(i+"- "+t);
                        i++;
                    }
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
