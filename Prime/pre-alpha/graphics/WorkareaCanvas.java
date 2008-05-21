/**
 * 
 */
package graphics;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.Border;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends Canvas //implements MouseListener, MouseMotionListener
{
	public WorkareaCanvas()
	{
		this.setMaximumSize(new Dimension(50,50));
	}
	
	public void paint(Graphics g)
    {
		// = this.getGraphics();
		
		Image t = createImageIcon("images/buttonIcons/cut.jpg").getImage();
		
		
		//Graphics2D g2d = ( Graphics2D ) g;
		
		
		//this.setBackground(Color.black);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
		g.drawImage(t,50,50,null);
    }
	
	


	// Handles the event of the user pressing down the mouse button.
	public void mousePressed(MouseEvent e)
	{
		
	}

	// Handles the event of a user dragging the mouse while holding down the
	// mouse button.
	public void mouseDragged(MouseEvent e)
	{

	}

	// Handles the event of a user releasing the mouse button.
	public void mouseReleased(MouseEvent e)
	{
		
	}

	// This method required by MouseListener.
	public void mouseMoved(MouseEvent e)
	{
		
	}

	// These methods are required by MouseMotionListener.
	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e)
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
