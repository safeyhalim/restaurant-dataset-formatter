package org.shalim.restaurantfinder.util;

public class FilePathUtil {
	
	public static String getFilePathWithoutName(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf('/'));
	}
}
