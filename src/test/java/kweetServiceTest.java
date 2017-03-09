import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.Assert.*;

/**
 * Created by Niek on 7-3-2017.
 */

public class kweetServiceTest {

    private KweetService ks ;

    @Before
    public void setUp() throws Exception {
        ks = new KweetService();
        Location location = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");
        User user = new User("Test user", "TEST", "Hello im test", location, "www.test.nl", role);
        ks.createUser(user);
    }

    @Test
    public void createUser() throws Exception {

    }

    @Test
    public void changeUserName() throws Exception {

    }

    @Test
    public void changeBio() throws Exception {

    }

    @Test
    public void getRecentKweets() throws Exception {

    }

    @Test
    public void getFollowers() throws Exception {

    }

    @Test
    public void placeKweet() throws Exception {

    }

    @Test
    public void removeKweet() throws Exception {

    }

    @Test
    public void getUsers() throws Exception {

    }

    @Test
    public void changeRole() throws Exception {

    }

    @Test
    public void logIn() throws Exception {

    }

    @Test
    public void register() throws Exception {

    }

    @Test
    public void followUser() throws Exception {

    }

}