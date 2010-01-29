package actions.toolbar;


import graphics.PrimeMain1;
import graphics.GUI.messageArea.MessageTabbed;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * An action class that will open all the Message Panel.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionAllMessageView extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionAllMessageView(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This action opens all the message views.");
	}


	/**
	 * A constructor for the class that takes a string which will be the name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionAllMessageView(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This action opens all the message views.");
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Gets the MessageTabbed that contains the tabs
		MessageTabbed msgTabs = (MessageTabbed) PrimeMain1.messagesPanel.getComponent(0);

		msgTabs.createNetworkMessagePanel();

		msgTabs.createConnectionMessagePanel();

		msgTabs.createSoftwareMessagePanel();

		msgTabs.createHardwareMessagePanel();
	}

}
