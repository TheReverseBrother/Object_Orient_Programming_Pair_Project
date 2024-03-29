package Server;

import CoreDetails.MovieDBDetails;
import DAOs.MysqlMovieDAO;
import DAOs.MysqlUserDAO;
import DAOs.WatchedDAO;
import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Connection implements Runnable
{
    private Socket socketToHandle;

    private static List pool = new LinkedList();
    public static MysqlUserDAO user = new MysqlUserDAO();
    public static WatchedDAO watched = new WatchedDAO();
    public static MysqlMovieDAO movies = new MysqlMovieDAO();

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

    /**
     * The connection is established and remains established as long as the user wishes for it to stay active
     *
     */
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
            System.out.println("Started Connection");
            while(connected)
            {
                incomingMessage = input.nextLine();
                System.out.println("Incoming Request: "+ incomingMessage);


                String[] messageArray = incomingMessage.split(MovieDBDetails.BREAKINGCHARACTERS);
                int choice = Integer.parseInt(messageArray[0]);
                ServerOptions options = ServerOptions.values()[choice];
                switch(options)
                {
                    case REGISTER:
                        System.out.println("Register Request");
                        returnMessage = Register(messageArray[1],messageArray[2]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case LOGIN:
                        System.out.println("Login Request");
                        System.out.println(messageArray[1]);
                        returnMessage = user.findUserByUsernamePassword(messageArray[1],messageArray[2]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case SEARCHBYACTOR:
                        System.out.println("Search by actor Request");
                        returnArray = movies.findMovieByActor(messageArray[1]);
                        if(returnArray == null)
                        {
                            returnMessage = "null";
                        }
                        else
                        {
                            returnMessage = returnArray.toString();
                        }
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case SEARCHBYTITLE:
                        System.out.println("Search by title request");
                        returnObject = movies.findMovieByTitle(messageArray[1]);
                        if(returnObject == null)
                        {
                            returnMessage = "null";
                        }
                        else
                        {
                            returnMessage = returnObject.toString();
                        }
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case SEARCHBYDIRECTOR:
                        System.out.println("search by director request");
                        returnArray = movies.findMovieByDirector(messageArray[1]);
                        if(returnArray == null)
                        {
                            returnMessage = "null";
                        }
                        else
                        {
                            returnMessage = returnArray.toString();
                        }
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case UPDATE:
                        System.out.println("Update Request");
                        JSONObject movie = new JSONObject(messageArray[1]);
                        returnMessage = movies.updateMovieByID(movie);
//                        returnMessage = movies.updateMovieByTitle(messageArray[1],messageArray[2]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case ADD_TO_WATCHED:
                        System.out.println("Add To Watched Request");
                        returnMessage = AddtoWatchedChecker(messageArray[1],messageArray[2],messageArray[3]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case RECOMMEND:
                        System.out.println("Recommend Request");
                        returnMessage = recommendMovie(messageArray[1]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case QUIT:
                        System.out.println("QUITTING");
                        connected = false;
                        break;
                    case GET_WATCHED:
                        System.out.println("Get Watched Request");
                        returnArray = watched.GetAllWatchedMoviesForClient(messageArray[1]);
                        if(returnArray == null)
                        {
                            returnMessage = "null";
                        }
                        else
                        {
                            returnMessage = returnArray.toString();
                        }
                        output.println(returnMessage);
                        output.flush();
                        break;
                    case DELETEUSER:
                        returnMessage = deleteUser(messageArray[1]);
                        output.println(returnMessage);
                        output.flush();
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
                System.out.println("End OF Switch");
            }
            System.out.println("Outside While");
            output.close();
            out.close();
            in.close();
            socketToHandle.close();
        }
        catch(IOException e)
        {
            System.out.println("Connection Abruptly Severed");
        }
        catch(DAOException e)
        {
            System.out.println("PDO Problem");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Connection Abruptly Terminated");
        }
    }

    /**
     * Takes in user ID Deletes it and associated table properties and
     * then returns message user has been deleted
     * @param userID
     * @return
     */
    public String deleteUser(String userID)
    {
        watched.removeUserWatched(userID);
        return user.deleteUser(userID);
    }

    /**
     * Takes in username password checks if said username
     * is already in use and either rejects or registers the user
     * @param Username
     * @param Password
     * @return
     */
    public String Register(String Username,String Password)
    {
        Boolean exists = user.checkIfUserExists(Username);

        if(exists)
        {
            return "false££User Exists";
        }
        else
        {
            return "true££"+user.registerUser(Username,Password);
        }
    }

    /**
     * takes in the users id who requested to add to watched, Takes in the movie then want to add and the movies Title
     * returns false if movie already in watched list and true if it has been added
     * @param user, User ID
     * @param movie, Movie ID
     * @param title, Movie Title
     * @return
     */
    public String AddtoWatchedChecker(String user,String movie, String title)
    {
        boolean isWatched = watched.CheckIfWatched(user,movie);

        if(isWatched)
        {
            return "false££Movie Has Been Already Added";
        }
        else
        {
            return "true££"+watched.AddtoWatched(user,movie,title);
        }
    }

    /**
     * Takes in the users id checks their list of watched movies finds a genre from one of the movies searches
     * for movies using that genre then returns a list of 10 movies based upon if they have watched them or not
     * @param user
     * @return
     */
    public static String recommendMovie(String user)
    {
        JSONArray WatchedList = watched.GetAllWatchedMovies(user);
        Random rand = new Random();
        int i = rand.nextInt(WatchedList.length());
        JSONObject movie = WatchedList.getJSONObject(i);
        String title = movie.get("title").toString();
        JSONObject movie2 = movies.findMovieByTitle(title);
        String genre = movie2.getString("genre");

        String[] genres = genre.split(",");
        if(genres.length > 1)
        {
            int j = rand.nextInt(genres.length);
            JSONArray MoviesWithSimilarGenre = movies.findMovieByGenre(genres[j]);

            for(int x = 0 ; x < MoviesWithSimilarGenre.length(); ++x)
            {
                for (int y = 0; y < WatchedList.length(); ++y)
                {
                    if(MoviesWithSimilarGenre.getJSONObject(x).get("title")== WatchedList.getJSONObject(y).get("title"))
                    {
                        MoviesWithSimilarGenre.remove(x);
                    }
                }
            }
            return MoviesWithSimilarGenre.toString();
        }
        else
        {
            JSONArray MoviesWithSimilarGenre = movies.findMovieByGenre(genres[0]);

            for(int x = 0 ; x < MoviesWithSimilarGenre.length(); ++x)
            {
                for (int y = 0; y < WatchedList.length(); ++y)
                {
                    if(MoviesWithSimilarGenre.getJSONObject(x).get("movieID")== WatchedList.getJSONObject(y).get("movieID"))
                    {
                        MoviesWithSimilarGenre.remove(x);
                    }
                }
            }
            return MoviesWithSimilarGenre.toString();
        }
    }
}
