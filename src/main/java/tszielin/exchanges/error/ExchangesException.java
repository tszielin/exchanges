package tszielin.exchanges.error;

/**
 * Base exception for all internal (application) exception
 * 
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class ExchangesException extends RuntimeException {
    private static final long serialVersionUID = 4433667571399955236L;

    /**
     * Constructs a new exception with the default {@code  Unexpected exception} message.
     */
    public ExchangesException() {
        super("Unexpected exception");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public ExchangesException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * @param cause the cause (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ExchangesException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param  message the detail message
     * @param  cause the cause 
     */
    public ExchangesException(String message, Throwable cause) {
        super(message, cause);
    }
}
