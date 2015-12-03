package tszielin.exchanges.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQuery;

/**
 * User entity.
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "byUsername", query = "select u from User u where u.username=:username")
public class User implements Identifiable<Long> {
    private static final long serialVersionUID = -5689618041375952809L;

    private Long id;    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private Address address;
    private boolean active;
    
    public User() {
    }
    
    public User(String username, String password, String firstName, String lastName,
            String email, String phoneNumber, Date birthday, Address address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.address = address;
        this.active = true;
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
    
    @Column(name = "PASSWORD", nullable = false, length=50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL", nullable = false, length=100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "USERNAME", unique = true, nullable = false, length=25)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "FIRST_NAME", nullable = false, length=30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false, length=50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name = "PHONE", nullable = true, length=15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Column(name = "BIRTHDAY", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "ACTIVE")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity=Address.class)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", firstName="
                + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", phoneNumber="
                + this.phoneNumber + ", birthday=" + this.birthday + ", address=[" + this.address + "], active="
                + this.active;
    }
}
