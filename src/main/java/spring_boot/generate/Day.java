package spring_boot.generate;

import java.util.Calendar;
import java.util.Date;

public class Day {
	private Integer day;
	private Integer month;
	private Integer year;
	
	public Day() {
	}

	public Day(Date date) {
		setDate(date);
	}
	
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public void setDate(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		
		this.day = d.get(Calendar.DAY_OF_MONTH);
		this.month = d.get(Calendar.MONTH) + 1;
		this.year = d.get(Calendar.YEAR);
	}
}
