/**
 * 
 */
package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;


/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ActionNew extends AbstractAction
{
	/**
	 * 
	 * Description NEEDED!
	 *
	 * @param text
	 * @param icon
	 */
	public ActionNew(String text, ImageIcon icon) 
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a New action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
	}
	

	public void actionPerformed(ActionEvent e)
	{
		
		JOptionPane.showMessageDialog(null,"You want to perform a \"New\" action.");
	}

}