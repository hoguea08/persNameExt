package edu.odu.cs.cs350;

import static edu.odu.cs.cs350.Classification.BP;
import static edu.odu.cs.cs350.Classification.CP;
import static edu.odu.cs.cs350.Classification.OTHER;

import java.util.List;

public class TagProvider implements TagMarker {

	/*
	 * This function takes a textBlock as input. It iterates through the list of
	 * tokens in that textblock and matches them within the string data member of
	 * the textblock Upon successful matching of the token, everything in the string
	 * up until that token is placed into the output buffer. The input string is
	 * then replaced with the substring of itself starting from the position of the
	 * current token to the end of the string. The function then checks the
	 * classification of the current token. If this token is the beginning of a name
	 * then <PER> is added to the output buffer. Regardless of classification the
	 * token is then added to the output buffer. If the token is the beginning of a
	 * name, or continuing a name, the function looks for the next token. If the
	 * current token is the last token then add </PER> to the output. If not, check
	 * the classification of the next token. If the classification of the next token
	 * is beginning of a name, or other, add </PER> to the output. Finally the
	 * string in the output buffer is returned.
	 */
	public String addPerTags(final TextBlock textBlock) {
		final List<Token> tokenList = textBlock.getTokens();
		final StringBuffer inBuffer = new StringBuffer();
		final StringBuffer outBuffer = new StringBuffer();

		// Add the string data member of the textblock to the input buffer
		inBuffer.append(textBlock.getText());

		// Number of tokens in the list
		final int listLength = textBlock.getTokens().size();
		boolean foundName = false;

		// Iterate through the tokens
		for (int i = 0; i < listLength; i++) {
			// Get the current token and it's string data member
			final Token theToken = tokenList.get(i);
			final String tokenString = theToken.getToken();

			// Convert the input buffer to a string for searching and look for the token
			// within that string.
			final String theText = inBuffer.toString();
			final int position = theText.indexOf(tokenString);

			// Make sure the token is actually found to avoid out of bounds errors.
			// The token should always be in the string if the textBlock class is setup
			// correctly
			if (position != -1) {
				// Add everthing from the input string into the output buffer, and replace the
				// input buffer
				// with a substring from the position of the token up to the end of the string
				outBuffer.append(theText.substring(0, position));
				inBuffer.delete(0, inBuffer.length());
				inBuffer.append(theText.substring(position + tokenString.length(), theText.length()));
				// System.out.println("IN: " + inBuffer.toString());
				// System.out.println("OUT:" + outBuffer.toString());

				// Get the classification of the current token
				final Classification classification = theToken.getClassification();

				// Add <PER> to the output if this token starts a new word
				if (classification == BP || (classification == CP && foundName == false)) {
				    foundName = true;
					outBuffer.append("<PER>");
				}

				// Regardless of classification add the token to the output
				outBuffer.append(tokenString);

				// If we are currently looking at a name, check the next token to see if this
				// one is the last in the name or the last
				// in the list. Add </PER> in either case.
				if (classification == BP || classification == CP) {
					if (i == listLength - 1) {
						outBuffer.append("</PER>");
					} else {
						final Token nextToken = tokenList.get(i + 1);
						final Classification nextClassification = nextToken.getClassification();
						if (nextClassification == BP || nextClassification == OTHER) {
						    foundName = false;
							outBuffer.append("</PER>");
						}
					}
				}
			}
		}
		return outBuffer.toString();
	}
}
