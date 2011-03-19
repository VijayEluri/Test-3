package jalg.util.xmlres;

import java.util.ArrayList;

public class ResourceSet 
{
	private ArrayList<WomanModel> womanModels = new ArrayList<WomanModel>(3);
	private ArrayList<Question> questions = new ArrayList<Question>(10);
	
	public ArrayList<WomanModel> getWomanModels()
	{
		return womanModels;
	}
	
	public ArrayList<Question> getQuestions()
	{
		return questions; 
	}
	
	public WomanModel getLastWomanModel()
	{
		return womanModels.get(womanModels.size() - 1);
	} 
	
	public Question getLastQuestion()
	{
		return questions.get(questions.size() - 1);
	}
}
