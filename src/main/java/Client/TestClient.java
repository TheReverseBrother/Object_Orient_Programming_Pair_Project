package Client;

import CoreDetails.MovieDBDetails;
import org.json.JSONArray;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient
{
    public int clientID;
    public ClientServerConnection movies = new ClientServerConnection();
    public static void main(String[] args)
    {
        boolean running = true;
        try
        {
            Socket dataSocket = new Socket("localhost", MovieDBDetails.SERVER_PORT);

            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));
//            String message = "0££TomasSMith@gmail.com££MRMEESEEKS";
            String message = "6££1££19££Tammy";
            boolean continueR = true;
//                while(continueR)
//                {
                    output.println(message);
                    output.flush();
                    String respone = input.nextLine();

//                    JSONArray ja = new JSONArray(respone);
                    System.out.println(respone);
//                }


                dataSocket.close();
        }
        catch(Exception iox)
        {
            iox.printStackTrace();
        }
    }
}
