package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/*
    1 A number                       NUMBER
    2 A punctuation mark             PUNCT
    3 A single capitalized letter    CAP_LETTER
    4 A capitalized word             CAP_WORD
    5 An ALL-CAPS word               ALL_CAPS
    6 Line feed / new line           LINE_FEED
    8 Other                          OTHER;
*/
public class TestLexical
{    
    @Test
    public void testFindLexical()
    {
        String s = "123.4";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.NUMBER));
        
        s = ".";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.PUNCT));
        
        s = "I";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.CAP_LETTER));
        
        s = "John";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.CAP_WORD));
        
        s = "CAPS";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.ALL_CAPS));
        
        s = "\n";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.LINE_FEED));
        
        s = "";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.OTHER));
        
        s = "abc";
        assertThat(Lexical.findLexical(s), equalTo(LexicalFeature.OTHER));
                 
    }

}
