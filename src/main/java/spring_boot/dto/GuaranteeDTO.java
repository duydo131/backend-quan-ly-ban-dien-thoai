package spring_boot.dto;

import spring_boot.entity.Guarantee;

public class GuaranteeDTO {
	private Long id;
	private Long totalAmount;

	public GuaranteeDTO() {
		super();
	}
	
	public void mapToGuaranteeDTO(Guarantee g) {
		this.setTotalAmount(g.getTotalAmount());
		this.setId(g.getId());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
}
