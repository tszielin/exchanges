package tszielin.exchanges.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Address entity
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Entity
@Table(name = "ADDRESSES")
public class Address implements Identifiable<Long> {
    private static final long serialVersionUID = -1660934740089825757L;

    private Long id;
    private String address;
    private String poBox;
    private String town;
    private String country;
    
    public Address() {
    }
    
    public Address(String address, String poBox, String town, String country) {
        this.address = address;
        this.poBox = poBox;
        this.town = town;
        this.country = country;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ADDRESS", nullable = false, length=150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "PO_BOX", nullable = true, length=10)
    public String getPoBox() {
        return this.poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    @Column(name = "TOWN", nullable = false, length=50)
    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Column(name = "COUNTRY", nullable = false, length=70)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Address other = (Address)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "street=" + this.address + ", poBox=" + this.poBox + ", town=" + this.town + ", country="
                + this.country;
    }
}
