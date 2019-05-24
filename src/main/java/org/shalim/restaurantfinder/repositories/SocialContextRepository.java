package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.shalim.restaurantfinder.model.SocialContext;

public class SocialContextRepository extends BaseRepository {

	private static SocialContextRepository instance = null;
	private static final String TAB_SOCIAL_CONTEXT = "guratorapp_usersurvey";
	private static final String COL_ID = "id";
	private static final String COL_FROM_PARTICIPANT_ID = "from_participant_id";
	private static final String COL_TO_PARTICIPANT_ID = "to_participant_id";
	private static final String COL_SOCIAL_CAPITAL = "social_capital";
	private static final String COL_TIE_STRENGTH = "tie_strength";
	private static final String COL_SOCIAL_SIMILARITY = "social_similarity";
	private static final String COL_SOCIAL_CONTEXT_SIMILARITY = "social_context_similarity";
	private static final String COL_SYMPATHY = "sympathy";
	private static final String COL_DOMAIN_EXPERTISE = "domain_expertise";
	private static final String COL_SOCIAL_HIERARCHY = "social_hierarchy";
	private static final String COL_RELATIONSHIP = "relationship";
	
	
	private SocialContextRepository(String dbPath) {
		super(dbPath);
	}
	
	public static SocialContextRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new SocialContextRepository(dbPath);
		}
		return instance;
	}
	
	public List<SocialContext> findAllSocialContexts() throws SQLException {
		ResultSet rs = null;
		try {
			rs = createStatement().executeQuery(String.format("select * from %s", TAB_SOCIAL_CONTEXT));
			List<SocialContext> socialContexts = new ArrayList<>();
			while (rs.next()) {
				SocialContext sc = new SocialContext();
				sc.setId(rs.getInt(COL_ID));
				sc.setFromParticipantId(rs.getInt(COL_FROM_PARTICIPANT_ID));
				sc.setToParticipantId(rs.getInt(COL_TO_PARTICIPANT_ID));
				sc.setSocialCapital(rs.getDouble(COL_SOCIAL_CAPITAL));
				sc.setTieStrength(rs.getDouble(COL_TIE_STRENGTH));
				sc.setSocialSimilarity(rs.getDouble(COL_SOCIAL_SIMILARITY));
				sc.setSocialContextSimilarity(rs.getDouble(COL_SOCIAL_CONTEXT_SIMILARITY));
				sc.setSympathy(rs.getDouble(COL_SYMPATHY));
				sc.setDomainExpertise(rs.getDouble(COL_DOMAIN_EXPERTISE));
				sc.setSocialHierarchy(rs.getDouble(COL_SOCIAL_HIERARCHY));
				sc.setRelationship(rs.getString(COL_RELATIONSHIP));
				
				socialContexts.add(sc);
			}
			
			return socialContexts;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}
