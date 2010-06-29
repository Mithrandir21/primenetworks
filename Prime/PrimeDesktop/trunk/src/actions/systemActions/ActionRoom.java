/**
 * 
 */
package actions.systemActions;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.menues.GenericPrimeToolbar;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.Settings;
import widgets.WorkareaCanvas;


/**
 * This action toggles the 'room manipulation' setting in regards to JPopup
 * menues for {@link WorkareaCanvas} and click'n'drags on the canvas.
 * 
 * @author Bahram Malaekeh
 */
public class ActionRoom extends AbstractSystemAction
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
	public ActionRoom(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionToggleCreateRoomButtonText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionRoom(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionToggleCreateRoomButtonText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Settings.roomsManipulation = !(Settings.roomsManipulation);

		// If the room toggle button is now true
		if ( Settings.roomsManipulation )
		{
			Settings.connectionToggle = false;
			GenericPrimeToolbar.untoggleAllOthersButtons(this.getClass());
		}


		GraphicalFunctions.JPopupMenuesToggle();
	}

}
