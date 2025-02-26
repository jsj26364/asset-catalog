package info.jsjackson.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.jsjackson.catalog.domain.Asset;
import info.jsjackson.catalog.repositories.AssetRepository;

@Service
public class AssetServiceImpl implements AssetService {

	private AssetRepository assetRepository;

	public AssetServiceImpl(AssetRepository assetRepository) {
		this.assetRepository = assetRepository;
	}

	@Override
	public Iterable<Asset> listAllAssets() {
		return assetRepository.findAll();
	}

	@Override
	public Asset getAssetById(Integer id) {
		return assetRepository.findById(id).orElse(null);
	}

	

	@Override
	public Asset saveAsset(Asset asset) {
		return assetRepository.save(asset);
	}
	
	
	@Override
	public void deleteAssetById(Integer id) {
		assetRepository.deleteById(id);
		
	}

	
}
