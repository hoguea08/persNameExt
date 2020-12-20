package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;

public class TokenProvider implements Tokenizer {
    
	@Override
	public List<Token> createTokens(final String text) {
		final List<Token> tokenList = new ArrayList<Token>();
		final String[] splitString = text.split("\\b");

		for (int i = 0; i < splitString.length; i++) {
			if (!(splitString[i].trim().isEmpty())) {
				final Token tempToken = new Token(splitString[i].trim());
				tokenList.add(tempToken);
			}
		}
		
		 // Create new list same as old list but combine < PER > or </ PER > into <PER> and </PER>
		 // to simplify classification later.
		 
		List<Token> adjustedTokenList = new ArrayList();
		
		for(int i = 0; i < tokenList.size(); i++)
		{
		    Token token = tokenList.get(i);
		    int amountToJump = 2;
		    
		    //Get token plus next two tokens as string
		    if ( i < tokenList.size() - 2)
		    {
                Token nextToken = tokenList.get(i + 1);
                Token nextNextToken = tokenList.get(i + 2);
                String t1 = token.getToken();
                String t2 = nextToken.getToken();
                String t3 = nextNextToken.getToken();
                                
                String checkOpenPer = t1.charAt(t1.length() - 1) + t2 + t3.charAt(0);
                String checkClosePer = "";
                if (t1.length() > 1)
                    checkClosePer = t1.substring(t1.length() - 2, t1.length()) + t2 + t3.charAt(0);
                
                // add anything before < in first token as new token in new list
                // add <PER> or </PER> as new token in new list
                // add anything after > as new token in new list.
                // Exception is </PER>,<PER> where the >,< token is replaced by
                // , <

                if (checkOpenPer.equals("<PER>"))
                {
                    String remainingText = t1.substring(0, t1.length() - 1);
                    Token newToken;
                    if (!remainingText.isEmpty())
                    {
                        newToken = new Token(remainingText);
                        adjustedTokenList.add(newToken);
                    }

                    newToken = new Token("<" + t2 + ">");
                    adjustedTokenList.add(newToken);
                    
                    remainingText = t3.substring(1, t3.length());
                    if (!remainingText.isEmpty())
                    {
                        newToken = new Token(t3.substring(1, t3.length()));
                        adjustedTokenList.add(newToken);
                    }              
                    i += amountToJump;
                }
                else if (checkClosePer.equals("</PER>"))
                {
                    String remainingText = t1.substring(0, t1.length() - 2);
                    Token newToken;
                    if (!remainingText.isEmpty())
                    {
                        newToken = new Token(remainingText);
                        adjustedTokenList.add(newToken);
                    }

                    newToken = new Token("</" + t2 + ">");
                    adjustedTokenList.add(newToken);
                    
                    remainingText = t3.substring(1, t3.length());
                    if (!remainingText.isEmpty())
                    {
                        if (!remainingText.equals(", <"))
                        {
                            newToken = new Token(t3.substring(1, t3.length()));
                            adjustedTokenList.add(newToken);
                            
                        }
                        else
                        {
                            tokenList.get(i + 2).setToken(", <");
                            amountToJump = 1;
                        }
                            
                    }              
                    i += amountToJump;
                }
                // not <PER> or </PER>
                else
                {
                    adjustedTokenList.add(token);
                }
		    }
		    //last 2 tokens
		    else
		    {
		        adjustedTokenList.add(token);
		    }
		}
		
		for(int i = 0; i < adjustedTokenList.size(); i++)
		{
		    Token t = adjustedTokenList.get(i);
		    t.findFeatures();
		}
		
		return adjustedTokenList;
	}
}