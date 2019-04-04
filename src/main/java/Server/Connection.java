package Server;

import DAOs.MysqlMovieDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Connection implements Runnable
{
    private Socket socketToHandle;

    private static List pool = new LinkedList();


    public Connection(){}

    public static void processRequest(Socket incomingClient)
    {
        synchronized(pool)
        {
            pool.add(pool.size(),incomingClient);
            //Notify all that there is a thread to be serviced
            pool.notifyAll();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            //Lock the pool if another thread has the lock then we will have to wait
            synchronized (pool)
            {
                //while the pool is empty, wait
                while(pool.isEmpty())
                {
                    try
                    {
                        pool.wait();
                    }
                    catch(InterruptedException ie)
                    {
                        return;
                    }
                }
                //At this point we have the lock on the pool and it is not empty
                socketToHandle = (Socket)pool.remove(0);
                //Interact with the client
                handleConnection();
            }
        }
    }

    public void handleConnection()
    {
        try
        {
            boolean connected = true;
            //Output and Input streams
            OutputStream out = socketToHandle.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = socketToHandle.getInputStream();
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
//                        returnMessage = RegisterFunction(messageArray);
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
                    case QUIT:
                        connected = false;
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
                System.out.println("Here");
            }
            output.close();
            out.close();
            in.close();
            socketToHandle.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("HAH BITCH");
        }
    }
}
