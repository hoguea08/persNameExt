package edu.odu.cs.cs350;
import static edu.odu.cs.cs350.Classification.*;
import java.util.ArrayList;
import java.util.List;

public class Window {
	
	// Store a List of Tokens and k in a Window
	List<Token> textBlockWindows;
	final int k = 5;
	Classification windowClassification;
	
	// Default Window Constructor
	public Window()
	{
		this.textBlockWindows = new ArrayList<Token>();
		this.windowClassification = null;
	}
	
	// Non-Default Window Constructor that creates a Window from a List of Tokens
	public Window(List<Token> t)
	{
	    this.textBlockWindows = t;
	    this.windowClassification = null;
	}
	
	// Get the Classification of the main Token in the Window
	public Classification getClassification()
	{
	    return this.windowClassification;
	}
	
	public void setClassification(Classification c) 
	{
		this.windowClassification = c;
	}
	
	// Get the List of Tokens in the Window
	public List<Token> getTokens() {
		return this.textBlockWindows;
	}
	
	// Create a Window from a TextBlock using the index of a Token
	public Window createWindow(final TextBlock block, final int index)
	{
		// Create a Window Object
		Window window = new Window();
		
		// For every Token +/- k away from the indexed Token, add it to the Window
		// If the index is out of bounds for the TextBlock, add a null Token
		for (int i = index - k; i <= index + k; i++) {
			if (i < 0) {
				window.textBlockWindows.add(new Token(null));
			} else if (i >= block.getLength()) {
				window.textBlockWindows.add(new Token(null));
			} else
				window.textBlockWindows.add(block.getTokens().get(i));
		}
		window.setClassification(window.getTokens().get(5).getClassification());
		
		// Return the new Window
		return window;
	}
	
	// Get the merged feature set of a Window as a String
	public String getMergedFeatureSet() {
		    
		// Create a new String object
		String merged = new String();

		// For each Token in the Window, get the FeatureSet as a String and concatenate them
		for (int i = 0; i < this.textBlockWindows.size(); i++)
		{
			merged += this.textBlockWindows.get(i).getFeatures() + ","; 
		}
		if (this.textBlockWindows.get(5).getClassification() == null) {
			merged += "?";
		} else {
			merged += this.textBlockWindows.get(5).getClassification();
		}
		// Return the merged String
		return merged; 
	}
	
	@Override
	public String toString() {
		
		return this.textBlockWindows.toString();
	}
}
