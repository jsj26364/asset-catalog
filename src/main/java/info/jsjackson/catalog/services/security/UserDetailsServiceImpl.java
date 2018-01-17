package info.jsjackson.catalog.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import info.jsjackson.catalog.domain.User;
import info.jsjackson.catalog.services.UserService;

/**
 * Implementation of Spring's UserDetailsService interface. Only has one method - loadUserByUsername.
 * 
 * Spring Security provides a UserDetailsService interface to lookup for any user: 
 * 	- username, 
 * 	- password 
 * 	- GrantedAuthorities 
 * This interface provides only one method, loadUserByUsername(). 
 * This method returns an implementation of Spring Security’s UserDetails interface that provides core user information.
 * 
 * @author josan
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	/*
	 *  auto wire UserService and Converter - we need them for the "loadUserByUsername" implementation method.
	 */
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private Converter<User, UserDetails> userDetailsConverter;
	@Autowired
	@Qualifier(value = "userToUserDetails")
	public void setUserDetailsConverter(Converter<User, UserDetails> userDetailsConverter) {
		this.userDetailsConverter = userDetailsConverter;
	}
	
	
	/**
	 * The lone overridden method loadUserByUsername() converts a User to UserDetails 
	 * by calling the convert() method of Converter.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//return userDetailsConverter.convert(userService.findByUsername(username));
		
		//broken down for clarity
		User user = userService.findByUsername(username);
		UserDetails userDetails = userDetailsConverter.convert(user);
		return userDetails;
	}

}
