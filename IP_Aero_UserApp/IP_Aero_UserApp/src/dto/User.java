package dto;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7740588960343777544L;
	private int id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String lastName;
	private String address;
	private String country;
	private String type = "";
	private String userGroup = "";
	
	public User() {
		
	}
	public User(String username, String password, String email, String name, String lastName, String address,
			String country, String type, String userGroup) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.country = country;
		this.type = type;
		this.userGroup = userGroup;
	}
	public User(int id, String username, String password, String email, String name, String lastName, String address,
			String country, String type, String userGroup) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.country = country;
		this.type = type;
		this.userGroup = userGroup;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

}
