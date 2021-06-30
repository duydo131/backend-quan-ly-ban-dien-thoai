package spring_boot.dto;

import spring_boot.entity.BrokenProduct;

public class BrokenProductDTO {
	private Integer quantity;
    private ProductDTO product;
    
	public BrokenProductDTO() {
		super();
	}
	
	public void mapToBrokenProductDTO(BrokenProduct b) {
		ProductDTO p = new ProductDTO();
		p.mapToProductDTO(b.getProduct());
		this.setQuantity(b.getQuantity());
		this.setProductDTO(p);
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public ProductDTO getProductDTO() {
		return product;
	}
	
	public void setProductDTO(ProductDTO product) {
		this.product = product;
	}
}
