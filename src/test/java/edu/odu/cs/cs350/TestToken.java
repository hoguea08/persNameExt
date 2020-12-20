package edu.odu.cs.cs350;

import static edu.odu.cs.cs350.Classification.BP;
import static edu.odu.cs.cs350.Classification.CP;
import static edu.odu.cs.cs350.Classification.OTHER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

public class TestToken
{
	Token defaultToken;
    Token token;
    Token token2;
    String theToken;
    Classification classification;

    @Before
    public void Setup()
    {
        theToken = "Bob";
        classification = BP;
        token = new Token(theToken, classification);
        token2 = new Token(theToken);
        defaultToken = new Token();
    }
    
    @Test
    public void TestNonDefaultConstructor1()
    {        
        assertThat(token.getClassification(), equalTo(BP));
        assertThat(token.getToken(), equalTo("Bob"));
    }
    
    @Test
    /*
     * Test Token(String) to make sure it
     * fills the FeatureSet when building the token
     * based on the token(string) passed to it
     */
    public void TestNonDefaultConstructor2()
    {
    	assertThat(token2.getClassification(), nullValue());
    	assertThat(token2.getToken(), equalTo("Bob"));
        assertThat(token2.features.getLexical(), equalTo(LexicalFeature.CAP_WORD));
        assertThat(token2.features.getPOS(), equalTo(POSFeature.OTHER));
    	
     	token2=new Token();
        token2.setClassification(CP);
    	token2.setToken("Sarah");
    	
    	assertThat(token2.getClassification(), equalTo(CP));
    	assertThat(token2.getToken(), equalTo("Sarah"));
    	assertThat(token2.features.getLexical(), equalTo(LexicalFeature.CAP_WORD));
        assertThat(token2.features.getPOS(), equalTo(POSFeature.OTHER));
    }
    
    @Test
    public void TestDefaultConstructor()
    {
    	assertThat(defaultToken.getClassification(), nullValue());
    	assertThat(defaultToken.getToken(), equalTo(""));
    	
    	defaultToken.setClassification(OTHER);
    	defaultToken.setToken("Tim");
    	
    	assertThat(defaultToken.getClassification(), equalTo(OTHER));
    	assertThat(defaultToken.getToken(), equalTo("Tim"));
    }
}
