package org.shalim.restaurantfinder.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.shalim.restaurantfinder.services.helpers.LineFormatter;

public class FileWriter {
	public static <T, F> void exportToFile(List<T> data, LineFormatter<T> formatter, String filePath, String fileName, String fileExtension, String header) throws IOException {
		filePath = filePath + "/" + fileName + System.currentTimeMillis() + "." + fileExtension;
		File file = new File(filePath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
			if (header != null) {
				writeLine(bufferedWriter, header);
			}
			for (T entry : data) {
				writeLine(bufferedWriter, formatter.format(entry));
			}
		}
	}
	
	
	private static void writeLine(BufferedWriter bufferedWriter, String line) throws IOException {
		bufferedWriter.write(line);
		bufferedWriter.newLine();
	}
}
