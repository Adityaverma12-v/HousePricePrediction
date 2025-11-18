package com.uity.model;
import java.io.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.Loader;

public class RegConvert {

    public static void Convert(String sourcepath,String destpath) throws Exception
    {
    	// load CSV
    	CSVLoader loader = new CSVLoader();
    	loader.setSource(new File(sourcepath));
    	Instances data = loader.getDataSet();
    	
    	// save ARFF
    	ArffSaver saver = new ArffSaver();
    	saver.setInstances(data);
    	saver.setFile(new File(destpath));
    	saver.setDestination(new File(destpath));
    	saver.writeBatch();
		
    	
    }
    
    public static void main(String args[]) throws Exception
    {
    	Convert("/Users/ayethantmay/Desktop/housing.csv","/Users/ayethantmay/Desktop/housing.arff");
    }
    
}
