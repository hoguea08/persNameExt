package edu.odu.cs.cs350;

import java.util.List;

public class FakeLearningMachine implements TokenClassifier {
	private List<Token> expectedClassifiedTokens;
	private List<Token> unclassifiedTokens;
	
	public FakeLearningMachine whenClassifyTokensIsCalledWith(List<Token> unclassifiedTokens) {
		this.unclassifiedTokens = unclassifiedTokens;
		return this;
	}

	public void willReturn(List<Token> expectedClassifiedTokens) {
		this.expectedClassifiedTokens = expectedClassifiedTokens;
		
	}

	@Override
	public List<Token> classifyTokens(List<Token> tokens) {
		if(unclassifiedTokens == null) {
			return expectedClassifiedTokens;
		}
		
		if (unclassifiedTokens.toString().equals(tokens.toString())) {
			return expectedClassifiedTokens;
		}
		
		throw new IllegalArgumentException("Tokenizer did not produce the right set of tokens");
	}

}
