package org.shalim.restaurantfinder.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.shalim.restaurantfinder.model.Restaurant;

public class RestaurantRepository {
	private String COL_ID = "id";
	private String COL_GOOGLE_PLACE_ID = "google_place_id";
	private String COL_NAME = "name";
	private String COL_CITY = "city";
	private String COL_COUNTRY = "country";
	private String COL_GOOGLE_RATING = "google_rating";
	private String COL_GOOGLE_TOTAL_RATINGS = "google_total_ratings";
	
	private String dbPath;
	
	public RestaurantRepository(String dbPath) {
		this.dbPath = dbPath;
	}
	
	private Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
	}
	
	public List<Restaurant> getAllRestaurants() throws SQLException {
		ResultSet rs = null;
		try {
			Connection connection = connect();
	        Statement statement = connection.createStatement();
	        rs = statement.executeQuery(String.format("select %s, %s from restaurant", COL_ID, COL_GOOGLE_PLACE_ID));
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
}
