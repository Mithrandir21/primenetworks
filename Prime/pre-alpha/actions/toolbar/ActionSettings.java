/**
 * 
 */
package actions.toolbar;


import graphics.GUI.settings.SettingsOverview;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionSettings extends AbstractAction
{

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public ActionSettings(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Open Settings action");
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param text
	 */
	public ActionSettings(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Open Settings action");
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		// JOptionPane.showMessageDialog(null, "You want to perform a \"Open Settings\" action.");
		new SettingsOverview();

	}

}
