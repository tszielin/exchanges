package tszielin.exchanges.domain;

import java.io.Serializable;

/**
 * Identity the entity by the primary key
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
public interface Identifiable<S extends Serializable> extends Serializable {
    S getId();
}
