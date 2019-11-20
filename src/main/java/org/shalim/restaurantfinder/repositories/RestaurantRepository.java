package org.shalim.restaurantfinder.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.shalim.restaurantfinder.model.Restaurant;

public class RestaurantRepository extends BaseRepository {
	private static RestaurantRepository instance;
	private String COL_ID = "id";
	private String COL_GOOGLE_PLACE_ID = "google_place_id";
	private String COL_NAME = "name";
	private String COL_CITY = "city";
	private String COL_COUNTRY = "country";
	private String COL_GOOGLE_RATING = "google_rating";
	private String COL_GOOGLE_TOTAL_RATINGS = "google_total_ratings";
	private String COL_PRICE_LEVEL = "price_level";
	
	public static RestaurantRepository getInstance(String dbPath) {
		if (instance == null) {
			instance = new RestaurantRepository(dbPath);
		}
		return instance;
	}
	
	private RestaurantRepository(String dbPath) {
		super(dbPath);
	}
	
	public List<Restaurant> getAllRestaurants() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select %s, %s from restaurant", COL_ID, COL_GOOGLE_PLACE_ID));
	        List<Restaurant> restaurants = new ArrayList<Restaurant>();
	        while (rs.next()) {
	        	Restaurant restaurant = new Restaurant(rs.getInt(COL_ID), rs.getString(COL_GOOGLE_PLACE_ID));
	        	restaurants.add(restaurant);
	        }
	        return restaurants;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public List<String> getAllRestauratsGooglePlacesIds() throws SQLException {
		ResultSet rs = null;
		try {
	        rs = createStatement().executeQuery(String.format("select %s from restaurant", COL_GOOGLE_PLACE_ID));
	        List<String> googlePlacesIds = new ArrayList<String>();
	        while (rs.next()) {
	        	googlePlacesIds.add(rs.getString(COL_GOOGLE_PLACE_ID));
	        }
	        return googlePlacesIds;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public void updateAllRestaurants(List<Restaurant> restaurants) throws SQLException {
		PreparedStatement updateStatement = null;
		String sql = String.format("update restaurant set %s = ?, %s = ?, %s = ?, "
				+ "%s = ?, %s = ?, %s = ? where google_place_id = ?", 
				COL_NAME, COL_CITY, COL_COUNTRY, COL_GOOGLE_RATING, 
				COL_GOOGLE_TOTAL_RATINGS, 
				COL_PRICE_LEVEL, 
				COL_GOOGLE_PLACE_ID);
		try {
			updateStatement = prepareStatement(sql);
			for (Restaurant restaurant : restaurants) {
				updateStatement.setString(1, restaurant.getName());
				updateStatement.setString(2, restaurant.getCity());
				updateStatement.setString(3, restaurant.getCountry());
				updateStatement.setDouble(4, restaurant.getGoogleRating());
				updateStatement.setInt(5, restaurant.getGoogleTotalRatings());
				updateStatement.setInt(6, restaurant.getPriceLevel());
				updateStatement.setString(7, restaurant.getGooglePlacesId());
				
				updateStatement.addBatch();
			}
			updateStatement.executeBatch();
		} finally {
			if (updateStatement != null) {
				updateStatement.close();
			}
		}
	}
}
