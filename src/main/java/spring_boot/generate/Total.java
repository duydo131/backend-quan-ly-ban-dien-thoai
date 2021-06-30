package spring_boot.generate;

public class Total {
	private String time;
	private Long total;
	
	public Total() {
	}
	
	public Total(String time, Long total) {
		this.time = time;
		this.total = total;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
