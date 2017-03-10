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

/**
 * Created by Niek on 8-3-2017.
 */
public class DAOJpaImplTest {

    private EntityManager em;
    private KweetService kweetService;
    private KweetDAO_Impl kweetDAO;
    private UserDAO_Impl userDao;



    @Before
    public void setUp() {
        kweetDAO = new KweetDAO_Impl();
        userDao = new UserDAO_Impl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPU");
        em = emf.createEntityManager();
        userDao.setEm(em);
        kweetDAO.setEm(em);
        kweetService = new KweetService();
        kweetService.setKweetDAO(kweetDAO);
        kweetService.setUserDAO(userDao);
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
