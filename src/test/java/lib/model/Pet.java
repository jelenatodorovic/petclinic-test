package lib.model;

import java.util.Date;

public class Pet {

	private String name;
	private Date birtDate;
	
	public Pet(String name, Date date) {
		this.name = name;
		this.birtDate = date;
	}
	
	public Date getBirtDate() {
		return birtDate;
	}
	public void setBirtDate(Date birtDate) {
		this.birtDate = birtDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
