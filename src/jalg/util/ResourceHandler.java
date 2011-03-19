package jalg.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import jalg.util.xmlres.*;

public class ResourceHandler extends DefaultHandler
{
	private ResourceSet resourceSet = null;
	
    private boolean in_mujerestipologias = false;
    private int mujerestipologias_level = 0;
    private boolean in_preguntas = false;
    private boolean bodyImage = false;
    private boolean clotheImage = false;
    private boolean questionImage = false;
    private int questionNumber = -1;
    
    public ResourceSet getResourceSet() 
    {
        return this.resourceSet;
    }
    
    @Override
    public void startDocument() throws SAXException 
    {
        this.resourceSet = new ResourceSet();
    }

    @Override
    public void endDocument() throws SAXException 
    {
    	
    }
 
    @Override
    public void startElement(String namespaceURI, String localName,
    		String qName, Attributes atts) throws SAXException 
    {
        if (localName.equals("mujerestipologias")) 
        {
                this.in_mujerestipologias = true;
        } 
        else if (localName.equals("preguntas")) 
        {
            this.in_preguntas = true;
        }
        else if (localName.equals("images")) 
        {
            if( in_mujerestipologias && mujerestipologias_level == 1 )//bodyImage
            {
            	bodyImage = true;
            }
            if( in_mujerestipologias && mujerestipologias_level == 2 )//clotheImage
            {
            	clotheImage = true;
            }
            if( in_preguntas && questionNumber >= 0 )
            {
            	questionImage = true;
            }
        } 
        else if (localName.equals("etiquetas"))
        {}
        else if (localName.equals("portadadelaaplicacion"))
        {}
        else if (localName.equals("fondos"))
        {}
        else if (localName.equals("RESOURCES"))
        {}
        else if (localName.equals("image"))
        {
        	String imageUrl = atts.getValue("url");
        	if(bodyImage)
        	{
        		resourceSet.getLastWomanModel().setBodyImage(imageUrl);
        		bodyImage = false;
        	}
        	else if(clotheImage)
        		resourceSet.getLastWomanModel().getLastCloseType().getClotheParts().add(imageUrl);
        	else if(questionImage)
        		resourceSet.getLastQuestion().getQuestionImages().add(imageUrl);
        }
        else
        {
        	if( in_mujerestipologias )
        	{
        		if( mujerestipologias_level == 0 )
        		{
        			WomanModel wm = new WomanModel();
        			wm.setTypology(localName);
        			resourceSet.getWomanModels().add(wm);
        		}
        		else if( mujerestipologias_level == 1 )
        		{
        			ClotheType ct = new ClotheType();
        			ct.setName(localName);
        			resourceSet.getLastWomanModel().getCloseTypes().add(ct);
        		}
        		mujerestipologias_level ++;
        	}
        	else if( in_preguntas )//new question
        	{	
        		Question nq = new Question();
        		nq.setNumber(++questionNumber);
        		
        		resourceSet.getQuestions().add(nq);
        	}
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)throws SAXException 
    {
        if (localName.equals("mujerestipologias")) 
        {
                this.in_mujerestipologias = false;
        } 
        else if (localName.equals("preguntas")) 
        {
                this.in_preguntas = false;
        } 
        else if (localName.equals("etiquetas"))
        {}
        else if (localName.equals("portadadelaaplicacion"))
        {}
        else if (localName.equals("fondos"))
        {}
        else if (localName.equals("RESOURCES"))
        {}
        else if (localName.equals("image"))
        {}
        else if(localName.equals("images") )
        {
        	if( in_mujerestipologias && mujerestipologias_level == 1 )//bodyImage
            {
            	bodyImage = false;
            }
        	if( in_mujerestipologias && mujerestipologias_level == 2 )//clotheImage
            {
        		clotheImage = false;
            }
        	if( in_preguntas && questionNumber >= 0 )
        		questionImage = false;
        }
        else
        {
        	if( in_mujerestipologias )
        	{
        		mujerestipologias_level--;
        	}
        	else if( in_preguntas )
        		questionImage = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
    {    	
    	/*String temp = String.valueOf(ch);
    
    	if(temp.contains("RESOURCES_mujerestipologias_tipoc_casual_02_02_00"))
    		temp += "";
    	
    	temp = temp.substring(start, length);
    	
    	if(temp.contains("RESOURCES_mujerestipologias_tipoc_casual"))
    		temp += "";
    	
    	if(bodyImage)
    	{
    		resourceSet.getLastWomanModel().setBodyImage(temp);
    		bodyImage = false;
    	}
    	
    	if(clotheImage)
    		resourceSet.getLastWomanModel().getLastCloseType().getClotheParts().add(temp);*/
    }
}
