package com.smeghammer.fractal.GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * listen for close events from menu and window.
 * */
public class CloseEventListener implements ActionListener,WindowListener {
	
	/*
	 * supply the expected item command string
	 * */
	public CloseEventListener(String actionCommand){
		this.EVENT_FLAG = actionCommand;
	}
	
	/*
	 * listen for item 'Exit'
	 * */
	private String EVENT_FLAG;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(this.EVENT_FLAG)){
			System.out.println("[actionevent] exiting...");
			System.exit(0);
		}
	}
	
	/*
	 * listen for window being closed
	 * */
	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("[windowevent] exiting...");
		System.exit(0);
	}
	
	/*
	 * don't need these
	 * */	
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}
}
