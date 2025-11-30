package com.uity.model;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Regression {
	
	
	public Double[] Reg() throws Exception{
		ArffLoader loader = new ArffLoader();
		// Load ARFF file from classpath
		InputStream is = getClass().getResourceAsStream("/housing.arff");
		if (is == null) {
			throw new FileNotFoundException("housing.arff not found in classpath");
		}
		File tempFile = File.createTempFile("housing", ".arff");
		tempFile.deleteOnExit();
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		}
		loader.setSource(tempFile);
		Instances dataset = loader.getDataSet();
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		
		Classifier classifier = new LinearRegression();
		classifier.buildClassifier(dataset);
		
		String str= classifier.toString();
		
		//System.out.print(str);
		
		Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
		Matcher m = p.matcher(str);
		
		
	Double[] abc=new Double[10];
		int i=0;
		while(m.find()) {
		     abc[i] = Double.parseDouble(m.group(1));
		     //System.out.println(abc[i]);
		    i++;
		}
		return abc;
	}

}
