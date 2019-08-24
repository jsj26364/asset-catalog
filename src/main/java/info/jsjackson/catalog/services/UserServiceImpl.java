package info.jsjackson.catalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.jsjackson.catalog.domain.User;
import info.jsjackson.catalog.repositories.UserRepository;
import info.jsjackson.catalog.services.security.EncryptionService;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private EncryptionService  encryptionService ;
	@Autowired
	public void setEnryyptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}
	
	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);  //fun with Java 8
		return users;
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User saveOrUpdate(User domainObject) {

		if(domainObject.getPassword() != null) {
			//domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
			
			//broken down for clarity
			String password = domainObject.getPassword();
			String encryptedPassword = encryptionService.encryptString(password);

			System.out.println("PLAIN PASSWORD: " + password + " ENCRYPTED PASSWORD: " + encryptedPassword);
			
			domainObject.setEncryptedPassword(encryptedPassword);
			return userRepository.save(domainObject);
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
