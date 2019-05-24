package org.shalim.restaurantfinder.services.helpers;

import java.util.List;

import org.shalim.restaurantfinder.model.SocialContext;

public class SocialContextNormalizer {
	
	private static double NORMALIZATION_FACTOR = 100;
	
	/**
	 * Normalizes the social context parameters to values between 0 and 1.<br>
	 * The old values are overwritten, therefore, the normalization happens in place<br>
	 * (in the same object list)
	 * @param socialContexts
	 */
	public static void normalizeSocialContextInPlace(List<SocialContext> socialContexts) {
		for (SocialContext sc : socialContexts) {
			sc.setDomainExpertise(sc.getDomainExpertise() / NORMALIZATION_FACTOR);
			sc.setSocialCapital(sc.getSocialCapital() / NORMALIZATION_FACTOR);
			sc.setSocialSimilarity(sc.getSocialSimilarity() / NORMALIZATION_FACTOR);
			sc.setSocialContextSimilarity(sc.getSocialContextSimilarity() / NORMALIZATION_FACTOR);
			sc.setSocialHierarchy(sc.getSocialHierarchy() / NORMALIZATION_FACTOR);
			sc.setSympathy(sc.getSympathy() / NORMALIZATION_FACTOR);
			sc.setTieStrength(sc.getTieStrength() / NORMALIZATION_FACTOR);
		}
	}
}
