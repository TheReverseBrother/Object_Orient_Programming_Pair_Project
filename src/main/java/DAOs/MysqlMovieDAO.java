package DAOs;

import DTOs.Movie;
import Exceptions.DAOException;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlMovieDAO extends MysqlDAO implements MovieDAOInterface
{
    @Override
    public List<Movie> findAllMovies() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS";
            ps = con.prepareStatement(query);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();

            while(rs.next())
            {
                int movieID = rs.getInt("ID");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("Plot");
                String location = rs.getString("Location");
                String poster = rs.getString("Poster");
                String rating = rs.getString("Rating");
                String format = rs.getString("Format");
                String year = rs.getString("Year");
                String starring = rs.getString("Starring");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("Barcode");
                String user_rating = rs.getString("User_Rating");

                Movie m = new Movie(movieID,title,genre,director,runtime,plot,location,poster
                ,rating,format,year,starring,copies,barcode,user_rating);
                movies.add(m);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("findAllUsers() " + e.getMessage());
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
                throw new DAOException("findAllUsers() "+ e.getMessage());
            }
        }

        return movies;
    }

    @Override
    public Movie findMovieByTitle(String TITLE) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE TITLE = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,TITLE);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();

            while(rs.next())
            {
                int movieID = rs.getInt("ID");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("Plot");
                String location = rs.getString("Location");
                String poster = rs.getString("Poster");
                String rating = rs.getString("Rating");
                String format = rs.getString("Format");
                String year = rs.getString("Year");
                String starring = rs.getString("Starring");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("Barcode");
                String user_rating = rs.getString("User_Rating");

                m = new Movie(movieID,title,genre,director,runtime,plot,location,poster
                        ,rating,format,year,starring,copies,barcode,user_rating);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("findMovieByTitle() " + e.getMessage());
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
                throw new DAOException("ffindUserByUsernamePassword() "+ e.getMessage());
            }
        }
        return m;
    }

    @Override
    public List<Movie> findMovieByDirector(String Director) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE DIRECTOR = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,Director);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();

            while(rs.next())
            {
                int movieID = rs.getInt("ID");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("Plot");
                String location = rs.getString("Location");
                String poster = rs.getString("Poster");
                String rating = rs.getString("Rating");
                String format = rs.getString("Format");
                String year = rs.getString("Year");
                String starring = rs.getString("Starring");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("Barcode");
                String user_rating = rs.getString("User_Rating");

                Movie m = new Movie(movieID,title,genre,director,runtime,plot,location,poster
                        ,rating,format,year,starring,copies,barcode,user_rating);
                movies.add(m);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("findAllUsers() " + e.getMessage());
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
                throw new DAOException("findAllUsers() "+ e.getMessage());
            }
        }

        return movies;
    }
}
