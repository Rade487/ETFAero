package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Comparable<Reservation>, Serializable{

	private static final long serialVersionUID = 3454302228957920981L;
	private User user;
	private Flight flight;
	private String status;
	private String comment;
	private int numOfPass;
	private String weight;
	private String fileName;
	private int flightReturnId;
	private Timestamp createdDate;
	
	public Reservation() {
		
	}
	
	public Reservation(User user, Flight flight, String status, String comment, int numOfPass, String weight,
			String fileName, int flightReturnId, Timestamp createdDate) {
		super();
		this.user = user;
		this.flight = flight;
		this.status = status;
		this.comment = comment;
		this.numOfPass = numOfPass;
		this.weight = weight;
		this.fileName = fileName;
		this.flightReturnId = flightReturnId;
		this.createdDate = createdDate;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNumOfPass() {
		return numOfPass;
	}

	public void setNumOfPass(int numOfPass) {
		this.numOfPass = numOfPass;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFlightReturnId() {
		return flightReturnId;
	}

	public void setFlightReturnId(int flightReturnId) {
		this.flightReturnId = flightReturnId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int compareTo(Reservation o) {
		// TODO Auto-generated method stub
		return o.getCreatedDate().compareTo(getCreatedDate());
	}
	
	

	
	
	
}
