package graphics.GUI.workareaCanvas.providers;


import graphics.ObjectDefiner;
import graphics.PrimeMain1;
import graphics.GUI.properties.PropertiesArea;

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
		if (! (widget instanceof ConnectionWidget))
		{
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				WidgetObject widgetobj = (WidgetObject) widget;
				PropertiesArea temp = (PropertiesArea) PrimeMain1.propertiesPanel.getComponent(0);
				
				temp.newObjectSelectedPropertiesTab(widgetobj.getObject());
				
//				System.out.println(widgetobj.getObject().getName());
			}
		}
		return State.REJECTED;
	}


	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
//			 System.out.println("Mouse Pressed");
			widget.bringToFront();
			return State.CONSUMED;
		}
		return State.REJECTED;
	}


	@Override
	public State mouseExited(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}

}
