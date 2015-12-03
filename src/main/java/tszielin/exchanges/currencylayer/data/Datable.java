package tszielin.exchanges.currencylayer.data;

import java.util.Date;
import java.util.List;

/**
 * Datability of a class is enabled by the class implementing the {@code Datable} interface.
 * The correct result got from currencylayer.com API is converted to this structure.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public interface Datable {
    /**
     * Get a date of publication
     * @return the date of publication
     */
    Date getDate();
    /**
     * Get a source currency
     * @return the source currency
     */
    String getSource();
    /**
     * Get a list of quotes
     * @return the list of quotes
     */
    List<Quoetable> getQuotes();
}
