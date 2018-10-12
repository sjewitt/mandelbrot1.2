package com.smeghammer;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.ICheckPointRange;
import com.smeghammer.fractal.IGridScalingObject;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;

public class test4 {
  public static void main(String[] args) {
    JFrame frame = new MxFrame();
    frame.setVisible(true);
  }
}



class MxFrame extends JFrame{
  
	private static final long serialVersionUID = 1L;
	
	private MPanel mp;
	
	public MxFrame() {
		
		/*
		 * get the container to add components to:
		 * */
		Container contentPane = this.getContentPane();
		this.setSize(400,400);
		this.setTitle("TESTING...");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * add the panel that does the funkyness:
		 * */
		mp = new MPanel(200, 200);		
		JPanel outer = new JPanel();
		outer.setLayout(null);
		outer.setBorder(new LineBorder(Color.BLACK));
		outer.setBounds(5, 5, 320, 220);
		outer.setVisible(true);
		outer.add(mp);
		


		/*
		 * add the mandelbrot panel:
		 * */
		//contentPane.add(mp, "Center");
		contentPane.add(outer, "Center");


		
		
		/*
		 * add a menu item:
		 * */
		JMenuBar menu 	= new JMenuBar();
		JMenu action 	= new JMenu("Action");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit 	= new JMenuItem("Exit");
		JMenuItem save 	= new JMenuItem("Save image...");
		

		
		action.add(reset);
		action.add(save);
		action.add(exit);
		
		menu.add(action);
		
		//this.setJMenuBar(menu);
	}
}

class MPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Properties props;
	
	public IGridScalingObject scale;
	
	public ICheckPointRange engine;
	
	/*
	 * constructor:
	 * */
	public MPanel(int w, int h){
		
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
