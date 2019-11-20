package org.shalim.restaurantfinder.services.personality;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import org.shalim.restaurantfinder.domain.Input;
import org.shalim.restaurantfinder.model.ParticipantPersonality;
import org.shalim.restaurantfinder.repositories.PersonalityRepository;

public class PersonalityExporterService {
	
    public static void exportParticipantPersonality(Input input) throws SQLException, IOException {
    	PersonalityRepository personalityRepository = PersonalityRepository.getIntance(input.getDbPath());
    	List<ParticipantPersonality> personalities = personalityRepository.getPersonalities();
    	for (ParticipantPersonality personality : personalities) {
    		personality.computeConflictModeWeight();
    	}
    	exportPersonalityToFile(input, personalities);
    }
    
    private static void exportPersonalityToFile(Input input, List<ParticipantPersonality> personalities) throws IOException {
    	String filePath = input.getDbPath().substring(0, input.getDbPath().lastIndexOf('/')) + "/personality.data";
    	File file = new File(filePath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
			for (ParticipantPersonality personality : personalities) {
				bufferedWriter.write(personality.getParticipantId() + "\t" + new DecimalFormat("#.##").format(personality.getPersonalityScore()));
				bufferedWriter.newLine();
			}
		}
    }
}
