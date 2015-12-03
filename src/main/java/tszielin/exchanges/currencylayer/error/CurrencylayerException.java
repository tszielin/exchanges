package tszielin.exchanges.currencylayer.error;

import tszielin.exchanges.error.ExchangesException;

/**
 * Any exception when error occurs during working with currencylayer.com API.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class CurrencylayerException extends ExchangesException {
    private static final long serialVersionUID = -3865428699318397095L;
    private final int code;

    /**
     * Constructs a new exception with the returned by {@code currencylayer.com} error code and error description as detail message
     * @param code error code
     * @param message error description
     */
    public CurrencylayerException(Object code, Object message) {
        super(message instanceof String ? (String)message : null);
        this.code = code instanceof Integer ? (Integer)code : Integer.MIN_VALUE;
    }

    /**
     * Get an error code
     * @return the error code
     */
    public int getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return code + "-" + getMessage();
    }
}
