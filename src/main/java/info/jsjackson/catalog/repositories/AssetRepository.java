package info.jsjackson.catalog.repositories;

import org.springframework.data.repository.CrudRepository;

import info.jsjackson.catalog.domain.Asset;

public interface AssetRepository extends CrudRepository<Asset, Integer> {

	
}
