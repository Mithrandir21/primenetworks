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
package actions.canvasActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import managment.DesktopCanvasManagment;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action deletes all the widgets and connections on a
 * {@link WorkareaCanvas}.
 * It removes everything, except rooms from {@link WorkareaCanvas}.
 * This method does <b>not</b> have a redo/undo actions.
 * 
 * @author Bahram Malaekeh
 */
public class ActionDeleteAllWidgets extends AbstractSystemAction
{
	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteAllWidgets(String text)
	{
		super(text);
	}


	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteAllWidgets(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		WorkareaCanvas canvas = PrimeMain.currentCanvas;

		if ( canvas != null )
		{
			String question = PrimeMain.texts
					.getString("actionDeleteAllWidgetsQuestions")
					+ System.getProperty("line.separator")
					+ PrimeMain.texts.getString("thisCannotBeUndoneMsg");



			// Custom button text
			Object[] options = { PrimeMain.texts.getString("yes"),
					PrimeMain.texts.getString("no") };


			int i = JOptionPane.showOptionDialog(null, question,
					PrimeMain.texts.getString("actionDeleteAllWidgetsName"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);


			// If the answer is yes(user can possibly close the
			// current canvas before pressing ok or cancel.)
			if ( i == 0 && canvas != null )
			{
				// gets all the Widgets on the current scene
				WidgetObject[] objects = canvas.getWidgetObjectsOnTheScene();

				for ( int j = 0; j < objects.length; j++ )
				{
					WorkareaCanvasActions.removeObject(canvas, objects[j],
							false);
				}

				DesktopCanvasManagment.canvasCleanUp(canvas);


				// Deletes all the undoable actions of the canvas
				canvas.getUndoManager().discardAllEdits();
			}

		}
	}
}
