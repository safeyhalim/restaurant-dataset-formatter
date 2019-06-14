package org.shalim.restaurantfinder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.shalim.restaurantfinder.model.UserGroup;
import org.shalim.restaurantfinder.repositories.UserGroupRepository;
import org.shalim.restaurantfinder.services.helpers.LineFormatter;
import org.shalim.restaurantfinder.util.FileWriter;

public class UserService {
	private static String FILE_NAME = "user_groups";
	private static String FILE_EXTENSION = "data";
	private static String HEADER = "user_id"
			+ "\t"
			+ "group_id";
	
	private static LineFormatter<UserGroup> getLineFormatter() {
		return new LineFormatter<UserGroup>() {
			@Override
			public String format(UserGroup entry) {
				return String.format("%s\t%s", entry.getParticipantId(), entry.getGroupId());
			}
			
		};
	};
	
	public static void exportUserGroupTabSeparated(String dbPath, String destinationPath) throws SQLException, IOException {
		List<UserGroup> userGroups = UserGroupRepository.getInstance(dbPath).getUsersInGroups();
		FileWriter.exportToFile(userGroups, getLineFormatter(), destinationPath, FILE_NAME, FILE_EXTENSION, HEADER);
	}
}
