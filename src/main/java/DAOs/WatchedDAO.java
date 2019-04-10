package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchedDAO extends MysqlDAO implements WatchedDAOInterface
{
    @Override
    public JSONArray GetAllWatchedMovies(String user)
    {
        int userID = Integer.parseInt(user);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray WatchedList = new JSONArray();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM WATCHEDTABLE WHERE USERID=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,userID);

            rs = ps.executeQuery();
            if(rs.next())
            {
                rs.beforeFirst();
                while(rs.next())
                {
                    String title   = rs.getString("MovieTitle");
                    String movieID = rs.getString("movieID");

                    JSONObject Watched = new JSONObject();
                    Watched.put("title      ",""+title);
                    Watched.put("movieID      ",""+movieID);

                    WatchedList.put(Watched);
                }
            }
            else
            {
                return null;
            }

        }
        catch(SQLException e)
        {

        }
        finally {
            try{
                if(rs !=null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if( con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch(SQLException e)
            {
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
            ps.setInt(1,userID);
            ps.setInt(2,movieID);
            ps.setString(3,title);
            ps.executeUpdate();
            result = "SuccesfullyAdded";

        }
        catch(SQLException e)
        {
            System.out.println("SQL");
            e.printStackTrace();
        }
        finally {
            try{
                if(rs !=null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if( con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch(SQLException e)
            {

            }
        }
        return result;
    }

    @Override
    public Boolean CheckIfWatched(String user,String movie)
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
            ps.setInt(1,userID);
            ps.setInt(2,movieID);
            rs = ps.executeQuery();

            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {

        }
        finally {
            try{
                if(rs !=null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if( con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch(SQLException e)
            {
            }
        }

        return false;
    }
}
