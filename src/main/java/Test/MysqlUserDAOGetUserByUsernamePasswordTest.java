package Test;

import DAOs.MysqlUserDAO;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.management.ConstructorParameters;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class MysqlUserDAOGetUserByUsernamePasswordTest {

    MysqlUserDAO user = null;
    private String Username;
    private String password;
    private String result;

    @Before
    public void before() {
        user = new MysqlUserDAO();
    }
    public MysqlUserDAOGetUserByUsernamePasswordTest(String username, String Password, String result)
    {
        this.Username = username;
        this.password = Password;
        this.result = result;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][] {{"tomassmith@gmail.com", "MRMEESEEKS", "true££1"},{"test@g.com","test","true££2"},{"test123@g.com","test","false££Invalid Username And Password"}});
    }

    @Test
    public void testUsernamePassword()
    {
        try
        {
            assertEquals(result, user.findUserByUsernamePassword(Username,password));
        }
        catch(Exception e)
        {

        }

    }
}