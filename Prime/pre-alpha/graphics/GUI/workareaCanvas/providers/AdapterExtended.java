package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.MouseEvent;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


public class AdapterExtended extends Adapter
{
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		// TODO - Add option of connection clicked
		if ( !(widget instanceof ConnectionWidget) )
		{
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				WidgetObject widgetobj = (WidgetObject) widget;
				if ( event.getClickCount() == 2 )
				{
					new ObjectView(widgetobj);
				}
				else if ( event.getClickCount() == 1 )
				{
					PrimeMain1.updatePropertiesObjectArea(widgetobj.getObject());
				}
			}
		}
		return State.REJECTED;
	}

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
	public State mouseEntered(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse entered");
		return State.REJECTED;
	}



	@Override
	public State mouseExited(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse exited");
		return State.REJECTED;
	}

}
