package Test;

import DAOs.MysqlMovieDAO;
import DAOs.WatchedDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WatchedDAOTest {

    WatchedDAO watched = null;
    JSONObject expectedObject = null;
    JSONArray expectedArray = null;
    JSONObject resultObject = null;
    JSONArray resultArray = null;
    String expected = null;
    String result = null;
    boolean expectedBoolean;
    boolean resultBoolean;

    @Before
    public void before()
    {
        watched = new WatchedDAO();
    }

    @Test
    public void getAllWatchedMovies()
    {
        expectedArray = new JSONArray("[{\"movieID\":\"14\",\"title\":\"Iron Man\"},{\"movieID\":\"14\",\"title\":\"Iron Man\"},{\"movieID\":\"15\",\"title\":\"Iron Man 2\"},{\"movieID\":\"16\",\"title\":\"x men first class\"},{\"movieID\":\"19\",\"title\":\"Tammy\"},{\"movieID\":\"19\",\"title\":\"Tammy\"}]\n");
        resultArray = watched.GetAllWatchedMovies("1");
        assertEquals(expectedArray.toString(),resultArray.toString());

        expectedArray = null;
        resultArray = watched.GetAllWatchedMovies("2");
        assertEquals(expectedArray,resultArray);
    }

    @Test
    public void checkIfWatched()
    {
        expectedBoolean = true;
        resultBoolean = watched.CheckIfWatched("1","15");
        assertEquals(expectedBoolean,resultBoolean);

        expectedBoolean = false;
        resultBoolean = watched.CheckIfWatched("2","50");
        assertEquals(expectedBoolean,resultBoolean);
    }

    @Test
    public void getAllWatchedMoviesForClient()
    {
        expectedArray = new JSONArray("[{\"year\":\"2008\",\"director\":\"Jon Favreau\",\"format\":\"DVD\",\"rating\":\"PG-13\",\"runtime\":\"126 min\",\"movieID\":\"14\",\"title\":\"iron man\",\"copies\":\"3\",\"plot\":\"Tony Stark. Genius, billionaire, playboy, philanthropist. Son of legendary inventor and weapons contractor Howard Stark. When Tony Stark is assigned to give a weapons presentation to an Iraqi unit led by Lt. Col. James Rhodes, he's given a ride on enemy lines. That ride ends badly when Stark's Humvee that he's riding in is attacked by enemy combatants. He survives - barely - with a chest full of shrapnel and a car battery attached to his heart. In order to survive he comes up with a way to miniaturize the battery and figures out that the battery can power something else. Thus Iron Man is born. He uses the primitive device to escape from the cave in Iraq. Once back home, he then begins work on perfecting the Iron Man suit. But the man who was put in charge of Stark Industries has plans of his own to take over Tony's technology for other matters.\",\"genre\":\"Marvel, Action, Adventure, Sci-Fi\",\"starring\":\"Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow\",\"location\":\"\",\"id\":\"14\",\"poster\":\"null\",\"barcode\":\"null\",\"user_rating\":\"null\"},{\"year\":\"2010\",\"director\":\"Jon Favreau\",\"format\":\"DVD\",\"rating\":\"PG-13\",\"runtime\":\"124 min\",\"movieID\":\"15\",\"title\":\"iron man 2\",\"copies\":\"3\",\"plot\":\"With the world now aware of his dual life as the armored superhero Iron Man, billionaire inventor Tony Stark faces pressure from the government, the press, and the public to share his technology with the military. Unwilling to let go of his invention, Stark, along with Pepper Potts, and James \\\"Rhodey\\\" Rhodes at his side, must forge new alliances - and confront powerful enemies.\",\"genre\":\"Disney, marvel,Action, Adventure, Sci-Fi\",\"starring\":\"Robert Downey Jr., Gwyneth Paltrow, Don Cheadle, Scarlett Johansson\",\"location\":\"\",\"id\":\"15\",\"poster\":\"null\",\"barcode\":\"null\",\"user_rating\":\"null\"},{\"year\":\"2011\",\"director\":\"Matthew Vaughn\",\"format\":\"DVD\",\"rating\":\"PG-13\",\"runtime\":\"132 min\",\"movieID\":\"16\",\"title\":\"x men first class\",\"copies\":\"1\",\"plot\":\"Before Charles Xavier and Erik Lensherr took the names Professor X and Magneto, they were two young men discovering their powers for the first time. Before they were archenemies, they were closest of friends, working together, with other Mutants (some familiar, some new), to stop the greatest threat the world has ever known. In the process, a rift between them opened, which began the eternal war between Magneto's Brotherhood and Professor X's X-MEN.\",\"genre\":\"Action, Adventure, Sci-Fi\",\"starring\":\"James McAvoy, Laurence Belcher, Michael Fassbender, Bill Milner\",\"location\":\"\",\"id\":\"16\",\"poster\":\"null\",\"barcode\":\"null\",\"user_rating\":\"null\"},{\"year\":\"2014\",\"director\":\"Ben Falcone\",\"format\":\"DVD\",\"rating\":\"R\",\"runtime\":\"97 min\",\"movieID\":\"19\",\"title\":\"tammy\",\"copies\":\"1\",\"plot\":\"Tammy, who was recently fired from a Topper Jack's fast food restaurant, returns home only to find her husband enjoying a romantic meal with the neighbor. She quickly packs her necessities, and travels down three houses to her parent's home. Upon denied use of her mom's car to drive to Niagara Falls, she quickly resorts to an \\\"ailing\\\" grandmother, who also lives in the home...Only instead of traveling alone, Grandma Pearl wants in on the road trip. After realizing Grandma Pearl has the funds, they hit the road. Pearl soon proves to be quite the alcoholic despite her diabetes, and Tammy quickly turns into the \\\"baby-sitter.\\\" From finding love in a bar to robbing a Topper Jack's in order to bail Pearl out of jail,the quirky adventure will have you finding yourself riding along for the misadventures of Tammy.\",\"genre\":\"Comedy\",\"starring\":\"Melissa McCarthy, Susan Sarandon, Kathy Bates, Allison Janney\",\"location\":\"\",\"id\":\"19\",\"poster\":\"null\",\"barcode\":\"null\",\"user_rating\":\"null\"},{\"year\":\"2014\",\"director\":\"Ben Falcone\",\"format\":\"DVD\",\"rating\":\"R\",\"runtime\":\"97 min\",\"movieID\":\"19\",\"title\":\"tammy\",\"copies\":\"1\",\"plot\":\"Tammy, who was recently fired from a Topper Jack's fast food restaurant, returns home only to find her husband enjoying a romantic meal with the neighbor. She quickly packs her necessities, and travels down three houses to her parent's home. Upon denied use of her mom's car to drive to Niagara Falls, she quickly resorts to an \\\"ailing\\\" grandmother, who also lives in the home...Only instead of traveling alone, Grandma Pearl wants in on the road trip. After realizing Grandma Pearl has the funds, they hit the road. Pearl soon proves to be quite the alcoholic despite her diabetes, and Tammy quickly turns into the \\\"baby-sitter.\\\" From finding love in a bar to robbing a Topper Jack's in order to bail Pearl out of jail,the quirky adventure will have you finding yourself riding along for the misadventures of Tammy.\",\"genre\":\"Comedy\",\"starring\":\"Melissa McCarthy, Susan Sarandon, Kathy Bates, Allison Janney\",\"location\":\"\",\"id\":\"19\",\"poster\":\"null\",\"barcode\":\"null\",\"user_rating\":\"null\"}]\n");
        resultArray = watched.GetAllWatchedMoviesForClient("1");

        assertEquals(expectedArray.toString(),resultArray.toString());

        expectedArray = new JSONArray();
        resultArray = watched.GetAllWatchedMoviesForClient("4");
        assertEquals(expectedArray,resultArray);
    }
}