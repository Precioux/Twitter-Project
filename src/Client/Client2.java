package Client;
/**
 * This class defines client2
 * @author Samin Mahdipour
 * @version 2.0
 * @since 12.7.2021
 * */
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
class Client2 {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 1000);
            BufferedReader bufferedReaderFromK=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader bufferedReaderFromB=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream=new PrintStream(socket.getOutputStream());
            String clientRequest,serverResponse;
            while (true)
            {
                System.out.println(bufferedReaderFromB.readLine());
                clientRequest=bufferedReaderFromK.readLine();
                if(clientRequest.equals("exit"))
                {
                    System.out.println("Closing this connection : " + socket);
                    socket.close();
                    System.out.println("Connection closed");
                    break;
                }
                else {
                    printStream.println(clientRequest);
                    serverResponse = bufferedReaderFromB.readLine();
                    System.out.println("Server : "+serverResponse);
                }
            }
            socket.close();
            bufferedReaderFromB.close();
            bufferedReaderFromK.close();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
