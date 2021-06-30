package spring_boot.generate;

public class ProductSales extends Docx{
	private Integer stt;
	private String name;
	private String code;
	private Long price;
	private Integer rate;
	private Integer qtt;

	@Override
	public void setStt(Integer i) {
		this.stt = i;
	}

	public ProductSales() {
		super();
	}

	public ProductSales(Integer stt, String name, String code, Long price, Integer rate, Integer qtt) {
		super();
		this.stt = stt;
		this.name = name;
		this.code = code;
		this.price = price;
		this.rate = rate;
		this.qtt = qtt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getQtt() {
		return qtt;
	}

	public void setQtt(Integer qtt) {
		this.qtt = qtt;
	}

	public Integer getStt() {
		return stt;
	}
}
