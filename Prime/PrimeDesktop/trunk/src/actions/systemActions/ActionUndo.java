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
import javax.swing.undo.UndoManager;

import logistical.AbstractSystemAction;
import logistical.CanvasUndoManager;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a Undo action, gotten from the {@link UndoManager} of the current {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionUndo extends AbstractSystemAction
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
	public ActionUndo(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts.getString("actionUndoText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionUndo(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain.texts.getString("actionUndoText"));
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
			// Gets the canvases undomanager
			CanvasUndoManager manager = PrimeMain.currentCanvas
					.getUndoManager();

			// If the manager has an undo to perform
			if ( manager.canUndo() )
			{
				// performs the undo
				manager.undo();

				// Cleans up the canvas
				PrimeMain.currentCanvas.cleanUp();
			}
		}
	}

}
