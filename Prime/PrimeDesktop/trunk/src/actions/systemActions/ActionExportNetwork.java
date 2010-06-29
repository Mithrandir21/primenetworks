package actions.systemActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;


/**
 * This action exports a Network to a given .dat file. The action call
 * 
 * @author Bahram Malaekeh
 */
public class ActionExportNetwork extends AbstractSystemAction
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
	public ActionExportNetwork(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportNetworkText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionExportNetwork(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportNetworkText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if ( PrimeMain.currentCanvas != null )
		{
			if ( DesktopFileManagment.exportNetwork(PrimeMain.currentCanvas) )
			{
				// Tells the user that the export was successful
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("exportNetworkToFileSuccess"));
			}
		}
	}

}
