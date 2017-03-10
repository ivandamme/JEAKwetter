import com.kwetter.dao.KweetDAO_Impl;
import com.kwetter.dao.UserDAO;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Kweet;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


public class kweetServiceTest {

    @InjectMocks
    private KweetService ks;
    @Mock
    private KweetDAO_Impl kwetterDAO;
    @Mock
    private UserDAO_Impl userDAO;

    public kweetServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        // will instantiate "kwetterDAO" and userDAO
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() throws Exception {
        User user1 = mock(User.class);
        ks.createUser(user1);
        //verify number of times method is called on mock object
        verify(userDAO, Mockito.times(1)).createUser(user1);
    }


    @Test
    public void editUser() throws Exception {
        User user1 = mock(User.class);
        ks.createUser(user1);
        user1.setPassword("test");
        ks.editUser(user1);
        Mockito.verify(userDAO, Mockito.times(1)).editUser(user1);

    }

    @Test
    public void getFollowers() throws Exception {
        User user1 = mock(User.class);
        ks.getFollowing(user1);
        Mockito.verify(userDAO, Mockito.times(1)).getAllFollowing(user1);
    }

    @Test
    public void placeKweet() throws Exception {
        Kweet kweet1 = mock(Kweet.class);
        ks.placeKweet(kweet1);
        Mockito.verify(kwetterDAO, Mockito.times(1)).create(kweet1);
    }

    @Test
    public void removeKweet() throws Exception {
        Kweet kweet1 = mock(Kweet.class);
        User user1 = mock(User.class);
        kweet1.setPoster(user1);
        ks.editKweet(kweet1);
        ks.removeKweet(kweet1);
        Mockito.verify(kwetterDAO, Mockito.times(1)).removeKweet(kweet1);
    }


    @Test
    public void followUser() throws Exception {
        User user1 = mock(User.class);
        ks.createUser(user1);
        User user2 = mock(User.class);
        ks.createUser(user2);
        ks.followUser(user1, user2);
        ks.editUser(user1);
        Mockito.verify(userDAO, Mockito.times(2)).editUser(user1);
    }

}