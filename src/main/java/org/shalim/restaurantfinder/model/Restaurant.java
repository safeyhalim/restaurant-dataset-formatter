package org.shalim.restaurantfinder.model;

public class Restaurant {
	private int id;
	private String googlePlaceId;
	private String name;
	private String city;
	private String country;
	private double googleRating;
	private int googleTotalRatings;
	
	public Restaurant() {}
	
	public Restaurant(int id, String googlePlaceId) {
		setId(id);
		setGooglePlaceId(googlePlaceId);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGooglePlaceId() {
		return googlePlaceId;
	}
	
	public void setGooglePlaceId(String googlePlaceId) {
		this.googlePlaceId = googlePlaceId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public double getGoogleRating() {
		return googleRating;
	}
	
	public void setGoogleRating(double googleRating) {
		this.googleRating = googleRating;
	}
	
	public int getGoogleTotalRatings() {
		return googleTotalRatings;
	}
	
	public void setGoogleTotalRatings(int googleTotalRatings) {
		this.googleTotalRatings = googleTotalRatings;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", googlePlaceId=" + googlePlaceId + ", name=" + name + ", city=" + city
				+ ", country=" + country + ", googleRating=" + googleRating + ", googleTotalRatings="
				+ googleTotalRatings + "]";
	}
}
