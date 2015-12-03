package tszielin.exchanges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import tszielin.exchanges.domain.Address;
import tszielin.exchanges.domain.User;

public class UserDAOTest extends DAOTest {
    @Autowired
    UserDAO userDAO;
    
    @Override
    public void testGet() {
        //Address address = new Address("address", null, "town", "country");
        User user = userDAO.get(1L);
        assertNotNull(user);
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertTrue(user.getAddress() instanceof Address);
        assertTrue(user.isActive());
    }
    
    @Test
    public void testGetByUsername() {
        //Address address = new Address("address", null, "town", "country");
        assertNotNull(userDAO.get("demo"));
    }

    @Override
    public void testNotFound() {
        User user = userDAO.get(-1L);
        assertNull(user);
    }
    
    @Test
    @Rollback(true)
    public void testSave() {
        User user = new  User("username", "password", "firstName", "lastName", "email", "phoneNumber", new Date(), 
                new Address("address", null, "town", "country"));        
        assertNull(user.getId());
        User saved = userDAO.save(user);
        assertEquals(user.getUsername(), saved.getUsername());
        assertEquals(user.getPassword(), saved.getPassword());
        assertEquals(user.getAddress().getAddress(), saved.getAddress().getAddress());
    }

    @Test
    @Rollback(true)
    public void testRemove() {
        User user = userDAO.get(1L);
        assertNotNull(user);
        userDAO.remove(user);
        user = userDAO.get(1L);
        assertNull(user);
    }

    @Test
    @Rollback(true)
    public void testRemoveById() {
        User user = userDAO.get(1L);
        assertNotNull(user);
        userDAO.remove(1L);
        user = userDAO.get(1L);
        assertNull(user);        
    }
}
