/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;
import widgets.WorkareaCanvas;


/**
 * This action is used when the currently open {@link WorkareaCanvas} is to be
 * exported as a file. It will call the exportWorkareaCanvas function in the
 * {@link DesktopFileManagment} class and given the
 * current {@link WorkareaCanvas} in the {@link PrimeMain1} class as a variable.
 * 
 * @author Bahram Malaekeh
 */
public class ActionExport extends AbstractSystemAction
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
	public ActionExport(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionExportNetworkToImageText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionExport(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionExportNetworkToImageText"));
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		// If the current canvas is not null
		if ( PrimeMain1.currentCanvas != null )
		{
			DesktopFileManagment.exportWorkareaCanvas(PrimeMain1.currentCanvas);
		}
	}

}
