package com.smeghammer.fractal.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.math.Coordinate;

public class ZoomListenerOld  implements MouseListener{

	private MandelbrotPanel portal;
	
	public ZoomListenerOld(MandelbrotPanel portal){
		super();
		this.portal = portal;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			this.portal.scale.setCurrentStartPoint(new Coordinate(arg0.getX(),arg0.getY()));
			this.portal.engine.go();
			
			Graphics g = this.portal.getGraphics();
			g.drawImage(this.portal.engine.getBufferedImage(), 0, 0, null);
			this.portal.repaint();
		}
		catch(GridScalingObjectException ex){}
		catch(CheckPointRangeException ex){}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
