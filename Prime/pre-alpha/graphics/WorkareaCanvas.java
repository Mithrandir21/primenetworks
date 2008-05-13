/**
 * 
 */
package graphics;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends Canvas implements MouseListener, MouseMotionListener
{
	public WorkareaCanvas()
	{
		setBackground(Color.black);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		
		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		BufferStrategy strategy = getBufferStrategy();
		
		
        // Get hold of a graphics context for the accelerated 
        // surface and blank it out
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,800,600);

        // finally, we've completed drawing so clear up the graphics
        // and flip the buffer over
        g.dispose();
        strategy.show();
	}
	
	
	private void setup()
	{
		
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
	

}
