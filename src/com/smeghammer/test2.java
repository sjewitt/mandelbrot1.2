package com.smeghammer;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.smeghammer.fractal.CheckPointRange;
import com.smeghammer.fractal.CheckPointRangeException;
import com.smeghammer.fractal.GridScalingObject;
import com.smeghammer.fractal.GridScalingObjectException;
import com.smeghammer.fractal.ICheckPointRange;
import com.smeghammer.fractal.IGridScalingObject;
import com.smeghammer.fractal.TendsToInfinityException;
import com.smeghammer.fractal.mandelbrot.TestPointMandelbrot;
import com.smeghammer.math.Coordinate;

public class test2 {
  public static void main(String[] args) {
    JFrame frame = new MandelbrotFrame();
    frame.setVisible(true);
  }
}



class MandelbrotFrame extends JFrame implements ActionListener{
  
	private static final long serialVersionUID = 1L;
	MandelbrotPanel mp;
	public MandelbrotFrame() {
    setTitle("Mandelbrot Test");
    setSize(1000, 1000);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    

    Container contentPane = getContentPane();
    /*
     * add the panel that does the funkyness:
     * */
    mp = new MandelbrotPanel();
    mp.init(this.getWidth(), this.getHeight());
    contentPane.add(mp, "Center");
    
    /*
     * add mouse listener (its self:)
     * */
    this.addMouseListener(mp);
    
    /*
     * add a menu item:
     * */
    MenuBar menu = new MenuBar();
    Menu action = new Menu("Action");
    MenuItem reset = new MenuItem("Reset");
    MenuItem exit = new MenuItem("Exit");
    reset.addActionListener(this);
    exit.addActionListener(this);
    
    action.add(reset);
    action.add(exit);
    
    menu.add(action);
    
    this.setMenuBar(menu);

  }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		if(arg0.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		if(arg0.getActionCommand().equals("Reset")){
			System.out.println("Resetting...");
			try{
				//this.mp.scale.reset();
			}
			catch(Exception ex){ //GridScalingObjectEx...
				//TODO: work out how to reset the fucking thing...
			}
		}
	}
}

class MandelbrotPanel extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	public IGridScalingObject scale;
	public ICheckPointRange engine;
	
	public void init(int w, int h){
		try{
			this.scale = new GridScalingObject(w,h,4);
			this.engine = new CheckPointRange(scale, new TestPointMandelbrot(255));
			this.engine.go();
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
			g.drawImage(engine.getBufferedImage(), 0, 0, null);
		}
		catch(CheckPointRangeException ex){}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			this.scale.setCurrentStartPoint(new Coordinate(arg0.getX(),arg0.getY()));
			this.engine.go();
			
			Graphics g = this.getGraphics();
			g.drawImage(engine.getBufferedImage(), 0, 0, null);
			this.repaint();
		}
		catch(GridScalingObjectException ex){}
		catch(CheckPointRangeException ex){}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}