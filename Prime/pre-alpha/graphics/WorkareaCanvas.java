/**
 * 
 */
package graphics;


import java.awt.*;
import java.awt.event.*;


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
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
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
