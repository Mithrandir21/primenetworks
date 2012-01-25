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
import graphics.GUI.selectArea.ObjectSelection;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.CreateObjects;
import managment.DesktopFileManagment;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionResetStandardObjects extends AbstractSystemAction
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
	public ActionResetStandardObjects(String text, ImageIcon icon)
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
	public ActionResetStandardObjects(String text)
	{
		super(text);
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( PrimeMain.stdObjView == null )
		{
			String question = PrimeMain.texts
					.getString("settingsAdvancedResetStdObjQuestion")
					+ System.getProperty("line.separator")
					+ PrimeMain.texts.getString("thisCannotBeUndoneMsg");

			// Custom button text
			Object[] options = { PrimeMain.texts.getString("yes"),
					PrimeMain.texts.getString("no") };


			int answer = JOptionPane.showOptionDialog(null, question,
					PrimeMain.texts.getString("confirm"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if ( answer == 0 )
			{
				File file = new File("./resource/objects.obj");

				// Attempt to delete the object file
				file.delete();

				// Removes all the objects in the arrayList
				PrimeMain.objectlist.clear();

				// Creates new Standard Object
				CreateObjects.createStandardObject();

				// Creates a new Objects list file
				DesktopFileManagment.saveObjectsFile();

				ObjectSelection.reloadGenericDevicesIcons();
			}
		}
		else
		{
			PrimeMain.stdObjView.toFront();
		}
	}
}
