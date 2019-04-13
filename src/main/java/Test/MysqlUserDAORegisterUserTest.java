package Test;

import DAOs.MysqlUserDAO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MysqlUserDAORegisterUserTest {

    MysqlUserDAO user = null;
    private String username;
    private String password;
    private String expectedResult;

    @Before
    public void before()
    {
        user = new MysqlUserDAO();
    }

    public MysqlUserDAORegisterUserTest(String username,String password,String expectedResult)
    {
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object> data()
    {
        return Arrays.asList(new Object[][] {
                {"AaronRichySon@gmail.com","Pa$$word", "Registered Congratulations Login to Enjoy Features"},
                {"TomasSmith@gmail.com","MRMEESEEKS",""},{"test3@gmail.com","test123","Registered Congratulations Login to Enjoy Features"},{"AaronRichySon@gmail.com","Pa$$word", ""}
        });
    }
    @Test
    public void testRegister()
    {
        assertEquals(expectedResult,user.registerUser(username,password));
    }
}