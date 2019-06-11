package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.shalim.restaurantfinder.model.Rating;
import org.shalim.restaurantfinder.model.UserRating;

public class UserRatingsRepository extends BaseRatingsRepository {
	private static UserRatingsRepository instance;
	private static final String TAB_USER_RATINGS = "guratorapp_restaurantsurvey";
	private static final String COL_USER_ID = "participant_id";
		
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
	        	buildRatingFromResultSet(userRating, rs);
	        	userRating.setUserId(rs.getInt(COL_USER_ID));
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
