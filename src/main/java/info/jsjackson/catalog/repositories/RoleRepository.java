package info.jsjackson.catalog.repositories;

import org.springframework.data.repository.CrudRepository;

import info.jsjackson.catalog.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
