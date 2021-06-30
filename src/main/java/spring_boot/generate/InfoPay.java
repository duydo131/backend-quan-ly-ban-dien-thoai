package spring_boot.generate;

public class InfoPay extends Docx{
	private Integer stt;
	private String name;
	private Long price;
	private Integer qtt;
	private String code;
	private Long amount;
	
	public InfoPay() {
		super();
	}

	public InfoPay(String name, Long price, Integer qtt, String code, Long amount) {
		super();
		this.name = name;
		this.price = price;
		this.qtt = qtt;
		this.code = code;
		this.amount = amount;
	}

	@Override
	public void setStt(Integer i) {
		this.stt = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getQtt() {
		return qtt;
	}

	public void setQtt(Integer qtt) {
		this.qtt = qtt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getStt() {
		return stt;
	}
}
