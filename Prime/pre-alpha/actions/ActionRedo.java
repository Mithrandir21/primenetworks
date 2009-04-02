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
public class ActionRedo extends AbstractAction
{
	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionRedo(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Redo action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
	}


	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "You want to perform a \"Redo\" action.");
	}

}
