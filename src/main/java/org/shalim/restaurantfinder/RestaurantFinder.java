package org.shalim.restaurantfinder;

import java.sql.SQLException;
import java.util.List;

import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.model.Restaurant;
import org.shalim.restaurantfinder.repositories.RestaurantRepository;
import org.shalim.restaurantfinder.util.ArgParser;

public class RestaurantFinder {

	public static void main(String[] args) throws SQLException {
		Input input = ArgParser.parse(args);
		RestaurantRepository repo = new RestaurantRepository(input.getDbPath());
		List<Restaurant> restaurants = repo.getAllRestaurants();
		for (Restaurant r : restaurants) {
			System.out.println(r.toString());
		}
	}

}
