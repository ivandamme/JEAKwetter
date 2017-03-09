import com.kwetter.dao.KweetDAOCollection_Impl;
import com.kwetter.dao.UserDAOCollection_Impl;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import org.junit.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by Niek on 8-3-2017.
 */
public class DAOJpaImplTest {



    private UserDAO_Impl userDao;
    private EntityManager em;

    public DAOJpaImplTest() {
        userDao = new UserDAO_Impl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPU");
        em = emf.createEntityManager();
        userDao.setEm(em);
    }

    @Before
    public void setUp() {
        //test
        em.getTransaction().begin();
    }

    @Test
    public void testCreateUser() throws Exception {
        Location location = new Location(1,1,"Venlo");
        Role role = new Role("Moderater");
        User user1 = new User("Niek","TEST","Hello im Niek",location,"www.niek.nl",role);
        userDao.createUser(user1);
        User foundUser = userDao.findUserByUserName(user1.getUserName());
        assertEquals(user1, foundUser);
    }



}
