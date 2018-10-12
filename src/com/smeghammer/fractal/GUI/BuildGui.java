package com.smeghammer.fractal.GUI;

import com.smeghammer.fractal.GUI.listeners.CloseEventListener;
import com.smeghammer.fractal.GUI.listeners.ResetEventListener;
import com.smeghammer.fractal.GUI.listeners.SaveEventListener;
import com.smeghammer.fractal.GUI.listeners.ZoomEventListener;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BuildGui extends JFrame {
	
	/*
	 * build UI:
	 * */
	private static final long serialVersionUID = 4940123192891853046L;
	
	private Properties props;

	public BuildGui()
	{
		/*
		 * portal dimensions. Other dimensions are based on this.
		 * */
		int portalWidth 				= 0;
		int portalHeight 				= 0;
		
		final int RIGHT_GUTTER_WIDTH 	= 220;
		final int BORDER 				= 10;
		
		this.props = new Properties();
		
		try{
			this.props.load(new FileInputStream(new File("../conf/settings.properties")));
			
			/*
			 * set primary image w/h:
			 * */
			portalWidth 	= Integer.parseInt(this.props.getProperty("SIZE"));
			portalHeight 	= Integer.parseInt(this.props.getProperty("SIZE"));
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		this.setSize(portalWidth + RIGHT_GUTTER_WIDTH,portalHeight + BORDER + 60);
		this.setTitle(this.props.getProperty("TITLE"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Build simple controls:
		 * */
		//container:
		final JPanel p = new JPanel();
		
		//we don't want the default FLOWLAYOUT: (if this was not set null, the positioning does not work)
		p.setLayout(null);
		
		//add panel to jframe:
		this.getContentPane().add(p);
		
		/*
		 * build labels for zooming info:
		 * */
		final JLabel lMag 	= new JLabel("Mag: 1x");
		lMag.setBounds(portalWidth + 20, 10, 200, 30);
		lMag.setVisible(true);
		
		final JLabel lZoom 	= new JLabel("View centred on:");
		lZoom.setBounds(portalWidth + 20, 40, 180, 30);
		lZoom.setVisible(true);
		final JLabel lZoomX 	= new JLabel("X");
		lZoomX.setBounds(portalWidth + 40, 60, 180, 30);
		lZoomX.setVisible(true);
		final JLabel lZoomY 	= new JLabel("Y");
		lZoomY.setBounds(portalWidth + 40, 80, 180, 30);
		lZoomY.setVisible(true);
		
		/*
		 * add the panel that does the funkiness:
		 * 
		 */
		FractalDisplayPanel displayPanel = new FractalDisplayPanel(this.props);
		displayPanel.setBounds(BORDER, BORDER, portalWidth, portalHeight);
		displayPanel.setBorder(new LineBorder(Color.RED));			
		
		/*
		 * build button:
		 */
		final JButton reset = new JButton("Reset");
		reset.setBounds(portalWidth + 20, 120, 80, 30);
		reset.addActionListener(new ResetEventListener("Reset",displayPanel,lMag,lZoomX,lZoomY));
		
		final JButton exit = new JButton("Close");
		exit.addActionListener(new CloseEventListener("Close"));
		exit.setBounds(portalWidth + 120, 120, 80, 30);


		
		/*
		 * add mouse listener
		 * */
		displayPanel.addMouseListener(new ZoomEventListener(displayPanel,lMag,lZoomX,lZoomY));
		
		//p.add(mp);
		p.add(displayPanel);
		p.add(reset);
		p.add(exit);
		p.add(lMag);
		p.add(lZoom);
		p.add(lZoomX);
		p.add(lZoomY);
		
		
		/*
		 * add a menu item:
		 * */
		JMenuBar menu 	= new JMenuBar();
		JMenu action 	= new JMenu("Action");
		JMenuItem action_reset = new JMenuItem("Reset");
		JMenuItem action_exit 	= new JMenuItem("Exit");
		JMenuItem action_save 	= new JMenuItem("Save image...");
		
		action_reset.addActionListener(new ResetEventListener("Reset",displayPanel,lMag,lZoomX,lZoomY));
		action_save.addActionListener(new SaveEventListener("Save image...",displayPanel));
		action_exit.addActionListener(new CloseEventListener("Exit"));
		
		action.add(action_reset);
		action.add(action_save);
		action.add(action_exit);
		
		menu.add(action);
		
		this.setJMenuBar(menu);
	}
}
