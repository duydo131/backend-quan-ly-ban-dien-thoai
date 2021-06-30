package spring_boot.dto;

public class AddProduct {
	private Integer add;
	private Integer update;
	
	public AddProduct(Integer add, Integer update) {
		super();
		this.add = add;
		this.update = update;
	}

	public Integer getAdd() {
		return add;
	}

	public void setAdd(Integer add) {
		this.add = add;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}
	
}
