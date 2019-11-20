package org.shalim.restaurantfinder.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shalim.restaurantfinder.model.ParticipantPersonality;

public class PersonalityRepository extends BaseRepository {
	private static PersonalityRepository instance;
	private String TAB_PARTICIPANT_PERSONALITY_QUESTION = "guratorapp_participantpersonalityquestion";
	private String COL_PARTICIPANT_ID = "participant_id";
	private String COL_QUESTION_ID = "personality_question_id";
	private String COL_ANSWER = "answer";
	
	private PersonalityRepository(String dbPath) {
		super(dbPath);
	}
	
	public static PersonalityRepository getIntance(String dbPath) {
		if (instance == null) {
			instance = new PersonalityRepository(dbPath);
		}
		return instance;
	}
	
	public List<ParticipantPersonality> getPersonalities() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select %s, %s, %s from %s", COL_PARTICIPANT_ID, COL_QUESTION_ID, COL_ANSWER, TAB_PARTICIPANT_PERSONALITY_QUESTION));
	        Map<Integer, ParticipantPersonality> participantAnswers = new HashMap<>();
	        while (rs.next()) {
	        	int participantId = rs.getInt(COL_PARTICIPANT_ID);
	        	int questionId = rs.getInt(COL_QUESTION_ID);
	        	String answer = rs.getString(COL_ANSWER);
	        	if (participantAnswers.containsKey(participantId)) {
	        		ParticipantPersonality personality = participantAnswers.get(participantId);
	        		personality.getAnswers()[questionId - 1] = answer;
	        	} else {
	        		ParticipantPersonality personality = new ParticipantPersonality();
	        		String[] answers = new String[30];
	        		answers[questionId - 1] = answer;
	        		personality.setParticipantId(participantId);
	        		personality.setAnswers(answers);
	        		participantAnswers.put(participantId, personality);
	        	}
	        }
	        return new ArrayList<ParticipantPersonality>(participantAnswers.values());
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}
