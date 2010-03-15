/**
 * 
 */
package actions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a copy action that will place the currently
 * selected Widget, if any, from the current {@link WorkareaCanvas} into the
 * {@link PrimeMain1 PrimeMain1s} 'copyWidget' variable.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionCopy extends AbstractSystemAction
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
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionCopyDescriptionText"));
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
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionCopyDescriptionText"));
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
