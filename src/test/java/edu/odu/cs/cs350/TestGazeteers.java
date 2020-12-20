package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TestGazeteers {
	
	@Test
	public void testGazetteersFirstName() {
		// Given
		String firstName = "John";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(firstName);
		
		// Then
		assertThat(gazetteers[0], is(true));
		assertThat(gazetteers[1], is(true));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(true));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	}
	
	@Test
	public void testGazetteersLastName() {
		// Given
		String lastName = "Doe";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(lastName);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(true));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersDictionaryWord() {
		// Given
		String dictionaryWord = "abdomen";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(dictionaryWord);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersPlace() {
		// Given
		String place = "Georgia Park";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(place);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(false));
		assertThat(gazetteers[3], is(true));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersHonorific() {
		// Given
		String honorific = "Doctor";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(honorific);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(true));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(true));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersPrefix() {
		// Given
		String prefix = "hadzi";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(prefix);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(false));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(true));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersSuffix() {
		// Given
		String suffix = "III";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(suffix);
		
		// Then
		assertThat(gazetteers[0], is(true));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(true));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersNonPersonalProperName() {
		// Given
		String nonPersonalProper = "deutsche";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(nonPersonalProper);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(false));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(true));
		assertThat(gazetteers[8], is(false));
	
	}
	
	@Test
	public void testGazetteersStopWord() {
		// Given
		String stopWord = "your";
	
		// When
		Boolean[] gazetteers = Gazetteers.determineGazetteers(stopWord);
		
		// Then
		assertThat(gazetteers[0], is(false));
		assertThat(gazetteers[1], is(false));
		assertThat(gazetteers[2], is(true));
		assertThat(gazetteers[3], is(false));
		assertThat(gazetteers[4], is(false));
		assertThat(gazetteers[5], is(false));
		assertThat(gazetteers[6], is(false));
		assertThat(gazetteers[7], is(false));
		assertThat(gazetteers[8], is(true));
	
	}
	
	
	
}
