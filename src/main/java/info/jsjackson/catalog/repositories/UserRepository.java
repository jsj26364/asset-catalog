package info.jsjackson.catalog.repositories;

import org.springframework.data.repository.CrudRepository;

import info.jsjackson.catalog.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
