package org.shalim.restaurantfinder.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import org.shalim.restaurantfinder.model.Restaurant;
import org.shalim.restaurantfinder.model.RestaurantWrapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GooglePlacesService {
	private static final String API_URI_FORMAT = "https://maps.googleapis.com/maps/api/place/details/json?placeid=%s&key=%s";
	private static Client client = null;
	private static ObjectMapper restaurantMapper = null;
	
	public static List<Restaurant> findRestaurantsByGooglePlacesIds(String apiKey, List<String> googlePlacesIds) throws IOException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (String googlePlacesId : googlePlacesIds) {
			restaurants.add(getGoogleRestaurantByPlacesId(apiKey, googlePlacesId));
		}
		return restaurants;
	}

   public static Restaurant getGoogleRestaurantByPlacesId(String apiKey, String googlePlacesId) throws IOException {
	   initIfNeeded();
	   
       Response response = client.target(String.format(API_URI_FORMAT, googlePlacesId, apiKey)).request().get();
       String responseAsStr = response.readEntity(String.class);
       Restaurant restaurant = restaurantMapper.readValue(responseAsStr, RestaurantWrapper.class).getRestaurant();
       return restaurant;
   }
   
   private static void initIfNeeded() {
	   if (client != null && restaurantMapper != null) {
		   return;
	   }
	   client = ClientBuilder.newClient();
	   restaurantMapper = new ObjectMapper();
	   restaurantMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   }
}
