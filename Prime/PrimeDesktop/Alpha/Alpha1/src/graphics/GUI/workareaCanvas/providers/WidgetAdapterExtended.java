package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.MouseEvent;

import objects.Object;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * This class overrides some of the methods in the {@link Adapter Adapter} class. The methods govern how widgets react
 * to actions like clicks and mouse movements.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetAdapterExtended extends Adapter
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		// If the object is not an instance of a connection widget
		if ( widget instanceof WidgetObject )
		{
			// If button1, which can be whatever depending on what the OS has chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// Casts the object to an WidgetObject
				WidgetObject widgetobj = (WidgetObject) widget;

				// The widgetobjects object
				Object obj = widgetobj.getObject();

				// Updates the information panel with information from the selected object.
				PrimeMain1.updatePropertiesObjectArea(widgetobj.getObject(), false);

				// If button1 is double clicked.
				if ( event.getClickCount() == 2 )
				{
					// Gets the view, if there exist any, with the given object
					ObjectView view = PrimeMain1.getObjectView(obj);

					// There exist no view with the given object.
					// Which means that there exist no open view for the given
					// object.
					if ( view == null )
					{
						// Creates a new ObjectView object with the WidgetObject that has been cast.
						ObjectView objView = new ObjectView(widgetobj);

						// Adds the view to the arraylist of object views.
						PrimeMain1.addObjectView(objView);
					}
					else
					{
						// Brings the pre-existing ObjectView to the front.
						view.toFront();
						// JOptionPane.showMessageDialog(null,
						// "Only one object can be edited at a time.");
					}
				}
			}
		}
		else if ( widget instanceof WidgetExtendedConnection )
		{
			// If button1, which can be whatever depending on what the OS has chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// Casts the object to an WidgetExtendedConnection
				WidgetExtendedConnection conWidget = (WidgetExtendedConnection) widget;

				// The connection
				Connection con = conWidget.getConnection();


			}
			System.out
					.println("There is still work to be done. AdapterExtended - mouseClicked - WidgetExtendedConnection");
		}


		// Consumes the Action so that no other Listener picks up the action.
		return State.CONSUMED;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mousePressed(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
			// System.out.println("Mouse Pressed");
			widget.bringToFront();
			return State.CONSUMED;
		}
		return State.REJECTED;
	}



	@Override
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}
}
