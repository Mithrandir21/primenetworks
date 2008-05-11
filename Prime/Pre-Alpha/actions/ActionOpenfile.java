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
public class ActionOpenfile extends AbstractAction
{
	/**
	 * 
	 * Description NEEDED!
	 *
	 * @param text
	 * @param icon
	 */
	public ActionOpenfile(String text, ImageIcon icon) 
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Open File action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
	}

	public void actionPerformed(ActionEvent e)
	{
		
		JOptionPane.showMessageDialog(null,"You want to perform a \"Open File\" action.");
	}

}
