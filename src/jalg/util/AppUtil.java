package jalg.util;

import jalg.dressadvisor.R;
import jalg.util.xmlres.ResourceSet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class AppUtil 
{
	
	protected int _background;
	
	protected Drawable _backgImg;
	protected ArrayList<Drawable> _drawables;
	
	private static AppUtil _appUtil = null;
	protected static final R.drawable resource = new R.drawable();
	
	ResourceSet resourceSet = null;
	
	
	protected AppUtil()
	{
		//code to initialize the instance 
		_drawables = new ArrayList<Drawable>(5);
	}
	
	//to return the singleton instance
	public static AppUtil getAppUtil()
	{
		if( _appUtil == null )
			_appUtil = new AppUtil();
		
		return _appUtil;
	}
	
	public void setBackGround(int bgResourceID)
	{
		_background = bgResourceID;
	}
	
	public int getBackGround()
	{
		return _background;
	}
	
	public void unbindDrawables()
	{
		for( int i = 0; i < _drawables.size(); i++ )
			_drawables.get(i).setCallback(null);
		
		_drawables.clear();
	}
	
	public void addDragable(Drawable drawable)
	{
		if( !_drawables.contains(drawable) )
			_drawables.add(drawable);
	}
	
	public Drawable getDrawableClipFromCenter( View view,int resourceID, int width, int height )
	{
		try
		{
			if(_backgImg != null)
			{
				_backgImg.setCallback(null);
				
				_drawables.remove(_backgImg);
			}
			Bitmap bg = BitmapFactory.decodeResource(view.getResources(), resourceID);
			Bitmap bMapScaled;
			
			int bgw = bg.getWidth();
			int bgh = bg.getHeight();
			
			int newHeight = (int)(((float)height/(float)width)*(float)bgw);
			
			if( newHeight <= bgh )//clip the image, taken all the width
			{
				bMapScaled = Bitmap.createBitmap(bg, 0, (bgh-newHeight)/2, bgw, newHeight);
				
				_backgImg = new BitmapDrawable(bMapScaled);
			}
			else//clip the image, taken all the height
			{
				int newWidth = (int)(((float)width/(float)height)*(float)bgh);
				
				bMapScaled = Bitmap.createBitmap(bg, (bgw-newWidth)/2, 0, newWidth, bgh);
				
				_backgImg = new BitmapDrawable(bMapScaled);
			}
			
			_drawables.add(_backgImg);
			
			return _backgImg;
		}
		catch(Exception ex)
		{
			Log.e("MyLog",ex.getMessage());
			return null;
		}
	}
	
	
	public int getDrawableID(String imageName)
	{
		try
		{
			Field f = R.drawable.class.getField(imageName);
			return f.getInt(resource);
		}
		catch(Exception ex)
		{
			return -1;
		}
	}
	
	public void GetResourceSet(InputSource xml)
    {
    	try	
    	{
        	SAXParserFactory spf = SAXParserFactory.newInstance();
        	
        	SAXParser sp = spf.newSAXParser();
        	
        	XMLReader xmlr = sp.getXMLReader();
        	
        	ResourceHandler rh = new ResourceHandler();
        	
        	xmlr.setContentHandler(rh);
        	
        	//Resources resources = getResources();
        	//new InputSource(resources.openRawResource(R.raw.reference))
        	xmlr.parse(xml);
        	
        	resourceSet = rh.getResourceSet();
        }
        catch(Exception ex)
        {
        	Log.e("MyLog",ex.getMessage());
        }
    }
	
	public String getRandomQuestionImage(int questionNumber)
	{
		ArrayList<String> questionImages = resourceSet.getQuestions().get(questionNumber).getQuestionImages();
		Random r = new Random();
		int imageNumber = r.nextInt(questionImages.size());
		return questionImages.get(imageNumber);
	}
	
}
