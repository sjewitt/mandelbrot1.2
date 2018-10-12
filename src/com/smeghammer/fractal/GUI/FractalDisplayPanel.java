package com.smeghammer.fractal.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.swing.JPanel;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.ICheckPointRange;
import com.smeghammer.fractal.IGridScalingObject;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;

public class FractalDisplayPanel extends JPanel implements IClickablePanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage imagedata;
	
	/*
	 * properties passed from constructor
	 * */
	public Properties props;

	public IGridScalingObject scale;
	
	public ICheckPointRange engine;	
	/*
	 * initialise with properties from containing frame:
	 * */
	public FractalDisplayPanel(Properties props){
		
		this.props = props;
		
		try{
			this.scale 	= new GridScalingObject(
				Integer.parseInt(this.props.getProperty("SIZE")),
				Integer.parseInt(this.props.getProperty("SIZE")),
				Integer.parseInt(this.props.getProperty("ZOOM"))
				);
			this.engine = new CheckPointRange(scale, new TestPointMandelbrot(255));
			this.engine.go();
			this.setImageData(this.engine.getBufferedImage());
		}
		catch(CheckPointRangeException ex){}
		catch(GridScalingObjectException ex){}
		catch(TendsToInfinityException ex){}
	}
	
	/*
	 * don't get this - need to play with it.
	 * */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try{
			g.drawImage(this.engine.getBufferedImage(), 0, 0, null);
		}
		catch(CheckPointRangeException ex){}
	}

	@Override
	public BufferedImage getImageData(){
		return this.imagedata;
	}
	
	@Override
	public void setImageData(BufferedImage data){
		this.imagedata = data;
	}
	
	
	

}
