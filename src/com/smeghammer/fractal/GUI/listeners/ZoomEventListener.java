package com.smeghammer.fractal.GUI.listeners;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.GUI.FractalDisplayPanel;
import com.smeghammer.math.Coordinate;

public class ZoomEventListener implements MouseListener,MouseMotionListener{

	private FractalDisplayPanel portal;
	private JLabel lMag;
	private JLabel lZoomX;
	private JLabel lZoomY;
	
	public ZoomEventListener(FractalDisplayPanel portal,JLabel lMag, JLabel lZoomX,JLabel lZoomY){
		super();
		this.portal 	= portal;
		this.lMag 		= lMag;
		this.lZoomX 	= lZoomX;
		this.lZoomY 	= lZoomY;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			this.portal.scale.setCurrentStartPoint(new Coordinate(arg0.getX(),arg0.getY()));
			this.portal.engine.go();
			this.lMag.setText("Mag: " + this.portal.scale.getCurrentZoomFactor() + "x");
			this.lZoomX.setText(Double.toString(this.portal.scale.getCurrentStartPoint().getX()));
			this.lZoomY.setText(Double.toString(this.portal.scale.getCurrentStartPoint().getY()));
			
			Graphics g = this.portal.getGraphics();
			g.drawImage(this.portal.engine.getBufferedImage(), 0, 0, null);
			this.portal.repaint();
			this.portal.setImageData(this.portal.engine.getBufferedImage());
		}
		catch(GridScalingObjectException ex){}
		catch(CheckPointRangeException ex){}
	}

	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("Mouse entered: " + arg0.getX() + ","+arg0.getY());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse exited: " + arg0.getX() + ","+arg0.getY());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse pressed: " + arg0.getX() + ","+arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse released: " + arg0.getX() + ","+arg0.getY());
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		System.out.println(arg0.getX() + "," + arg0.getY());
		
	}

}
