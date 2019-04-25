package DAOs;

import Client.Client;
import Exceptions.DAOException;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class MysqlUserDAO extends MysqlDAO implements UserDAOInterface
{
    /**
     * Find a username by username password returns message if both items match
     * @param username
     * @param Password
     * @return
     * @throws DAOException
     */
    @Override
    public String findUserByUsernamePassword(String username, String Password) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        int userID = 0;
        String dbPassword = "";
        try
        {
            System.out.println("HOOO" + username);
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE EMAIL=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next())
            {
                userID = rs.getInt("UserID");
                System.out.println("In RS" + userID);
                dbPassword = rs.getString("Password");
                System.out.println(dbPassword);
                System.out.println("Passed: " + Password);
            }


            if (dbPassword.equals(Password))
            {
                result = "true££" + userID;
            }
            else
            {
                result = "false££Invalid Username And Password";
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("findAllUsers() " + e.getMessage());
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
                throw new DAOException("findAllUsers() " + e.getMessage());
            }
        }

        return result;
    }

    /**
     * takes in username and password and add it to the user DB returns a success message
     * @param Username
     * @param Password
     * @return
     */
    @Override
    public String registerUser(String Username, String Password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        int userID = 0;
        String dbPassword = null;
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO USERS(EMAIL,PASSWORD) VALUES(?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1,Username);
            ps.setString(2,Password);
            ps.executeUpdate();
            result = "Registered Congratulations Login to Enjoy Features";

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

    /**
     *  Pass in username and checks if said user is already registerd
     * @param username
     * @return
     */
    public Boolean checkIfUserExists(String username)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        int rowCount = 0;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE EMAIL=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();


            while(rs.next())
            {
                rowCount++; //or rs.getString("column name");
            }


            if(rs.next()){rowCount =1 ;}


        }
        catch (SQLException e)
        {
            System.out.println("Error In PDO");
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
                System.out.println("test");
            }
        }
        if(rowCount == 1){check = true;}
        return check;
    }


    /**
     * Deletes a user from user table returns a message if successful
     * @param user
     * @return
     */
    @Override
    public String deleteUser(String user)
    {
        int userID = Integer.parseInt(user);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        String dbPassword = null;
        try
        {
            con = this.getConnection();
            String query = "DELETE from USERS WHERE USERID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1,userID);
            ps.executeUpdate();
            result = "Account DELETED";

        }
        catch(SQLException e)
        {
            System.out.println("Error In PDO");
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
}
