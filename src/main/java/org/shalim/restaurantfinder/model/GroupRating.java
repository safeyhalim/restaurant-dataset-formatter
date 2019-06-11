package org.shalim.restaurantfinder.model;

public class GroupRating extends Rating {
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "GroupRating [groupId=" + groupId + ", id=" + id + ", restaurantId=" + restaurantId
				+ ", googleRestaurantId=" + googleRestaurantId + ", price=" + price + ", taste=" + taste
				+ ", clumsiness=" + clumsiness + ", service=" + service + ", hippieness=" + hippieness + ", location="
				+ location + ", socialOverlap=" + socialOverlap + ", other=" + other + "]";
	}
}
