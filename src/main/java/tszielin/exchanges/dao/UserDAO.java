package tszielin.exchanges.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tszielin.exchanges.domain.User;

/**
 * User DAO
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Repository
public class UserDAO extends AbstractDAO<User, Long> {
    public UserDAO() {
        super(User.class);
    }

    @Transactional(readOnly=true)
    public User get(String username) {
        return getEntityManager().createNamedQuery("byUsername", User.class).setParameter("username", username).getSingleResult();
    }
}
