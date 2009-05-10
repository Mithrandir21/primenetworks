/**
 * 
 */
package actions;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 * An action class that will perform a Save All action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionSaveAll extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionSaveAll(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Save All action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
	}



	/**
	 * A constructor for the class that takes a string which will be the name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionSaveAll(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Save All action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "You want to perform a \"Save All\" action.");
	}

}
