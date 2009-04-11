/**
 * 
 */
package actions;

import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ActionUpdate extends AbstractAction
{
	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionUpdate(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Update action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R));
	}

	
	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionUpdate(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Update action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R));
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		PrimeMain1.updateCanvasAndObjectInfo();
	}

}
