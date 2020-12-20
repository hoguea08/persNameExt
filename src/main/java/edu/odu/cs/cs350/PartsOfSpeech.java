/**
 * 
 */
package edu.odu.cs.cs350;

/**
 * @author ahogue
 *
 */
public class PartsOfSpeech {
	/*
	 * @param input string (our token to be checked)
	 * 
	 * @return a POSFeature to be added to the token's feature set (which is the result of the Parts of Speech Analysis)
	 * 
	 * the results of passing a string to findPartsOfSpeech() should fit into 1 of 6
	 * categories: 
        1 Articles (�a�, �an�, �the�)      ARTICLE,
        2 Conjunction, specifically �and�  CONJ,
        3 Period                           PERIOD,
        4 Comma                            COMMA,
        5 Hyphen                           HYPHEN,
        6 Other                            OTHER;
	 */
	public static POSFeature findPartsOfSpeech(final String token) {
		
		// Create a partOfSpeech String and an int to return category
		// If part of speech does not equal any category, default is "other"
		POSFeature partOfSpeech = POSFeature.OTHER;

		// Create Arrays of articles and conjunctions to search
		final String[] articles = { "a", "an", "the" };
		final String[] conjunctions = { "for", "and", "nor", "but", "or", "yet", "so" };

		// See if token matches an article
		for (String i : articles) {
			if (token == i) {
				partOfSpeech = POSFeature.ARTICLE;
			}
		}

		// If not an article, see if token matches a conjunction
		if (token != "article") {
			for (String i : conjunctions) {
				if (token == i) {
					partOfSpeech = POSFeature.CONJ;
				}
			}
		}

		// If not an article or conjunction, see if token is a period, comma, hyphen, or null
		if (token != "article" && token != "conjunction") {
			if (token == ".") {
				partOfSpeech = POSFeature.PERIOD;
			} else if (token == ",") {
				partOfSpeech = POSFeature.COMMA;
			} else if (token == "-") {
				partOfSpeech = POSFeature.HYPHEN;
			} else if (token == null) {
				partOfSpeech = POSFeature.OTHER;
			}
		}
		// return part of speech
		return partOfSpeech;
	}
}
