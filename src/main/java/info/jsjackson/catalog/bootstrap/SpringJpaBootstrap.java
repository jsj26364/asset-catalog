package info.jsjackson.catalog.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import info.jsjackson.catalog.domain.Asset;
import info.jsjackson.catalog.domain.Role;
import info.jsjackson.catalog.domain.User;
import info.jsjackson.catalog.repositories.AssetRepository;
import info.jsjackson.catalog.services.AssetService;
import info.jsjackson.catalog.services.RoleService;
import info.jsjackson.catalog.services.UserService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AssetService assetService;
	private UserService userService;
	private RoleService roleService;

	public SpringJpaBootstrap(AssetService assetService, UserService userService, RoleService roleService) {
		this.assetService = assetService;
		this.userService = userService;
		this.roleService = roleService;
	}

	private Logger log = LoggerFactory.getLogger(SpringJpaBootstrap.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadAssets();
		loadUsers();
		loadRoles();
		assignUsersToUserRole();
		assignUsersToAdminRole();

	}

	private void loadAssets() {
		Asset yoga = new Asset();
		yoga.setDescription("Lenovo Yoga 920 Laptop");
		yoga.setValue(new BigDecimal("3000.00"));
		yoga.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_laptop-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		yoga.setAssetId("80Y7006CAU");
		assetService.saveAsset(yoga);

		log.info("Saved Lenovo laptop - id: " + yoga.getId());

		Asset tv = new Asset();
		tv.setDescription("Sony Bravia 43\" Smart TV");
		tv.setValue(new BigDecimal("1200.00"));
		tv.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_tv-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		tv.setAssetId("KDL-43W800C");
		assetService.saveAsset(tv);

		log.info("Saved TV - id: " + tv.getId());

		Asset desktop = new Asset();
		desktop.setDescription("HP Desktop");
		desktop.setValue(new BigDecimal("500.00"));
		desktop.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_desktop-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		desktop.setAssetId("7552-6884-5711-0661");
		assetService.saveAsset(desktop);

		log.info("Saved Desktop - id: " + desktop.getId());

		Asset dellLaptop = new Asset();
		dellLaptop.setDescription("Old Dell Laptop");
		dellLaptop.setValue(new BigDecimal("120.00"));
		dellLaptop.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_oldlaptop-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		dellLaptop.setAssetId("8462-5735-5711-0479");
		assetService.saveAsset(dellLaptop);

		log.info("Saved Laptop - id: " + dellLaptop.getId());

		Asset phone = new Asset();
		phone.setDescription("Samsung Galaxy S7 phone");
		phone.setValue(new BigDecimal("900.00"));
		phone.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_smartphone-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		phone.setAssetId("SM-G930F");
		assetService.saveAsset(phone);

		log.info("Saved phone - id: " + phone.getId());

		Asset ipad = new Asset();
		ipad.setDescription("Ipad 2");
		ipad.setValue(new BigDecimal("850.00"));
		ipad.setImageUrl(
				"https://js-jackson.info/wp-content/uploads/2017/12/jackson_collection_tablet-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		ipad.setAssetId("xder-4625-jkut-7355");
		assetService.saveAsset(ipad);

		log.info("Saved pad - id: " + ipad.getId());

	}

	private void loadUsers() {
		User user1 = new User();
		user1.setUsername("user");
		user1.setPassword("user");
		userService.saveOrUpdate(user1);
		log.info("Saved user: " + user1.getUsername());  //

		User user2 = new User();
		user2.setUsername("admin");
		user2.setPassword("admin");
		userService.saveOrUpdate(user2);
		log.info("Saved user: " + user2.getUsername());  //
	}

	private void loadRoles() {
		Role userRole = new Role();
		userRole.setRole("USER");
		roleService.saveOrUpdate(userRole);
		log.info("Saved role: " + userRole.getRole());

		Role adminRole = new Role();
		adminRole.setRole("ADMIN");
		roleService.saveOrUpdate(adminRole);
		log.info("Saved role: " + adminRole.getRole());
	}

	private void assignUsersToUserRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
		List<User> users = (List<User>) userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("USER")) {
				users.forEach(user -> {
					if (user.getUsername().equals("user")) {
						user.addRole(role);
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}
	
	private void assignUsersToAdminRole() {
		List<Role> roles = (List<Role>)roleService.listAll();
		List<User> users = (List<User>)userService.listAll();
		
		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("ADMIN")) {
				users.forEach(user -> {
					if (user.getUsername().equals("admin")) {
						user.addRole(role);
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}

}
