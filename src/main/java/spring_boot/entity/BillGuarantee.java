	package spring_boot.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_guarantee")
public class BillGuarantee extends Bill{
	
	@OneToOne
    @JoinColumn(name = "guarantee_id")
    private Guarantee guarantee;

	public Guarantee getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Guarantee guarantee) {
		this.guarantee = guarantee;
	}
}
