import com.kwetter.dao.KweetDAOCollection_Impl;
import com.kwetter.dao.UserDAOCollection_Impl;
import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Niek on 6-3-2017.
 */
public class DAOCollectionImplTest {

    private UserDAOCollection_Impl userDAOCollectionImpl;
    private KweetDAOCollection_Impl kweetDAOCollectionImpl;

    @Before
    public void setUp() throws Exception {
        userDAOCollectionImpl = new UserDAOCollection_Impl();
        kweetDAOCollectionImpl = new KweetDAOCollection_Impl();
        Location location = new Location(1,1,"Venlo");
        Role role = new Role("Moderater");
        User user = new User("Test user","TEST","Hello im test",location,"www.test.nl",role);
        userDAOCollectionImpl.createUser(user);
    }

    //<editor-fold defaultstate="collapsed" desc="User DAO Tests">
    @Test
    public void testCreateUser() throws Exception {
        Location location = new Location(1,1,"Venlo");
        Role role = new Role("Moderater");
        User user = new User("Niek","TEST","Hello im Niek",location,"www.niek.nl",role);
        userDAOCollectionImpl.createUser(user);
        assertEquals(user, userDAOCollectionImpl.findUserByUserName("Niek"));
    }

    @Test
    public void testGetAllUser() throws Exception {
        Location location = new Location(1,1,"Venlo");
        Role role = new Role("Moderater");
        User user1 = new User("Niek","TEST","Hello im Niek",location,"www.niek.nl",role);
        userDAOCollectionImpl.createUser(user1);
        assertEquals(2, userDAOCollectionImpl.getAllUsers().size());
        User user2 = new User("Piet","TEST","Hello im Piet",location,"www.piet.nl",role);
        userDAOCollectionImpl.createUser(user2);
        assertEquals(3, userDAOCollectionImpl.getAllUsers().size());
    }

    @Test
    public void testRemoveUser() throws Exception {
        Location location = new Location(1,1,"Venlo");
        Role role = new Role("Moderater");
        User user1 = new User("Niek","TEST","Hello im Niek",location,"www.niek.nl",role);
        userDAOCollectionImpl.createUser(user1);
        userDAOCollectionImpl.removeUser(user1);
        assertEquals(null, userDAOCollectionImpl.findUserByUserName("Niek"));
    }

    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Kweet DAO Tests">

    @Test
    public void testCreateKweet() throws Exception {
        User user = userDAOCollectionImpl.findUserByUserName("Test user");
        Kweet kweet = new Kweet("test",user);
        kweetDAOCollectionImpl.create(kweet);
        assertEquals(1,kweetDAOCollectionImpl.findAll().size());
    }

    @Test
    public void testRemoveKweet() throws Exception {
        User user = userDAOCollectionImpl.findUserByUserName("Test user");
        Kweet kweet = new Kweet("test",user);
        kweetDAOCollectionImpl.create(kweet);
        assertEquals(1,kweetDAOCollectionImpl.findAll().size());

        kweetDAOCollectionImpl.removeKweet(kweet , user);
        assertEquals(0,kweetDAOCollectionImpl.findAll().size());
    }

    //</editor-fold>

}
