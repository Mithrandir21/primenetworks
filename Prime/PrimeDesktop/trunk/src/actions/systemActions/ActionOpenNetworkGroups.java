/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package actions.systemActions;


import graphics.PrimeMain;
import graphics.GUI.userGroups.NetworkGroupsDialog;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionOpenNetworkGroups extends AbstractSystemAction
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
	public ActionOpenNetworkGroups(String text, ImageIcon icon)
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
	public ActionOpenNetworkGroups(String text)
	{
		super(text);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if ( PrimeMain.groupsDialog != null )
		{
			PrimeMain.groupsDialog.toFront();
		}
		// Cannot show groups if any ObjectViews are open
		else if ( !PrimeMain.objView.isEmpty() )
		{
			PrimeMain.objView.get(0).toFront();
		}
		// Cannot show groups if any RackOverviews are open
		else if ( !PrimeMain.rackView.isEmpty() )
		{
			PrimeMain.rackView.get(0).toFront();
		}
		else
		{
			if ( PrimeMain.currentCanvas != null )
			{
				PrimeMain.groupsDialog = new NetworkGroupsDialog(
						PrimeMain.currentCanvas);
			}
		}
	}
}
