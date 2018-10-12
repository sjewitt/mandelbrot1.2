package com.smeghammer.fractal.GUI;

import java.awt.Container;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.ICheckPointRange;
import com.smeghammer.fractal.IGridScalingObject;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.GUI.listeners.CloseEventListener;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;

public class ViewPortal {
  public static void main(String[] args) {
    JFrame frame = new MandelbrotFrame();
    frame.setVisible(true);
  }
}



class MandelbrotFrame extends JFrame{
  
	private static final long serialVersionUID = 1L;
	
	private MandelbrotPanel mp;
	
	private Properties props;
	
	public MandelbrotFrame() {
		
		/*
		 * add window close handler:
		 * */
		this.addWindowListener(new CloseEventListener("Exit"));
		
		/*
		 * get the container to add components to:
		 * */
		Container contentPane = this.getContentPane();
		
		this.props = new Properties();
		try
		{
			this.props.load(new FileInputStream(new File("../conf/settings.properties")));
			this.setTitle(this.props.getProperty("TITLE"));
			this.setSize(
					Integer.parseInt(this.props.getProperty("SIZE")), 
					Integer.parseInt(this.props.getProperty("SIZE")));
		}
		catch(FileNotFoundException ex){}
		catch(IOException ex){}
		
		/*
		 * add the panel that does the funkyness:
		 * */
		mp = new MandelbrotPanel(this.getWidth(), this.getHeight());

		/*
		 * add the mandelbrot panel:
		 * */
		contentPane.add(mp, "Center");

		/*
		 * add mouse listener
		 * */
		this.addMouseListener(new ZoomListenerOld(mp));
		
		
		/*
		 * add a menu item:
		 * */
		JMenuBar menu 	= new JMenuBar();
		JMenu action 	= new JMenu("Action");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit 	= new JMenuItem("Exit");
		JMenuItem save 	= new JMenuItem("Save image...");
		
		/*
		 * TODO: add a reset handler.
		 * */
		reset.addActionListener(new CloseEventListener("Exit"));
		
		exit.addActionListener(new CloseEventListener("Exit"));
		
		action.add(reset);
		action.add(save);
		action.add(exit);
		
		menu.add(action);
		
		this.setJMenuBar(menu);
	}
}

class MandelbrotPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Properties props;
	
	public IGridScalingObject scale;
	
	public ICheckPointRange engine;
	
	/*
	 * constructor:
	 * */
	public MandelbrotPanel(int w, int h){
		
		this.props = new Properties();
		
		try{
			this.props.load(new FileInputStream(new File("../conf/settings.properties")));
			this.scale 	= new GridScalingObject(w,h,Double.parseDouble(this.props.getProperty("ZOOM")));
			this.engine = new CheckPointRange(scale, new TestPointMandelbrot(255));
			this.engine.go();
		}
		catch(CheckPointRangeException ex){}
		catch(GridScalingObjectException ex){}
		catch(TendsToInfinityException ex){}
		catch(FileNotFoundException ex){}
		catch(IOException ex){}
	}
	
	/*
	 * don't get this - need to play with it.
	 * */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try{
			g.drawImage(engine.getBufferedImage(), 0, 0, null);
		}
		catch(CheckPointRangeException ex){}
	}
}