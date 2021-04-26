package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Flight implements Comparable<Flight>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7269769871981173937L;
	private int id;
	private Route route;
	private Timestamp departure; 
	private Timestamp return_; 
	private int passengers;
	private String cargo;
	private String file;
	public Flight() {
		
	}
	

	public Flight(Route route, Timestamp departure, Timestamp return_, int passengers, String cargo, String file) {
		super();
		this.route = route;
		this.departure = departure;
		this.return_ = return_;
		this.passengers = passengers;
		this.cargo = cargo;
		this.file = file;
	}


	public Flight(int id, Route route, Timestamp departure, Timestamp return_, int passengers, String cargo,
			String file) {
		super();
		this.id = id;
		this.route = route;
		this.departure = departure;
		this.return_ = return_;
		this.passengers = passengers;
		this.cargo = cargo;
		this.file = file;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}

	public Timestamp getDeparture() {
		return departure;
	}


	public void setDeparture(Timestamp departure) {
		this.departure = departure;
	}


	public Timestamp getReturn_() {
		return return_;
	}


	public void setReturn_(Timestamp return_) {
		this.return_ = return_;
	}


	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}


	@Override
	public int compareTo(Flight o) {
		// TODO Auto-generated method stub
		return o.getReturn_().compareTo(getReturn_());
	}
}
