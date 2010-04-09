/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain1;
import graphics.GUI.visualObjectCustomization.VisualCustomFrame;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * This action opens, or brings to the front, the visual icon editing JDialog.
 * 
 * @author Bahram Malaekeh
 */
public class ActionOpenVisualEdit extends AbstractSystemAction
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
	public ActionOpenVisualEdit(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionAboutDescriptionText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionOpenVisualEdit(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionAboutDescriptionText"));
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( PrimeMain1.vcf == null )
		{
			PrimeMain1.vcf = new VisualCustomFrame();
		}
		else
		{
			PrimeMain1.vcf.toFront();
		}
	}

}