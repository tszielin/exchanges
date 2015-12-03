package tszielin.exchanges.currencylayer.data;

/**
 * Quoetable of a class is enabled by the class implementing the {@code Quoetable} interface.
 * The quotes returned from from currencylayer.com API are converted to this structure.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public interface Quoetable {    
    /**
     * Get a quote currency identifier (i.e. AUD)
     * @return the quote currency identifier
     */
    String getQuote();
    /**
     * Get a rate
     * @return the rate
     */
    double getRate();
}
