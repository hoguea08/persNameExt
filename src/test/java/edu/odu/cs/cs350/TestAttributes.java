package edu.odu.cs.cs350;

import static edu.odu.cs.cs350.Classification.BP;
import static edu.odu.cs.cs350.Classification.CP;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

public class TestAttributes {

	Attributes attrInfo;
	
	@Before
    public void setup()
    {
        attrInfo = new Attributes();
    }
    
    @Test
    public void TestDefaultConstructor()
    {        
        assertThat(attrInfo.getAttributes().toString(), containsString("LexicalFeatures3"));
        assertThat(attrInfo.getAttributes().toString(), containsString("PartsOfSpeech9"));
        assertThat(attrInfo.getAttributes().toString(), containsString("FirstName7"));
        assertThat(attrInfo.getAttributes().toString(), containsString("LastName2"));
        assertThat(attrInfo.getAttributes().toString(), containsString("DictionaryWord8"));
        assertThat(attrInfo.getAttributes().toString(), containsString("Place1"));
        assertThat(attrInfo.getAttributes().toString(), containsString("Honorific0"));
        assertThat(attrInfo.getAttributes().toString(), containsString("PersonalName"));
        assertThat(attrInfo.getAttributes().toString(), containsString("{NUMBER,PUNCT,CAP_LETTER,CAP_WORD,ALL_CAPS,LINE_FEED,OTHER}"));
        assertThat(attrInfo.getAttributes().toString(), containsString("{ARTICLE,CONJ,PERIOD,COMMA,HYPHEN,OTHER}"));
        assertThat(attrInfo.getAttributes().toString(), containsString("{BP,CP,OTHER}"));
    }
}
