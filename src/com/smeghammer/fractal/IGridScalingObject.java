package com.smeghammer.fractal;

import java.awt.Dimension;

import com.smeghammer.math.Coordinate;

/*
 * retrieves and sets scaling for point calculations.
 * */
public interface IGridScalingObject {
	
	/*
	 * set up/get portal dimensions as a runtime constant
	 * */
	public void 		setDisplayDimensions(int sizeX, int sizeY) throws GridScalingObjectException;
	public Dimension 	getDisplayDimensions() throws GridScalingObjectException;
	
	/*
	 * set up/gets zoom factor as runtime constant
	 * */
	public void 		setZoomFactor(double zoomFactor) throws GridScalingObjectException;
	public double 		getZoomFactor() throws GridScalingObjectException;
	
	/*
	 * set top-left co-ordinates to begin iterations. This is re-set following
	 * each zoom. It is passed the Point defining the centre of the area to zoom,
	 * and calculates the top-left co-ords of the sub-portal based on the current
	 * scale factor and offset
	 * */
	public void 		setCurrentStartPoint(Coordinate zoomPoint) throws GridScalingObjectException;
	public Coordinate 		getCurrentStartPoint() throws GridScalingObjectException;
	
	/*
	 * get/set the currently calculated X,Y increment value:
	 * [currently working with a square, but this may change]
	 * */
	public void 		setCurrentIncrementX() 	throws GridScalingObjectException;
	public void 		setCurrentIncrementY() 	throws GridScalingObjectException;
	public double 		getCurrentIncrementX() 	throws GridScalingObjectException;
	public double 		getCurrentIncrementY() 	throws GridScalingObjectException;
	
	/*
	 * set.get the current zoom factor. It is simply zoom factor squared each time
	 * currently:
	 * */
	public void 		setCurrentZoomFactor() 	throws GridScalingObjectException;
	public double 		getCurrentZoomFactor() 	throws GridScalingObjectException;
	
	/*
	 * scale the supplied co-ordinates to +-2
	 * */
	public double		getScaledX(double xIn)	throws GridScalingObjectException;
	public double		getScaledY(double yIn)	throws GridScalingObjectException;
	
	/*
	 * reset to initial conditions:
	 * */
	public void 		reset()					throws GridScalingObjectException;
}
