package edu.odu.cs.cs350;

import java.util.List;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Attribute;
import weka.core.Instances;

import java.io.File;
import java.util.ArrayList;

/**
 * Facilitates <PER> tag markup.
 *
 * @author mjoyce
 *
 */
public class Extractor {
	/**
	 *
	 * @param textBlock Input text to be marked up.
	 *
	 * @return String The input text marked up with <PER> tags.
	 *
	 */
	public static String markPersonalNames(final String text) {
		
	    Document document = new Document(text);
	    
		ARFFCreator data = new ARFFCreator();
		TagMarker markPerTags = new TagProvider();
        List<String> markedUpList = new ArrayList<String>();
        Attributes attrInfo = new Attributes();
		
		for(TextBlock textBlock : document.getBlocks())
		{
    		// Break TextBlock into Windows and store
    		List<Window> windows = new ArrayList<Window>();
    		for (int i = 0; i < textBlock.getLength(); i++) {
    			Window tempWindow = new Window();
    			tempWindow = tempWindow.createWindow(textBlock, i);
    			windows.add(tempWindow);
    		}
    		
    		// Get merged feature sets for Windows
    		String[] blockString = new String[windows.size()];
    		for (int i = 0; i < windows.size(); i++) {
    			blockString[i] = windows.get(i).getMergedFeatureSet();
    		}
    		
    		Instances toClassify = null;
    		// Create ARFF file 
    		try {
    			data.createARFF(blockString);
    			DataSource source = new DataSource("file.arff");
    			toClassify = source.getDataSet();
    			toClassify.setClassIndex(attrInfo.getAttributes().size() - 1);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		
    		File file = new File("file.arff");
    		
    		//New TrainedLearningMachine
    		TrainedLearningMachine svm = new TrainedLearningMachine();
    		List<String> classifications = new ArrayList<String>();
    		Attribute classify = toClassify.attribute(attrInfo.getAttributes().size() - 1);
    		
    		// Get Predicted Classifications
    		try {
				classifications = svm.returnClassifications(svm.getSMO(), toClassify, classify);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		// Input Windows into Learning Machine, retrieve Classification and store in each Token
    		for (int i = 0; i < textBlock.getTokens().size(); i++) {
    			if (classifications.get(i).equals("OTHER")) {
    				textBlock.getTokens().get(i).setClassification(Classification.OTHER);
    			} else if (classifications.get(i).equals("BP")) {
    				textBlock.getTokens().get(i).setClassification(Classification.BP);
    			} else if (classifications.get(i).equals("CP")) {
    				textBlock.getTokens().get(i).setClassification(Classification.CP);
    			}
    		}
            
            String temp = markPerTags.addPerTags(textBlock);
    
    		if (document.getHasNerTags())   
    		{
    		    temp = "<NER>" + temp + "</NER>";
    		}
    		
    		// return markedUp;
    		markedUpList.add(temp);
    	}
		String markedUp = new String();
    		// For now:
		for (String s : markedUpList) {
			markedUp += s + "\n";
		}
    	return markedUp;
	}
}
