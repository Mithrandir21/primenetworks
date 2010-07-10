/**
 * 
 */
package actions.systemActions;


import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionImportStandardRules extends AbstractSystemAction
{

	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionImportStandardRules(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionImportStandardRules(String text)
	{
		super(text);
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		DesktopFileManagment.importStandardRules();
	}

}
