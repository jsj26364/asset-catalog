package info.jsjackson.catalog.services;

import info.jsjackson.catalog.domain.User;

public interface UserService extends CRUDService<User> {

	User findByUsername(String username);
}
