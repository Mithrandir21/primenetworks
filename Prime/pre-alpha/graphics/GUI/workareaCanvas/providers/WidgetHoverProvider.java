/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import org.netbeans.api.visual.action.HoverProvider;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WidgetHoverProvider implements HoverProvider
{

	@Override
	public void widgetHovered(Widget widget)
	{
		if ( widget != null )
		{
			// System.out.println(widget.getToolTipText());
			WidgetObject wid = (WidgetObject) widget;
			// System.out.println("Hovering over - " +
			// wid.getObject().getDescription());
		}
	}

}
