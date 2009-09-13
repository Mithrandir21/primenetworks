/**
 * 
 */
package actions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import managment.CanvasManagment;
import managment.ComponentsManagment;
import objects.Object;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;


/**
 * An action class that will perform a paste action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionPaste extends AbstractAction
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

				copyFrom = PrimeMain1.cutWidget;
			}

			if ( copyFrom != null )
			{
				// The location of the new Widget
				Point newLocation = new Point(25, 25);

				// Creates a deep copy of the object within the classes Widget
				Object newObject = ComponentsManagment.deepObjectCopy(copyFrom
						.getObject());

				// Creates a new WidgetObject
				WidgetObject newWidget = new WidgetObject(
						PrimeMain1.currentCanvas.getScene(), newObject,
						copyFrom.getImage());

				// Sets the location of the object
				newWidget.getObject().setLocation(newLocation);

				// Adds the newly created WidgetObject to the classes canvas
				PrimeMain1.currentCanvas.addWidgetObject(newWidget,
						newLocation, true);

				// Adds the clicking actions to the Widget on the scene
				ActionsAdder.makeWidgetObjectReady(PrimeMain1.currentCanvas,
						newWidget);


				// When the paste function is finished, the cut and copy should
				// be reset to null. If the Cut object is the one used, that
				// object will be removed from the canvas
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
									PrimeMain1.canvases), PrimeMain1.cutWidget);

					PrimeMain1.cutWidget = null;
				}
			}
		}
	}

}
