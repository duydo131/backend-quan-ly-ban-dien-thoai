package spring_boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "guarantee")
public class Guarantee extends BaseEntity{
	
	@Column
	private Long totalAmount;
	
	@ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne 
    @JoinColumn(name = "bill_broken_id")
    private BillBroken bill_broken;
	
	@OneToOne
    @JoinColumn(name = "bill_guarantee_id")
    private BillBroken bill_guarantee;
	
	@ManyToOne 
    @JoinColumn(name = "product_id")
	private Product product;

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BillBroken getBill_broken() {
		return bill_broken;
	}

	public void setBill_broken(BillBroken bill_broken) {
		this.bill_broken = bill_broken;
	}

	public BillBroken getBill_guarantee() {
		return bill_guarantee;
	}

	public void setBill_guarantee(BillBroken bill_guarantee) {
		this.bill_guarantee = bill_guarantee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
