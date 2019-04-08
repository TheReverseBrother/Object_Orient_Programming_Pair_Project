package MothballedCode;

import CoreDetails.MovieDBDetails;
import DAOs.MysqlMovieDAO;
import Exceptions.DAOException;
import Server.ServerOptions;
import org.json.JSONArray;
import org.json.JSONObject;

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
                JSONObject returnObject = null;
                JSONArray returnArray = null;
                while(connected)
                {
                    incomingMessage = input.nextLine();
                    System.out.println("Incoming Request: "+ incomingMessage);

                    String[] messageArray = incomingMessage.split("££");
                    int choice = Integer.parseInt(messageArray[0]);
                    ServerOptions options = ServerOptions.values()[choice];
                    MysqlMovieDAO movies = new MysqlMovieDAO();
                    switch(options)
                    {
                        case REGISTER:
                            System.out.println("Register Request");
                            returnMessage = RegisterFunction(messageArray);
                            output.println(returnMessage);
                            output.flush();
                            break;
                        case LOGIN:
                            System.out.println("Login Request");
                            returnArray = movies.findAllMovies();
                            output.println(returnArray.toString());
                            output.flush();
                            break;
                        case SEARCHBYACTOR:
                            System.out.println("Search by actor Request");
                            returnArray = movies.findMovieByActor(messageArray[1]);
                            output.println(returnArray.toString());
                            output.flush();
                            break;
                        case SEARCHBYTITLE:
                            System.out.println("Search by title request");
                            returnObject = movies.findMovieByTitle(messageArray[1]);
                            output.println(returnObject.toString());
                            output.flush();
                            break;
                        case SEARCHBYDIRECTOR:
                            System.out.println("search by director request");
                            returnArray = movies.findMovieByDirector(messageArray[1]);
                            output.println(returnArray.toString());
                            output.flush();
                            break;
                        case UPDATE:
                            System.out.println("Update Request");
                            returnMessage = "Update";
                            output.println(returnMessage);
                            output.flush();
                            break;
                        case WATCHED:
                            System.out.println("Watched Request");
                            returnMessage = "Watched";
                            output.println(returnMessage);
                            output.flush();
                            break;
                        case RECOMMEND:
                            System.out.println("Recommend Request");
                            returnMessage = "Recommend";
                            output.println(returnMessage);
                            output.flush();
                            break;
                        default:
                            System.out.println("Invalid Input.");
                    }
                    System.out.println("Here");
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
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    public static String RegisterFunction(String[] Info)
    {
     return "Registered";
    }
}
