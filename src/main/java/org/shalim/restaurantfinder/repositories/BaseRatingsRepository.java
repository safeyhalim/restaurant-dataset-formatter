package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shalim.restaurantfinder.model.Rating;

public class BaseRatingsRepository extends BaseRepository {
	private static BaseRatingsRepository instance;
	private static final String COL_ID = "id";
	private static final String COL_RESTAURANT_ID = "restaurant_id";
	private static final String COL_PRICE = "price";
	private static final String COL_TASTE = "taste";
	private static final String COL_CLUMSINESS = "clumsiness";
	private static final String COL_HIPPIENESS = "hippieness";
	private static final String COL_LOCATION = "location";
	private static final String COL_SOCIAL_OVERLAP = "social_overlap";
	private static final String COL_OTHER = "other";
	private static final String COL_GOOGLE_RESTAURANT_ID = "google_restaurant_id";
		
	public static BaseRatingsRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new BaseRatingsRepository(dbPath);
		}
		return instance;
	}
	
	protected BaseRatingsRepository(String dbPath) {
		super(dbPath);
	}
	
	protected void buildRatingFromResultSet(Rating rating, ResultSet rs) throws SQLException {
		rating.setId(rs.getInt(COL_ID));
		rating.setRestaurantId(rs.getInt(COL_RESTAURANT_ID));
		rating.setPrice(rs.getInt(COL_PRICE));
		rating.setTaste(rs.getInt(COL_TASTE));
		rating.setClumsiness(rs.getInt(COL_CLUMSINESS));
		rating.setHippieness(rs.getInt(COL_HIPPIENESS));
		rating.setLocation(rs.getInt(COL_LOCATION));
		rating.setSocialOverlap(rs.getInt(COL_SOCIAL_OVERLAP));
		rating.setOther(rs.getString(COL_OTHER));
		rating.setGoogleRestaurantId(rs.getString(COL_GOOGLE_RESTAURANT_ID));
	}
}
