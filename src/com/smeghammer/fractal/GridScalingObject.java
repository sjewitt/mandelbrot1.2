package com.smeghammer.fractal;

import java.awt.Dimension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.smeghammer.math.Coordinate;

public class GridScalingObject implements IGridScalingObject {

	static
	{
		PropertyConfigurator.configureAndWatch("../conf/log4j.properties");
	}
	
	private static Log logger = LogFactory.getLog(GridScalingObject.class);
	
	/*
	 * The x,y size of the viewport.
	 * This remains constant for the 
	 * entire runtime. Set at class instantiation.
	 * */
	private Dimension dimension 	= null;
	
	/*
	 * default to 1:1 scaling.
	 * This is the current scaling factor for viewport
	 * size
	 * */
	private double 	currentZoomFactor 	= 1;
	private double	previousZoomFactor	= 1;
	
	/*
	 * the zoom factor for each magnification.
	 * set at runtime initialisation.
	 * */
	private double 	zoomFactor 	= -1;
	
	/*
	 * the point to which we will zoom.
	 * Will be (double,double) and scaled by zoomFactor,
	 * */
	private Coordinate 	currentStartPoint 	= null;
	
	/*
	 * the currently calculated increments for X and Y.
	 * Initially this is 1.
	 * */
	private double incrementX = 1;
	private double incrementY = 1;
	
	/**
	 * @params int size (sets up a square viewport)<br />
	 * int zoomFactor: each zoom magnifies by this.
	 * */
	public GridScalingObject(int sizeX, int sizeY,double zoomFactor) throws GridScalingObjectException{

		
		logger.info("INIT: setting viewport dimensions to " + sizeX + ", " + sizeY);
		this.setDisplayDimensions(sizeX,sizeY);
		
		logger.info("INIT: setting zoom factor to " + zoomFactor);
		this.setZoomFactor(zoomFactor);
		
		logger.info("INIT: setting initial zoom point to top left of viewport (0,0)");
		this.setInitialStartPoint(new Coordinate(0.0,0.0));
	}

	@Override
	/*
	 * the x,y size of the on-screen display. This determines the 
	 * number of iterations in the x and the y plane.
	 * */
	public void setDisplayDimensions(int sizeX, int sizeY) throws GridScalingObjectException {
		this.dimension = new Dimension(sizeX,sizeY);
	}
	
	@Override
	public Dimension getDisplayDimensions() throws GridScalingObjectException {
		return this.dimension;
	}
	
	
	/**
	 * This is the runtime constant that defines by how much the view is magnified.
	 * */
	@Override
	public void setZoomFactor(double zoomFactor) throws GridScalingObjectException {
		this.zoomFactor = zoomFactor;
	}
	
	@Override
	public double getZoomFactor() throws GridScalingObjectException {
		return this.zoomFactor;
	}
	
	/*
	 * Accepts a Point (the centre of the area to zoom to).
	 * This method determines the current PORTAL top-left
	 * Double co-ords using zoom factor, supplied start point and the 
	 * current x,y offset 
	 * */
	@Override
	public void setCurrentStartPoint(Coordinate zoomPoint) throws GridScalingObjectException {
		this.setCurrentZoomFactor();	//increment the zoom factor
		logger.info(" -> current zoom: " + this.currentZoomFactor);
		
		this.setCurrentIncrementX();
		this.setCurrentIncrementY();
		logger.info(" -> Increment x,y: " + this.getCurrentIncrementX() + ", " + this.getCurrentIncrementY());
		
		/*
		 * offset CURRENT startpoint
		 * */
		//work out x,y co-ords of top left corner:
		double newX = 0;
		double newY = 0;

		logger.info(" -> current startpoint: " + this.getCurrentStartPoint().getX() + ","+this.getCurrentStartPoint().getY());
	
		/*
		 * OK lets try the new version...
		 * */
		newX = this.getCurrentStartPoint().getX() + ((zoomPoint.getX()/this.previousZoomFactor) - (this.dimension.width/(2 * this.getCurrentZoomFactor())));
		newY = this.getCurrentStartPoint().getY() + ((zoomPoint.getY()/this.previousZoomFactor) - (this.dimension.height/(2 * this.getCurrentZoomFactor())));		
		
		//we dont want to go outside the bounds
		
		if(newX < 0) 						newX = 0;
		if(newY < 0) 						newY = 0;
		//if(newX > this.dimension.width) 	newX = this.dimension.width-(this.dimension.width/this.currentZoomFactor);
		//if(newY > this.dimension.height) 	newY = this.dimension.height-(this.dimension.height/this.currentZoomFactor);
		
		logger.info(" -> setting start point: " + newX + ", " + newY);
		
		this.currentStartPoint = new Coordinate((double)newX,(double)newY);
		logger.info(" -> retrieve start point: " + this.getCurrentStartPoint());
		logger.info(" -> passed zoompoint to offset: " + zoomPoint);
	}
	
	@Override
	public Coordinate getCurrentStartPoint() throws GridScalingObjectException {
		return this.currentStartPoint;
	}
	
	/*
	 * set/get the current zoom factor. This is modified at runtime and is 
	 * current zoom * zoomfactor
	 * */
	@Override
	public void setCurrentZoomFactor() throws GridScalingObjectException {
		this.previousZoomFactor = this.currentZoomFactor;
		this.currentZoomFactor = this.currentZoomFactor * this.zoomFactor;
		
	}
	
	@Override
	public double getCurrentZoomFactor() throws GridScalingObjectException {
		return this.currentZoomFactor;
	}

	@Override
	public double getCurrentIncrementX() throws GridScalingObjectException {
		return this.incrementX;
	}

	@Override
	public double getCurrentIncrementY() throws GridScalingObjectException {
		return this.incrementY;
	}

	@Override
	public void setCurrentIncrementX() throws GridScalingObjectException {
		this.incrementX = (double)1 / this.currentZoomFactor;
	}

	@Override
	public void setCurrentIncrementY() throws GridScalingObjectException {
		this.incrementY = (double)1 / this.currentZoomFactor;
	}
	
	/*
	 * non-interface methods
	 * */
	private void setInitialStartPoint(Coordinate zoomPoint) throws GridScalingObjectException {
		this.currentStartPoint = zoomPoint;
	}
	
	public double getScaledX(double in) throws GridScalingObjectException {

		double out = in - ((double)this.getDisplayDimensions().width/2);
		out = out * 4/(this.getDisplayDimensions().width);
		return out;
	}
	public double getScaledY(double in) throws GridScalingObjectException {

		double out = in - ((double)this.getDisplayDimensions().height/2);
		out = out * 4/(this.getDisplayDimensions().height);
		return out;
	}

	/*
	 * hmm...
	 * */
	@Override
	public void reset() throws GridScalingObjectException {
		this.setCurrentStartPoint(new Coordinate(0,0));
		this.currentZoomFactor = 1;
		this.previousZoomFactor = 1;
		this.incrementX = 1;
		this.incrementY = 1;
	}
	
}
