/**
 * 
 */
package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mjoyce
 *
 */

public class TestPNE
{
    double BEST_GAMMA;
    double BEST_C;
    
    @Before
    public void setup()
    {
        BEST_GAMMA = 0.01;
        BEST_C = 0.1;
    }
    
    @Test
    public void systemTest()
    {
        String testString = "<NER>M. Angeles Villarreal \r\n" + 
                " Specialist in International Trade and Finance \r\n" + 
                " Foreign Affairs, Defense, and Trade Division \r\n" + 
                " Marisabel Cid \r\n" + 
                " Research Associate \r\n" + 
                " Foreign Affairs, Defense, and Trade</NER>\r\n" + 
                "<NER>M. Angeles Villarreal</NER>";

        Document document = new Document(testString);
        int size = document.getSize();
        assertThat(size, equalTo(2));
        
        String block1 = "M. Angeles Villarreal \r\n" + 
                " Specialist in International Trade and Finance \r\n" + 
                " Foreign Affairs, Defense, and Trade Division \r\n" + 
                " Marisabel Cid \r\n" + 
                " Research Associate \r\n" + 
                " Foreign Affairs, Defense, and Trade";
        String block2 = "M. Angeles Villarreal";
        
        String docBlock1 = document.getTextBlocks().get(0);
        assertThat(docBlock1, equalTo(block1));
        
        String docBlock2 = document.getTextBlocks().get(1);
        assertThat(docBlock2, equalTo(block2));
        
        LearningMachineTrainer trainer = new LearningMachineTrainer();
        assertThat(trainer.getGamma(), equalTo(BEST_GAMMA));
        assertThat(trainer.getC(), equalTo(BEST_C));
        
        //Extractor extractor = new Extractor(new TokenProvider(), new TagProvider(), new FakeLearningMachine());
        //extractor.Classify(document);  
        
        String markUp = "<NER><PER>M. Angeles Villarreal</PER> \r\n" + 
                " Specialist in International Trade and Finance \r\n" + 
                " Foreign Affairs, Defense, and Trade Division \r\n" + 
                " <PER>Marisabel Cid</PER> \r\n" + 
                " Research Associate \r\n" + 
                " Foreign Affairs, Defense, and Trade</NER>\r\n" + 
                "<NER><PER>M. Angeles Villarreal</PER></NER>";
        
        String docMarkUp = document.getMarkUp();
        
        assertThat(docMarkUp, equalTo(markUp));
    }
}
