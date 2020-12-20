package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestPartsOfSpeech {

	@Test
	public void testPartsOfSpeech() {
		assertThat(PartsOfSpeech.findPartsOfSpeech("an"), is(POSFeature.ARTICLE));
		assertThat(PartsOfSpeech.findPartsOfSpeech("but"), is(POSFeature.CONJ));
		assertThat(PartsOfSpeech.findPartsOfSpeech("."), is(POSFeature.PERIOD));
		assertThat(PartsOfSpeech.findPartsOfSpeech(","), is(POSFeature.COMMA));
		assertThat(PartsOfSpeech.findPartsOfSpeech("-"), is(POSFeature.HYPHEN));
		assertThat(PartsOfSpeech.findPartsOfSpeech(null), is(POSFeature.OTHER));
		assertThat(PartsOfSpeech.findPartsOfSpeech("\\n"), is(POSFeature.OTHER));
		assertThat(PartsOfSpeech.findPartsOfSpeech("\n"), is(POSFeature.OTHER));
		assertThat(PartsOfSpeech.findPartsOfSpeech("300"), is(POSFeature.OTHER));
	}
}
