package org.shalim.restaurantfinder;

import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.util.ArgParser;

public class RestaurantFinder {

	public static void main(String[] args) {
		Input input = ArgParser.parse(args);
		System.out.println("DB Path " + input.getDbPath());
		System.out.println("API Key  " + input.getApiKey());
	}

}
