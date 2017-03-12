package org.realtor.rets.retsapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetsParameters {
	
	Map<String, String> map=new HashMap<String, String>();
	public String property_listing_id;
	public String property_type;
	public Long id;
	public String property_primary_image;
	public String house_number;
//	public List<Object> Address=new ArrayList<Object>();
	public String address_street;
	public String address_city;
	public String address_zip_postal_code;
	public String address_state;
	public String address_country;
	public int area_square_feet;
	public String house_category;
	public String house_type;
	public String parameters_square_feet;	
	public String parameters_lot_size;
	public String parameters_parking_garage;
	public String parameters_total_bathrooms;
	public String parameters_total_bedrooms;
	public String parameters_kitchen_size;
	public String parameters_year_built;
	public String parameters_address;	
	public static final String contacts_proprietor_name="contacts_proprietor_name";
	public static final String contacts_proprietor_role="contacts_proprietor_role";
	public String map_latitude;
	public String map_longitude;	
	public String mls_id;
	public String property_owner;
	public int sale;
	public int rent;
	public String property_price;
	public String property_currency;
	public String property_listing_type;
	public String property_status;
	public String property_data=null;
	public String property_expriry_date;
	public RetsParameters(Map<String, String> map, String property_type,
			Long id, String property_primary_image, String house_number,
			String address_street, String address_city,
			String address_zip_postal_code, String address_state,
			String address_country, int area_square_feet,
			String house_category, String house_type,
			String parameters_square_feet, String parameters_lot_size,
			String parameters_parking_garage,
			String parameters_total_bathrooms,
			String parameters_total_bedrooms, String parameters_kitchen_size,
			String parameters_year_built, String parameters_address,
			String map_latitude, String map_longitude, String mls_id,
			String property_owner, int sale, int rent, String property_price,
			String property_currency, String property_listing_type,
			String property_status) {
		super();
		this.map = map;
		this.property_type = property_type;
		this.id = id;
		this.property_primary_image = property_primary_image;
		this.house_number = house_number;
		this.address_street = address_street;
		this.address_city = address_city;
		this.address_zip_postal_code = address_zip_postal_code;
		this.address_state = address_state;
		this.address_country = address_country;
		this.area_square_feet = area_square_feet;
		this.house_category = house_category;
		this.house_type = house_type;
		this.parameters_square_feet = parameters_square_feet;
		this.parameters_lot_size = parameters_lot_size;
		this.parameters_parking_garage = parameters_parking_garage;
		this.parameters_total_bathrooms = parameters_total_bathrooms;
		this.parameters_total_bedrooms = parameters_total_bedrooms;
		this.parameters_kitchen_size = parameters_kitchen_size;
		this.parameters_year_built = parameters_year_built;
		this.parameters_address = parameters_address;
		this.map_latitude = map_latitude;
		this.map_longitude = map_longitude;
		this.mls_id = mls_id;
		this.property_owner = property_owner;
		this.sale = sale;
		this.rent = rent;
		this.property_price = property_price;
		this.property_currency = property_currency;
		this.property_listing_type = property_listing_type;
		this.property_status = property_status;
	}
	public RetsParameters() {
		super();
	}
	public Map<String, String> getMap() {
		return map;
	}
	public String getProperty_type() {
		return property_type;
	}
	public Long getId() {
		return id;
	}
	public String getProperty_primary_image() {
		return property_primary_image;
	}
	public String getHouse_number() {
		return house_number;
	}
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
	public int getArea_square_feet() {
		return area_square_feet;
	}
	public String getHouse_category() {
		return house_category;
	}
	public String getHouse_type() {
		return house_type;
	}
	public String getParameters_square_feet() {
		return parameters_square_feet;
	}
	public String getParameters_lot_size() {
		return parameters_lot_size;
	}
	public String getParameters_parking_garage() {
		return parameters_parking_garage;
	}
	public String getParameters_total_bathrooms() {
		return parameters_total_bathrooms;
	}
	public String getParameters_total_bedrooms() {
		return parameters_total_bedrooms;
	}
	public String getParameters_kitchen_size() {
		return parameters_kitchen_size;
	}
	public String getParameters_year_built() {
		return parameters_year_built;
	}
	public String getParameters_address() {
		return parameters_address;
	}
	public static String getContactsProprietorName() {
		return contacts_proprietor_name;
	}
	public static String getContactsProprietorRole() {
		return contacts_proprietor_role;
	}
	public String getMap_latitude() {
		return map_latitude;
	}
	public String getMap_longitude() {
		return map_longitude;
	}
	public String getMls_id() {
		return mls_id;
	}
	public String getProperty_owner() {
		return property_owner;
	}
	public int getSale() {
		return sale;
	}
	public int getRent() {
		return rent;
	}
	public String getProperty_price() {
		return property_price;
	}
	public String getProperty_currency() {
		return property_currency;
	}
	public String getProperty_listing_type() {
		return property_listing_type;
	}
	public String getProperty_status() {
		return property_status;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProperty_primary_image(String property_primary_image) {
		this.property_primary_image = property_primary_image;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
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
	public void setArea_square_feet(int area_square_feet) {
		this.area_square_feet = area_square_feet;
	}
	public void setHouse_category(String house_category) {
		this.house_category = house_category;
	}
	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}
	public void setParameters_square_feet(String parameters_square_feet) {
		this.parameters_square_feet = parameters_square_feet;
	}
	public void setParameters_lot_size(String parameters_lot_size) {
		this.parameters_lot_size = parameters_lot_size;
	}
	public void setParameters_parking_garage(String parameters_parking_garage) {
		this.parameters_parking_garage = parameters_parking_garage;
	}
	public void setParameters_total_bathrooms(String parameters_total_bathrooms) {
		this.parameters_total_bathrooms = parameters_total_bathrooms;
	}
	public void setParameters_total_bedrooms(String parameters_total_bedrooms) {
		this.parameters_total_bedrooms = parameters_total_bedrooms;
	}
	public void setParameters_kitchen_size(String parameters_kitchen_size) {
		this.parameters_kitchen_size = parameters_kitchen_size;
	}
	public void setParameters_year_built(String parameters_year_built) {
		this.parameters_year_built = parameters_year_built;
	}
	public void setParameters_address(String parameters_address) {
		this.parameters_address = parameters_address;
	}
	public void setMap_latitude(String map_latitude) {
		this.map_latitude = map_latitude;
	}
	public void setMap_longitude(String map_longitude) {
		this.map_longitude = map_longitude;
	}
	public void setMls_id(String mls_id) {
		this.mls_id = mls_id;
	}
	public void setProperty_owner(String property_owner) {
		this.property_owner = property_owner;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public void setProperty_price(String property_price) {
		this.property_price = property_price;
	}
	public void setProperty_currency(String property_currency) {
		this.property_currency = property_currency;
	}
	public void setProperty_listing_type(String property_listing_type) {
		this.property_listing_type = property_listing_type;
	}
	public void setProperty_status(String property_status) {
		this.property_status = property_status;
	}
	
	
	
}
