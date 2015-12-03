package tszielin.exchanges.currencylayer.data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Representation of information returned from currencylayer.com API. 
 * Contains date of publication, source currency identifier and list of quotes. The structure is mapped from JSON data:
 * <pre><small>
 * {
 *  "terms": "https://currencylayer.com/terms",
 *  "privacy": "https://currencylayer.com/privacy",
 *    "timestamp": 1430401802,
 *    "source": "USD",
 *    "quotes": {
 *        "USDAED": 3.672982,
 *        "USDAFN": 57.8936,
 *        "USDALL": 126.1652,
 *        "USDAMD": 475.306,
 *        "USDANG": 1.78952,
 *        "USDAOA": 109.216875,
 *        "USDARS": 8.901966,
 *        "USDAUD": 1.269072,
 *        "USDAWG": 1.792375,
 *        "USDAZN": 1.04945,
 *        "USDBAM": 1.757305,
 *    [...]
 *    }
 * }    
 * </small></pre>
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class ExchangeResponse implements Datable {    
    private final long timestamp;
    private final String source;
    private final List<Quoetable> quotes;
    
    /**
     * Create an object.
     * @param timestamp the date of publication
     * @param source the source currency identifier
     * @param quotes list of quotes
     */
    public ExchangeResponse(Object timestamp, Object source, List<Quoetable> quotes) {
        this.timestamp = timestamp instanceof Integer ? (Integer)timestamp * 1000L : Long.MIN_VALUE;
        this.source = source instanceof String ? (String)source : null;
        this.quotes = quotes;
    }

    @JsonIgnore
    /**
     * Get a date of publication as a timestamp
     * @return the date of publication as a timestamp
     */
    public long getTimestamp() {
        return this.timestamp;
    }
    
    @Override
    public Date getDate() {
        return new Date(this.timestamp);
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public List<Quoetable> getQuotes() {
        return this.quotes == null ? Arrays.<Quoetable>asList() : this.quotes;
    }

    @Override
    public String toString() {
        return "date=" + getDate() + ", source=" + this.source + ", quotes=" + getQuotes();
    }
}
