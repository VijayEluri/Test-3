package jalg.weather;

import java.util.ArrayList;


public class WeatherSet 
{

	private WeatherCurrentCondition myCurrentCondition = null;
	private ArrayList<WeatherForecastCondition> myForecastConditions =
		new ArrayList<WeatherForecastCondition>(4);

	public WeatherCurrentCondition getWeatherCurrentCondition() 
	{
        return myCurrentCondition;
    }
	
    public void setWeatherCurrentCondition(WeatherCurrentCondition myCurrentWeather) 
    {
	    this.myCurrentCondition = myCurrentWeather;
    }
    
    public ArrayList<WeatherForecastCondition> getWeatherForecastConditions() 
    {
    	return this.myForecastConditions;
    }
    
    public WeatherForecastCondition getLastWeatherForecastCondition() 
    {
    	return this.myForecastConditions.get(this.myForecastConditions.size() - 1);
    }
}

