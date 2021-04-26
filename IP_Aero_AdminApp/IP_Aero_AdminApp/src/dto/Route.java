package dto;

public class Route {
	private int id;
	private String origin_country;
	private String origin_town;
	private String destination_country;
	private String destination_town;
	
	public Route() {
		
	}
	
	public Route(String origin_country, String origin_town, String destination_country, String destination_town) {
		super();
		this.origin_country = origin_country;
		this.origin_town = origin_town;
		this.destination_country = destination_country;
		this.destination_town = destination_town;
	}


	public Route(int id, String origin_country, String origin_town, String destination_country,
			String destination_town) {
		super();
		this.id = id;
		this.origin_country = origin_country;
		this.origin_town = origin_town;
		this.destination_country = destination_country;
		this.destination_town = destination_town;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin_country() {
		return origin_country;
	}

	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}

	public String getOrigin_town() {
		return origin_town;
	}

	public void setOrigin_town(String origin_town) {
		this.origin_town = origin_town;
	}

	public String getDestination_country() {
		return destination_country;
	}

	public void setDestination_country(String destination_country) {
		this.destination_country = destination_country;
	}

	public String getDestination_town() {
		return destination_town;
	}

	public void setDestination_town(String destination_town) {
		this.destination_town = destination_town;
	}
	
	
	
}
