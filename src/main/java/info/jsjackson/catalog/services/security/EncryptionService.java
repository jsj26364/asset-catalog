package info.jsjackson.catalog.services.security;

/**
 * Provides one-way encryption of user passwords
 * 
 * @author josan
 *
 */
public interface EncryptionService {

	//for encrypting the password
	String encryptString(String input);
	
	//for comparing passwords
	boolean checkPassword(String plainPassword, String encryptedPassword);
	
}
