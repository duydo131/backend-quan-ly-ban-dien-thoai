package spring_boot.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends BaseEntity{
	
	@Column(nullable = true)
	private String name = "";
	
	@Column(nullable = true)
	private UUID code;
	
	@Column
	private String image_url = "";
	
	@Column
	private String short_description = "";
	
	@Column
	private String description = "";
	
	@Column(nullable = true)
	private Long price = 0L;
	
	@Column
	private Integer guarantee = 0;
	
	@Column
	private Integer quantity = 0;
	
	@Column
	private Integer rating;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BrokenProduct> brokenProduct;
	
	@OneToMany(mappedBy = "product")
	private List<Guarantee> guarantees;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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

	public List<BrokenProduct> getBrokenProduct() {
		return brokenProduct;
	}

	public void setBrokenProduct(List<BrokenProduct> brokenProduct) {
		this.brokenProduct = brokenProduct;
	}

	public UUID getCode() {
		return code;
	}

	public void setCode(UUID code) {
		this.code = code;
	}
	
	public boolean equals(Product p) {
		return p.getName().equals(name) &&  p.getPrice().equals(price) && p.getDescription().equals(description);
	}

	public List<Guarantee> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<Guarantee> guarantees) {
		this.guarantees = guarantees;
	}
}
