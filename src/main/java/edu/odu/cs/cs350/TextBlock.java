package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;

import static edu.odu.cs.cs350.Classification.*;
/**
 * @author ahogue
 * 
 * TextBlock class that holds a set of Tokens and an original String of a text block
 *
 */
public class TextBlock {
	
	private final List<Token> tokens;
	private final String theText;
	private String theMarkUp;
	private Tokenizer tokenizer;

	// Default Constructor
	public TextBlock() {
		this.tokens = new ArrayList<Token>();
		this.theText = "";
		this.theMarkUp = "";
	}

	// Constructor that takes a text block String
	public TextBlock(final String text) {
		this.tokenizer = new TokenProvider();
		this.tokens = new ArrayList<Token>(tokenizer.createTokens(text));
		this.theText = text;
		
		this.theMarkUp = "";
	}
	
	// Constructor that takes a list of Tokens and an original text block String
	public TextBlock(final List<Token> t, final String text) {
		this.tokens = t;
		this.theText = text;
		this.theMarkUp = "";
	}

	// Return list of Tokens
	public List<Token> getTokens() {
		return this.tokens;
	}

	// Return the length (# Tokens) of the TextBlock
	public int getLength() {
		return this.tokens.size();
	}

	// Get the original String of text
	public String getText() {
		return this.theText;
	}

	@Override
	public String toString() {
		return this.tokens.toString();
	}

	// Sets the Marked Up String
	public void setMarkUp(final String mUp) {
		theMarkUp = mUp;
	}

	// Returns the Marked Up String
	public String getMarkUp() {
		return theMarkUp;
	}
	
	/*
	 * Iterate through the list of tokens, first token after <PER> classified
	 * as BP, subsequent tokens before </PER> classified as CP.  EVerything else
	 * classified as OTHER.
	 */
	public void getTokenTrainingClassification()
	{
	    boolean foundName = false;
	    boolean startName = false;
	    int numTokens = tokens.size();
	    
	    for(int i = 0; i < numTokens; i++)
	    {
	        Token t = tokens.get(i);
	        if (t.getToken().equals("<PER>"))
	        {
	            foundName = true;
	            startName = true;
	            tokens.remove(i);
	            i--;
	            numTokens--;
	        }
	        else if (t.getToken().equals("</PER>"))
            {
                foundName = false;
                tokens.remove(i);
                i--;
                numTokens--;
            }
	        else
	        {
	            if (foundName == false)
	            {
	                t.setClassification(OTHER);
	            }
	            else
	            {
                   if (startName == true)
                    {
                        t.setClassification(BP);
                        startName = false;
                    }
                    else
                    {
                        t.setClassification(CP);
                    }
	            }   
	        }
	    }
	}
}
