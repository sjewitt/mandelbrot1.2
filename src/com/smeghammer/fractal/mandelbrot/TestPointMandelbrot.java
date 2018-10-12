package com.smeghammer.fractal.mandelbrot;

import com.smeghammer.fractal.ITendsToInfinity;
import com.smeghammer.fractal.TendsToInfinityException;

public class TestPointMandelbrot implements ITendsToInfinity {

	private double x 				= 0;
	private double y 				= 0;
	private int MAX_ITERATIONS 		= 16;	//default
	private int iterations 			= 0;
	
	/*
	 * bounding constants. The max unsigned range for point checking.
	 * There is no point in checking outside these values.
	 * */
	public static final int X_MAX = 2;
	public static final int Y_MAX = 2;
	public static final int X_MIN = -2;
	public static final int Y_MIN = -2;
	
	public TestPointMandelbrot() throws TendsToInfinityException{}
	
	/*
	 * initialise with max iterations
	 * */
	public TestPointMandelbrot(int maxIterations) throws TendsToInfinityException
	{
		this.setMaxIterations(maxIterations);
	}
	
	@Override
	public void testPoints(double x, double y) throws TendsToInfinityException {
		
		/*
		 * reset the test points and
		 * iteration count:
		 * 
		 * return -1 if tends to infinity.
		 * */
		
		this.x = 0.0;
		this.y = 0.0;
		this.iterations = 0;
		
		/*
		 * iterate until x,y > 2 or MAX_ITERATIONS
		 * is reached: 
		 * */
		do{
			double xnew = this.x * this.x - this.y * this.y + x;
			double ynew = 2 * this.x * this.y + y;
			this.x = xnew;
			this.y = ynew;
			this.iterations++;
			if(this.iterations == this.MAX_ITERATIONS){
				this.iterations = -1;
				break;
			}
		}
		while(this.x <= X_MAX && this.y <= Y_MAX);
	}

	@Override
	public void setMaxIterations(int maxIterations) throws TendsToInfinityException 
	{
		this.MAX_ITERATIONS = maxIterations;
	}

	@Override
	public int getIterations() throws TendsToInfinityException {
		return this.iterations;
	}
}
