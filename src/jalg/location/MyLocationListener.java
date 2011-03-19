package jalg.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener
{
	
	public void onLocationChanged(Location loc)
	{
		loc.getLatitude();
	
		loc.getLongitude();
	
		String Text = "My current location is: " +
			"Latitud = " + loc.getLatitude() +
			"Longitud = " + loc.getLongitude();
	
	}

	@Override
	public void onProviderDisabled(String provider)
	{

	}

	@Override
	public void onProviderEnabled(String provider)
	{

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{

	}

}
