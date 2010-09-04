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
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.CanvasManagment;
import managment.ComponentsManagment;
import managment.DesktopCanvasManagment;
import objects.Object;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a paste action that will first check to see
 * if the copyWidget or the cutWidget in the {@link PrimeMain} is null. If the
 * copyWidget variable is not null, it will perform a copy action. If the
 * copyWidget variable is null and the cutWidget is not null, it will perform a
 * cut action.
 * After the action has been performed, where the selected widget has been
 * either cut or copied to the currently open {@link WorkareaCanvas}, the
 * copyWidget or cutWidget variable will be set to null, depending on what
 * action has been performed.
 * This action has an undo/redo function.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionPaste extends AbstractSystemAction implements
		SystemActionInterface
{
	// The widgetObject that is to be copied
	private WidgetObject widgetObject = null;

	// The newly created widgetObject
	private WidgetObject newWidgetObject = null;

	// The WorkareaCanvas where the WidgetObject now resides
	private WorkareaCanvas fromCanvas = null;

	// The WorkareaCanvas where the WidgetObject is to be copied to
	private WorkareaCanvas toCanvas = null;

	// If this is cut or not
	private boolean isCut = false;


	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionPaste(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION,
				PrimeMain.texts.getString("actionPasteDescriptionText"));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionPaste(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION,
				PrimeMain.texts.getString("actionPasteDescriptionText"));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		performAction(true);
	}


	@Override
	public boolean canRedo()
	{
		return true;
	}

	@Override
	public boolean canUndo()
	{
		return true;
	}

	@Override
	public void die()
	{
		widgetObject = null;
		newWidgetObject = null;
		fromCanvas = null;
		toCanvas = null;
	}

	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts.getString("actionPasteActionPresNameText");
	}

	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts.getString("actionPasteRedoPresNameText");
	}

	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts.getString("actionPasteUndoPresNameText");
	}

	@Override
	public boolean isSignificant()
	{
		return true;
	}

	@Override
	public void redo() throws CannotRedoException
	{
		if ( widgetObject != null
				&& !(toCanvas.getMainLayer().getChildren()
						.contains(newWidgetObject)) )
		{
			// Creates a deep copy of the object within the classes Widget
			Object newObject = ComponentsManagment
					.deepObjectCopy(newWidgetObject.getObject());

			// Creates a new WidgetObject
			WidgetObject newWidget = new WidgetObject(toCanvas.getScene(),
					newObject, newWidgetObject.getImage());

			// Adds the newly created WidgetObject to the classes canvas
			DesktopCanvasManagment.addWidgetToCanvas(newWidget,
					newWidgetObject.getLocation(), toCanvas, true, true);

			// Adds the clicking actions to the Widget on the scene
			ActionsAdder.makeWidgetObjectReady(toCanvas, newWidget);


			newWidgetObject = newWidget;

			if ( isCut )
			{
				// Removes the original object from the original canvas
				WorkareaCanvasActions.removeObject(fromCanvas, widgetObject,
						false);
			}

			DesktopCanvasManagment.canvasCleanUp(fromCanvas);
		}
	}

	@Override
	public void undo() throws CannotUndoException
	{
		if ( newWidgetObject != null && toCanvas != null )
		{
			// Removes the object from the canvas
			WorkareaCanvasActions
					.removeObject(toCanvas, newWidgetObject, false);

			// If the original object was cut(removed)
			if ( isCut )
			{
				// Creates a deep copy of the object within the classes
				// Widget
				Object newObject = ComponentsManagment
						.deepObjectCopy(widgetObject.getObject());

				// Creates a new WidgetObject
				WidgetObject newWidget = new WidgetObject(
						fromCanvas.getScene(), newObject,
						widgetObject.getImage());

				// Adds the newly created WidgetObject to the classes canvas
				DesktopCanvasManagment.addWidgetToCanvas(newWidget,
						newWidgetObject.getLocation(), fromCanvas, true, true);

				// Adds the clicking actions to the Widget on the scene
				ActionsAdder.makeWidgetObjectReady(fromCanvas, newWidget);


				widgetObject = newWidget;
			}

			DesktopCanvasManagment.canvasCleanUp(toCanvas);
		}
	}



	@Override
	public void performAction(boolean undoable)
	{
		if ( PrimeMain.currentCanvas != null )
		{
			// The user wants to paste a new WidgetObject, but not replace
			// the current WidgetObject
			WidgetObject copyFrom = null;


			// Either the cut or copy pointers will be used
			if ( PrimeMain.copyWidget != null )
			{
				copyFrom = PrimeMain.copyWidget;
			}
			else
			{
				assert PrimeMain.cutWidget != null;

				isCut = true;

				copyFrom = PrimeMain.cutWidget;
			}

			if ( copyFrom != null )
			{
				// Sets the object to be copied
				widgetObject = copyFrom;

				// Finds the canvas where the WidgetObject to be copied is
				// located
				fromCanvas = CanvasManagment.findCanvas(
						widgetObject.getScene(), PrimeMain.canvases);

				// If the canvas that the WidgetObject to be copied from does
				// not exist in the array of canvases(Which means that it is
				// either deleted of closed), the copy can not take place
				if ( fromCanvas != null )
				{
					// Sets the current canvas as the toCanvas
					toCanvas = PrimeMain.currentCanvas;

					if ( undoable )
					{
						// Adds this action to the undomanager of both the
						// canvases,
						// if the canvases are not the same canvas
						if ( fromCanvas.getSerial() != toCanvas.getSerial() )
						{
							fromCanvas.addUndoableAction(this);
							toCanvas.addUndoableAction(this);
						}
						else
						{
							fromCanvas.addUndoableAction(this);
						}
					}

					// The location of the new Widget
					Point newLocation = new Point(25, 25);

					// Creates a deep copy of the object within the classes
					// Widget
					Object newObject = ComponentsManagment
							.deepObjectCopy(copyFrom.getObject());

					// Creates a new WidgetObject
					WidgetObject newWidget = new WidgetObject(
							PrimeMain.currentCanvas.getScene(), newObject,
							copyFrom.getImage());

					// Sets the new WidgetObject
					newWidgetObject = newWidget;

					// Sets the location of the object
					newWidget.getObject().setLocation(newLocation);

					// Adds the newly created WidgetObject to the classes canvas
					DesktopCanvasManagment.addWidgetToCanvas(newWidget,
							newLocation, toCanvas, true, true);

					// Adds the clicking actions to the Widget on the scene
					ActionsAdder.makeWidgetObjectReady(PrimeMain.currentCanvas,
							newWidget);


					// When the paste function is finished, the cut and copy
					// should be reset to null. If the Cut object is the one
					// used, that object will be removed from the canvas
					if ( PrimeMain.copyWidget != null )
					{
						PrimeMain.copyWidget = null;
					}
					else
					{
						// Assures that the pointer is not null
						assert PrimeMain.cutWidget != null;

						// Removes the object from the canvas
						WorkareaCanvasActions.removeObject(CanvasManagment
								.findCanvas(PrimeMain.cutWidget.getScene(),
										PrimeMain.canvases),
								PrimeMain.cutWidget, true);

						PrimeMain.cutWidget = null;
					}
				}
			}
		}
	}

}
