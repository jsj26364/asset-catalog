package info.jsjackson.catalog.configuration;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 *  When doing Java configuration for Spring Security, you need to extend the WebSecurityConfigurerAdapater class 
 *  and override the configure method.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 *  auto wire in the AuthenticationProvider as we want the Spring Context to manage it.
	 */
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	@Qualifier("daoAuthenticationProvider")
	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}
	
	
	/**
	 * Auto wire in the AuthenticationManagerBuilder. 
	 * Spring Security will use this to set up the AuthenticationProvider.
	 */
	@Autowired
	public void configureAuthManager(AuthenticationManagerBuilder  authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
	}
	
	/**
	 * Set up a password encoder to encode passwords present in the UserDetails object returned by the configured UserDetailsService
	 * @param passwordEncryptor
	 * @return PasswordEncoder
	 */
	/*@Bean
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(passwordEncryptor);
		return passwordEncoder;
	}*/
	
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
	
	 @Autowired  //--Spring Security’s in-memory authentication provider
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth
	     	.inMemoryAuthentication()
	        .withUser("admin").password("admin").roles("ADMIN")
	        .and().withUser("user").password("user").roles("USER");
	    }
	
	/**
	 *  Set up the DAO authentication provider
	 * @param passwordEncoder
	 * @param userDetailsService
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
		
	}
	
	/** About antmatchers: (see https://docs.spring.io/spring/docs/3.1.x/javadoc-api/org/springframework/util/AntPathMatcher.html)
	 * 
	 * The mapping matches URLs using the following rules:
		? matches one character
	 	* matches zero or more characters
	 	** matches zero or more 'directories' in a path
		Some examples:
		com/t?st.jsp - matches com/test.jsp but also com/tast.jsp or com/txst.jsp
		com/*.jsp - matches all .jsp files in the com directory
		com/**'/test.jsp - matches all test.jsp files underneath the com path
			org/springframework/**'/*.jsp - matches all .jsp files underneath the org/springframework path
				org/**'/servlet/bla.jsp - matches org/springframework/servlet/bla.jsp but also org/springframework/testing/servlet/bla.jsp and org/servlet/bla.jsp
	 * 
	 */
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		 *httpSecurity.authorizeRequests().antMatchers("/").permitAll()
					.and().authorizeRequests().antMatchers("/console/*").permitAll();
			httpSecurity.csrf().disable();
			httpSecurity.headers().frameOptions().disable(); 
		 */
		
		//let all request go in to the root context, as well as following paths:
		httpSecurity
			.authorizeRequests().antMatchers("/","/assets","/asset/show/*","/console/**").permitAll()
			.anyRequest().authenticated()
		    .and()
		    .formLogin().loginPage("/login").permitAll()
		    .and()
		    .logout().permitAll();

		   httpSecurity.csrf().disable();
		   httpSecurity.headers().frameOptions().disable();
    }
	
	
	
	
	
}
