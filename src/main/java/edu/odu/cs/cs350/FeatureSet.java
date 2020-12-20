/**
 * 
 */
package edu.odu.cs.cs350;

/**
 * @author mjoyce
 *
 */
public class FeatureSet
{
    private LexicalFeature  lexFeature;
    private POSFeature      posFeature;  
    private Boolean[]       gazFeatures;  
    
    public FeatureSet()
    {
        this.lexFeature = null;
        this.posFeature = null;
        gazFeatures = new Boolean[9];
        for (Boolean gaz: gazFeatures)
        {
            gaz = null;
        }       
    }
    
    public FeatureSet(String s)
    {
        this.lexFeature = null;
        this.posFeature = null;
        gazFeatures = new Boolean[9];
        for (Boolean gaz: gazFeatures)
        {
            gaz = null;
        }       
    }
    public LexicalFeature getLexical() 
    {
        return this.lexFeature;
    }

    public POSFeature getPOS() 
    {
        return this.posFeature;
    }

    public Boolean[] getGazetteer() 
    {
        return this.gazFeatures;
    }
    
    public Boolean getGazFeature(int cell) 
    {
        return this.gazFeatures[cell];
    }    

    public void setLexical(LexicalFeature lex) 
    {
        this.lexFeature = lex;
    }

    public void setPOS(POSFeature pos) 
    {
        this.posFeature = pos;
    }
    
    public void setGazFeature(boolean bool, int cell)
    {
        this.gazFeatures[cell] = bool;
    }
    
    public void setGazFeatures(Boolean[] b)
    {
        gazFeatures = new Boolean[9];
        for(int i = 0; i < 9; i++)
            gazFeatures[i] = b[i];
    }
    // CapLetter, other, 1, 0, 0, 0,  0, 0, 0, 0, 0, 0, 1, 0, other, other, 1, 0, 0, 0,
    @Override
    public String toString() 
    { 
        
        StringBuilder output = new StringBuilder ();
        output.append(lexFeature);
        output.append(",");
        output.append(posFeature);
        
        for (Boolean i: gazFeatures)
        {
            if (i == null)
            {
                output.append(",0");
            }
            else
            {
                output.append(",");
                if (i == true)
                {
                    output.append("1");
                }
                else 
                {
                    output.append("0");
                }
            }
        }
        return output.toString();
    } 
}





