package spring_boot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "list_broken")
public class ListBroken extends BaseEntity{
	
	@Column
	private Long totalAmount;
	
	@ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;

	@OneToMany(mappedBy = "list_broken", cascade = CascadeType.ALL)
	private List<BrokenProduct> brokenProduct;

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

	public List<BrokenProduct> getBrokenProduct() {
		return brokenProduct;
	}

	public void setBrokenProduct(List<BrokenProduct> brokenProduct) {
		this.brokenProduct = brokenProduct;
	}
}
