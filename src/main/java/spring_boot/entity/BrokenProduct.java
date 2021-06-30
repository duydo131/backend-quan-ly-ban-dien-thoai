package spring_boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "broken_product")
public class BrokenProduct extends BaseEntity{
	
	@Column
	private Integer quantity;
	
	@ManyToOne 
    @JoinColumn(name = "list_broken_id")
    private ListBroken list_broken;
	
	@ManyToOne 
    @JoinColumn(name = "product_id")
    private Product product;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ListBroken getList_broken() {
		return list_broken;
	}

	public void setList_broken(ListBroken list_broken) {
		this.list_broken = list_broken;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
