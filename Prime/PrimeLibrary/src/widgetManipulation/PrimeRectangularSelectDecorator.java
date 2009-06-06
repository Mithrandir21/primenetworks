/**
 * 
 */
package widgetManipulation;


import managment.RoomManagment;

import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeRectangularSelectDecorator implements RectangularSelectDecorator
{
	private WorkareaCanvas canvas;

	public PrimeRectangularSelectDecorator(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.RectangularSelectDecorator#createSelectionWidget()
	 */
	@Override
	public Widget createSelectionWidget()
	{
		WidgetRoom widget = RoomManagment.createWidgetRoom(canvas, "RoomName");

		return widget;
	}
}