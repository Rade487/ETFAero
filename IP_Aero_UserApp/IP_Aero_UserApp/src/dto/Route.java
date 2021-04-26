package dto;

import java.io.Serializable;

public class Route implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String originCountry;
	private String originTown;
	private String destinationCountry;
	private String destinationTown;
	
	public Route () {
		
	}
	public Route(String originCountry, String originTown, String destinationCountry, String destinationTown) {
		super();
		this.originCountry = originCountry;
		this.originTown = originTown;
		this.destinationCountry = destinationCountry;
		this.destinationTown = destinationTown;
	}

	public Route(int id, String originCountry, String originTown, String destinationCountry, String destinationTown) {
		super();
		this.id = id;
		this.originCountry = originCountry;
		this.originTown = originTown;
		this.destinationCountry = destinationCountry;
		this.destinationTown = destinationTown;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getOriginTown() {
		return originTown;
	}
	public void setOriginTown(String originTown) {
		this.originTown = originTown;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getDestinationTown() {
		return destinationTown;
	}
	public void setDestinationTown(String destinationTown) {
		this.destinationTown = destinationTown;
	}
	
	
}
