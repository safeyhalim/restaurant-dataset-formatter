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
	
	private enum Mode {
		USERS, GROUPS, INTERNAL_GROUPS, EXTERNAL_GROUPS
	};
	
	public static void exportUserRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> userRatings = UserRatingsRepository.getInstance(input.getDbPath()).getAllUserRatings();
		normalizeRatings(userRatings);
		exportRatingsToFile(input, userRatings, Mode.USERS);
	}
	
	public static void exportGroupRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> groupRatings = GroupRatingsRepository.getInstance(input.getDbPath()).getAllGroupRatings();
		normalizeRatings(groupRatings);
		exportRatingsToFile(input, groupRatings, Mode.GROUPS);
	}
	
	public static void exportInternalGroupRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> groupRatings = GroupRatingsRepository.getInstance(input.getDbPath()).getInternalGroupRatings();
		normalizeRatings(groupRatings);
		exportRatingsToFile(input, groupRatings, Mode.INTERNAL_GROUPS);
	}
	
	public static void exportExternalGroupRatingsTabSeparated(Input input) throws SQLException, IOException {
		List<Rating> groupRatings = GroupRatingsRepository.getInstance(input.getDbPath()).getExternalGroupRatings();
		normalizeRatings(groupRatings);
		exportRatingsToFile(input, groupRatings, Mode.EXTERNAL_GROUPS);
	}
	
	private static void normalizeRatings(List<Rating> ratings) {
		RatingsHelper.normalizeRatingsInRangeOneToFive(ratings);
		RatingsHelper.setOverallRatingAverage(ratings);
	}
	
	private static void exportRatingsToFile(Input input, List<Rating> ratings, Mode mode) throws IOException {
		String dbPath = input.getDbPath();
		String filePath = input.getDbPath().substring(0, dbPath.lastIndexOf('/')) + "/" + getFilePrefixFromMode(mode) + "_" + System.currentTimeMillis() + ".data";
		
		File file = new File(filePath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
			for (Rating rating : ratings) {
				bufferedWriter.write((isGroup(mode) ? ((GroupRating)rating).getGroupId() : ((UserRating)rating).getUserId())
						+ "\t" +  rating.getRestaurantId() + "\t" + new DecimalFormat("#.##").format(rating.getOverallRating()));
				bufferedWriter.newLine();
			}
		}
	}
	
	private static String getFilePrefixFromMode(Mode mode) {
		switch(mode) {
		case USERS:
			return "u";
		case GROUPS:
			return "g";
		case INTERNAL_GROUPS:
			return "g_in";
		case EXTERNAL_GROUPS:
			return "g_ex";
		}
		return "u"; // Default if the mode is unknown
	}
	
	private static boolean isGroup(Mode mode) {
		return mode != Mode.USERS;
	}
}
