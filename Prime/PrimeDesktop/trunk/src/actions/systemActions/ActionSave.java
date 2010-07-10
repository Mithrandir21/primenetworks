/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package actions.systemActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a Save action that will save the current {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionSave extends AbstractSystemAction
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
	public ActionSave(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionSaveCurrentNetworkText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionSave(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionSaveCurrentNetworkText"));
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if ( PrimeMain.currentCanvas != null )
		{
			// Saves the current canvas
			DesktopFileManagment.saveWorkareaCanvas(PrimeMain.currentCanvas);
		}
	}
}
