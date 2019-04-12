package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlMovieDAO extends MysqlDAO implements MovieDAOInterface
{
    @Override
    public JSONArray findAllMovies()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray movies = new JSONArray();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES";
            ps = con.prepareStatement(query);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.beforeFirst();

                while (rs.next())
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


                    JSONObject movie = new JSONObject();
                    movie.put("movieID", "" + movieID);
                    movie.put("title", "" + title);
                    movie.put("genre", "" + genre);
                    movie.put("director", "" + director);
                    movie.put("runtime", "" + runtime);
                    movie.put("plot", "" + plot);
                    movie.put("location", "" + location);
                    movie.put("poster", "" + poster);
                    movie.put("rating", "" + rating);
                    movie.put("format", "" + format);
                    movie.put("year", "" + year);
                    movie.put("starring", "" + starring);
                    movie.put("copies", "" + copies);
                    movie.put("barcode", "" + barcode);
                    movie.put("user_rating", "" + user_rating);


                    movies.put(movie);
                }
            }
            else
            {
                return null;
            }

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

        return movies;
    }

    @Override
    public JSONObject findMovieByTitle(String TITLE)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONObject movie = new JSONObject();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE TITLE = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, TITLE);
            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
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

                movie.put("movieID", "" + movieID);
                movie.put("title", "" + title);
                movie.put("genre", "" + genre);
                movie.put("director", "" + director);
                movie.put("runtime", "" + runtime);
                movie.put("plot", "" + plot);
                movie.put("location", "" + location);
                movie.put("poster", "" + poster);
                movie.put("rating", "" + rating);
                movie.put("format", "" + format);
                movie.put("year", "" + year);
                movie.put("starring", "" + starring);
                movie.put("copies", "" + copies);
                movie.put("barcode", "" + barcode);
                movie.put("user_rating", "" + user_rating);

            }
            else
            {
                return null;
            }
            return movie;
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
        return null;
    }

    @Override
    public JSONArray findMovieByDirector(String Director)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray movies = new JSONArray();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE DIRECTOR = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, Director);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.beforeFirst();

                while (rs.next())
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

                    JSONObject movie = new JSONObject();
                    movie.put("movieID", "" + movieID);
                    movie.put("title", "" + title);
                    movie.put("genre", "" + genre);
                    movie.put("director", "" + director);
                    movie.put("runtime", "" + runtime);
                    movie.put("plot", "" + plot);
                    movie.put("location", "" + location);
                    movie.put("poster", "" + poster);
                    movie.put("rating", "" + rating);
                    movie.put("format", "" + format);
                    movie.put("year", "" + year);
                    movie.put("starring", "" + starring);
                    movie.put("copies", "" + copies);
                    movie.put("barcode", "" + barcode);
                    movie.put("user_rating", "" + user_rating);


                    movies.put(movie);
                }
            }
            else
            {
                return null;
            }

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

        return movies;
    }

    @Override
    public JSONArray findMovieByActor(String Actor)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray movies = new JSONArray();
        try
        {
            String actorQuery = "%" + Actor + "%";
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE STARRING LIKE ? GROUP BY title";
            ps = con.prepareStatement(query);
            ps.setString(1, actorQuery);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.beforeFirst();
                while (rs.next())
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

                    JSONObject movie = new JSONObject();
                    movie.put("movieID", "" + movieID);
                    movie.put("title", "" + title);
                    movie.put("genre", "" + genre);
                    movie.put("director", "" + director);
                    movie.put("runtime", "" + runtime);
                    movie.put("plot", "" + plot);
                    movie.put("location", "" + location);
                    movie.put("poster", "" + poster);
                    movie.put("rating", "" + rating);
                    movie.put("format", "" + format);
                    movie.put("year", "" + year);
                    movie.put("starring", "" + starring);
                    movie.put("copies", "" + copies);
                    movie.put("barcode", "" + barcode);
                    movie.put("user_rating", "" + user_rating);


                    movies.put(movie);

                }
            }
            else
            {
                return null;
            }

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

        return movies;
    }

    @Override
    public String updateMovieByID(JSONObject movie)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println(movie);
        try
        {
            con = this.getConnection();
            String query = "UPDATE movies set title=?, genre=?,director=?,runtime=?,plot=?,starring=?,user_rating=? WHERE ID = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, movie.getString("title"));
            ps.setString(2, movie.getString("genre"));
            ps.setString(3, movie.getString("director"));
            ps.setString(4, movie.getString("runtime"));
            ps.setString(5, movie.getString("plot"));
            ps.setString(6, movie.getString("starring"));
            ps.setString(7, movie.getString("user_rating"));
            ps.setString(8, movie.getString("MovieID"));

            ps.executeUpdate();
            return "SuccessFully Updated";
        }
        catch (SQLException e)
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
        return null;
    }

    @Override
    public String updateMovieByTitle(String input)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray movies = new JSONArray();
        String movieIDString = "";
        String[] inputArray = input.split(":");

        try
        {
            movies.put(findMovieByTitle(inputArray[1]));
        }
        catch (Exception e)
        {
        }

        movieIDString = movies.getJSONObject(0).get("ID").toString();

        try
        {
            con = this.getConnection();
            String query = "UPDATE MOVIES SET user_rating =? WHERE MovieID=?";
            ps = con.prepareStatement(query);
            ps.setString(1, inputArray[0]);
            ps.setString(2, movieIDString);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.beforeFirst();
                while (rs.next())
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

                    JSONObject movie = new JSONObject();
                    movie.put("movieID", "" + movieID);
                    movie.put("title", "" + title);
                    movie.put("genre", "" + genre);
                    movie.put("director", "" + director);
                    movie.put("runtime", "" + runtime);
                    movie.put("plot", "" + plot);
                    movie.put("location", "" + location);
                    movie.put("poster", "" + poster);
                    movie.put("rating", "" + rating);
                    movie.put("format", "" + format);
                    movie.put("year", "" + year);
                    movie.put("starring", "" + starring);
                    movie.put("copies", "" + copies);
                    movie.put("barcode", "" + barcode);
                    movie.put("user_rating", "" + user_rating);


                    movies.put(movie);

                }
            }
            else
            {
                return null;
            }

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

        return "Successfully updated User_rating";
    }


    @Override
    public String getGenres(String title)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String genre = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT genre FROM MOVIES WHERE TITLE = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.first();
                genre = rs.getString("genre");
                return genre;

            }
            else
            {
                return null;
            }
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
        return genre;
    }


    @Override
    public JSONObject findWatchedMovie(String userID)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONObject movie = new JSONObject();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM movies,watchedtable WHERE UserID=?";
            ps = con.prepareStatement(query);
            ps.setString(1, userID);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
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

                movie.put("movieID", "" + movieID);
                movie.put("title", "" + title);
                movie.put("genre", "" + genre);
                movie.put("director", "" + director);
                movie.put("runtime", "" + runtime);
                movie.put("plot", "" + plot);
                movie.put("location", "" + location);
                movie.put("poster", "" + poster);
                movie.put("rating", "" + rating);
                movie.put("format", "" + format);
                movie.put("year", "" + year);
                movie.put("starring", "" + starring);
                movie.put("copies", "" + copies);
                movie.put("barcode", "" + barcode);
                movie.put("user_rating", "" + user_rating);

            }
            else
            {
                return null;
            }
            return movie;
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
                System.out.println("SHIT!");
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public JSONArray findMovieByGenre(String GENRE)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray movies = new JSONArray();
        try
        {
            String genreQuery = "%" + GENRE + "%";
            con = this.getConnection();
            String query = "SELECT * FROM MOVIES WHERE GENRE LIKE ? LIMIT 10";
            ps = con.prepareStatement(query);
            ps.setString(1, genreQuery);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();
            if (rs.next())
            {
                rs.first();
                while (rs.next())
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

                    JSONObject movie = new JSONObject();
                    movie.put("movieID", "" + movieID);
                    movie.put("title", "" + title);
                    movie.put("genre", "" + genre);
                    movie.put("director", "" + director);
                    movie.put("runtime", "" + runtime);
                    movie.put("plot", "" + plot);
                    movie.put("location", "" + location);
                    movie.put("poster", "" + poster);
                    movie.put("rating", "" + rating);
                    movie.put("format", "" + format);
                    movie.put("year", "" + year);
                    movie.put("starring", "" + starring);
                    movie.put("copies", "" + copies);
                    movie.put("barcode", "" + barcode);
                    movie.put("user_rating", "" + user_rating);


                    movies.put(movie);

                }
            }
            else
            {
                return null;
            }

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

        return movies;
    }
}