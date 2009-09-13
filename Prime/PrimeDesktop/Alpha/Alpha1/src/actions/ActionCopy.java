/**
 * 
 */
package actions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * An action class that will perform a copy action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionCopy extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionCopy(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Copy action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_C));
	}

	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionCopy(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Copy action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_C));
	}




	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		// Sets the copyWidget pointer to null
		PrimeMain1.cutWidget = null;

		if ( PrimeMain1.currentCanvas != null )
		{
			if ( PrimeMain1.currentCanvas.getCurrentWidgetObject() != null )
			{
				// Sets the widget as the widget to be copied
				PrimeMain1.copyWidget = PrimeMain1.currentCanvas
						.getCurrentWidgetObject();
			}
			else
			{
				PrimeMain1.copyWidget = null;
			}
		}
	}

}
