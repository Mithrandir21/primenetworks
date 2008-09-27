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
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ActionPaste extends AbstractAction
{
	/**
	 * 
	 * Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionPaste(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Paste action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_V));
	}


	/**
	 * 
	 * Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionPaste(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Paste action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_V));
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "You want to perform a \"Paste\" action.");
	}

}
