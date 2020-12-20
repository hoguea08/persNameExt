package edu.odu.cs.cs350;

import static edu.odu.cs.cs350.Classification.BP;
import static edu.odu.cs.cs350.Classification.CP;
import static edu.odu.cs.cs350.Classification.OTHER;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestExtractor {
	//private Extractor uut;
	private FakeLearningMachine fakeLearningMachine;
	private Tokenizer tokenizer;
	private TagMarker tagMarker;

	@Before
	public void Setup() {
		fakeLearningMachine = new FakeLearningMachine();
		tokenizer = new TokenProvider();
		tagMarker = new TagProvider();
		//uut = new Extractor(tokenizer, tagMarker, fakeLearningMachine);
	}

	@Test
	public void testMarkPersonalNames() {
		// Given
		final String input = "John Jacob Johnson: Maggin A. B., Anderson Sr.!     -- -        Jake-Manfred Jr's Car5456'a  .";
		final String expectedOutput = "<PER>John</PER> <PER>Jacob Johnson</PER>: Maggin A. B., Anderson Sr.</PER>!     -- -        <PER>Jake-Manfred Jr</PER>'s Car5456'a  .";
		
		fakeLearningMachine.whenClassifyTokensIsCalledWith(tokenizer.createTokens(input))
						   .willReturn(defaultClassifiedTokens());

		// When
		final String output = Extractor.markPersonalNames(input);

		// Then
		assertThat(output, equalTo(expectedOutput));
	}

	@Test
	public void testShouldAddTagToBeginningOfTextBlock() {
		// Given
		final String input = "Ondra Torkilson is a 25y/o mother of 1.";

		fakeLearningMachine.whenClassifyTokensIsCalledWith(tokenizer.createTokens(input))
						   .willReturn(classifiedTokenStartingWithName());

		// When
		final String output = Extractor.markPersonalNames(input);

		// Then
		assertThat(output, equalTo("<PER>Ondra Torkilson</PER> is a 25y/o mother of 1.\n"));
	}

	@Test
	public void testShouldAddTagToMiddleOfTextBlock() {
		// Given
		final String input = "Her name is Ondra Torkilson, and she likes eggs.";

		fakeLearningMachine.whenClassifyTokensIsCalledWith(tokenizer.createTokens(input))
		   				   .willReturn(classifiedTokensWithNameInTheMiddle());

		// When
		final String output = Extractor.markPersonalNames(input);

		// Then
		assertThat(output, equalTo("Her name is <PER>Ondra Torkilson</PER>, and she likes eggs."));
	}

	@Test
	public void testShouldAddTagToEndOfTextBlock() {
		final String input = "That woman is named Ashley Hogue.";

		fakeLearningMachine.whenClassifyTokensIsCalledWith(tokenizer.createTokens(input))
		   				   .willReturn(classifiedTokensEndingWithName());
		// When
		final String output = Extractor.markPersonalNames(input);

		// Then
		assertThat(output, equalTo("That woman is named <PER>Ashley Hogue</PER>."));
	}

    private List<Token> defaultClassifiedTokens() {
    	return asList(new Token("John", BP), 
	  			 new Token("Jacob", BP), 
	  			 new Token("Johnson", CP), 
	  			 new Token(":", OTHER), 
	  			 new Token("Maggie", BP), 
	  			 new Token("A", CP),
				 new Token(".", CP), 
				 new Token("B", CP), 
				 new Token(".", CP), 
				 new Token(",", CP),
				 new Token("Anderson", CP),
				 new Token("Sr", CP),
				 new Token(".", CP),
				 new Token("!", OTHER),
				 new Token("-", OTHER),
				 new Token("-", OTHER),
				 new Token("-", OTHER),
				 new Token("Jake", BP),
				 new Token("-", CP),
				 new Token("Manfred", CP),
				 new Token("Jr", CP),
				 new Token("'", OTHER),
				 new Token("s", OTHER),
				 new Token("Car", OTHER),
				 new Token("5456", OTHER),
				 new Token("'", OTHER),
				 new Token("a", OTHER),
				 new Token(".", OTHER));
    }
    
    private List<Token> classifiedTokenStartingWithName() {
    	return asList(new Token("Ondra", BP), 
    				  new Token("Torkilson", CP),  
    				  new Token("is", OTHER), 
    				  new Token("a", OTHER),
    				  new Token("a", OTHER),
    				  new Token("25y/o", OTHER),
    				  new Token("mother", OTHER),
    				  new Token("of", OTHER),
    				  new Token("1", OTHER),
    				  new Token(".", OTHER));
    }
    
    private List<Token> classifiedTokensWithNameInTheMiddle() {
    	return asList(new Token("Her", OTHER)
    	        	 ,new Token("name", OTHER)
    	        	 ,new Token("is", OTHER)
    	        	 ,new Token("Ondra", BP)
    	        	 ,new Token("Torkilson", CP)
    	        	 ,new Token(",", OTHER)
    	        	 ,new Token("and", OTHER)
    	        	 ,new Token("she", OTHER)
    	        	 ,new Token("likes", OTHER)
    	        	 ,new Token("eggs", OTHER)
    	        	 ,new Token(".", OTHER));
    }
    
    private List<Token> classifiedTokensEndingWithName() {
        return asList(new Token("That", OTHER)
        			 ,new Token("woman", OTHER)
        			 ,new Token("is", OTHER)
        			 ,new Token("named", OTHER)
        			 ,new Token("Ashley", BP)
        			 ,new Token("Hogue", CP)
        			 ,new Token(".", OTHER));
    }
}
