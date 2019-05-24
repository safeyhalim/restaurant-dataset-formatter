package org.shalim.restaurantfinder.model;

public class SocialContext {
	private int id;
	private int fromParticipantId;
	private int toParticipantId;
	private double socialCapital;
	private double tieStrength;
	private double socialSimilarity;
	private double socialContextSimilarity;
	private double sympathy;
	private double domainExpertise;
	private double socialHierarchy;
	private String relationship;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFromParticipantId() {
		return fromParticipantId;
	}
	
	public void setFromParticipantId(int fromParticipantId) {
		this.fromParticipantId = fromParticipantId;
	}
	
	public int getToParticipantId() {
		return toParticipantId;
	}
	
	public void setToParticipantId(int toParticipantId) {
		this.toParticipantId = toParticipantId;
	}
	
	public double getSocialCapital() {
		return socialCapital;
	}
	
	public void setSocialCapital(double socialCapital) {
		this.socialCapital = socialCapital;
	}
	
	public double getTieStrength() {
		return tieStrength;
	}
	
	public void setTieStrength(double tieStrength) {
		this.tieStrength = tieStrength;
	}
	
	public double getSocialSimilarity() {
		return socialSimilarity;
	}
	
	public void setSocialSimilarity(double socialSimilarity) {
		this.socialSimilarity = socialSimilarity;
	}
	
	public double getSocialContextSimilarity() {
		return socialContextSimilarity;
	}
	
	public void setSocialContextSimilarity(double socialContextSimilarity) {
		this.socialContextSimilarity = socialContextSimilarity;
	}
	
	public double getSympathy() {
		return sympathy;
	}
	
	public void setSympathy(double sympathy) {
		this.sympathy = sympathy;
	}
	
	public double getDomainExpertise() {
		return domainExpertise;
	}
	
	public void setDomainExpertise(double domainExpertise) {
		this.domainExpertise = domainExpertise;
	}
	
	public double getSocialHierarchy() {
		return socialHierarchy;
	}
	
	public void setSocialHierarchy(double socialHierarchy) {
		this.socialHierarchy = socialHierarchy;
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	@Override
	public String toString() {
		return "SocialContext [id=" + id + ", fromParticipantId=" + fromParticipantId + ", toParticipantId="
				+ toParticipantId + ", socialCapital=" + socialCapital + ", tieStrength=" + tieStrength
				+ ", socialSimilarity=" + socialSimilarity + ", socialContextSimilarity=" + socialContextSimilarity
				+ ", sympathy=" + sympathy + ", domainExpertise=" + domainExpertise + ", socialHierarchy="
				+ socialHierarchy + ", relationship=" + relationship + "]";
	}
}
