package spring_boot.dto;

import java.util.ArrayList;
import java.util.List;

import spring_boot.entity.BillBroken;
import spring_boot.entity.Guarantee;

public class BillBrokenDTO {
	private Long id;
	private ListBrokenDTO listBrokenDTO;
	private List<GuaranteeDTO> listGuaranteeDTO;
	
	public BillBrokenDTO() {
		super();
	}
	
	public void mapToBillBrokenDTO(BillBroken b) {
		this.setId(b.getId());
		ListBrokenDTO listBrokenDTO = new ListBrokenDTO();
		listBrokenDTO.mapToListBrokenDTO(b.getList_broken());
		this.setListBrokenDTO(listBrokenDTO);
		if(b.getList_guarantee() != null) {
			List<GuaranteeDTO> list = new ArrayList<>();
			for(Guarantee g : b.getList_guarantee()) {
				GuaranteeDTO gD = new GuaranteeDTO();
				gD.mapToGuaranteeDTO(g);
				list.add(gD);
			}
			this.setListGuaranteeDTO(list);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ListBrokenDTO getListBrokenDTO() {
		return listBrokenDTO;
	}

	public void setListBrokenDTO(ListBrokenDTO listBrokenDTO) {
		this.listBrokenDTO = listBrokenDTO;
	}

	public List<GuaranteeDTO> getListGuaranteeDTO() {
		return listGuaranteeDTO;
	}

	public void setListGuaranteeDTO(List<GuaranteeDTO> listGuaranteeDTO) {
		this.listGuaranteeDTO = listGuaranteeDTO;
	}
}
