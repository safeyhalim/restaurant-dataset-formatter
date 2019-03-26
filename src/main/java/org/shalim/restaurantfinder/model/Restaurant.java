package org.shalim.restaurantfinder.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = "id")
public class Restaurant {
	private int id;
	
	@JsonProperty("place_id")
	private String googlePlacesId;
	
	@JsonProperty("name")
	private String name;
	
	private String city;
	private String country;
	
	@JsonProperty("rating")
	private double googleRating;
	
	@JsonProperty("user_ratings_total")
	private int googleTotalRatings;
	
	@JsonProperty("price_level")
	private int priceLevel;
	
	public Restaurant() {}
	
	public Restaurant(int id, String googlePlacesId) {
		setId(id);
		setGooglePlacesId(googlePlacesId);
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("address_components")
    private void unpackAddressComponents(List<Map<String, Object>> addressComponents) {
        for (Map<String, Object> addressComponent : addressComponents) {
        	List<String> types = (List<String>)(addressComponent.get("types"));
        	for (String type : types) {
        		if (type.equals("locality")) {
        			setCity((String)addressComponent.get("long_name"));
        			break;
        		}
        		if (type.equals("country")) {
        			setCountry((String)addressComponent.get("long_name"));
        			break;
        		}
        	}
        	if (!StringUtils.isEmpty(city) && !StringUtils.isEmpty(country)) {
        		break;
        	}
        }
    }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGooglePlacesId() {
		return googlePlacesId;
	}
	
	public void setGooglePlacesId(String googlePlacesId) {
		this.googlePlacesId = googlePlacesId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public double getGoogleRating() {
		return googleRating;
	}
	
	public void setGoogleRating(double googleRating) {
		this.googleRating = googleRating;
	}
	
	public int getGoogleTotalRatings() {
		return googleTotalRatings;
	}
	
	public void setGoogleTotalRatings(int googleTotalRatings) {
		this.googleTotalRatings = googleTotalRatings;
	}
	
	public int getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(int priceLevel) {
		this.priceLevel = priceLevel;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", googlePlacesId=" + googlePlacesId + ", name=" + name + ", city=" + city
				+ ", country=" + country + ", googleRating=" + googleRating + ", googleTotalRatings="
				+ googleTotalRatings + ", priceLevel=" + priceLevel + "]";
	}
}
