package tszielin.exchanges.currencylayer.data;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Representation of exchange. Contains source currency identifier, quote currency identifier and rate.
 * @author Thomas Zielinski
 * @since 2015-11-28
 */
public class Quote implements Quoetable {
    private String quote;
    private double rate;
    
    /**
     * Create an object. 
     * @param quote the source and quote currencies identifiers (i.e. USDAUD)
     * @param rate the rate
     */
    public Quote(String quote, Object rate) {
        this.quote = quote;
        this.rate = rate instanceof Number ? ((Number)rate).doubleValue() : Double.NaN;
    }
    
    @JsonIgnore
    /**
     * Get a source currency identifier (i.e. USD)
     * @return the source currency identifier
     */
    public String getSource() {
        return this.quote.substring(0, 3);
    }
        
    @Override
    public String getQuote() {
        return this.quote.substring(3);
    }
    
    @Override
    public double getRate() {
        return this.rate;        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.quote == null) ? 0 : this.quote.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.rate);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quote other = (Quote)obj;
        if (this.quote == null) {
            if (other.quote != null)
                return false;
        } else if (!this.quote.equals(other.quote))
            return false;
        if (Double.doubleToLongBits(this.rate) != Double.doubleToLongBits(other.rate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getRate() + " " + getQuote();
    }
}
