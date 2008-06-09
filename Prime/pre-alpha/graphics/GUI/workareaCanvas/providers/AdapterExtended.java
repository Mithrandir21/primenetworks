package graphics.GUI.workareaCanvas.providers;

import java.awt.event.MouseEvent;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;

public class AdapterExtended extends Adapter
{
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
			System.out.println("Mouse Clicked");
			widget.bringToFront();
			return State.CONSUMED;
		}
		return State.REJECTED;
	}
	
	
	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
			System.out.println("Mouse Pressed");
			widget.bringToFront();
			return State.CONSUMED;
		}
		return State.REJECTED;
	}
	
}
