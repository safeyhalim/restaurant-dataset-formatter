package org.shalim.restaurantfinder.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.shalim.restaurantfinder.domain.Input;

public class ArgParser {
	public static Input parse(String[] args) {
		Options options = createOptions();
		
		CommandLine cmd = null;
		try { 
			cmd = new DefaultParser().parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			new HelpFormatter().printHelp("restaurant-finder", options);
			System.exit(1);
		}
		Input input = new Input(cmd.getOptionValue("path"), cmd.getOptionValue("key"));
		return input;
	}

	private static Options createOptions() {
		Options options = new Options();
		Option path = new Option("p", "path", true, "Restaurants SQLite database file path");
		path.setRequired(true);

		Option apiKey = new Option("k", "key", true, "Google Places API key");
		apiKey.setRequired(true);

		options.addOption(path);
		options.addOption(apiKey);

		return options;
	}
}
