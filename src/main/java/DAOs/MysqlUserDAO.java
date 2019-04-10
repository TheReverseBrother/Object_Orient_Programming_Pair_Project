package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlUserDAO extends MysqlDAO implements UserDAOInterface
{
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

    @Override
    public String registerUser(String Username, String Password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        int userID = 0;
        String dbPassword = null;

        if (checkIfUserExists(Username))
        {
            result = "User already registered with this email please try another";
            return result;
        }
        else
        {

            try
            {
                con = this.getConnection();
                String query = "INSERT INTO USERS(EMAIL,PASSWORD) VALUES(?,?)";
                ps = con.prepareStatement(query);
                ps.setString(1, Username);
                ps.setString(2, Password);
                ps.executeUpdate();
                result = "Registered Congratulations Login to Enjoy Features";

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
    }

    public Boolean checkIfUserExists(String username)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE EMAIL=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            System.out.println("RS" + rs);
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
