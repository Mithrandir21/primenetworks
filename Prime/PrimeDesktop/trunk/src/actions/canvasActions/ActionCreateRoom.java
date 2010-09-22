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
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom.JMenuWidgetRoom;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.DesktopCanvasManagment;
import managment.RoomManagment;

import org.netbeans.api.visual.action.ActionFactory;

import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


/**
 * This action creates a {@link WidgetRoom} on the currently open
 * {@link WorkareaCanvas}.
 * This action contains a undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionCreateRoom extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The WidgetRoom that is created
	private WidgetRoom room = null;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon and a {@link WidgetRoom}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param widRoom
	 *            The {@link WidgetRoom} that is to be added to the currently
	 *            open {@link WorkareaCanvas}.
	 */
	public ActionCreateRoom(String text, ImageIcon icon, WidgetRoom widRoom)
	{
		super(text, icon);
		this.room = widRoom;
	}


	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon and a {@link WidgetRoom}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param widRoom
	 *            The {@link WidgetRoom} that is to be added to the currently
	 *            open {@link WorkareaCanvas}.
	 */
	public ActionCreateRoom(String text, WidgetRoom widRoom)
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
		this.canvas = null;
		this.room = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts.getString("actionCreateRoomActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts.getString("actionCreateRoomRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts.getString("actionCreateRoomUndoPresNameText");
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
		RoomManagment.addRoom(this.canvas, this.room);

		// Adds the actions supported by the WidgetRoom
		ActionsAdder.makeWidgetRoomReady(this.canvas, this.room);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		WorkareaCanvasActions.removeRoom(this.canvas, this.room, true);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		if ( this.room != null )
		{
			this.canvas = PrimeMain.currentCanvas;

			if ( this.room.getBounds().height < 50 && this.room.getBounds().width < 50 )
			{
				// Removes the WidgetRoom from the roomLayer
				this.room.getParentWidget().removeChild(this.room);
			}
			else
			{
				// Add the JMenuPopup action the WidgetRoom
				this.room.getActions().addAction(
						ActionFactory
								.createPopupMenuAction(new JMenuWidgetRoom(
										PrimeMain.currentCanvas)));

				// Repaints roomLayer
				DesktopCanvasManagment.canvasCleanUp(this.canvas);

				if ( undoable )
				{
					this.canvas.addUndoableAction(this);
				}
			}
		}
	}
}
