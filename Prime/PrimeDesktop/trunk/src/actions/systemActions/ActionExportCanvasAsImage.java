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
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.DesktopFileManagment;
import widgets.WorkareaCanvas;


/**
 * This action is used when the currently open {@link WorkareaCanvas} is to be
 * exported as a file. It will call the exportWorkareaCanvasAsImage function in
 * the {@link DesktopFileManagment} class and given the current {@link WorkareaCanvas} in the {@link PrimeMain} class as a
 * variable.
 * 
 * @author Bahram Malaekeh
 */
public class ActionExportCanvasAsImage extends AbstractSystemAction
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
	public ActionExportCanvasAsImage(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportNetworkToImageText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionExportCanvasAsImage(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionExportNetworkToImageText"));
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		// If the current canvas is not null
		if ( PrimeMain.currentCanvas != null )
		{
			// IF the export is successful
			if ( DesktopFileManagment
					.exportWorkareaCanvasAsImage(PrimeMain.currentCanvas) )
			{
				// Tells the user that the export was successful
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("exportNetworkToImageSuccess"));
			}
		}
	}

}
