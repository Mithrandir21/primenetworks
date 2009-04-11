package actions.toolbar;

import graphics.PrimeMain1;
import graphics.GUI.messageArea.MessageTabbed;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ActionAllMessageView extends AbstractAction
{
	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionAllMessageView(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This action opens all the message views.");
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionAllMessageView(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This action opens all the message views.");
	}
	
	
	
	
	/* (non-Javadoc)
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
