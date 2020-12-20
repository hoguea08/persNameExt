/**
 * 
 */
package edu.odu.cs.cs350;

import static edu.odu.cs.cs350.Classification.BP;
import static edu.odu.cs.cs350.Classification.CP;
import static edu.odu.cs.cs350.Classification.OTHER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ahogue
 *
 */
public class TestWindow {
	
	Window defaultWindow;
    Window nonDefaultWindow;
    List<Token> tokenList;
    TextBlock textBlock;

    @Before
    public void Setup()
    {
    	defaultWindow = new Window();
    	Token first = new Token("Test", BP);
    	Token second = new Token("Window", CP);
    	Token third = new Token("Constructor", OTHER);
    	tokenList = new ArrayList<Token>();
    	tokenList.add(first);
    	tokenList.add(second);
    	tokenList.add(third);
    	nonDefaultWindow = new Window(tokenList);
    	nonDefaultWindow.setClassification(CP);
    	textBlock = new TextBlock("I am John.");
    }
    
    @Test
    public void TestDefaultConstructor()
    {        
        assertThat(defaultWindow.getClassification(), equalTo(null));
        assertThat(defaultWindow.getTokens().size(), equalTo(0));
    }
    
    @Test
    public void TestNonDefaultConstructor()
    {
    	assertThat(nonDefaultWindow.getClassification(), equalTo(CP));
    	assertThat(nonDefaultWindow.getTokens().toString(), equalTo("[Test, Window, Constructor]"));
    }
    
    @Test
    public void TestSetClassification()
    {
    	defaultWindow.setClassification(BP);
    	assertThat(defaultWindow.getClassification(), equalTo(BP));
    	assertThat(defaultWindow.getTokens().size(), equalTo(0));
    	
    	defaultWindow.setClassification(OTHER);
    	assertThat(defaultWindow.getClassification(), equalTo(OTHER));
    	assertThat(defaultWindow.getTokens().size(), equalTo(0));
    	
    	defaultWindow.setClassification(CP);
    	assertThat(defaultWindow.getClassification(), equalTo(CP));
    	assertThat(defaultWindow.getTokens().size(), equalTo(0));
    }
    
    @Test
    public void TestCreateWindows()
    {
    	assertThat(nonDefaultWindow.createWindow(textBlock, 0).toString(), containsString("null, null, null, null, null, I, am, John, ., null, null"));
    	assertThat(nonDefaultWindow.createWindow(textBlock, 1).toString(), containsString("null, null, null, null, I, am, John, ., null, null, null"));
    	assertThat(nonDefaultWindow.createWindow(textBlock, 2).toString(), containsString("null, null, null, I, am, John, ., null, null, null, null"));
    	assertThat(nonDefaultWindow.createWindow(textBlock, 3).toString(), containsString("null, null, I, am, John, ., null, null, null, null, null"));
    }
}
