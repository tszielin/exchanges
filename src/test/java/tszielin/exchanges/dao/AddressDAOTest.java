package tszielin.exchanges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import tszielin.exchanges.domain.Address;

public class AddressDAOTest extends DAOTest {
    @Autowired
    AddressDAO addressDAO;

    @Override
    public void testGet() {
        Address address = addressDAO.get(1L);
        assertNotNull(address);
        assertNotNull(address.getAddress());
        assertNotNull(address.getCountry());
        assertNotNull(address.getTown());
        assertNotNull(address.getPoBox());
    }

    @Override
    public void testNotFound() {
        Address address = addressDAO.get(-1L);
        assertNull(address);
    }

    @Test
    @Rollback(true)
    public void testSave() {
        Address address = new Address("address", null, "town", "country");
        assertNull(address.getId());
        Address saved = addressDAO.save(address);
        assertNotNull(saved.getId());        
        assertEquals(address.getAddress(), saved.getAddress());
        assertEquals(address.getCountry(), saved.getCountry());
        assertEquals(address.getPoBox(), saved.getPoBox());
        assertEquals(address.getTown(), saved.getTown());
    }

    @Test
    @Rollback(true)
    public void testRemoveAddress() {
        Address address = addressDAO.get(1L);
        assertNotNull(address);
        addressDAO.remove(address);
        address = addressDAO.get(1L);
        assertNull(address);
    }

    @Test
    @Rollback(true)
    public void testRemoveById() {
        Address address = addressDAO.get(1L);
        assertNotNull(address);
        addressDAO.remove(1L);
        address = addressDAO.get(1L);
        assertNull(address);        
    }
}
