import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Niek on 2-3-2017.
 */
public class UserTest {
    private static List<User> users;
    private static List<User> following;

    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();
        Location testlocation = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");

        for (int i = 0; i < 10; i++) {
            users.add(new User(" Test User " + i, " Test Password " + i, "Test Bio " + i,
                    testlocation, "Test website " + i));
        }

        following = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            following.add(new User(" Test following " + i, " Test Password " + i, "Test Bio " + i,
                    testlocation, "Test website " + i));
        }
    }

    @Test
    public void addKweet() throws Exception {
        int i;
        Location location = new Location(1, 1, "Venlo");

        for (i = 0; i < 10; i++) {
            Kweet testKweet = new Kweet("Test Kweet " + i, users.get(1));
            users.get(1).addKweet(testKweet);
        }
        assertEquals("Expected a different number of kweets", 10, users.get(1).getKweets().size());

        Kweet nullkweet = null;
        users.get(1).addKweet(nullkweet);

        assertEquals("Expected a different number of kweets", 10, users.get(1).getKweets().size());
    }

    @Test
    public void removeKweet() throws Exception {
        Kweet testKweet = new Kweet("Test Kweet " , users.get(1));
        users.get(1).addKweet(testKweet);
        assertEquals("Expected different number of kweets",1,users.get(1).getKweets().size());
        users.get(1).removeKweet(testKweet);
        assertEquals("Expected different number of kweets",0,users.get(1).getKweets().size());
    }

    @Test
    public void addFollowing() throws Exception {
        int i = 9;
        for (User follower : following) {
            users.get(i).addFollowing(follower);
            i--;
        }

        assertTrue("Expected last user to have first following", users.get(9).getFollowing().contains(following.get(0)));
        assertTrue("Expected first user to have last following", users.get(0).getFollowing().contains(following.get(9)));
    }

    @Test
    public void removeFollowing() throws Exception {
        users.get(1).addFollowing(following.get(0));
        users.get(1).addFollowing(following.get(1));
        users.get(1).addFollowing(following.get(2));
        assertEquals("Expected a different number of followings", 3, users.get(1).getFollowing().size());
        users.get(1).removeFollowing(following.get(0));
        assertEquals("Expected a different number of followings", 2, users.get(1).getFollowing().size());
    }

}