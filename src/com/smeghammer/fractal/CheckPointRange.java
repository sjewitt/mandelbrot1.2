package com.smeghammer.fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.smeghammer.image.Palette;
import com.smeghammer.image.PaletteException;

public class CheckPointRange implements ICheckPointRange {
	static
	{
		PropertyConfigurator.configureAndWatch("../conf/log4j.properties");
	}
	
	private static Log logger = LogFactory.getLog(CheckPointRange.class);
	private ITendsToInfinity executor;
	private IGridScalingObject scalingObject;
	private Palette palette;
	private BufferedImage bufferedImage;

	public CheckPointRange(IGridScalingObject gridScalingObject,ITendsToInfinity testPointMandelbrot) throws GridScalingObjectException{
		
		/*
		 * set the executor:
		 * */
		logger.info("Setting executor");
		this.setExecutor(testPointMandelbrot);
		
		/*
		 * set the scaling object so we can work out iterations etc.
		 * */
		logger.info("Setting scaling object");
		this.setScalingObject(gridScalingObject);
		
		/*
		 * set up buffered image for return
		 * */
		this.bufferedImage = new BufferedImage(
				this.scalingObject.getDisplayDimensions().width, 
				this.scalingObject.getDisplayDimensions().height,
				BufferedImage.TYPE_INT_ARGB);
		
		/*
		 * set up a palette:
		 * */
		this.palette = new Palette();
	}
	
	/*
	 * add the executor
	 * */
	private void setExecutor(ITendsToInfinity executor){
		this.executor = executor;
	}
	
	/*
	 * add the range data
	 * */
	private void setScalingObject(IGridScalingObject gridScalingObject){
		this.scalingObject = gridScalingObject;
	}
	
	/*
	 * execute for the current settings
	 * */
	public void go() throws CheckPointRangeException{
		try
		{
			/*
			 * determine offset
			 * */
			double xStart = 0;
			double yStart = 0;

			/*
			 * set up stuff for graphic
			 * */
			WritableRaster raster 	= this.bufferedImage.getRaster();	//allows creation of pixels
			ColorModel model 		= this.bufferedImage.getColorModel();	//for pixel color setting
			Color fractalColor;
			int argb;
			
			if(this.scalingObject.getCurrentStartPoint() != null){
				
				xStart = this.scalingObject.getCurrentStartPoint().getX();
				yStart = this.scalingObject.getCurrentStartPoint().getY();
				
				logger.info(" ----> current X start: " + xStart);
				logger.info(" ----> current Y start: " + yStart);
			}
			
			for(int y = 0;y < this.scalingObject.getDisplayDimensions().height; y++)
			{
				//System.out.print(y +": ");
				
				//reset xStart:
				xStart = this.scalingObject.getCurrentStartPoint().getX();
				
				for(int x = 0;x < this.scalingObject.getDisplayDimensions().height; x++)
				{
					this.executor.testPoints(this.scalingObject.getScaledX(xStart), this.scalingObject.getScaledX(yStart));
					
					if(this.executor.getIterations() == -1){
						//System.out.print("  ");
						fractalColor = new Color(0,0,0);
					}
					else{
						/*
						 * plug something in to here?
						 * */
						//fractalColor = new Color(0,this.executor.getIterations(),255-this.executor.getIterations());
						int iter = this.executor.getIterations();
						if(iter>251)
							iter = 251;
							//System.out.println(this.palette.getPaletteEntry(this.executor.getIterations()));
						
						//logger.fatal(this.executor.getIterations());
						fractalColor = this.palette.getPaletteEntry(iter);
						//System.out.println(this.palette.getPaletteEntry(this.executor.getIterations()));
					}
					/*
					 * determine the color data for the current pixel:
					 * */
					argb = fractalColor.getRGB();
				    Object colorData = model.getDataElements(argb, null);
				    
				    /*
				     * and set the pixel color:
				     * */
				    raster.setDataElements(x,y,colorData);
					
					xStart = xStart + this.scalingObject.getCurrentIncrementX();
				}
				yStart = yStart + this.scalingObject.getCurrentIncrementY();
				
				//System.out.println();
			}
			//add a space
			//System.out.println();
		}
		catch(TendsToInfinityException ex)
		{
			ex.printStackTrace();
		}
		catch(GridScalingObjectException ex)
		{
			ex.printStackTrace();
		}
		/**/
		catch(PaletteException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public BufferedImage getBufferedImage() throws CheckPointRangeException{
		return this.bufferedImage;
	}
}
