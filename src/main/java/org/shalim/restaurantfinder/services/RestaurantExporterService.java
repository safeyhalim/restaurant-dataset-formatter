package org.shalim.restaurantfinder.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.model.Rating;
import org.shalim.restaurantfinder.model.UserRating;
import org.shalim.restaurantfinder.repositories.UserRatingsRepository;
import org.shalim.restaurantfinder.services.helpers.RatingsHelper;

/**
 * Exports the restaurants dataset into a MovieLens like data format
 * @author Safey A.Halim
 *
 */
public class RestaurantExporterService {
	
	public static void exportUserRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> userRatings = UserRatingsRepository.getInstance(input.getDbPath()).getAllUserRatings();
		RatingsHelper.normalizeRatingsInRangeOneToFive(userRatings);
		RatingsHelper.setOverallRatingAverage(userRatings);
		exportRatingsToFile(input, userRatings);
	}
	
	private static void exportRatingsToFile(Input input, List<Rating> ratings) throws IOException {
		String dbPath = input.getDbPath();
		String filePath = input.getDbPath().substring(0, dbPath.lastIndexOf('/')) + "/u_" + System.currentTimeMillis() + ".data";
		
		File file = new File(filePath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
			for (Rating rating : ratings) {
				UserRating userRating  = (UserRating)rating;
				bufferedWriter.write(userRating.getUserId() + "\t" +  userRating.getRestaurantId() + "\t" + new DecimalFormat("#.##").format(userRating.getOverallRating()));
				bufferedWriter.newLine();
			}
		}
	}
}
