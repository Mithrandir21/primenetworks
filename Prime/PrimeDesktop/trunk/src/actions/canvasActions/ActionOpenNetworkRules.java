/**
 * 
 */
package actions.canvasActions;


import graphics.PrimeMain;
import graphics.GUI.customNetworks.NetworkRulesFrame;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionOpenNetworkRules extends AbstractSystemAction
{

	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionOpenNetworkRules(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionOpenNetworkRules(String text)
	{
		super(text);
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( PrimeMain.currentCanvas != null )
		{
			// Creates and places a new StandardObjects JFrame into the system main
			// registry.

			if ( PrimeMain.rulesFrame == null )
			{
				PrimeMain.rulesFrame = new NetworkRulesFrame(
						PrimeMain.currentCanvas);
			}
			else
			{
				PrimeMain.rulesFrame.toFront();
			}

		}
	}

}
