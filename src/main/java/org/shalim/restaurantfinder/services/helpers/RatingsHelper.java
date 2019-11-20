package org.shalim.restaurantfinder.services.helpers;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.shalim.restaurantfinder.model.Rating;
import org.shalim.restaurantfinder.model.UserRating;
import org.shalim.restaurantfinder.model.GroupRating;

public class RatingsHelper {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Rating> findDuplicates(List<Rating> ratings, boolean isGroup) {
		MultiKeyMap ratingsMap = new MultiKeyMap<>();
		List<Rating> duplicateRatings = new ArrayList<>();
		for (Rating rating : ratings) {
			int raterId = isGroup ? ((GroupRating)rating).getGroupId() : ((UserRating)rating).getUserId();
			if (ratingsMap.containsKey(raterId, rating.getRestaurantId())) {
				duplicateRatings.add(rating);
			}
			else {
				ratingsMap.put(raterId, rating.getRestaurantId(), rating);
			}
		}
		return duplicateRatings;
	}
	
	public static void normalizeRatingsInRangeOneToFive(List<Rating> ratings) {
		normalizeRatingsInRange(ratings, 20);
	}
	
	public static void setOverallRatingAverage(List<Rating> ratings) {
		for (Rating rating : ratings) {
			double sumRatings = rating.getClumsiness() + rating.getHippieness() + 
					rating.getLocation() + rating.getPrice() + rating.getService() + 
					rating.getSocialOverlap() + rating.getTaste();
			rating.setOverallRating(sumRatings / 7);
		}
	}
	
	private static void normalizeRatingsInRange(List<Rating> ratings, double divisor) {
		for (Rating rating : ratings) {
			rating.setClumsiness(rating.getClumsiness() / divisor);
			rating.setHippieness(rating.getHippieness() / divisor);
			rating.setLocation(rating.getHippieness() / divisor);
			rating.setPrice(rating.getPrice() / divisor);
			rating.setService(rating.getService() / divisor);
			rating.setSocialOverlap(rating.getSocialOverlap() / divisor);
			rating.setTaste(rating.getTaste() / divisor);
		}
	}
}
