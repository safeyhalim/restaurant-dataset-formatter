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
	private static final String TAB_GROUPS = "guratorapp_group";
	private static final String TAB_GROUPS_COL_INTERNAL = TAB_GROUPS + ".internal";
	private static final String TAB_GROUPS_COL_ID = TAB_GROUPS + ".id";
		
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
		return doGetRatings(String.format("select * from %s", TAB_GROUP_RATINGS));
	}
	
	public List<Rating> getInternalGroupRatings() throws SQLException {
		return doGetRatings(prepareTypedGroupRatingsQuery(true));
	}
	
	public List<Rating> getExternalGroupRatings() throws SQLException {
		return doGetRatings(prepareTypedGroupRatingsQuery(false));
	}
	
	private String prepareTypedGroupRatingsQuery(boolean isInternal) {
		return String.format("select * from %s, %s where %s = %s and %s = %s", TAB_GROUP_RATINGS, TAB_GROUPS, TAB_GROUPS_COL_INTERNAL, isInternal ? "'in'" : "'ex'",  TAB_GROUPS_COL_ID, COL_GROUP_ID);		
	}
	
	private List<Rating> doGetRatings(String statement) throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(statement);
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
