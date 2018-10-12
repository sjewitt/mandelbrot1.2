package com.smeghammer.fractal.GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import com.smeghammer.fractal.GUI.ClickablePanelException;
import com.smeghammer.fractal.GUI.IClickablePanel;

public class SaveEventListener implements ActionListener {

	private String EVENT_FLAG;
	private IClickablePanel fractalDisplayPanel;
	
	public SaveEventListener(String actionCommand, IClickablePanel fractalDisplayPanel){
		this.EVENT_FLAG = actionCommand;
		this.fractalDisplayPanel = fractalDisplayPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		System.out.println(this.EVENT_FLAG);
		if(arg0.getActionCommand().equals(this.EVENT_FLAG))
		{
			try
			{
				Timestamp t = new Timestamp((new GregorianCalendar()).getTimeInMillis());
				
				ImageIO.write(
						this.fractalDisplayPanel.getImageData(), 
						"png", 
						new File("../output/output_" + t.getTime() + ".png"));
			}
			catch(ClickablePanelException ex){
				
			}
			catch(IOException ex){
				
			}
		}
	}
}
