package net.dao;


import net.model.User;
import net.model.Role;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
//    private final Map<String, User> userMap = Collections.singletonMap("test",
//            new User(1L, "test", "test","test", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        return entityManager
                .createQuery("select u from User u where u.username =?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public void updateUsers(User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(long id) {
        entityManager.remove(getUserById(id));

    }

    @Override
    public User getUserById(long id) {
        return entityManager
                .createQuery("select u from User u where u.id =?1", User.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager
                .createQuery("select u from Role u where u.name =?1", Role.class)
                .setParameter(1, name)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }
}

