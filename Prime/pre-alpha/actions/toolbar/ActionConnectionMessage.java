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
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ActionConnectionMessage extends AbstractAction
{
	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionConnectionMessage(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This action opens the Connection messages.");
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionConnectionMessage(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This action opens the Connection messages.");
	}



	/* (non-Javadoc)
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