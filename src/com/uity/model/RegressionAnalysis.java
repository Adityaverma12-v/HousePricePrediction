package com.uity.model;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import weka.core.Attribute;
import weka.core.matrix.Matrix;

public class RegressionAnalysis {

	public Double RA() throws Exception {
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File("/Users/ayethantmay/Desktop/housing.arff"));
		Instances dataset = loader.getDataSet();
		Attribute[] attr = new Attribute[10];
		
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
		    i++;
		}
		
		for (int j = 0; j < 10; j++) {

			attr[j]  = dataset.attribute(j);
			
		}
		
		
		double ssr = calculateSSR (dataset,attr,abc,abc[9]);
		double coeff_deter_rsq = calculateRSquared (dataset,ssr);
		
		return coeff_deter_rsq;

		//System.out.println("\nCoefficient of Determination R squared for the dataset of house price prediction is : \n" + coeff_deter_rsq);
	}


	public static double calculateSSR(Instances data, Attribute[] chosen, Double[] slope, double intercept)
			throws Exception {
		double ssr = 0.0;
		for (int i = 0; i < data.numInstances(); i++) {
			//double yHat = slope * data.instance(i).value(chosen[i]) + abc;
			double yHat = intercept*-1 + slope[0]*-1*data.instance(i).value(chosen[0]) + slope[1]*data.instance(i).value(chosen[1]) + slope[2]*data.instance(i).value(chosen[2]) + slope[3]*-1*data.instance(i).value(chosen[3]) + slope[4]*data.instance(i).value(chosen[4]) + slope[5]*data.instance(i).value(chosen[5]) + slope[6]*-1*data.instance(i).value(chosen[6]) + slope[7]*data.instance(i).value(chosen[7]) + slope[8]*data.instance(i).value(chosen[8]);
			double resid = data.instance(i).value(data.classIndex()) - yHat;
			ssr += resid * resid;
		}
		return ssr;
	}

	public static double calculateRSquared(Instances data, double ssr) throws Exception {
		// calculate total sum of squares (derivation of y from mean)
		double yMean = data.meanOrMode(data.classIndex());
		double tss = 0.0;
		for (int i = 0; i < data.numInstances(); i++) {
			tss += (data.instance(i).value(data.classIndex()) - yMean)
					* (data.instance(i).value(data.classIndex()) - yMean);
		}

		// calculate R-squared value and return
		double rsq = 1 - (ssr / tss);
		return rsq;
	}

}
