package com.smeghammer.fractal;

/*
 * interface defining a check for a point tending to
 * DIVERGE to infinity (ie point x,y is/is not within)
 * the set.
 * 
 * The number of iterations determines the colour of the point.
 * */
public interface ITendsToInfinity {
	public void testPoints(double x, double y) throws TendsToInfinityException;
	public void setMaxIterations(int maxIterations) throws TendsToInfinityException;
	public int getIterations() throws TendsToInfinityException;
}
