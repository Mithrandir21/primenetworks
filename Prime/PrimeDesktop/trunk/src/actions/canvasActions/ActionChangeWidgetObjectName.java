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
package actions.canvasActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import logistical.checkLogic;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action changes the name of a given {@link WidgetObject}. The action
 * automatically changed the name displayed on the {@link WorkareaCanvas} where
 * the {@link WidgetObject} is placed.
 * This action contains a undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionChangeWidgetObjectName extends AbstractSystemAction implements SystemActionInterface
{

	// The widget object where the name is to be changed
	WidgetObject widObject = null;

	// The new name of the widget object
	String newName = null;

	// The old name of the widget object
	String oldName = null;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon, a {@link WidgetObject} and a String that is to be the new name of
	 * the given {@link WidgetObject}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param widgetObject
	 *            The {@link WidgetObject} whose name is to be changed.
	 * @param givenName
	 *            The new name of the {@link WidgetObject}.
	 */
	public ActionChangeWidgetObjectName(String text, ImageIcon icon,
			WidgetObject widgetObject, String givenName)
	{
		super(text, icon);
		widObject = widgetObject;
		newName = givenName;
		oldName = widgetObject.getObject().getObjectName();
	}

	/**
	 * A constructor for the class that takes a string, the action name, a {@link WidgetObject} and a String that is to be the new
	 * name of
	 * the given {@link WidgetObject}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param widgetObject
	 *            The {@link WidgetObject} whose name is to be changed.
	 * @param givenName
	 *            The new name of the {@link WidgetObject}.
	 */
	public ActionChangeWidgetObjectName(String text, WidgetObject widgetObject,
			String givenName)
	{
		super(text);
		widObject = widgetObject;
		newName = givenName;
		oldName = widgetObject.getObject().getObjectName();
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		performAction(true);
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * logistical.AbstractSystemAction#addEdit(javax.swing.undo.UndoableEdit)
	 */
	@Override
	public boolean addEdit(UndoableEdit anEdit)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canRedo()
	 */
	@Override
	public boolean canRedo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canUndo()
	 */
	@Override
	public boolean canUndo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#die()
	 */
	@Override
	public void die()
	{
		widObject = null;
		newName = null;
		oldName = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetNameActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetNameRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetNameUndoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#isSignificant()
	 */
	@Override
	public boolean isSignificant()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#redo()
	 */
	@Override
	public void redo() throws CannotRedoException
	{
		if ( widObject != null )
		{
			// Updates the name of the LabelWidget on the scene
			WorkareaCanvasActions.updateWidgetObjectCanvasName(
					PrimeMain.currentCanvas, widObject.getObject(), newName);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		if ( widObject != null )
		{
			// Updates the name of the LabelWidget on the scene
			WorkareaCanvasActions.updateWidgetObjectCanvasName(
					PrimeMain.currentCanvas, widObject.getObject(), oldName);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction(boolean)
	 */
	@Override
	public void performAction(boolean undoable)
	{
		if ( widObject != null )
		{
			// If the text is validated
			if ( checkLogic.validateName(newName) )
			{
				// Updates the name of the LabelWidget on the scene
				WorkareaCanvasActions
						.updateWidgetObjectCanvasName(PrimeMain.currentCanvas,
								widObject.getObject(), newName);
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("actionChangeWidgetNameInvalidNameText"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);
			}

			if ( undoable )
			{
				PrimeMain.currentCanvas.addUndoableAction(this);
			}
		}
	}
}
