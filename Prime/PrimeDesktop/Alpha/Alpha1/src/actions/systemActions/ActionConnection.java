package actions.systemActions;


import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.Settings;


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
		putValue(SHORT_DESCRIPTION,
				"This action allows the creation of Connections.");
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
		putValue(SHORT_DESCRIPTION,
				"This action allows the creation of Connections.");
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
	}
}
