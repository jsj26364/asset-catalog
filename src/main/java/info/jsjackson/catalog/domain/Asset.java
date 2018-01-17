package info.jsjackson.catalog.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Asset {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//best option for MySQL DBs = GenerationType.IDENTITY - but not the best for performance, (SEQUENCE faster)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_id_seq")
	@SequenceGenerator(name="asset_id_seq", sequenceName = "ASSET_ID_SEQ", allocationSize = 10)
	private Integer id;
	
	@Version
	private Integer version;
	
	private String assetId;
	private String description;
	private String imageUrl;
	private BigDecimal value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
	
	
	
}
