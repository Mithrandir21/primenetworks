/**
 * 
 */
package actions.toolbar;


import graphics.PrimeMain1;
import graphics.GUI.messageArea.MessageTabbed;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * An action class that will open the Connection Messages Panel.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionConnectionMessage extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionConnectionMessage(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This action opens the Connection messages.");
	}


	/**
	 * A constructor for the class that takes a string which will be the name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionConnectionMessage(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This action opens the Connection messages.");
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

		msgTabs.createConnectionMessagePanel();
	}

}
