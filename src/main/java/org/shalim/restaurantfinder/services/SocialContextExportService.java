package org.shalim.restaurantfinder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.shalim.restaurantfinder.model.SocialContext;
import org.shalim.restaurantfinder.repositories.SocialContextRepository;
import org.shalim.restaurantfinder.services.helpers.LineFormatter;
import org.shalim.restaurantfinder.services.helpers.SocialContextNormalizer;
import org.shalim.restaurantfinder.util.FileWriter;

/**
 * Exports social context data
 * @author Safey A.Halim
 *
 */
public class SocialContextExportService {
	private static String FILE_NAME = "social_contexts";
	private static String FILE_EXTENSION = "data";
	private static String HEADER = "from"
			+ "\t"
			+ "to"
			+ "\tsocial_capital"
			+ "\t"
			+ "tie_strength"
			+ "\t"
			+ "social_similarity"
			+ "\t"
			+ "social_context_similarity"
			+ "\t"
			+ "sympathy"
			+ "\t"
			+ "domain_expertise"
			+ "\t"
			+ "social_hierarchy"
			+ "\t"
			+ "relationship";
	
	
	private static LineFormatter<SocialContext> getLineFormatter() {
		return new LineFormatter<SocialContext>() {
			@Override
			public String format(SocialContext sc) {
				return String.format("%1$d\t%2$d\t%3$s\t%4$s\t%5$s\t%6$s\t%7$s\t%8$s\t%9$s\t%10$s", 
						sc.getFromParticipantId(), sc.getToParticipantId(), sc.getSocialCapital(), 
						sc.getTieStrength(),sc.getSocialSimilarity(), sc.getSocialContextSimilarity(), 
						sc.getSympathy(), sc.getDomainExpertise(), sc.getSocialHierarchy(),
						sc.getRelationship());
			}
		};
	}
	
	public static void exportSocialContextTabSeparated(String dbPath, String destinationPath) throws SQLException, IOException {
		List<SocialContext> socialContexts = SocialContextRepository.getInstance(dbPath).findAllSocialContexts();
		SocialContextNormalizer.normalizeSocialContextInPlace(socialContexts);
		
		FileWriter.exportToFile(socialContexts, getLineFormatter(), destinationPath, FILE_NAME, FILE_EXTENSION, HEADER);
	}
}
