package org.shalim.restaurantfinder.model;

public class UserGroup {
	private int id;
	private int participantId;
	private int groupId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParticipantId() {
		return participantId;
	}
	
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", participantId=" + participantId + ", groupId=" + groupId + "]";
	}
}
