package Client;

import CoreDetails.MovieDBDetails;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient
{
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

            Scanner keyboard = new Scanner(System.in);
            String inputMessage = "";
            String message = "0££BITCH";
            while(running)
            {

                output.println(message);
                output.flush();

                String response = input.nextLine();
                System.out.println("Response: " +response);


            }
            dataSocket.close();
        }
        catch(IOException iox)
        {
            iox.printStackTrace();
        }
    }
}
