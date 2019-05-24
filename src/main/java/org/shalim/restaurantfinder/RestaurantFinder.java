package org.shalim.restaurantfinder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.model.Restaurant;
import org.shalim.restaurantfinder.repositories.RestaurantRepository;
import org.shalim.restaurantfinder.services.GooglePlacesService;
import org.shalim.restaurantfinder.services.RestaurantDumpService;
import org.shalim.restaurantfinder.services.RestaurantExporterService;
import org.shalim.restaurantfinder.services.RestaurantKFoldService;
import org.shalim.restaurantfinder.services.SocialContextExportService;
import org.shalim.restaurantfinder.util.ArgParser;
import org.shalim.restaurantfinder.util.FilePathUtil;

public class RestaurantFinder {

	public static void main(String[] args) throws SQLException, IOException {
		Input input = ArgParser.parse(args);
		
		if (input.isExportUserRatings()) {
			RestaurantExporterService.exportUserRatingsTabSeparated(input);
		} else if (input.isExportSocialContexts()) {
			exportSocialContexts(input);
		} else if (input.getApiKey() != null) {
			GooglePlacesService.findRestaurantsByGooglePlacesIds(input.getApiKey(), RestaurantRepository.getInstance(input.getDbPath()).getAllRestauratsGooglePlacesIds());
		} else if (input.getDumpPath() != null) {
			updateRestaurantsFromDump(input);
		} else if (input.getDataSetPath() != null) {
			RestaurantKFoldService.createKFoldTrainingTestSets(input);
		}
		else {
			System.err.println("Fatal error: API key and dump file path cannot both be missing!");
		}
	}
	
	private static void updateRestaurantsFromDump(Input input) throws IOException, SQLException {
		List<Restaurant> restaurants = RestaurantDumpService.readRestaurantDataFromDumpFile(input.getDumpPath());
		RestaurantRepository.getInstance(input.getDbPath()).updateAllRestaurants(restaurants);
	}
	
	private static void exportSocialContexts(Input input) throws SQLException, IOException {
		SocialContextExportService.exportSocialContextTabSeparated(input.getDbPath(), FilePathUtil.getFilePathWithoutName(input.getDbPath()));
	}
}
