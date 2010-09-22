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

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.RoomManagment;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import connections.Connection;


/**
 * This action deletes the given {@link WidgetRoom} on the
 * currently open {@link WorkareaCanvas}. (It does not delete any
 * {@link WidgetObject} or {@link Connection} inside the room.
 * 
 * @author Bahram Malaekeh
 */
public class ActionRemoveRoom extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget room that is to be deleted
	private WidgetRoom room = null;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param widRoom
	 *            The {@link WidgetRoom} that is to be deleted.
	 */
	public ActionRemoveRoom(String text, ImageIcon icon, WidgetRoom widRoom)
	{
		super(text, icon);
		this.room = widRoom;
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param widRoom
	 *            The {@link WidgetRoom} that is to be deleted.
	 */
	public ActionRemoveRoom(String text, WidgetRoom widRoom)
	{
		super(text);
		this.room = widRoom;
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
		performAction(true);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#canRedo()
	 */
	@Override
	public boolean canRedo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#canUndo()
	 */
	@Override
	public boolean canUndo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#die()
	 */
	@Override
	public void die()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts.getString("actionDeleteRoomActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts.getString("actionDeleteRoomRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts.getString("actionDeleteRoomUndoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#isSignificant()
	 */
	@Override
	public boolean isSignificant()
	{
		return true;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#redo()
	 */
	@Override
	public void redo() throws CannotRedoException
	{
		// Gets all the rooms on the current canvas
		List<Widget> list = this.canvas.getRoomLayer().getChildren();

		WidgetRoom temp = null;

		boolean found = false;


		WidgetRoom testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetRoom) iter.next();

			if ( testingWidget.equals(this.room) )
			{
				found = true;
				temp = testingWidget;
			}
		}

		if ( found )
		{
			this.canvas.getRoomLayer().removeChild(temp);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		RoomManagment.addRoom(this.canvas, this.room);

		// Adds the actions supported by the WidgetRoom
		ActionsAdder.makeWidgetRoomReady(this.canvas, this.room);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Sets the current canvas
		this.canvas = PrimeMain.currentCanvas;

		WorkareaCanvasActions.removeRoom(this.canvas, this.room, true);

		if ( undoable )
		{
			this.canvas.addUndoableAction(this);
		}
	}
}
