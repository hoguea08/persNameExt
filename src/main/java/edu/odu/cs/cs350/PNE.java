/**
 * 
 */
package edu.odu.cs.cs350;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;


/**
 * @author mjoyce
 *
 */
public class PNE
{
    public static void main(String[] args) throws IOException  
    { 
        // Read input from Standard In
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        // Read in lines and create a String
        while (in.hasNextLine())
        {
            String s = in.nextLine(); 
            sb.append(s).append("\n");
        }
        
        String fileAsString = sb.toString();
        String output = Extractor.markPersonalNames(fileAsString);
        
        System.out.println(output);
    }
}