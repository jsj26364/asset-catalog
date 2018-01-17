package info.jsjackson.catalog.services;

import info.jsjackson.catalog.domain.Asset;

public interface AssetService {

	Iterable<Asset> listAllAssets();

	Asset getAssetById(Integer id);

	Asset saveAsset(Asset asset);
    
    void deleteAssetById(Integer id);
    
}
