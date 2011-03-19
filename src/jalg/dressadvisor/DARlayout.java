package jalg.dressadvisor;

import jalg.util.AppUtil;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class DARlayout extends RelativeLayout 
{
	//Dynamic Answer Relative Layout
	ImageButton bgButtonLeft;
	ImageButton bgButtonRight;
	Drawable draw = null;
	Drawable draw2 = null;
	int width;
	int height;
	
	
	public DARlayout(Context context)
	{
		
		super(context);
		
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		
		LayoutInflater li;
		
		li = (LayoutInflater)getContext().getSystemService(infService);
		
		li.inflate(R.layout.qview, this, true);
		
		bgButtonLeft = (ImageButton)findViewById(R.id.bgButtonLeft);
		bgButtonRight = (ImageButton)findViewById(R.id.bgButtonRight);
		
		hookupButtons();
	}
			
	public DARlayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	public DARlayout(Context context, int parentWidth, int parentHeight)
	{
		super(context);
		
		this.width = parentWidth;
		this.height = parentHeight;
		
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		
		LayoutInflater li;
		
		li = (LayoutInflater)getContext().getSystemService(infService);
		
		li.inflate(R.layout.qview, this, true);
		
		bgButtonLeft = (ImageButton)findViewById(R.id.bgButtonLeft);
		bgButtonRight = (ImageButton)findViewById(R.id.bgButtonRight);
		
		android.view.ViewGroup.LayoutParams bgParams = bgButtonLeft.getLayoutParams();
	    bgParams.width = parentWidth/2;
	    bgParams.height = parentHeight/2;
	    bgButtonLeft.setLayoutParams(bgParams);
		hookupButtons();
	}
	
	protected void hookupButtons()
	{
		bgButtonLeft.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View w)
					{
						click(w);
					}
				});
		
		bgButtonRight.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View w)
					{
						click(w);
					}
				});
	}
	
	
	protected void click(View w)
	{
		try
		{
			AppUtil util = AppUtil.getAppUtil();
			
			if( w == bgButtonLeft )
			{
				Drawable draw2 = util.getDrawableClipFromCenter(this, R.drawable.resources_fondos_landscape, width, height);
				
				util.addDragable(draw2);
				
				this.setBackgroundDrawable(draw2);
			}
			else
			{
				if( draw == null ) 
				{
					//draw = getResources().getDrawable(R.drawable.bg_a);
					//draw = getResources().getDrawable(util.getDrawableID("resources_fondos_landscape_off"));
					
					draw = util.getDrawableClipFromCenter(this, R.drawable.resources_fondos_landscape_off, width, height);
					
					util.addDragable(draw);
				}
				
				this.setBackgroundDrawable(draw);
			}
		}
		catch(Exception ex)
		{
			Log.e("MyLog",ex.getMessage());
		}
	}
	
	@Override
	protected void onConfigurationChanged(Configuration newConfiguration)
	{
		super.onConfigurationChanged(newConfiguration);
		
		AppUtil util = AppUtil.getAppUtil();
		
		Activity act = (Activity)this.getContext();
		
		Display display = act.getWindowManager().getDefaultDisplay(); 
		int width = display.getWidth();
		int height = display.getHeight();
		 
		this.setBackgroundDrawable(util.getDrawableClipFromCenter(this, R.drawable.resources_fondos_landscape, width, height));
	}
			
	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	
	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
	    
	    android.view.ViewGroup.LayoutParams bgParams = bgButtonLeft.getLayoutParams();
	    bgParams.width = parentWidth/3;
	    bgParams.height = parentHeight/3;
	    bgButtonLeft.setLayoutParams(bgParams);
	}*/
	
	
	private int measureHeight(int measureSpec) 
	{
		
		int specMode = MeasureSpec.getMode(measureSpec);
		
		int specSize = MeasureSpec.getSize(measureSpec);
		
		//[ ... Calculate the view height ... ]
		
		return specSize;
	}
	
	private int measureWidth(int measureSpec) 
	{
		
		int specMode = MeasureSpec.getMode(measureSpec);
		
		int specSize = MeasureSpec.getSize(measureSpec);
		
		//[ ... Calculate the view width ... ]
		
		return specSize;
	}
		
}
