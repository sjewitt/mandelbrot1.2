package com.smeghammer.fractal;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2684049323546611045L;

	public void paintComponent(Graphics g, ICheckPointRange engine)
	{
	    super.paintComponent(g);
	    try
	    {
	    	g.drawImage(engine.getBufferedImage(), 0, 0, null);
	    }
	    catch(CheckPointRangeException ex)
	    {
	    	
	    }
	}
}
