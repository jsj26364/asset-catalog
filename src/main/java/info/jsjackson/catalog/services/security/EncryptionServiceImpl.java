package info.jsjackson.catalog.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * encryptPassword() - encrypts the password passed to it
 * checkPassword() - returns a boolean result of the password comparison.
 * 
 * @author josan
 *
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {

	private StrongPasswordEncryptor passwordEncryptor;
	
	@Autowired
	public void setPasswordEncryptor(StrongPasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}
	
	@Override
	public String encryptString(String input) {
		return passwordEncryptor.encryptPassword(input);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		//return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
		
		boolean passwordFound = passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
		
		/*System.out.println("PLAIN PASSWORD: " + plainPassword + " ENCRYPTED PASSWORD: " + encryptedPassword);
		System.out.println("PASSWORD FOUND: " + passwordFound);*/
		
		return passwordFound;
		
	}
	
}


/*
 * Each time you want to inject (autowire) a bean, ask yourself this question:
 * IS THERE A BEAN CONFIGURATION FOR THIS BEAN?
 */

