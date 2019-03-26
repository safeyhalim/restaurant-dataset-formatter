package org.shalim.restaurantfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantWrapper {
	@JsonProperty("result")
	private Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
