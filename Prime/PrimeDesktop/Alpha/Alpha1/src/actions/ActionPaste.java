/**
 * 
 */
package actions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.CanvasManagment;
import managment.ComponentsManagment;
import objects.Object;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * An action class that will perform a paste action.
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
		putValue(SHORT_DESCRIPTION, "This is a Paste action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_V));
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
		putValue(SHORT_DESCRIPTION, "This is a Paste action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_V));
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		performAction();
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
		return "Paste an Object";
	}

	@Override
	public String getRedoPresentationName()
	{
		return "Repaste an Object";
	}

	@Override
	public String getUndoPresentationName()
	{
		return "Remove a pasted Object";
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


			// Sets the location of the object
			newWidget.getObject().setLocation(newWidgetObject.getLocation());

			// Adds the clicking actions to the Widget on the scene
			ActionsAdder.makeWidgetObjectReady(toCanvas, newWidget);

			// Adds the newly created WidgetObject to the classes canvas
			toCanvas.addWidgetObject(newWidget, newWidgetObject.getLocation(),
					false);

			newWidgetObject = newWidget;

			if ( isCut )
			{
				// Removes the original object from the original canvas
				WorkareaCanvasActions.deleteObject(fromCanvas, widgetObject);
			}

			fromCanvas.cleanUp();
		}
	}

	@Override
	public void undo() throws CannotUndoException
	{
		if ( newWidgetObject != null && toCanvas != null )
		{
			// Removes the object from the canvas
			WorkareaCanvasActions.deleteObject(toCanvas, newWidgetObject);

			// If the original object was cut(removed)
			if ( isCut )
			{
				// Creates a deep copy of the object within the classes
				// Widget
				Object newObject = ComponentsManagment
						.deepObjectCopy(widgetObject.getObject());

				// Creates a new WidgetObject
				WidgetObject newWidget = new WidgetObject(
						fromCanvas.getScene(), newObject, widgetObject
								.getImage());

				// Sets the location of the object
				newWidget.getObject().setLocation(widgetObject.getLocation());

				// Adds the clicking actions to the Widget on the scene
				ActionsAdder.makeWidgetObjectReady(fromCanvas, newWidget);

				// Adds the WidgetObject to the original canvas
				fromCanvas.addWidgetObject(newWidget, widgetObject
						.getLocation(), false);

				widgetObject = newWidget;
			}

			toCanvas.cleanUp();
		}
	}



	@Override
	public void performAction()
	{
		if ( PrimeMain1.currentCanvas != null )
		{
			// The user wants to paste a new WidgetObject, but not replace
			// the current WidgetObject
			WidgetObject copyFrom = null;


			// Either the cut or copy pointers will be used
			if ( PrimeMain1.copyWidget != null )
			{
				copyFrom = PrimeMain1.copyWidget;
			}
			else
			{
				assert PrimeMain1.cutWidget != null;

				isCut = true;

				copyFrom = PrimeMain1.cutWidget;
			}

			if ( copyFrom != null )
			{
				// Sets the object to be copied
				widgetObject = copyFrom;

				// Finds the canvas where the WidgetObject to be copied is
				// located
				fromCanvas = CanvasManagment.findCanvas(
						widgetObject.getScene(), PrimeMain1.canvases);

				// If the canvas that the WidgetObject to be copied from does
				// not exist in the array of canvases(Which means that it is
				// either deleted of closed), the copy can not take place
				if ( fromCanvas != null )
				{
					// Sets the current canvas as the toCanvas
					toCanvas = PrimeMain1.currentCanvas;

					// Adds this action to the undomanager of both the canvases,
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

					// The location of the new Widget
					Point newLocation = new Point(25, 25);

					// Creates a deep copy of the object within the classes
					// Widget
					Object newObject = ComponentsManagment
							.deepObjectCopy(copyFrom.getObject());

					// Creates a new WidgetObject
					WidgetObject newWidget = new WidgetObject(
							PrimeMain1.currentCanvas.getScene(), newObject,
							copyFrom.getImage());

					// Sets the new WidgetObject
					newWidgetObject = newWidget;

					// Sets the location of the object
					newWidget.getObject().setLocation(newLocation);

					// Adds the newly created WidgetObject to the classes canvas
					PrimeMain1.currentCanvas.addWidgetObject(newWidget,
							newLocation, true);

					// Adds the clicking actions to the Widget on the scene
					ActionsAdder.makeWidgetObjectReady(
							PrimeMain1.currentCanvas, newWidget);


					// When the paste function is finished, the cut and copy
					// should be reset to null. If the Cut object is the one
					// used, that object will be removed from the canvas
					if ( PrimeMain1.copyWidget != null )
					{
						PrimeMain1.copyWidget = null;
					}
					else
					{
						// Assures that the pointer is not null
						assert PrimeMain1.cutWidget != null;

						// Removes the object from the canvas
						WorkareaCanvasActions.deleteObject(CanvasManagment
								.findCanvas(PrimeMain1.cutWidget.getScene(),
										PrimeMain1.canvases),
								PrimeMain1.cutWidget);

						PrimeMain1.cutWidget = null;
					}
				}
			}
		}
	}

}
