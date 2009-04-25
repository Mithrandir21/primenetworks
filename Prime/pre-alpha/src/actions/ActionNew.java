/**
 * 
 */
package actions;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import managment.FileManagment;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ActionNew extends AbstractAction
{
	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionNew(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "Creates a new Network");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionNew(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "Creates a new Network");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		new graphics.GUI.workareaCanvas.CreateNewWorkareaCanvas();
	}

}
