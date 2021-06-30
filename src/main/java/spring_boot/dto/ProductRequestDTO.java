package spring_boot.dto;

import spring_boot.entity.Product;

public class ProductRequestDTO {
	private String name;
	private String image_url;
	private String short_description;
	private String description;
	private Long price;
	private Integer guarantee;
	private Integer quantity;
	
	public Product mapToProduct() {
		Product product = new Product();
		product.setName(name);
		product.setImage_url(image_url);
		product.setShort_description(short_description);
		product.setDescription(description);
		product.setPrice(price);
		product.setGuarantee(guarantee);
		product.setQuantity(quantity);
		return product;
	}
	
	public ProductRequestDTO() {
		super();
	}

	public ProductRequestDTO(String name, String image_url, String short_description, String description, Long price,
			Integer guarantee, Integer quantity) {
		super();
		this.name = name;
		this.image_url = image_url;
		this.short_description = short_description;
		this.description = description;
		this.price = price;
		this.guarantee = guarantee;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
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
}
