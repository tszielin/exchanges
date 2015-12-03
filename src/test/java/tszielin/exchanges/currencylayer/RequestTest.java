package tszielin.exchanges.currencylayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import tszielin.exchanges.currencylayer.data.Datable;
import tszielin.exchanges.currencylayer.error.CurrencylayerException;
import tszielin.exchanges.currencylayer.error.MapperException;
import tszielin.exchanges.error.ExchangesException;

public class RequestTest {
    private final static String JSON = "{\"timestamp\": 1430401802,\"source\": \"USD\", \"quotes\": {\"USDAED\": 3.672982,\"USDAFN\": 57.8936,\"USDALL\": 126.1652,\"USDAMD\": 475.306,\"USDANG\": 1.78952,\"USDAOA\": 109.216875}}";
    private final static String JSON_NOKEY = "{\"success\":false,\"error\":{\"code\":101,\"type\":\"invalid_access_key\",\"info\":\"You have not supplied a valid API Access Key. [Technical Support: support@apilayer.net]\"}}";
    private final static String JSON_ERROR =  "{\"success\":false,\"error\":{\"code\":103,\"info\":\"This API Function does not exist.\"}}";
    private Request request = new Request();

    @Test
    public void testGetEndpoint() {
        assertEquals("live", request.getEndpoint());
    }

    @Test
    public void testGetURL() {
        assertEquals("http://apilayer.net/api/{endpoint}?access_key={access_key}", request.getURL());
    }

    @Test
    public void testGetParams() {
        Map<?,?> params = request.getParams();
        assertNotNull(params);
        assertFalse(params.isEmpty());
        assertTrue(params.containsKey("access_key"));
    }
    
    @Test
    public void test() {
        Datable datable = Mockito.mock(Datable.class);
        Request req = Mockito.mock(Request.class);
        Mockito.when(req.getData(Mockito.any(Map.class))).thenReturn(datable);
        Mockito.when(req.getResponse()).thenReturn(datable);
    }
    
    @Test
    public void testJSON() {
        Object obj = request.readJSON(JSON);
        assertNotNull(obj);
        assertTrue(obj instanceof Map);
        assertFalse(((Map<?,?>)obj).isEmpty());
    }
    
    @Test(expected=MapperException.class)
    public void testMapperException() throws ExchangesException {
        request.readJSON(null);
    }
    
    @Test(expected=CurrencylayerException.class)
    public void testNoKeyError() throws ExchangesException {
        try {
            request.checkForError(request.readJSON(JSON_NOKEY));
        }
        catch(CurrencylayerException ex) {
            assertEquals(ex.getCode(), 101);
            assertTrue(ex.getMessage().contains("API Access Key"));
            throw ex;
        }
    }
    
    @Test(expected=CurrencylayerException.class)
    public void testError() throws ExchangesException {
        try {
            request.checkForError(request.readJSON(JSON_ERROR));
        }
        catch(CurrencylayerException ex) {
            assertEquals(ex.getCode(), 103);
            assertTrue(ex.getMessage().contains("API Function"));
            throw ex;
        }
    }
}
