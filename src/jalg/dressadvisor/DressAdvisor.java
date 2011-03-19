package jalg.dressadvisor;

import jalg.location.LocationUtils;
import jalg.util.AppUtil;
import jalg.util.ResourceHandler;
import jalg.util.xmlres.ResourceSet;
import jalg.weather.GoogleWeatherHandler;
import jalg.weather.WeatherSet;

import java.net.ContentHandler;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class DressAdvisor extends Activity implements LocationListener 
{
	
	private String orientation;
	
	Animation outAnim;
	Animation enterAnim;
	
	ImageButton bgButtonLeft = null;
	ImageButton bgButtonRight = null;
	
	RelativeLayout mLayout = null;
	
	AppUtil appUtil = AppUtil.getAppUtil();
	
	int width = 0;
	int height = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.qview);
        
        outAnim = AnimationUtils.loadAnimation(this, R.anim.outquestion);
        enterAnim = AnimationUtils.loadAnimation(this, R.anim.enternewquestion);
        
        bgButtonLeft = (ImageButton) findViewById(R.id.bgButtonLeft);
        bgButtonLeft.setOnClickListener(imageButtonListener);
        
        bgButtonRight = (ImageButton) findViewById(R.id.bgButtonRight);
        bgButtonRight.setOnClickListener(imageButtonListener);
                
        Resources resources = getResources();
    	
        appUtil.GetResourceSet(new InputSource(resources.openRawResource(R.raw.reference)));
                       
        WindowManager wm = getWindowManager(); 
        Display d = wm.getDefaultDisplay();
        
        width = d.getWidth();
        height = d.getHeight();
        
        int buttonWidth = (width-30)/2;
        int buttonHeight = (int)((float)304/(float)487 * buttonWidth);
        resizeView(bgButtonLeft, buttonWidth, buttonHeight);
        resizeView(bgButtonRight, buttonWidth, buttonHeight);
        
        mLayout = (RelativeLayout)findViewById(R.id.RLayout);
        
        setBackground(R.drawable.resources_fondos_landscape_off);
            
        //GetResourceSet();
        //weather temperature recovery tested
        //tv.setText(GetWeatherConditions());
        //test getlocation tested, no t
        //GetLocation();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
    	super.onConfigurationChanged(newConfig);
    	
    	/* 
    	if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
    	{
    		orientation = "landscape";
    	}
    	if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
    		orientation = "portrait";
    	if(newConfig.orientation == Configuration.ORIENTATION_SQUARE)
    		orientation = "square";
    	if(newConfig.orientation == Configuration.ORIENTATION_UNDEFINED)
    		orientation = "undefined";
    	 */
    } 
    
    @Override
    protected void onDestroy()
    {
    	AppUtil.getAppUtil().unbindDrawables();
    	super.onDestroy();
    }
    
    View.OnClickListener imageButtonListener = new OnClickListener() 
    {
        public void onClick(View v) 
        {
        	selectImage(v);
        }
    };
    
    protected void selectImage( View v )
    {
    	if( v == bgButtonLeft )
    	{
    		/*
    		enterAnim.setFillAfter(true);
    		
    		enterAnim.setRepeatMode(Animation.RESTART);
        	
    		mLayout.startAnimation(enterAnim);
    		
    		bgButtonLeft.setBackgroundResource(R.drawable.resources_preguntas_02_4);
    		
    		mLayout.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.resources_fondos_landscape));
    		*/
    	}
    	else if( v == bgButtonRight )
    	{
    		/*
    		outAnim.setRepeatMode(Animation.RESTART);
    		        	
    		mLayout.startAnimation(outAnim);
    		
    		bgButtonLeft.setBackgroundResource(R.drawable.resources_preguntas_02_2);
    		
    		mLayout.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.resources_fondos_landscape_off));
    		
    		*/
    	}
    	
    	applyAnimation(outAnim, enterAnim); 
    }
    
    protected void resizeView(View v, int newWidth, int newHeight)
    {
    	android.view.ViewGroup.LayoutParams bgParams = v.getLayoutParams();
	    bgParams.width = newWidth;
	    bgParams.height = newHeight;
	    v.setLayoutParams(bgParams);
    }
    
    protected void setBackground(int imageID)
    {
    	Drawable background = appUtil.getDrawableClipFromCenter(mLayout, imageID, width, height);
        
        mLayout.setBackgroundDrawable(background);
    }
    
    private void applyAnimation(Animation _out, Animation _in) 
    {
    	final Animation in = _in;
    
    	// Ensure the text stays out of screen when the slide-out
    	// animation has completed.
    	_out.setFillAfter(false);
    	
    	// Create a listener to wait for the slide-out animation to complete.
    	_out.setAnimationListener(new AnimationListener() 
	    	{
		    	public void onAnimationEnd(Animation _animation) 
		    	{
		            setBackground(R.drawable.resources_fondos_landscape_off);
		            
		            String image = appUtil.getRandomQuestionImage(0);
		            
		    		bgButtonLeft.setBackgroundResource(appUtil.getDrawableID(image));
		    		
		    		// Slide it back in to view
			    	mLayout.startAnimation(in);
		    	}
		    	public void onAnimationRepeat(Animation _animation) {}
		    	public void onAnimationStart(Animation _animation) {}
	    	});
    	
		// Apply the slide-out animation
		mLayout.startAnimation(_out);
		
		if( width > height )
			setBackground(R.drawable.resources_fondos_landscape);
		else
			setBackground(R.drawable.resources_fondos_portrait);
	}
    
    /*
    boolean b = false;
    
    View.OnClickListener myListener = new OnClickListener() 
    {
        public void onClick(View v) 
        {
        	changeBG();
        }
    };
     
    Drawable draw = null;
    
    public void changeBG()
    {
    	LinearLayout mLayout = (LinearLayout)findViewById(R.id.MainLayout1);
    	
    	if( b )
    	{
    		try
    		{	        	
	        	if( draw != null )
	        	{
	        		draw = getResources().getDrawable(R.drawable.resources_fondos_landscape_off);
	        		
	        		mLayout.setBackgroundDrawable(draw);
	        		
	        		draw = null;
	        	}
	        	else
	        	{
	        		draw = getResources().getDrawable(R.drawable.resources_fondos_landscape);
	        		
	        		mLayout.setBackgroundDrawable(draw);
	        	}
    		}
    		catch(Exception ex)
    		{
    			//
    		}
    	}
    	else
    	{
    		anim.setRepeatMode(Animation.INFINITE);
    		mLayout.startAnimation(anim);
    	}
    	b = !b;
    }*/
    
    public String GetWeatherConditions()
    {
    	try	
    	{
        	URL url = new URL("http://www.google.com/ig/api?weather=havana,cuba");
        	SAXParserFactory spf = SAXParserFactory.newInstance();
        	
        	SAXParser sp = spf.newSAXParser();
        	
        	XMLReader xmlr = sp.getXMLReader();
        	
        	GoogleWeatherHandler gh = new GoogleWeatherHandler();
        	
        	xmlr.setContentHandler(gh);
        	
        	xmlr.parse(new InputSource(url.openStream()));
        	
        	WeatherSet wDS = gh.getWeatherSet();
        	
        	int temperature = wDS.getWeatherCurrentCondition().getTempCelcius();
        	
        	return "Temperature: " + String.valueOf(temperature) + "C";
        }
        catch(Exception ex)
        {
        	Log.e("MyLog",ex.getMessage());
        	return ex.getStackTrace() + ex.getMessage();
        }
    }
    
    public void GetLocation()
    {
    	LocationUtils.getLocation(this);
    }
    
    //methods from LocationListener interface
    
    public void onLocationChanged(Location loc)
	{
		loc.getLatitude();
	
		loc.getLongitude();
	
		String locationText = "My current location is: " +
			"Latitud = " + loc.getLatitude() +
			"Longitud = " + loc.getLongitude();
		
		 //TextView tv = (TextView) findViewById(R.id.textView1);
		 
		 //tv.setText(locationText);
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
	
	//end methods from LocationListener interface
}