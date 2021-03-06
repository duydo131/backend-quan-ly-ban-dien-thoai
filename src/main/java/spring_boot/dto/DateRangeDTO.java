package spring_boot.dto;

import java.util.Date;

public class DateRangeDTO {
	private Date startDate;
	private Date endDate;
	
	public DateRangeDTO() {
		super();
	}

	public DateRangeDTO(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
