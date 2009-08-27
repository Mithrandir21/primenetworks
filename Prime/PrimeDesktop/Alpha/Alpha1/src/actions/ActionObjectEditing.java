/**
 * 
 */
package actions;


import graphics.GUI.standardObjectEdit.StandardObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ActionObjectEditing extends AbstractAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a Icon.
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
		new StandardObjects();
	}

}
