package edu.odu.cs.cs350;

import java.util.List;

public interface Tokenizer {
	public List<Token> createTokens(final String text);
}
