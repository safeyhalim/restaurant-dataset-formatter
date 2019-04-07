package org.shalim.restaurantfinder.services;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.shalim.restaurantfinder.model.Restaurant;

public class RestaurantDumpService {
	
	public static List<Restaurant> readRestaurantDataFromDumpFile(String filePath) throws IOException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			while (line != null) {
				Restaurant restaurant = convertLineToRestaurant(line);
				if (restaurant != null) {
					restaurants.add(restaurant);
				}
				line = reader.readLine();
			}
		}
		System.out.println("Loaded " + restaurants.size() + " restaurants");
		return restaurants;
	}
	
	private static Restaurant convertLineToRestaurant(String line) {
		line = StringUtils.deleteWhitespace(line);
		String[] lineArr = line.split(",");
		Restaurant restaurant = new Restaurant();
		for (String keyVal : lineArr) {
			String[] keyValArr = keyVal.split("=");
			String key = keyValArr[0];
			String val = keyValArr[1];
			switch (key) {
			case "id":
				restaurant.setId(Integer.parseInt(val));
				break;
			case "googlePlacesId":
				restaurant.setGooglePlacesId(val);
				break;
			case "name":
				restaurant.setName(val);
				break;
			case "city":
				restaurant.setCity(val);
				break;
			case "country":
				restaurant.setCountry(val);
				break;
			case "googleRating":
				restaurant.setGoogleRating(Double.parseDouble(val));
				break;
			case "googleTotalRatings":
				restaurant.setGoogleTotalRatings(Integer.parseInt(val));
				break;
			case "priceLevel":
				restaurant.setPriceLevel(Integer.parseInt(val));
				break;
			default:
				break;
			}
		}
		if (restaurant.getGooglePlacesId() == null || restaurant.getName() == null) {
			return null;
		}
		return restaurant;
	}
}
