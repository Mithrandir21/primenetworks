/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.GraphicalFunctions;

import java.awt.event.MouseEvent;

import managment.Settings;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetRoomAdapterExtended extends Adapter
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
		if ( Settings.roomsManipulation )
		{
			// If button1, which can be whatever depending on what the OS has chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// If button1 is double clicked.
				if ( event.getClickCount() == 2 )
				{
					GraphicalFunctions.changeWidgetRoomName(widget);
				}
			}
			return State.CONSUMED;
		}

		return State.REJECTED;
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
			widget.bringToBack();
		}
		return State.REJECTED;
	}



	@Override
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}
}
