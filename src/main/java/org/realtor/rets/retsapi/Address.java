package org.realtor.rets.retsapi;

public class Address {
	public String address_street;
	public String address_city;
	public String address_zip_postal_code;
	public String address_state;
	public String address_country;
	public String getAddress_street() {
		return address_street;
	}
	public String getAddress_city() {
		return address_city;
	}
	public String getAddress_zip_postal_code() {
		return address_zip_postal_code;
	}
	public String getAddress_state() {
		return address_state;
	}
	public String getAddress_country() {
		return address_country;
	}
	public void setAddress_street(String address_street) {
		this.address_street = address_street;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public void setAddress_zip_postal_code(String address_zip_postal_code) {
		this.address_zip_postal_code = address_zip_postal_code;
	}
	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}
	public void setAddress_country(String address_country) {
		this.address_country = address_country;
	}
	public Address() {
		super();
	}
	
	
}
