package Tools;

import Services.impl.TweetingServiceImp;
import entity.Account;
import requestsFormats.ForServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Remover {
    TweetingServiceImp tweetingServiceImp=new TweetingServiceImp();
    JSONtool jsoNtool=new JSONtool();
    String user="";
    String tweet="";
    public void read()
    {
        System.out.println("this is remover");
        File file=new File("./files/RemoveSource.txt");
        System.out.println(file.exists());
        FileReader fileReader=null;
        try {
            fileReader=new FileReader(file);
            Scanner scanner=new Scanner(fileReader).useDelimiter("\n");
            user+=scanner.next();
            tweet+=scanner.next();
            fileReader.close();
            remove();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove() throws IOException {
        Account account=new Account();
        account.addID(user);
        tweetingServiceImp.addAccount(account);
        ForServices forServices =new ForServices(2,tweet);
        int rslt=tweetingServiceImp.begin(jsoNtool.toJSON(forServices));

        if(rslt==0)
        {
            File file=new File("./files/RemoveSource.txt");
            file.delete();
        }

        }

}
