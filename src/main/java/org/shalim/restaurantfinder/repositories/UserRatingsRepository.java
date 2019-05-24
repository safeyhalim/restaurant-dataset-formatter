package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.shalim.restaurantfinder.model.Rating;
import org.shalim.restaurantfinder.model.UserRating;

public class UserRatingsRepository extends BaseRepository {
	private static UserRatingsRepository instance;
	private static final String TAB_USER_RATINGS = "guratorapp_restaurantsurvey";
	private static final String COL_ID = "id";
	private static final String COL_USER_ID = "participant_id";
	private static final String COL_RESTAURANT_ID = "restaurant_id";
	private static final String COL_PRICE = "price";
	private static final String COL_TASTE = "taste";
	private static final String COL_CLUMSINESS = "clumsiness";
	private static final String COL_HIPPIENESS = "hippieness";
	private static final String COL_LOCATION = "location";
	private static final String COL_SOCIAL_OVERLAP = "social_overlap";
	private static final String COL_OTHER = "other";
	private static final String COL_GOOGLE_RESTAURANT_ID = "google_restaurant_id";
		
	public static UserRatingsRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new UserRatingsRepository(dbPath);
		}
		return instance;
	}
	
	private UserRatingsRepository(String dbPath) {
		super(dbPath);
	}
	
	public List<Rating> getAllUserRatings() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select * from %s", TAB_USER_RATINGS));
	        List<Rating> userRatings = new ArrayList<Rating>();
	        while (rs.next()) {
	        	UserRating userRating = new UserRating();
	        	userRating.setId(rs.getInt(COL_ID));
	        	userRating.setUserId(rs.getInt(COL_USER_ID));
	        	userRating.setRestaurantId(rs.getInt(COL_RESTAURANT_ID));
	        	userRating.setPrice(rs.getInt(COL_PRICE));
	        	userRating.setTaste(rs.getInt(COL_TASTE));
	        	userRating.setClumsiness(rs.getInt(COL_CLUMSINESS));
	        	userRating.setHippieness(rs.getInt(COL_HIPPIENESS));
	        	userRating.setLocation(rs.getInt(COL_LOCATION));
	        	userRating.setSocialOverlap(rs.getInt(COL_SOCIAL_OVERLAP));
	        	userRating.setOther(rs.getString(COL_OTHER));
	        	userRating.setGoogleRestaurantId(rs.getString(COL_GOOGLE_RESTAURANT_ID));
	        	userRatings.add(userRating);
	        }
	        return userRatings;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

}
