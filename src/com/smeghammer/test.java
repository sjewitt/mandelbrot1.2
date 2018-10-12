package com.smeghammer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.ICheckPointRange;
import com.smeghammer.fractal.IGridScalingObject;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.GUI.ViewPortal;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;
import com.smeghammer.math.Coordinate;


/*
 * VIEWPORT is always (eg) 100,100
 * TODO: 150208 - viewport offset stuff
 * BEGIN:
 * mag 1x:
 * set default zoom point to (VIEWPORT/2,VIEWPORT/2) (50,50)
 * set bounding box/(2*zoom factor) centred on this at viewport and get top-left of this:
 * (50 - 100/(2*1)),(50-100/(2*1))
 *  = 0,0
 * iter is over viewport X, viewport Y - ie 100/100 nested iters
 * iter increment is 1/mag = 1 (obviously...)
 * THEREFORE: we get the full picture.
 * 
 * ZOOM POINT 1:
 *  -> set zoom factor to 2.
 *  -> set zoom point (50,25)
 *  -> set bounding box/(2*zoom factor) centred on this at viewport and get top-left of this.
 *   --> (50 - (100/(2*2))),(25-(100/(2*2)))
 *   --> 25,0
 *   IF calculated x or y is < 0, reset to zero.
 * iterate over VIEWPORT (100) beginning at 25,0 incrementing by 1/zoomfactor
 * 
 * KEEP TRACK OF CURRENT SUBSECTION OF VIEWPORT (25,0)(??)
 * 
 * ZOOM POINT 2:
 *  -> set zoom factor to 4
 *  -> (25,25)
 *  -> set bounding box centred on CALCULATED point of VIEWPORT:
 *   --> current VIEWPORT bounds + (current zoom point/current zoom factor):
 *   --> (25 - (100/(2*4))),((100/(2*4)))
 * */

public class test {

	/**
	 * @param args
	 */
	
	static
	{
		PropertyConfigurator.configureAndWatch("../conf/log4j.properties");
	}
	
	private static Log logger = LogFactory.getLog(test.class);
	
	public static void main(String[] args) {
		
		logger.info("Initialising fractal engine.");	
		
		ViewPortal v = new ViewPortal();
		
		/*
		try
		{
			
			IGridScalingObject scale = new GridScalingObject(16,16,2);	//assume square
			ICheckPointRange engine = new CheckPointRange(scale, new TestPointMandelbrot(9));
			engine.go();
			
			scale.setCurrentStartPoint(new Coordinate(6,6));
			engine.go();
			
			scale.setCurrentStartPoint(new Coordinate(6,9));
			engine.go();
			
			scale.setCurrentStartPoint(new Coordinate(7,5));
			engine.go();
			
			scale.setCurrentStartPoint(new Coordinate(7,11));
			engine.go();
		}
		catch(GridScalingObjectException ex)
		{
			ex.printStackTrace();
		}
		catch(TendsToInfinityException ex)
		{
			ex.printStackTrace();
		}
		catch(CheckPointRangeException ex)
		{
			ex.printStackTrace();
		}
		*/
	}
}
