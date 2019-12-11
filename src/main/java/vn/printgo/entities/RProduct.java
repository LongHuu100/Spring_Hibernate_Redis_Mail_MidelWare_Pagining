package vn.printgo.entities;

public class RProduct {
	
	private Integer id;
	private Integer typeId;
	private String providerCode;
	private String imageUrl;
	
	public RProduct() {}
	
	public RProduct(int id, int typeId, String providerCode, String imageUrl) {
		this.id = id;
		this.typeId = typeId;
		this.providerCode = providerCode;
		this.imageUrl = imageUrl;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public int getTypeId() {
		return this.typeId;
	}
	
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	
	public String getProviderCode() {
		return this.providerCode;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
}
