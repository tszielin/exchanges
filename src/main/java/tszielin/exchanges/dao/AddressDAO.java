package tszielin.exchanges.dao;

import org.springframework.stereotype.Repository;

import tszielin.exchanges.domain.Address;

/**
 * Address DAO
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Repository
public class AddressDAO extends AbstractDAO<Address, Long> {
    public AddressDAO() {
        super(Address.class);
    }
}
