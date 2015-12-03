package tszielin.exchanges.currencylayer.error;

import tszielin.exchanges.error.ExchangesException;

/**
 * Any exception when error occurs during mapping JSON data to object.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class MapperException extends ExchangesException {
    private static final long serialVersionUID = 754354546490986717L;

    public MapperException(String message) {
        super(message);
    }
}
