package dto;

import java.sql.Date;

public class Counter {
	private int id;
	private Date date_;
	private int numberOfVisitor;
	
	public Counter() {
		
	}
	public Counter(int id, Date date_, int numberOfVisitor) {
		super();
		this.id = id;
		this.date_ = date_;
		this.numberOfVisitor = numberOfVisitor;
	}
	public Counter(Date date_, int numberOfVisitor) {
		super();
		this.date_ = date_;
		this.numberOfVisitor = numberOfVisitor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_() {
		return date_;
	}
	public void setDate_(Date date_) {
		this.date_ = date_;
	}
	public int getNumberOfVisitor() {
		return numberOfVisitor;
	}
	public void setNumberOfVisitor(int numberOfVisitor) {
		this.numberOfVisitor = numberOfVisitor;
	}
	
	
}
