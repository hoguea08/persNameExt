package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Librarian
{     
    public static void main(String[] args) throws Exception 
    {
        String fileName = "sample2.txt";
        String input = getInput(fileName);
        Document document = new Document(input);
        Trainer trainer = new LearningMachineTrainer();
        trainer.train(document);   
    }
    
    public static String getInput(String fileName) throws IOException 
    {
        InputStream is = new FileInputStream(fileName);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
                
        while(line != null)
        {
           sb.append(line).append("\n");
           line = buf.readLine();
        }
                
        String fileAsString = sb.toString();
        
        buf.close();
        return fileAsString;
    }
}

