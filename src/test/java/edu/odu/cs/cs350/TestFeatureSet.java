package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*; 
import java.util.*; 
import java.util.ArrayList;

public class TestFeatureSet {

    @Test
    public void testToString() 
    {
        FeatureSet testFeatures = new FeatureSet();
        
        testFeatures.setLexical(LexicalFeature.CAP_LETTER);
        testFeatures.setPOS(POSFeature.PERIOD);
        
        for(int i = 0; i < testFeatures.getGazetteer().length; i++) 
        {
            testFeatures.setGazFeature(false, i);
            i++;
            if (i != 9)
            	testFeatures.setGazFeature(true, i);
            
            
        }
        
        String testString = "CAP_LETTER,PERIOD,0,1,0,1,0,1,0,1,0";

        assertThat(testFeatures.toString(), equalTo(testString));
    }

}
