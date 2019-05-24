package org.shalim.restaurantfinder.services;


import java.io.File;
import java.io.IOException;
import org.shalim.restaurantfinder.domain.Input;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

public class RestaurantKFoldService {
	
	public static void createKFoldTrainingTestSets(Input input) throws IOException {
		Random rand = new Random(1);
		String dataSetPath = input.getDataSetPath();
		Instances dataSet = loadDataSet(dataSetPath, rand);
		int numFolds = input.getK();
		for (int i = 0; i < numFolds; i++) {
			Instances trainingSet = dataSet.trainCV(numFolds, i, rand);
			Instances testingSet = dataSet.testCV(numFolds, i);
			writeTrainingAndTestingSetsToFiles(trainingSet, testingSet, i + 1, getDirPath(dataSetPath));
		}
	}
	
	private static Instances loadDataSet(String filePath, Random rand) throws IOException {
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator("\t");
		loader.setSource(new File(filePath));
		Instances dataSet = new Instances(loader.getDataSet());
		dataSet.randomize(rand);
		return dataSet;
	}
	
	private static String getDirPath(String dataSetFilePath) {
		return dataSetFilePath.substring(0, dataSetFilePath.lastIndexOf("/")) + "/";
	}
	
	private static void writeTrainingAndTestingSetsToFiles(Instances trainingSet, Instances testingSet, int foldId, String dirPath) throws IOException {
		String trainingSetFilePath = String.format("%s/u%d.base", dirPath, foldId);
		String testingSetFilePath = String.format("%s/u%d.test", dirPath, foldId);
		doWriteDataSetToFile(trainingSetFilePath, trainingSet);
		doWriteDataSetToFile(testingSetFilePath, testingSet);
	}
	
	private static void doWriteDataSetToFile(String filePath, Instances dataSet) throws IOException {
		CSVSaver saver = new CSVSaver();
	    saver.setFieldSeparator("\t");
	    saver.setInstances(dataSet);
	    saver.setFile(new File(filePath));
	    saver.writeBatch();
	}
}
