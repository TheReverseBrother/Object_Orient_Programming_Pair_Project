package Server;

import CoreDetails.MovieDBDetails;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MovieDBServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket listeningSocket = new ServerSocket(MovieDBDetails.SERVER_PORT);

            boolean continueRuninng = true;
            boolean connected = true;
            while(continueRuninng)
            {
                Socket dataSocket = listeningSocket.accept();
                //Output and Input streams
                OutputStream out = dataSocket.getOutputStream();
                PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

                InputStream in = dataSocket.getInputStream();
                Scanner input = new Scanner(new InputStreamReader(in));



                //The messages in and messages out we only ever need to use these 2 they are reusable
                String returnMessage = "";
                String incomingMessage = "";

                while(connected)
                {
                    System.out.println("Test Connection");

                    incomingMessage = input.nextLine();
                    String[] messageArray = incomingMessage.split("££");
                    System.out.println("Length is" +messageArray.length);
                    System.out.println(messageArray[0] +"-");
                    int choice = Integer.parseInt(messageArray[1]);
                    ServerOptions options = ServerOptions.values()[choice];
                    switch(options)
                    {
                        case REGISTER:
                            //Return information to be displayed back by the functions
                            returnMessage = RegisterFunction(messageArray);
                            break;
                        case LOGIN:
                            returnMessage = "login";
                            break;
                        case SEARCHBYACTOR:
                            returnMessage = "Actor";
                            break;
                        case SEARCHBYTITLE:
                            returnMessage = "Title";
                            break;
                        case SEARCHBYDIRECTOR:
                            returnMessage = "Director";
                            break;
                        case UPDATE:
                            returnMessage = "Update";
                            break;
                        case WATCHED:
                            returnMessage = "Watched";
                            break;
                        case RECOMMEND:
                            returnMessage = "Recommend";
                            break;
                        default:
                            System.out.println("Invalid Input.");
                    }

                    // We build the return message using the switch statements no need to juggle outputstreams
                    output.println(returnMessage);
                    output.flush();
                }

                //Step 5  - Shut down connection
                dataSocket.close();
                continueRuninng = false;
            }
            listeningSocket.close();
        }
        catch(IOException iox)
        {
            iox.printStackTrace();
        }
    }

    public static String RegisterFunction(String[] Info)
    {
     return "Registered";
    }
}
