/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteAllConnections;



/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaWidgetActionListener implements ActionListener
{

	/**
	 * The {@link WorkareaCanvas} the event will take place in.
	 */
	private WorkareaCanvas canvas;


	/**
	 * The {@link Widget} that the actions are to be performed for.
	 */
	private WidgetObject widget;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public WorkareaWidgetActionListener(WorkareaCanvas canvas, Widget widget)
	{
		this.canvas = canvas;
		this.widget = (WidgetObject) widget;
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();

		String actionName = "";

		if ( action.getActionCommand() != null )
		{
			actionName = action.getActionCommand();
		}

		if ( !actionName.equals("") )
		{

			if ( actionName.equals("OpenDevice") )
			{
				// Gets the view, if there exist any, with the given object
				ObjectView view = PrimeMain1.getObjectView(widget.getObject());

				// There exist no view with the given object.
				// Which means that there exist no open view for the given
				// object.
				if ( view == null )
				{
					// Creates a new ObjectView object with the WidgetObject
					// that has been cast.
					ObjectView objView = new ObjectView(widget);

					// Adds the view to the arraylist of object views.
					PrimeMain1.addObjectView(objView);
				}
				else
				{
					// Brings the pre-existing ObjectView to the front.
					view.toFront();
				}
			}
			else if ( actionName.equals("CopyObject") )
			{
				// Sets the cutWidget pointer to null
				PrimeMain1.cutWidget = null;

				// Sets the widget as the widget to be copied
				PrimeMain1.copyWidget = widget;
			}
			else if ( actionName.equals("CutObject") )
			{
				// Sets the copyWidget pointer to null
				PrimeMain1.copyWidget = null;

				// Sets the widget as the widget to be cut(copied and deleted)
				PrimeMain1.cutWidget = widget;
			}
			// else if ( actionName.equals("PasteObject") )
			// {
			// // The user wants to paste a new WidgetObject, but not replace
			// // the current WidgetObject
			// WidgetObject copyFrom = null;
			//
			//
			// // Either the cut or copy pointers will be used
			// if ( PrimeMain1.copyWidget != null )
			// {
			// copyFrom = PrimeMain1.copyWidget;
			// }
			// else
			// {
			// assert PrimeMain1.cutWidget != null;
			//
			// copyFrom = PrimeMain1.cutWidget;
			// }
			//
			//
			// // The location of the new Widget
			// Point newLocation = new Point(copyFrom.getLocation().x + 25,
			// copyFrom.getLocation().y + 25);
			//
			// // Creates a deep copy of the object within the classes Widget
			// Object newObject = ComponentsManagment.deepObjectCopy(copyFrom
			// .getObject());
			//
			// // Creates a new WidgetObject
			// WidgetObject newWidget = new WidgetObject(canvas.getScene(),
			// newObject, copyFrom.getImage());
			//
			// // Sets the location of the object
			// newWidget.getObject().setLocation(newLocation);
			//
			// // Adds the newly created WidgetObject to the classes canvas
			// canvas.addWidgetObject(newWidget, newLocation, true);
			//
			// // Adds the clicking actions to the Widget on the scene
			// ActionsAdder.makeWidgetObjectReady(canvas, newWidget);
			//
			//
			// // When the paste function is finished, the cut and copy should
			// // be reset to null. If the Cut object is the one used, that
			// // object will be removed from the canvas
			// if ( PrimeMain1.copyWidget != null )
			// {
			// PrimeMain1.copyWidget = null;
			// }
			// else
			// {
			// // Assures that the pointer is not null
			// assert PrimeMain1.cutWidget != null;
			//
			// // Removes the object from the canvas
			// WorkareaCanvasActions.deleteObject(CanvasManagment
			// .findCanvas(PrimeMain1.cutWidget.getScene(),
			// PrimeMain1.canvases), PrimeMain1.cutWidget);
			//
			// PrimeMain1.cutWidget = null;
			// }
			// }
			else if ( actionName.equals("DeleteConnectionsObject") )
			{
				// WorkareaCanvasActions.removeAllConnectionsToFromObject(canvas,
				// widget.getObject());
				ActionDeleteAllConnections actionDeleteConnections = new ActionDeleteAllConnections(
						"Delete all connections");
				actionDeleteConnections.performAction(true);
			}
			// else if ( actionName.equals("DeleteThisObject") )
			// {
			// WorkareaCanvasActions.deleteObject(canvas, widget);
			//
			// PrimeMain1.runCanvasObjectCheck();
			// }
		}


		canvas.cleanUp();

		PrimeMain1.updatePropertiesCanvasArea(false);
	}
}
