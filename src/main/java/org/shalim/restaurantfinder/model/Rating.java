package org.shalim.restaurantfinder.model;

public class Rating {
	protected int id;
	protected int restaurantId;
	protected String googleRestaurantId;
	protected double price;
	protected double taste;
	protected double clumsiness;
	protected double service;
	protected double hippieness;
	protected double location;
	protected double socialOverlap;
	protected String other;
	
	private double overallRating; // This property is not a part of the data model
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public String getGoogleRestaurantId() {
		return googleRestaurantId;
	}
	
	public void setGoogleRestaurantId(String googleRestaurantId) {
		this.googleRestaurantId = googleRestaurantId;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTaste() {
		return taste;
	}
	
	public void setTaste(double taste) {
		this.taste = taste;
	}
	
	public double getClumsiness() {
		return clumsiness;
	}
	
	public void setClumsiness(double clumsiness) {
		this.clumsiness = clumsiness;
	}
	
	public double getService() {
		return service;
	}
	
	public void setService(double service) {
		this.service = service;
	}
	
	public double getHippieness() {
		return hippieness;
	}
	
	public void setHippieness(double hippieness) {
		this.hippieness = hippieness;
	}
	
	public double getLocation() {
		return location;
	}
	
	public void setLocation(double location) {
		this.location = location;
	}
	
	public double getSocialOverlap() {
		return socialOverlap;
	}
	
	public void setSocialOverlap(double socialOverlap) {
		this.socialOverlap = socialOverlap;
	}
	
	public String getOther() {
		return other;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
	
	public double getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	
	@Override
	public String toString() {
		return "Rating [id=" + id + ", restaurantId=" + restaurantId + ", googleRestaurantId=" + googleRestaurantId
				+ ", price=" + price + ", taste=" + taste + ", clumsiness=" + clumsiness + ", service=" + service
				+ ", hippieness=" + hippieness + ", location=" + location + ", socialOverlap=" + socialOverlap
				+ ", other=" + other + ", overallRating=" + overallRating + "]";
	}

}
