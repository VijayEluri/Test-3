package jalg.util.xmlres;
import java.util.ArrayList;

public class WomanModel 
{
	private String typology; //tipo a, tipo b, tipo c
	private String bodyImage;
	protected ArrayList<ClotheType> clotheTypes = new ArrayList<ClotheType>(4);
	
	public void setTypology(String typology) 
	{
		this.typology = typology;
	}
	
	public String getTypology() 
	{
		return typology;
	}

	public void setBodyImage(String bodyImage) 
	{
		this.bodyImage = bodyImage;
	}

	public String getBodyImage() 
	{
		return bodyImage;
	}
	
	public ArrayList<ClotheType> getCloseTypes()
	{
		return clotheTypes;
	}
	
	public ClotheType getLastCloseType()
	{
		return clotheTypes.get(clotheTypes.size() - 1);
	}
	
}
