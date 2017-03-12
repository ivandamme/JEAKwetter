import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

/**
 * Created by Niek on 12-3-2017.
 */
public class DatabaseCleaner {
    private static final Class<?>[] ENTITY_TYPES = {
            User.class,
            Role.class,
            Location.class
    };
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }

}
