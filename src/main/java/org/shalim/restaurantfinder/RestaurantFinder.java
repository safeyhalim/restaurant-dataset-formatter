package org.shalim.restaurantfinder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.model.Restaurant;
import org.shalim.restaurantfinder.repositories.RestaurantRepository;
import org.shalim.restaurantfinder.services.GooglePlacesService;
import org.shalim.restaurantfinder.services.RestaurantDumpService;
import org.shalim.restaurantfinder.util.ArgParser;

public class RestaurantFinder {

	public static void main(String[] args) throws SQLException, IOException {
		Input input = ArgParser.parse(args);
		RestaurantRepository repo = new RestaurantRepository(input.getDbPath());
		List<Restaurant> restaurants = null;
		if (input.getApiKey() != null) {
			restaurants = GooglePlacesService.findRestaurantsByGooglePlacesIds(input.getApiKey(), repo.getAllRestauratsGooglePlacesIds());
		} else if (input.getDumpPath() != null) {
			restaurants = RestaurantDumpService.readRestaurantDataFromDumpFile(input.getDumpPath());
		} else {
			System.err.println("Fatal error: API key and dump file path cannot both be missing!");
		}
		for (Restaurant r : restaurants) {
			System.out.println(r.toString());
		}
		repo.updateAllRestaurants(restaurants);
	}
}
