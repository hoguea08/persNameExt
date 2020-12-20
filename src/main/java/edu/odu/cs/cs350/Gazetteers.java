package edu.odu.cs.cs350;

import java.util.Arrays;

import edu.odu.cs.extract.wordlists.WordLists;

public class Gazetteers {
	/*
	 * @param input string (our token to be checked)
	 * 
	 * @return an array of booleans to be added to the token's feature set (which is the result of the Gazetteer Analysis)
	 * 
	 * the results of passing a string to determineGazetteers() should return an array of booleans where each element of the array
	 * represents a 
	 * 		
	 * 		firstName -- combined DTIC first names and common first names
	 * 		lastName -- combined DTIC last names and common last names
	 * 		dictionaryWord
	 * 		place -- the combined gazetteer lists of cities and states in US, places, and countries/territories
	 * 		honorific 
	 * 		last name prefix 
	 * 		last name suffix
	 * 		nonPersonalProperNames
	 * 		stopList
	 */
	
	public static Boolean[] determineGazetteers(String token) {
		
		if (token == null) {
			Boolean[] getGazetteers = new Boolean[9];
			Arrays.fill(getGazetteers, false);
			return getGazetteers;
		} else {
			 WordLists.commonFirstNames();
			 WordLists.firstNames();
			 WordLists.commonLastNames();
			 WordLists.lastNames();
			 WordLists.englishDictionary();
			 WordLists.citiesAndStatesUS();
			 WordLists.countriesAndTerritories();
			 WordLists.places();
			 WordLists.honorifics();
			 WordLists.lastNamePrefixes();
			 WordLists.lastNameSuffixes();
			 WordLists.nonPersonalIdentifierCues();
			 WordLists.stoplist();
			
			 
			 Boolean firstName = isFirstName(token);
			 Boolean lastName = isLastName(token);
			 Boolean englishDictionary = isDictionaryWord(token);
			 Boolean place = isPlace(token);
			 Boolean honorific = isHonorific(token);
			 Boolean lastNamePrefix = isLastNamePrefix(token);
			 Boolean lastNameSuffix = isLastNameSuffix(token);
			 Boolean nonPersonalProper = isNonPersonalProper(token);
			 Boolean stopWord = isStopWord(token);
			 
			 Boolean[] getGazetteers = {firstName, lastName, englishDictionary, place, honorific, 
					 					lastNamePrefix, lastNameSuffix, nonPersonalProper, stopWord};
	
			 return getGazetteers;
		}
	}
	
	// function to check if token is first name returning boolean
	private static boolean isFirstName(String token) {
		for(String wordInList:WordLists.commonFirstNames()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		for(String wordInList:WordLists.firstNames()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		return false;
	}
	
	// function to check if token is last name returning boolean
	private static boolean isLastName(String token) {
		for(String wordInList:WordLists.commonLastNames()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		for(String wordInList:WordLists.lastNames()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		return false;
	}
	
	// function to check if token is dictionary word returning boolean
	private static boolean isDictionaryWord(String token) {
		for(String wordInList:WordLists.englishDictionary()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		return false;
	}
	
	// function to check if token is place returning boolean
	private static boolean isPlace(String token) {
		for(String wordInList:WordLists.citiesAndStatesUS()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		for(String wordInList:WordLists.countriesAndTerritories()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		for(String wordInList:WordLists.places()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		
		return false;
	}
	
	// function to check if token is honorific returning boolean
	private static boolean isHonorific(String token) {
		for(String wordInList:WordLists.honorifics()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		return false;
	}
	
	// function to check if token is last name prefix returning boolean
	private static boolean isLastNamePrefix(String token) {
		for(String wordInList:WordLists.lastNamePrefixes()) {
			 if (wordInList.equals(token)){
				 return true;
			 }
		 }
		return false;
	}
	
	// function to check if token is last name suffix returning boolean
	private static boolean isLastNameSuffix(String token) {
		for(String wordInList:WordLists.lastNameSuffixes()) {
			 if (wordInList.equals(token)){
				 return true;
			 }
		 }
		return false;
	}

	// function to check if token is non Personal Proper Noun returning boolean
	private static boolean isNonPersonalProper(String token) {
		for(String wordInList:WordLists.nonPersonalIdentifierCues()) {
			 if (wordInList.equals(token)){
				 return true;
			 }
		 }
		return false;
	}
	
	// function to check if token is stop word returning boolean
	private static boolean isStopWord(String token) {
		for(String wordInList:WordLists.stoplist()) {
			 if (wordInList.equalsIgnoreCase(token)){
				 return true;
			 }
		 }
		return false;
	}

}
