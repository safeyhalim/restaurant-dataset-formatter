package org.shalim.restaurantfinder.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
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
		
		Input input = new Input(cmd.getOptionValue("path"), cmd.getOptionValue("key"), cmd.getOptionValue("dumpPath"),
				cmd.hasOption("export-user-ratings"), cmd.hasOption("export-social-contexts"), cmd.hasOption("export-all"), cmd.getOptionValue("dataset-path"), getK(cmd));
		return input;
	}

	private static Options createOptions() {
		Option path = new Option("p", "path", true, "Restaurants SQLite database file path");
		path.setRequired(false);

		Option apiKey = new Option("k", "key", true, "Google Places API key");
		apiKey.setRequired(false);
		
		Option dumpFilePath = new Option("d", "dumpPath", true, "Path of the dump file to read restaurants from");
		dumpFilePath.setRequired(false);
		
		Option exportUserRatings = new Option("eu", "export-user-ratings", false, "Exports user ratings in a file in the same directory as the dbFile");
		Option exportSocialContexts = new Option("es", "export-social-contexts", false, "Exports social contexts in a file in the same directory as the dbFile");
		Option exportAll = new Option("a", "export-all", false, "Exports the whole dataset in the same directory where the dbFile exists");
		
		OptionGroup mode = new OptionGroup();
		mode.addOption(apiKey);
		mode.addOption(dumpFilePath);
		mode.addOption(exportUserRatings);
		mode.addOption(exportSocialContexts);
		mode.addOption(exportAll);
		mode.setRequired(false);
		
		Option folds = new Option("f", "folds", true, "Number of folds to create f folds training/testing (80%/20%) data set files");
		folds.setRequired(false);
		Option dataSetPath = new Option("s", "dataset-path", true, "Path to the dataset file to which training and testing sets should be created");
		dataSetPath.setRequired(false);

		Options options = new Options();
		options.addOption(path);
		options.addOption(folds);
		options.addOption(dataSetPath);
		options.addOptionGroup(mode);

		return options;
	}
	
	private static int getK(CommandLine cmd) {
		String folds = cmd.getOptionValue("folds");
		return  folds == null ? -1 : Integer.parseInt(folds);
	}
}
