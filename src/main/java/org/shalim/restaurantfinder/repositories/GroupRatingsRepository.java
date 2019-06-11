package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.shalim.restaurantfinder.model.GroupRating;
import org.shalim.restaurantfinder.model.Rating;

public class GroupRatingsRepository extends BaseRatingsRepository {
	private static GroupRatingsRepository instance;
	private static final String TAB_GROUP_RATINGS = "guratorapp_grouprestaurantsurvey";
	private static final String COL_GROUP_ID = "group_id";
		
	public static GroupRatingsRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new GroupRatingsRepository(dbPath);
		}
		return instance;
	}
	
	private GroupRatingsRepository(String dbPath) {
		super(dbPath);
	}
	
	public List<Rating> getAllGroupRatings() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select * from %s", TAB_GROUP_RATINGS));
	        List<Rating> groupRatings = new ArrayList<Rating>();
	        while (rs.next()) {
	        	GroupRating groupRating = new GroupRating();
	        	buildRatingFromResultSet(groupRating, rs);
	        	groupRating.setGroupId(rs.getInt(COL_GROUP_ID));
	        	groupRatings.add(groupRating);
	        }
	        return groupRatings;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

}
