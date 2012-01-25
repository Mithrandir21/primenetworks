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
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.DesktopCanvasManagment;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action adds a given {@link WidgetObject} to a given
 * {@link WorkareaCanvas} at a given {@link Point}. This action contains a
 * undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionAddWidgetToWorkareaCanvas extends AbstractSystemAction
		implements SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget object
	private WidgetObject widObject = null;

	// The point where the Object is to be added
	private Point objectPoint;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon, a {@link WorkareaCanvas}, a {@link WidgetObject} and a
	 * {@link Point}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the given {@link WidgetObject}
	 *            is to be added to.
	 * @param widObject
	 *            The {@link WidgetObject} that is to be added to the given
	 *            {@link WorkareaCanvas}.
	 * @param objectPoint
	 *            The {@link Point} where the {@link WidgetObject} is to be
	 *            placed on the {@link WorkareaCanvas}.
	 */
	public ActionAddWidgetToWorkareaCanvas(String text, ImageIcon icon,
			WorkareaCanvas canvas, WidgetObject widObject, Point objectPoint)
	{
		super(text, icon);
		this.canvas = canvas;
		this.widObject = widObject;
		this.objectPoint = objectPoint;
	}


	/**
	 * A constructor for the class that takes a string, the action name, a
	 * {@link WorkareaCanvas}, a {@link WidgetObject} and a {@link Point}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the given {@link WidgetObject}
	 *            is to be added to.
	 * @param widObject
	 *            The {@link WidgetObject} that is to be added to the given
	 *            {@link WorkareaCanvas}.
	 * @param objectPoint
	 *            The {@link Point} where the {@link WidgetObject} is to be
	 *            placed on the {@link WorkareaCanvas}.
	 */
	public ActionAddWidgetToWorkareaCanvas(String text, WorkareaCanvas canvas,
			WidgetObject widObject, Point objectPoint)
	{
		super(text);
		this.canvas = canvas;
		this.widObject = widObject;
		this.objectPoint = objectPoint;
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
		this.canvas = null;
		this.widObject = null;
		this.objectPoint = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasUndoPresNameText");
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
		if ( this.widObject != null )
		{
			// Gets the point the Widget has on the scene
			Point sceneLocation = this.canvas.getScene().convertSceneToLocal(
					this.objectPoint);

			// Adds the newly created WidgetObject to the classes canvas
			DesktopCanvasManagment.addWidgetToCanvas(this.widObject,
					sceneLocation, this.canvas, true, true);

			// Adds the actions that the new widget supports
			ActionsAdder.makeWidgetObjectReady(this.canvas, this.widObject);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		if ( this.widObject != null )
		{
			WorkareaCanvasActions.removeObject(this.canvas, this.widObject,
					true);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Gets the point the Widget has on the scene
		Point sceneLocation = this.canvas.getScene().convertSceneToLocal(
				this.objectPoint);

		// Adds the newly created WidgetObject to the classes canvas
		DesktopCanvasManagment.addWidgetToCanvas(this.widObject, sceneLocation,
				this.canvas, true, true);

		// Adds the actions that the new widget supports
		ActionsAdder.makeWidgetObjectReady(this.canvas, this.widObject);

		// Checks for custom object
		DesktopCanvasManagment.checkForCustomObject(this.widObject,
				canvas);

		if ( undoable )
		{
			this.canvas.addUndoableAction(this);
		}


		PrimeMain.guiLog.fine("Widget, "
				+ this.widObject.getObject().getObjectName()
				+ " added to canvas " + this.canvas.getCanvasName() + ".");
	}
}
