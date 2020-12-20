package edu.odu.cs.cs350;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;

public class ARFFCreator implements DataCompiler {
	
	// This compiles data in a .arff file to be passed into an Instances Object and evaluated by the Learning Machine
	public void createARFF(final String[] blockStrings) throws IOException {
			
		// Create a String List from the Attribute ArrayList
		Attributes attrInfo = new Attributes();
    	List<String> attributes = new ArrayList<String>();
    	for (Attribute i : attrInfo.getAttributes()) {
    		attributes.add(i.toString());
    	}
    	
    	// Create a new File object for a "file.arff" file and set up a PrintWriter Object to write to it
    	File newfile = new File("file.arff");
    	FileWriter fileWriter = new FileWriter(newfile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        // Print title of .arff file
        printWriter.println("@relation Data");
        printWriter.println();
        
        // Print Attributes
        for (String i : attributes) {
        	printWriter.println(i);
        }
        printWriter.println();
        
        // Print heading to begin the data section
        printWriter.println("@data");
        
        // Print merged feature sets
        for (String i : blockStrings) {
        	printWriter.println(i);
        }

        printWriter.close();
    }

}
