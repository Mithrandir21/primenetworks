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
public class ActionSave extends AbstractAction
{
	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionSave(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Save action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionSave(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Save action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
	}


	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "You want to perform a \"Save\" action.");
	}


}
