package actions.systemActions;


import graphics.PrimeMain;
import graphics.GUI.customOSviews.osSelectionOverView;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


public class ActionOpenCustomOS extends AbstractSystemAction
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
	public ActionOpenCustomOS(String text, ImageIcon icon)
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
	public ActionOpenCustomOS(String text)
	{
		super(text);
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if ( PrimeMain.osSelect != null )
		{
			PrimeMain.osSelect.toFront();
		}
		else
		{
			PrimeMain.osSelect = new osSelectionOverView();
		}
	}
}
