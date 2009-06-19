/**
 * 
 */
package actions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import managment.DesktopFileManagment;


/**
 * An action class that will perform a Save action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionSave extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionSave(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Save action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionSave(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Save action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if ( PrimeMain1.currentCanvas != null )
		{
			// Saves the current canvas
			DesktopFileManagment.saveWorkareaCanvas(PrimeMain1.currentCanvas);
		}
	}
}
