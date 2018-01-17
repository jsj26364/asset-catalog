package info.jsjackson.catalog.configuration;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A bean for StrongPasswordEncryptor of Jasypt
 *   - a utility class for easily performing high-strength password encryption and checking
 * 
 * @author josan
 *
 */
@Configuration
public class CommonBeanConfig {

	@Bean
	public StrongPasswordEncryptor passwordEncryptor() {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor;
	}
	
}
