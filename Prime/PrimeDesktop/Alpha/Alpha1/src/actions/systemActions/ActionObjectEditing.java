/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain1;
import graphics.GUI.standardObjectEdit.StandardObjects;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ActionObjectEditing extends AbstractSystemAction
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
	public ActionObjectEditing(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "Edit Standard Objects");
	}



	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// Creates and places a new StandardObjects JFrame into the system main
		// registry.

		if ( PrimeMain1.stdObjView == null )
		{
			PrimeMain1.stdObjView = new StandardObjects();
		}
		else
		{
			PrimeMain1.stdObjView.toFront();
		}
	}

}
