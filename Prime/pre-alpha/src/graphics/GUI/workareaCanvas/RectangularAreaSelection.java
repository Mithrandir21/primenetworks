package graphics.GUI.workareaCanvas;


import java.awt.event.MouseEvent;

import org.netbeans.api.visual.action.WidgetAction.LockedAdapter;
import org.netbeans.api.visual.action.WidgetAction.State;
import org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent;
import org.netbeans.api.visual.widget.Widget;


public class RectangularAreaSelection extends LockedAdapter
{

	public RectangularAreaSelection()
	{

	}


	@Override
	protected boolean isLocked()
	{
		// TODO Auto-generated method stub
		return false;
	}


	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 1 )
		{

		}

		return State.REJECTED;
	}


	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{

		return State.REJECTED;
	}


	public State mouseDragged(Widget widget, WidgetMouseEvent event)
	{

		return State.REJECTED;
	}
}
