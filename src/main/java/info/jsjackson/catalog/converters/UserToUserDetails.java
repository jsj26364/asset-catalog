package info.jsjackson.catalog.converters;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import info.jsjackson.catalog.domain.User;
import info.jsjackson.catalog.services.security.UserDetailsImpl;

/**
 * A converter to convert User to UserDetails implementation.
 * 
 * We need to provide an implementation of the loadUserByUsername() method of UserDetailsService. 
 * But the challenge is that the findByUsername() method of our UserService returns a User entity, 
 * while Spring Security expects a UserDetails object from the loadUserByUsername() method.
 * 
 * Hence this converter
 * 
 * @author josan
 *
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	@Override
	public UserDetails convert(User user) {
		UserDetailsImpl userDetails = new UserDetailsImpl();
		if(user != null) {
			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getPassword());
			userDetails.setEnabled(user.getEnabled());
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

			user.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			userDetails.setAuthorities(authorities);
		}
		return userDetails;
	}

	
}
