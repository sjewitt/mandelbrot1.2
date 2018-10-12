package com.smeghammer.fractal.GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.GUI.FractalDisplayPanel;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;

/*
 * reset the fractal display to initial conditions
 * */
public class ResetEventListener implements ActionListener{
	
	/*
	 * listen for item 'Exit'
	 * */
	private String EVENT_FLAG;
	private FractalDisplayPanel displayPanel;
	private JLabel lMag;
	private JLabel lZoomX;
	private JLabel lZoomY;
	
	public ResetEventListener(String actionCommand,FractalDisplayPanel displayPanel, JLabel lMag, JLabel lZoomX, JLabel lZoomY){
		
		this.EVENT_FLAG = actionCommand;
		this.displayPanel = displayPanel;
		this.lMag = lMag;
		this.lZoomX = lZoomX;
		this.lZoomY = lZoomY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("reset");
		if(e.getActionCommand().equals(this.EVENT_FLAG)){
			/*
			 * rebuild the display to initial defaults:
			 * */		
			try{
				displayPanel.scale 	= new GridScalingObject(
					Integer.parseInt(displayPanel.props.getProperty("SIZE")),
					Integer.parseInt(displayPanel.props.getProperty("SIZE")),
					Integer.parseInt(displayPanel.props.getProperty("ZOOM"))
					);
				displayPanel.engine = new CheckPointRange(displayPanel.scale, new TestPointMandelbrot(255));
				displayPanel.engine.go();
				
				/*
				 * redraw it:
				 * */
				displayPanel.repaint();
				
				/*
				 * Reset the labels:
				 * */
				this.lMag.setText("Mag: x1");
				this.lZoomX.setText("");
				this.lZoomY.setText("");
			}
			catch(CheckPointRangeException ex){}
			catch(GridScalingObjectException ex){}
			catch(TendsToInfinityException ex){}
		}
	}
}
