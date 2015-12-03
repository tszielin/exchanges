package tszielin.exchanges.currencylayer;

import java.util.Arrays;
import java.util.Map;

/**
 * Request a data from {@code currencylayer.com} for given set of quotes. 
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class CurrenciesRequest extends Request {
    private final String[] currencies;
    
    /**
     * Create an object with given set of quotes.
     * @param currencies the set of quotes
     */
    public CurrenciesRequest(String...currencies) {
        this.currencies = currencies;
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = super.getParams();
        params.put("currencies", Arrays.toString(currencies).replaceAll(" ", "").replace("[", "").replace("]", ""));
        return params;
    }
    
    @Override
    protected String getURL() {
        return super.getURL() + "&currencies={currencies}";
    }
}
