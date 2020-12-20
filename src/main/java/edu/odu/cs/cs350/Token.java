package edu.odu.cs.cs350;

/**
 * @author ahogue
 *
 *	Token Class that holds the String, Classification, and Feature Set of a Token
 */
public class Token {
	
	private String token;
	private Classification classification;
	FeatureSet features;

	// Default Constructor
	public Token() {
		this.features = new FeatureSet();
		this.token = "";
		this.classification = null;
	}
	
	// Constructor that creates a Token using a String and a Classification
	public Token(final String t, final Classification c) {
		this.features = new FeatureSet();
		this.findFeatures();
		this.token = t;
		this.classification = c;
	}

	// Constructor that creates a Token using a String; Classification is set to null
	public Token(final String token) {
		this.features = new FeatureSet();
		this.token = token;
		this.classification = null;
		this.findFeatures();
	}

	// Return the classification of a Token
	public Classification getClassification() {
		return this.classification;
	}

	// Set the classification of a Token
	public void setClassification(final Classification classifyAs) {
		this.classification = classifyAs;
	}

	// Return the String of a Token
	public String getToken() {
		return this.token;
	}

	// Set the String of a Token and find its Feature Set
	public void setToken(final String setToken) {
		this.token = setToken;
		this.findFeatures();
	}

	// Return the Feature Set of a Token
	public FeatureSet getFeatures() {
		return this.features;
	}
	
	// Determine the Feature Set for a Token
	public void findFeatures() 
	{
	    this.features.setLexical(Lexical.findLexical(this.token));
	    this.features.setPOS(PartsOfSpeech.findPartsOfSpeech(this.token));
	    this.features.setGazFeatures(Gazetteers.determineGazetteers(this.token));
	}
	
	// Return the Feature Set and Classification as a String
	public String getFeaturesAndClass()
	{
	    return this.features + "," + this.classification;
	}

	@Override
	public String toString() {
		return this.token;
	}
}
