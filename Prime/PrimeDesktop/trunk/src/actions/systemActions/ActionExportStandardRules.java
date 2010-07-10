/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionExportStandardRules extends AbstractSystemAction
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
	public ActionExportStandardRules(String text, ImageIcon icon)
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
	public ActionExportStandardRules(String text)
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

		if ( DesktopFileManagment.exportStandardRules() )
		{
			// Tells the user that the export was successful
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("exportRulesToFileSuccess"));
		}
	}

}
