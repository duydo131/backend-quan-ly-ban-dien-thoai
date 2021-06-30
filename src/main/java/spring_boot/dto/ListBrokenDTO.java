package spring_boot.dto;

import java.util.ArrayList;
import java.util.Collection;

import spring_boot.entity.BrokenProduct;
import spring_boot.entity.ListBroken;

public class ListBrokenDTO {
	private Collection<BrokenProductDTO> broken;
	private Long totalAmount;

	public ListBrokenDTO() {
		super();
	}

	public void mapToListBrokenDTO(ListBroken l) {
		this.setTotalAmount(l.getTotalAmount());
		Collection<BrokenProductDTO> list = new ArrayList<>();
		for(BrokenProduct br : l.getBrokenProduct()) {
			BrokenProductDTO brD = new BrokenProductDTO();
			brD.mapToBrokenProductDTO(br);
			list.add(brD);
		}
		this.setBroken(list);
	}
	
	public Collection<BrokenProductDTO> getBroken() {
		return broken;
	}

	public void setBroken(Collection<BrokenProductDTO> broken) {
		this.broken = broken;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
}
