package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.MouseEvent;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class AdapterExtended extends Adapter
{
	/* (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org.netbeans.api.visual.widget.Widget, org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
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
					if ( PrimeMain1.getObjectView() == null )
					{
						ObjectView objView = new ObjectView(widgetobj);
						PrimeMain1.setObjectView(objView);
					}
				}
				else if ( event.getClickCount() == 1 )
				{
					PrimeMain1.updatePropertiesObjectArea(widgetobj.getObject());
				}
			}
		}
		return State.REJECTED;
	}

	/* (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mousePressed(org.netbeans.api.visual.widget.Widget, org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
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


	/* (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseEntered(org.netbeans.api.visual.widget.Widget, org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseEntered(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse entered");
		return State.REJECTED;
	}



	/* (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseExited(org.netbeans.api.visual.widget.Widget, org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseExited(Widget widget, WidgetMouseEvent event)
	{
		// System.out.println("Mouse exited");
		return State.REJECTED;
	}

}
