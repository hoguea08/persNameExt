package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.List;

//import org.junit.jupiter.api.Test;

public class TokenProviderTest {

	@Test
	public void testTokenCreation() {
		// Given
		final TokenProvider tokenProvider = new TokenProvider();

		// When
		final List<Token> tokens = tokenProvider.createTokens("Jane and Smith!");

		// Then
		assertThat(tokens.toString(), equalTo("[Jane, and, Smith, !]"));
	}

}
