package tszielin.exchanges.currencylayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import tszielin.exchanges.currencylayer.data.Datable;
import tszielin.exchanges.currencylayer.data.ExchangeResponse;
import tszielin.exchanges.currencylayer.data.Quoetable;
import tszielin.exchanges.currencylayer.data.Quote;
import tszielin.exchanges.currencylayer.error.CurrencylayerException;
import tszielin.exchanges.currencylayer.error.MapperException;

/**
 * Request a data from {@code currencylayer.com}.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class Request {
    private static final Logger LOG = LoggerFactory.getLogger(Request.class);
    
    private final static String ACCESS_KEY = "86ddde8d154c45a8995f3bc1e4d1e8e7";
    private final static String URL = "http://apilayer.net/api/{endpoint}?access_key={access_key}";
    
    private String source;
   
    /**
     * Construct an object
     */
    public Request() {        
    }
    
    /**
     * Construct an object
     * @param source the source currency identifier (i.e. PLN)
     */
    public Request(String source) {
        if (source != null && source.matches("[A-Za-z]{3}")) {
            this.source = source.toUpperCase();
        }
    }
        
    /**
     * Get a response from {@code currencylayer.com} and convert it to {@link Datable}
     * @return the {@code Datable} result
     * @throws MapperException error during converting response from endpoint to the {@code Datable} structure
     * @throws CurrencylayerException error during requesting data from endpoint
     */
    public Datable getResponse() {        
        Object obj = readJSON(new RestTemplate().getForObject(getURL(), String.class, getParams()));
        LOG.debug("Got response {} from endpoint");
        checkForError(obj);
        return getData(obj);
    }
    
    @SuppressWarnings("unchecked")
    protected void checkForError(Object obj) throws CurrencylayerException {
        if (obj instanceof Map) {
            Map<String,?> map = (Map<String,?>)obj;
            if (map.get("success") instanceof Boolean && !(Boolean)map.get("success") && map.containsKey("error")) {
                if (map.get("error") instanceof Map) {
                    Map<String, ?> error = (Map<String, ?>)map.get("error");
                    throw new CurrencylayerException((Integer)error.get("code"), (String)error.get("info"));
                }
            }
        }        
    }
    
    @SuppressWarnings("unchecked")
    protected Datable getData(Object obj) {
        if (obj instanceof Map) {
            Map<String,?> map = (Map<String,?>)obj;
            if (map.containsKey("timestamp") && map.containsKey("source") && map.containsKey("quotes")) {
                List<Quoetable> list = new ArrayList<>();
                if (map.get("quotes") instanceof Map) {
                    Map<String,?> quotes = (Map<String,?>)map.get("quotes");
                    for (Map.Entry<String, ?> entry : quotes.entrySet()) {
                        list.add(new Quote(entry.getKey(), entry.getValue()));
                    }
                }
                return new ExchangeResponse(map.get("timestamp"), map.get("source"), list);
            }            
        }
        return null;
    }
    
    protected Object readJSON(String json) {
        if (json == null) {
            throw new MapperException("Cannot parse null");
        }
        try {
            return new ObjectMapper().readValue(json, Object.class);
        } catch (IOException ex) {
            throw new MapperException(ex.getMessage());
        }
    }
    
    /**
     * Get an endpoint
     * @return the endpoint
     */
    protected String getEndpoint() {
        return "live";
    }
    
    /**
     * Get an URL
     * @return the URL
     */
    protected String getURL() {
        return source == null || source.isEmpty() ? URL : URL + "&source={source}";
    }
    
    /**
     * Get the request parameters
     * @return the request parameters
     */
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("endpoint", getEndpoint());
        params.put("access_key", ACCESS_KEY);
        if (source != null && !source.isEmpty()) {
            params.put("source", source);
        }
        return params;
    }
}
