package org.shalim.restaurantfinder.services.helpers;

import java.util.List;
import org.shalim.restaurantfinder.model.Rating;

public class RatingsHelper {
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
