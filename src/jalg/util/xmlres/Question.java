package jalg.util.xmlres;

import java.util.ArrayList;

public class Question 
{
	private int number;//1, 2, ...
	protected ArrayList<String> questionImages = new ArrayList<String>(5);
	
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	public int getNumber() 
	{
		return number;
	}
	
	public ArrayList<String> getQuestionImages()
	{
		return questionImages;
	}
}
