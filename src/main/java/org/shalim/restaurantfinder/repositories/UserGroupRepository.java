package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.shalim.restaurantfinder.model.UserGroup;

public class UserGroupRepository extends BaseRepository {

	private static UserGroupRepository instance;
	private static final String TAB_USER_GROUP = "guratorapp_groupparticipant";
	private static final String COL_ID = "id";
	private static final String COL_GROUP_ID = "group_id";
	private static final String COL_PARTICIPANT_ID = "participant_id";
	
	private UserGroupRepository(String dbPath) {
		super(dbPath);
	}
	
	public static UserGroupRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new UserGroupRepository(dbPath);
		}
		return instance;
	}
	
	public List<UserGroup> getUsersInGroups() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select * from %s", TAB_USER_GROUP));
	        List<UserGroup> userGroups = new ArrayList<UserGroup>();
	        while (rs.next()) {
	        	UserGroup userGroup = new UserGroup();
	        	userGroup.setId(rs.getInt(COL_ID));
	        	userGroup.setGroupId(rs.getInt(COL_GROUP_ID));
	        	userGroup.setParticipantId(rs.getInt(COL_PARTICIPANT_ID));
	        	userGroups.add(userGroup);
	        }
	        return userGroups;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
}
