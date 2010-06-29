/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a cut action that will place the currently
 * selected Widget, if any, from the current {@link WorkareaCanvas} into the
 * {@link PrimeMain PrimeMain1s} 'cutWidget' variable.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionCut extends AbstractSystemAction
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
	public ActionCut(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionCutDescriptionText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionCut(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionCutDescriptionText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{

		// Sets the copyWidget pointer to null
		PrimeMain.copyWidget = null;

		if ( PrimeMain.currentCanvas != null )
		{
			if ( PrimeMain.currentCanvas.getCurrentWidgetObject() != null )
			{
				// Sets the widget as the widget to be cut(copied and deleted)
				PrimeMain.cutWidget = PrimeMain.currentCanvas
						.getCurrentWidgetObject();
			}
			else
			{
				PrimeMain.cutWidget = null;
			}
		}
	}

}
