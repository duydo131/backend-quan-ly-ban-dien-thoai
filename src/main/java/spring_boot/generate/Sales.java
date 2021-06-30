package spring_boot.generate;

public class Sales extends Docx{
	private Integer stt;
	private String name;
	private Integer quantity;
	private Integer customer;
	private Long revense;

	@Override
	public void setStt(Integer i) {
		this.stt = i;
	}
	
	public Sales(String name, Integer quantity, Integer customer, Long revense) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.customer = customer;
		this.revense = revense;
	}

	public Sales(Integer stt, String name, Integer quantity, Integer customer, Long revense) {
		super();
		this.stt = stt;
		this.name = name;
		this.quantity = quantity;
		this.customer = customer;
		this.revense = revense;
	}

	public Sales() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public Long getRevense() {
		return revense;
	}

	public void setRevense(Long revense) {
		this.revense = revense;
	}

	public Integer getStt() {
		return stt;
	}
}
