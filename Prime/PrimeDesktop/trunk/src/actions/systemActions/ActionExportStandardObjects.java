package actions.systemActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;


/**
 * This action is used when the user wants to export the standard objects list
 * to a file. It will call the exportStandardObjects function in the {@link DesktopFileManagment} class. This function is not
 * undoable.
 * 
 * @author Bahram Malaekeh
 */
public class ActionExportStandardObjects extends AbstractSystemAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionExportStandardObjects(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportStandardObjectsText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionExportStandardObjects(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportStandardObjectsText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if ( DesktopFileManagment.exportStandardObjects() )
		{
			// Tells the user that the export was successful
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("exportObjectsToFileSuccess"));
		}
	}

}
