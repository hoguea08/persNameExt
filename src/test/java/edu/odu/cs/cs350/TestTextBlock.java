package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.awt.geom.Point2D;
import java.lang.Object;
import org.junit.Before;
import org.junit.Test;
import static edu.odu.cs.cs350.Classification.*;

public class TestTextBlock {
	private TextBlock aTextBlock;
	private TextBlock defaultTextBlock;
	private String theText;
	int length;
	Point2D object;

	@Before
	public void Setup() {
		theText = "Jane and Smith!";
		aTextBlock = new TextBlock(theText);
		defaultTextBlock = new TextBlock();
		object = new Point2D.Double();
		
	}
	
	@Test
	public void testSetLocation() {
		object.setLocation(1,2);
		Point2D temp = new Point2D.Double(2, 4);
		
		assertThat(object.distance(1, 2), equalTo(0.0));
		assertThat(object.distanceSq(1, 4), equalTo(4.0));
		assertThat(object.equals(temp), equalTo(false));
		assertThat(object.getX(), equalTo(1.0));
		assertThat(object.getY(), equalTo(2.0));
		assertThat(object.hashCode(), not(temp.hashCode()));
	}

	@Test
	public void testDefaultConstructor() {
		assertThat(defaultTextBlock.getText(), equalTo(""));
		assertThat(defaultTextBlock.getTokens().toString(), equalTo("[]"));
	}

	@Test
	public void testNonDefaultConstructor() {
		assertThat(aTextBlock.getText(), equalTo("Jane and Smith!"));
	}
	
	@Test
	public void testGetTokenTrainingClassification()
	{
	    TextBlock tb = new TextBlock(" ;<PER>Karen S. Friedman</PER>, <PER>Christopher Wackerman</PER> ");
	    tb.getTokenTrainingClassification();
	    System.out.println(tb.toString());
	    assertThat(tb.getTokens().size(), equalTo(8));	  
	    assertThat(tb.getTokens().get(0).getClassification(), equalTo(OTHER));
	    assertThat(tb.getTokens().get(1).getClassification(), equalTo(BP));
	    assertThat(tb.getTokens().get(2).getClassification(), equalTo(CP));
	    assertThat(tb.getTokens().get(3).getClassification(), equalTo(CP));
	    assertThat(tb.getTokens().get(4).getClassification(), equalTo(CP));
	    assertThat(tb.getTokens().get(5).getClassification(), equalTo(OTHER));
	    assertThat(tb.getTokens().get(6).getClassification(), equalTo(BP));
	    assertThat(tb.getTokens().get(7).getClassification(), equalTo(CP)); 
	}
}
