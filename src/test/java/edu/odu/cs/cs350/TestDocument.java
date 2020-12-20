package edu.odu.cs.cs350;

import java.io.*; 
import java.util.*; 
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestDocument {
    
    
    String testString =   "<NER>This should be line 1</NER> "
                        + "<NER>This should be line 2</NER> "
                        + "<NER>This should be line 3</NER> "
                        + "<NER>This should be line 4</NER> "
                        + "<NER>This should be line 5</NER> ";
    ArrayList<String> expectedTextBlocks = new ArrayList<String>();

    
    Document defaultDoc = new Document();
    Document filledDoc = new Document(testString);
            
    @Before
    public void Setup()
    {
        defaultDoc = new Document();
        filledDoc = new Document(testString);
        
        expectedTextBlocks.clear();
        expectedTextBlocks.add("This should be line 1");
        expectedTextBlocks.add("This should be line 2");
        expectedTextBlocks.add("This should be line 3");
        expectedTextBlocks.add("This should be line 4");
        expectedTextBlocks.add("This should be line 5");
    }
    
    @Test
    public void testDefaultConstructor() 
    {         
        assertThat(defaultDoc.getDocument(), nullValue());
        assertThat(defaultDoc.getTextBlocks(), nullValue());
    }
    
    @Test
    public void testConstructor() 
    {         
        assertThat(filledDoc.getDocument(), equalTo(testString));
        assertThat(filledDoc.getTextBlocks(), equalTo(expectedTextBlocks));
    }
    
    @Test
    public void testGetDocument() 
    {
        assertThat(defaultDoc.getDocument(), nullValue());
        assertThat(filledDoc.getDocument(), equalTo(testString));
    }

    @Test
    public void testSetDocument() 
    {
        String newString = "<NER>This is new line 1</NER>"
       		+ "<NER>This is new line 2 with random NER</NER>"
       		+ "<NER>This is new line 3 with Random /NER</NER>";

		ArrayList<String> newTextBlocks = new ArrayList<String>();
			newTextBlocks.add("This is new line 1");
			newTextBlocks.add("This is new line 2 with random NER");
			newTextBlocks.add("This is new line 3 with Random /NER");
			
        assertThat(filledDoc.getDocument(), equalTo(testString));
                
        filledDoc.setDocument(newString);
        
        assertThat(filledDoc.getDocument(), equalTo(newString));
        
        assertThat(filledDoc.getTextBlocks(), equalTo(newTextBlocks));
	    
        
    }

    @Test
    public void testGetTextBlocks() 
    {
        assertThat(defaultDoc.getTextBlocks(), nullValue());
        assertThat(filledDoc.getTextBlocks(), equalTo(expectedTextBlocks));
    }


/**
    @Test
    public void testDettectBlocks() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testSeparateIntoTextBlocks() {
        fail("Not yet implemented"); // TODO
    } */

}
