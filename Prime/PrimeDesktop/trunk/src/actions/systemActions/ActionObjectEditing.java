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
import graphics.GUI.standardObjectEdit.StandardObjects;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * Thei action opens, or brings to the front if open already, a {@link StandardObjects} JFrame that contains information about all
 * the
 * systems standard units.
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
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionOpenStandardObjectsViewText"));
	}


	/**
	 * A constructor for the class that takes a string, the action name.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionObjectEditing(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts
				.getString("actionOpenStandardObjectsViewText"));
	}



	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// Creates and places a new StandardObjects JFrame into the system main
		// registry.

		if ( PrimeMain.stdObjView == null )
		{
			PrimeMain.stdObjView = new StandardObjects();
		}
		else
		{
			PrimeMain.stdObjView.toFront();
		}
	}

}
