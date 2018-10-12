package com.smeghammer.fractal;

import java.awt.image.BufferedImage;

/*
 * defines an engine for checking a range of points.
 * */
public interface ICheckPointRange {
	public void go() throws CheckPointRangeException;
	public BufferedImage getBufferedImage() throws CheckPointRangeException;
}
