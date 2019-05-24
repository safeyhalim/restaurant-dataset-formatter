package org.shalim.restaurantfinder.model;

public class UserRating extends Rating {
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRating [userId=" + userId + ", id=" + id + ", restaurantId=" + restaurantId
				+ ", googleRestaurantId=" + googleRestaurantId + ", price=" + price + ", taste=" + taste
				+ ", clumsiness=" + clumsiness + ", service=" + service + ", hippieness=" + hippieness + ", location="
				+ location + ", socialOverlap=" + socialOverlap + ", other=" + other + "]";
	}
}
