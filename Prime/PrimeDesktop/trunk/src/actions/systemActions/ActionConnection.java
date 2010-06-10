package actions.systemActions;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.menues.GenericPrimeToolbar;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.Settings;


/**
 * Toggles the "create connections" button.
 * 
 * @author Bahram Malaekeh
 */
public class ActionConnection extends AbstractSystemAction
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
	public ActionConnection(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionToggleCreateConnectionButtonDescriptionText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionConnection(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionToggleCreateConnectionButtonDescriptionText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Settings.connectionToggle = !(Settings.connectionToggle);

		// If the connection toggle button is now true
		if ( Settings.connectionToggle )
		{
			Settings.roomsManipulation = false;
			GenericPrimeToolbar.untoggleAllOthersButtons(this.getClass());
		}

		GraphicalFunctions.JPopupMenuesToggle();
	}
}
