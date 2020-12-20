package edu.odu.cs.cs350;

import java.io.IOException;

public class Lexical
{
    /*
        @param input string (our word to be checked)
        @return a LexicalFeture (which is the result of the lexical Analysis)
        
        the results of passing a string to findLexical() 
        should fit into 1 of 8 categories:
        1 A number                       NUMBER
        2 A punctuation mark             PUNCT
        3 A single capitalized letter    CAP_LETTER
        4 A capitalized word             CAP_WORD
        5 An ALL-CAPS word               ALL_CAPS
        6 Line feed / new line           LINE_FEED
        7 Other                          OTHER;
    */
    public static LexicalFeature findLexical(String token)
    {
        LexicalFeature result = null; //where we will store what lexical category our token is
        
        /*7. Check if the Token is NULL.*/
        if (token == null || token.length() == 0)
            result = LexicalFeature.OTHER;
        else
        {
            
            /*1. Check if token is a NUMBER*/
            boolean isNumber = true;
            try
            {
                double d = Double.parseDouble(token);
            }
            catch(Exception e)
            {
                isNumber = false;
            }
            
            if (isNumber == true)
                result = LexicalFeature.NUMBER;
            
            /*2. Check if token is a PUNCT*/
            String punctuations = ".,'\"!?:;-_(){}[]";
                    
            if (punctuations.contains(token))
                result = LexicalFeature.PUNCT;
            
            /*3. Check if token is a CAP_LETTER*/
            if (token.length() == 1)
            {
                char ch = token.charAt(0);
                if (Character.isUpperCase(ch))
                    result = LexicalFeature.CAP_LETTER;
            }
            
            /*4. Check if token is a CAP_WORD*/
            /*5. Check if token is ALL_CAPS*/
            else if (result != LexicalFeature.NUMBER)
            {
                boolean hasLowerCase = !token.equals(token.toUpperCase());
                char ch = token.charAt(0);
                if (hasLowerCase && Character.isUpperCase(ch))
                    result = LexicalFeature.CAP_WORD;
                else if (!hasLowerCase)
                    result = LexicalFeature.ALL_CAPS;
                
            }
            
            /*6. Check if token is a LINE_FEED*/
            if (token == "\n")
                result = LexicalFeature.LINE_FEED;
        }        
        
        /*8. If other checks failes, the token is OTHER*/
        if (result == null)
            result = LexicalFeature.OTHER;
        
       return result;
    }
}
