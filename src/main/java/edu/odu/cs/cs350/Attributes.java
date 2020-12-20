/**
 * 
 */
package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Attribute;

/**
 * @author ahogue
 * 
 * This class holds the Attributes vector to be used for both the Pre- and Post-Trained Learning Machine
 *
 */
public class Attributes {
	
	// Attribute ArrayList 
	private final ArrayList<Attribute> attrInfo = new ArrayList<Attribute>();
	
	// Constructor to set up Attribute ArrayList for Learning Machine data
	public Attributes() {
	    	
		// String Arrays lexical, pos, and nameClassification hold the String values for the
		// Lexical, Parts of Speech, and Name Classification categories
		String[] lexical = {"NUMBER", "PUNCT", "CAP_LETTER", "CAP_WORD",
							"ALL_CAPS", "LINE_FEED", "OTHER"};
	    String[] pos = {"ARTICLE", "CONJ", "PERIOD", "COMMA", "HYPHEN", "OTHER"};
	    String[] nameClassification = {"BP","CP","OTHER"};
		
	    // Add 2k + 1 of the FeatureSet for a merged feature set
	    for (int i = 0; i < 11; i++) {
			
			// Set up Attributes for each Token by index
		    Attribute lexicalFeatures = new Attribute("LexicalFeatures"+i, Arrays.asList(lexical));
		    Attribute partsOfSpeech = new Attribute("PartsOfSpeech"+i, Arrays.asList(pos));
		    Attribute firstName = new Attribute("FirstName"+i);
		    Attribute lastName = new Attribute("LastName"+i);
		    Attribute dictionary = new Attribute("DictionaryWord"+i);
		    Attribute place = new Attribute("Place"+i);
		    Attribute honorific = new Attribute("Honorific"+i);
		    Attribute prefix = new Attribute("Prefix"+i);
		    Attribute suffix = new Attribute("Suffix"+i);
		    Attribute nonPersonalProperName = new Attribute("NonPersonalProperName"+i);
		    Attribute stopWord = new Attribute("StopList"+i);
		
		    // Add Attributes to Attribute ArrayList
		    attrInfo.add(lexicalFeatures);
		    attrInfo.add(partsOfSpeech);
		    attrInfo.add(firstName);
		    attrInfo.add(lastName);
		    attrInfo.add(dictionary);
		    attrInfo.add(place);
		    attrInfo.add(honorific);
		    attrInfo.add(prefix);
		    attrInfo.add(suffix);
		    attrInfo.add(nonPersonalProperName);
		    attrInfo.add(stopWord);
	    }
	    
	    // Add the Window Classification Attribute to be classified
	    Attribute classification = new Attribute("PersonalName", Arrays.asList(nameClassification));
	    attrInfo.add(classification);
	}
	 
	// Return ArrayList of Attributes
	public ArrayList<Attribute> getAttributes() {
		return this.attrInfo;
	}
}

