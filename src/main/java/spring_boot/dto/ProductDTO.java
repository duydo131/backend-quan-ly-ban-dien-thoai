package spring_boot.dto;

import java.util.UUID;

import spring_boot.entity.Product;


public class ProductDTO {
	private Long id;
	private String name;
	private UUID code;
	private String image_url = "";
	private String description = "";
	private Long price = 0L;
	private Integer guarantee = 0;
	private Integer quantity = 0;
	private Integer rating;
	
	public ProductDTO() {}
	
	public void mapToProductDTO(Product p) {
		this.setName(p.getName());
		this.setCode(p.getCode());
		this.setImage_url(p.getImage_url());
		this.setDescription(p.getDescription());
		this.setPrice(p.getPrice());
		this.setGuarantee(p.getGuarantee()); 
		this.setQuantity(p.getQuantity());
		this.setRating(p.getRating());
		this.setId(p.getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getCode() {
		return code;
	}

	public void setCode(UUID code) {
		this.code = code;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
