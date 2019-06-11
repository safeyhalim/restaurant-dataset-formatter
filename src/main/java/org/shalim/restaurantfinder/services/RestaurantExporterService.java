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
import org.shalim.restaurantfinder.model.GroupRating;
import org.shalim.restaurantfinder.model.Rating;
import org.shalim.restaurantfinder.model.UserRating;
import org.shalim.restaurantfinder.repositories.GroupRatingsRepository;
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
		normalizeRatings(userRatings);
		exportRatingsToFile(input, userRatings, false);
	}
	
	public static void exportGroupRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> groupRatings = GroupRatingsRepository.getInstance(input.getDbPath()).getAllGroupRatings();
		normalizeRatings(groupRatings);
		exportRatingsToFile(input, groupRatings, true);
	}
	
	private static void normalizeRatings(List<Rating> ratings) {
		RatingsHelper.normalizeRatingsInRangeOneToFive(ratings);
		RatingsHelper.setOverallRatingAverage(ratings);
	}
	
	private static void exportRatingsToFile(Input input, List<Rating> ratings, boolean isGroup) throws IOException {
		String dbPath = input.getDbPath();
		String filePath = input.getDbPath().substring(0, dbPath.lastIndexOf('/')) + "/u_" + System.currentTimeMillis() + ".data";
		
		File file = new File(filePath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
			for (Rating rating : ratings) {
				bufferedWriter.write((isGroup ? ((GroupRating)rating).getGroupId() : ((UserRating)rating).getUserId())
						+ "\t" +  rating.getRestaurantId() + "\t" + new DecimalFormat("#.##").format(rating.getOverallRating()));
				bufferedWriter.newLine();
			}
		}
	}
}
