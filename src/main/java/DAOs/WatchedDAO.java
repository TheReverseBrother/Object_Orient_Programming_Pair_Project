package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class WatchedDAO extends MysqlDAO implements WatchedDAOInterface
{
    @Override
    public JSONArray GetAllWatchedMovies(String user)
    {
        int userID = Integer.parseInt(user);
        Connection con = null;
        PreparedStatement ps = null;
        JSONArray emptyArray = new JSONArray();
        ResultSet rs = null;
        ArrayList<Integer> movieIDs = new ArrayList<>();



        try
        {
            int movieID = -1;
            con = this.getConnection();
            String query = "SELECT * FROM WATCHEDTABLE WHERE USERID=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            System.out.println("test1");
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("test2");
                rs.beforeFirst();
                while (rs.next())
                {
                    System.out.println("test3");
                    movieID = Integer.parseInt(rs.getString("MovieID"));
                    movieIDs.add(movieID);
                    System.out.println(movieIDs);

                }

            }
            else
            {
                return emptyArray;
            }

        }
        catch (SQLException e)
        {
            System.out.println("SHIT");
            e.printStackTrace();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(movieIDs.size()>0){return null;}
        else { return getMovieList(movieIDs);}
    }

    private JSONArray getMovieList(ArrayList<Integer> movieIDs)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray WatchedList = new JSONArray();
        System.out.println("test");
        System.out.println(movieIDs);

        if (movieIDs == null)
        {
            return null;
        }
        else
        {
            try
            {
                for (int i = 0; i < movieIDs.size(); i++)
                {
                    con = this.getConnection();
                    String query = "SELECT * FROM movies WHERE id=?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, 14);
                    System.out.println(query);

                    rs = ps.executeQuery();

                    while (rs.next())
                    {
                        String title = rs.getString("title");
                        String genre = rs.getString("genre");
                        String rating = rs.getString("user_rating");
                        String director = rs.getString("director");
                        String starring = rs.getString("starring");

                        JSONObject Watched = new JSONObject();
                        Watched.put("title", "" + title);
                        Watched.put("genre", "" + genre);
                        Watched.put("user_rating", "" + rating);
                        Watched.put("director", "" + director);
                        Watched.put("starring", "" + starring);
                        System.out.println(WatchedList.toString());

                        WatchedList.put(Watched);
                    }
                }
            }
            catch (SQLException e)
            {
            }
            catch (NullPointerException e)
            {
                System.out.println("reeee");
            }
        }
        return WatchedList;
    }


    @Override
    public String AddtoWatched(String user, String movie, String title)
    {
        int userID = Integer.parseInt(user);
        int movieID = Integer.parseInt(movie);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        try
        {
            System.out.println("Fight Me");
            con = this.getConnection();
            String query = "INSERT INTO WATCHEDTABLE(USERID,MOVIEID,MOVIETITLE) VALUES(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, movieID);
            ps.setString(3, title);
            ps.executeUpdate();
            result = "SuccesfullyAdded";

        }
        catch (SQLException e)
        {
            System.out.println("SQL");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch (SQLException e)
            {

            }
        }
        return result;
    }

    @Override
    public Boolean CheckIfWatched(String user, String movie)
    {
        int userID = Integer.parseInt(user);
        int movieID = Integer.parseInt(movie);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM WATCHEDTABLE WHERE USERID=? AND MOVIEID=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, movieID);
            rs = ps.executeQuery();

            return rs.next();
        }
        catch (SQLException e)
        {

        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch (SQLException e)
            {
            }
        }

        return false;
    }
}
