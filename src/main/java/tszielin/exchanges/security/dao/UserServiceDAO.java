package tszielin.exchanges.security.dao;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tszielin.exchanges.dao.UserDAO;
import tszielin.exchanges.domain.User;

/**
 * Check if user exist in database.
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Service
public class UserServiceDAO implements UserDetailsService {
    @Autowired
    UserDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final User user = userDAO.get(username);
            return new UserDetails() {               
                private static final long serialVersionUID = 348130674517514490L;

                @Override
                public boolean isEnabled() {
                    return user.isActive();
                }
                
                @Override
                public boolean isCredentialsNonExpired() {
                    return user.isActive();
                }
                
                @Override
                public boolean isAccountNonLocked() {
                    return user.isActive();
                }
                
                @Override
                public boolean isAccountNonExpired() {
                    return user.isActive();
                }
                
                @Override
                public String getUsername() {
                    return user.getUsername();
                }
                
                @Override
                public String getPassword() {
                    return user.getPassword();
                }
                
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Arrays.<SimpleGrantedAuthority>asList(new SimpleGrantedAuthority("ROLE_USER"));
                }
            };
        }
        catch(Exception ex) {
            throw new UsernameNotFoundException(String.format("User %s not found", username), ex);
        }
    }
}
