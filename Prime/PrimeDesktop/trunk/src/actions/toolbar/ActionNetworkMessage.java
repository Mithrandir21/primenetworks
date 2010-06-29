/**
 * 
 */
package actions.toolbar;


import graphics.PrimeMain;
import graphics.GUI.messageArea.MessageTabbed;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * An action class that will open the Network Messages Panel.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionNetworkMessage extends AbstractSystemAction
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
	public ActionNetworkMessage(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionActionOpenNetworkMessageDescriptionText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionNetworkMessage(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionActionOpenNetworkMessageDescriptionText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Gets the MessageTabbed that contains the tabs
		MessageTabbed msgTabs = (MessageTabbed) PrimeMain.messagesPanel
				.getComponent(0);

		msgTabs.createNetworkMessagePanel();
	}

}
