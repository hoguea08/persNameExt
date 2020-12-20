/**
 * 
 */
package edu.odu.cs.cs350;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author mjoyce
 *
 */
public class TestLibrarian
{
    @Test
    public void systemTest() throws Exception
    {
        String testString = "<NER><PER>M. Angeles Villarreal</PER> \n" + 
                " Specialist in International Trade and Finance \n" + 
                " Foreign Affairs, Defense, and Trade Division \n" + 
                " <PER>Marisabel Cid</PER> \n" + 
                " Research Associate \n" + 
                " Foreign Affairs, Defense, and Trade</NER>\n" + 
                "<NER><PER>M. Angeles Villarreal</PER></NER>";

        Document document = new Document(testString);
        int size = document.getSize();
        assertThat(size, equalTo(2));
        
        String block1 = "<PER>M. Angeles Villarreal</PER> \n" + 
                " Specialist in International Trade and Finance \n" + 
                " Foreign Affairs, Defense, and Trade Division \n" + 
                " <PER>Marisabel Cid</PER> \n" + 
                " Research Associate \n" + 
                " Foreign Affairs, Defense, and Trade";
        String block2 = "<PER>M. Angeles Villarreal</PER>";
        
        String docBlock1 = document.getTextBlocks().get(0);
        assertThat(docBlock1, equalTo(block1));
        
        String docBlock2 = document.getTextBlocks().get(1);
        assertThat(docBlock2, equalTo(block2));
        
        double gamma = 0.01;
        double C = 0.1;
        LearningMachineTrainer learningMachineTrainer = new LearningMachineTrainer();
		learningMachineTrainer.train(document);   

        assertThat(learningMachineTrainer.getGamma(), equalTo(gamma));
        assertThat(learningMachineTrainer.getC(), equalTo(C));
    }
}
