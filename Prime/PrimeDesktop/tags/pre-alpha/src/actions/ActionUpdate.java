/**
 * 
 */
package actions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * An action class that will perform a Update action. This action is used when the system wished to update the processed
 * information about the {@link WorkareaCanvas WorkareaCanvases}.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionUpdate extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionUpdate(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Update action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionUpdate(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Update action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R));
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		PrimeMain1.updateCanvasAndObjectInfo();
	}

}
