import com.kwetter.dao.KweetDAO_Impl;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import static org.junit.Assert.assertEquals;


import static org.junit.Assert.*;

/**
 * Created by Niek on 8-3-2017.
 */
public class DAOJpaImplTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    EntityManager em;
    UserDAO_Impl userDao;

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        userDao = new UserDAO_Impl(em);
    }

    @Test
    public void testCreateUser() {
        // create user
        Location location = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");
        User user = new User("NiekForTesting", "TEST", "Hello im test", location, "www.test.nl", role);
        userDao.createUser(user);

        // find user
        User finduserNiek = userDao.findUserByUserName("NiekForTesting");
        assertNotNull(finduserNiek);
    }


}
