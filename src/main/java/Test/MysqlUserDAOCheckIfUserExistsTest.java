package Test;

import DAOs.MysqlUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MysqlUserDAOCheckIfUserExistsTest
{
    MysqlUserDAO user = null;
    private String username;
    private boolean expectedResult;
    @Before
    public void before()
    {
        user = new MysqlUserDAO();
    }

    public MysqlUserDAOCheckIfUserExistsTest(String username, Boolean expectedResult)
    {
        this.username = username;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object> data()
    {
        return Arrays.asList(new Object[][] {
                {"tomassmith@gmail.com",true},{"JohnLoane@gmail.com", false},{"test@g.com",true},{"test2@g.com",true},{"JuliusCaesar@Roma.com",false}
        });
    }

    @Test
    public void testCheckIfexists()
    {
        assertEquals(expectedResult,user.checkIfUserExists(username));
    }
}