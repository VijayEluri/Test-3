package jalg.util.xmlres;

import java.util.ArrayList;

public class ClotheType {
	
	protected String name;//casual, elegant, office, sport, winter
	protected ArrayList<String> clotheParts = new ArrayList<String>(5); //clothes pieces
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<String> getClotheParts()
	{
		return clotheParts;
	}
	
}
