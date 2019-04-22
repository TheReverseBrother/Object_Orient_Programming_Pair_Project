package Server;

import CoreDetails.MovieDBDetails;

import java.net.ServerSocket;
import java.net.Socket;

public class MovieServer
{
    private ServerSocket serverSocket;
    private int maxConnections;
    private int listenPort;

    public MovieServer(int maxConnections, int listenPort)
    {
        this.maxConnections = maxConnections;
        this.listenPort = listenPort;
    }


    public static void main(String[] args)
    {

        MovieServer server = new MovieServer(MovieDBDetails.SERVER_PORT, MovieDBDetails.maxConnections);

        server.setUpHandlers();
        server.acceptConnections();
    }
    //Set up the connectionPool - create all threads that will be reused during the lifetime
    //of the server
    public void setUpHandlers()
    {
        for(int i = 0; i <MovieDBDetails.maxConnections;++i)
        {
            Connection currentHandler = new Connection();
            Thread t = new Thread(currentHandler);
            t.start();
        }
    }

    /**
     *Once handlers are set up connections can be accepted
     */
    public void acceptConnections()
    {
        try
        {
            ServerSocket server = new ServerSocket(MovieDBDetails.SERVER_PORT, 5);
            Socket incomingConnection = null;
            while(true)
            {
                System.out.println("Incoming :D");
                incomingConnection = server.accept();
                handleConnection(incomingConnection);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("FUUUUUUCK");
        }
    }

    /**
     * Takes in the connection to handle and creates a thread
     * @param connectionToHandle
     */
    public void handleConnection(Socket connectionToHandle)
    {
        Connection.processRequest(connectionToHandle);
    }
}
