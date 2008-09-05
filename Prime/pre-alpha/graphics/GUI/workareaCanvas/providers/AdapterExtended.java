package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * This class overrides some of the methods in the {@link  Adapter  Adapter}
 * class. The methods govern how widgets react to actions like clicks and mouse
 * movements.
 * 
 * @author Bahram Malaekeh
 */
public class AdapterExtended extends Adapter
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org.netbeans.api.visual.widget.Widget,
	 *      org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		// If the object is not an instance of a connection widget
		if ( !(widget instanceof ConnectionWidget) )
		{
			// If button1, which can be whatever depending on what the OS has
			// chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// Casts the object to an WidgetObject
				WidgetObject widgetobj = (WidgetObject) widget;

				// If button1 is double clicked.
				if ( event.getClickCount() == 2 )
				{
					// Creates a new ObjectView object with the WidgetObject
					// that has been cast.
					ObjectView objView = new ObjectView(widgetobj);

					// If there is not an ObjectView present in the system.
					// There can only be one.
					if ( PrimeMain1.objView == null )
					{
						// Sets the systems ObjectView so as to be able to
						// update it later.
						PrimeMain1.objView = objView;
					}
					// If there already exists a ObjectView.
					else
					{
						// Brings the pre-existing ObjectView to the front.
						PrimeMain1.objView.toFront();
						JOptionPane.showMessageDialog(null,
								"Only one object can be edited at a time.");
					}
				}
				// If button1 is clicked only once.
				else if ( event.getClickCount() == 1 )
				{
					// Updates the information panel with information from the
					// selected object.
					PrimeMain1.updatePropertiesObjectArea(widgetobj.getObject());
				}
			}
		}
		else if ( widget instanceof ConnectionWidget )
		{
			System.out.println("There is still work to be done. AdapterExtended - mouseClicked");
		}
		
		return State.REJECTED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mousePressed(org.netbeans.api.visual.widget.Widget,
	 *      org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
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


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseEntered(org.netbeans.api.visual.widget.Widget,
	 *      org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseEntered(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse entered");
		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseExited(org.netbeans.api.visual.widget.Widget,
	 *      org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseExited(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse exited");
		return State.REJECTED;
	}

}
