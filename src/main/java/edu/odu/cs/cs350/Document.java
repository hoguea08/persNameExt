package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Documents primary function is to take a long string 
 * from in input file (ie: .txt) and cbreak it down
 * into smaller strings, based on <NER> tags, to be evaluated
 */

public class Document 
{
    private ArrayList<TextBlock> theBlocks;
    private ArrayList<String> textBlocks;
    private String document;
    private boolean hasNerTags;
    
    Document()
    {
        this.document = null;
        this.textBlocks = null;
    }
    
    Document(String doc)
    {
        this.document = doc;
        hasNerTags = false;
        this.separateIntoTextBlocks();
        //System.out.println(textBlocks.get(1));
        //System.exit(0);
        theBlocks = new ArrayList<TextBlock>();
        try
        {
        for(int i = 0; i < textBlocks.size(); i++)
            {
                TextBlock tb = new TextBlock(textBlocks.get(i));
                theBlocks.add(tb);
            }   
        }
        catch(Exception e)
        {
            System.out.println("There are no textBlocks");
            System.exit(0);
        }
    }
    
    public boolean getHasNerTags()
    {
        return hasNerTags;
    }
     
    public String getDocument()
    {
        return this.document;
    }
    
    public ArrayList<String> getTextBlocks()
    {
        return this.textBlocks;
    }
    
    public void setDocument(String str)
    {
        this.document = str;
        if (this.textBlocks != null)
        	this.textBlocks.clear();
        this.separateIntoTextBlocks();
    }
    
    public ArrayList<TextBlock> getBlocks()
    {
        return theBlocks;
    }
    
    /*
     * This breaks down a string into smaller string(textBlocks)
     * based on the location of <NER> tags
     * 
     */
    public void separateIntoTextBlocks()
    {
        /**
         * text - a String Scanner to iterate through string word-by-word
         * word - stores current word being read
         * i - tracks current array element # being added to
         * textBlocks - an arrayList of textBlocks (strings)
         *              this is what is returned from this function
        */
        Scanner text = new Scanner(this.document);
        String word;
        int i = 0;
        ArrayList<String> textBlocks = new ArrayList<String>();
        boolean endOfBlock = true;
        

        /**
         * load first string into word
         * remove the <NER> tag
         * add the string to first element of textBlocks
        */
        String line = "";
        while(text.hasNextLine())
        {
            line = text.nextLine();
            //System.out.println(line);
            Scanner lineScan = new Scanner(line);
            if (lineScan.hasNext())
            {
                if (endOfBlock == true)
                {
                    endOfBlock = false;
                    word = lineScan.next();
                    //System.out.println(word);
                    word = word.replace("<NER>", ""); 
                    textBlocks.add(word);
                }
                
                if(!lineScan.hasNext())
                {
                    //System.out.println(word);
                    textBlocks.set(i, textBlocks.get(i) + "\n");
                }
                
                while(lineScan.hasNext()) 
                {            
                    word = lineScan.next();
                    
                    /**
                     * 3 branches:
                     * 1. if </NER> is found:
                     *      - remove the tag
                     *      - append the word end of current string element
                     *      - iterate to next textBlocks element
                     * 2. if <NER> tag is found
                     *      - remove the tag
                     *      - append the word end of current string element
                     * 3. else all other words
                     *      - append the word end of current string element
                    */ 
                    if(word.contains("</NER>"))
                    {   
                        hasNerTags = true;
                        endOfBlock = true;
                        //##System.out.println("End: " + word);
                        word = word.replace("</NER>", "");
                        /** 
                         *  Handles when when to tags are touching
                         *  ie: </NER><NER>
                         */
                        if (word.length() > 4 && word.substring(0,5).equals("<NER>"))
                        {
                            //##System.out.println("One word: " + word);
                            textBlocks.add("");
                            word = word.replace("<NER>", "");
                        }
        
                        if(word.contains("<NER>"))
                        {
                            hasNerTags = true;
                            //##System.out.println("Next Block: " + word);
                            word = word.replace("<NER>", " ");
                        	Scanner temp = new Scanner(word);
        
                        	textBlocks.set(i, textBlocks.get(i) + " " + temp.next());
                        	i++;
                        	textBlocks.add(temp.next());
                        	temp.close();
                        }
                        
                        else
                        {
                            //##System.out.println(word);
                        	textBlocks.set(i, textBlocks.get(i) + " " + word);
                        	i++;  
                        	//##System.out.println("Not here");
                        }
        
                    }
                    
                    else if(word.contains("<NER>"))
                    {
                        word = word.replace("<NER>", "");
                        textBlocks.add(word);
                    }
                    
                    else
                    {
                        if (lineScan.hasNext())
                            textBlocks.set(i, textBlocks.get(i) + " " + word);
                        else
                            textBlocks.set(i, textBlocks.get(i) + " " + word + "\n");
                       
                    }
                }         
            }
        }
                text.close();
                this.textBlocks = (ArrayList<String>)textBlocks.clone();
    }
    
    public String getMarkUp()
    {
        return "";
    }
    public int getSize()
    {
        return textBlocks.size();
    }
}