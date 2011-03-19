package jalg.location;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationUtils 
{
	
	public LocationUtils()
	{
		
	}
	
	public static void getLocation(Context context)
	{
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener mlocListener = (LocationListener)context;
		
		lm.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
	}
	
}
