/**
 * 
 */
package widgetManipulation.Providers;


import java.awt.Color;

import org.netbeans.api.visual.action.HoverProvider;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WidgetHoverProvider implements HoverProvider
{

	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.HoverProvider#widgetHovered(org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public void widgetHovered(Widget widget)
	{
		if ( widget != null )
		{
			// FIXME - make something out of this.
			// System.out.println(widget.getToolTipText());
			WidgetObject wid = (WidgetObject) widget;
			wid.setBorder(BorderFactory.createRoundedBorder(10, 10,
					Color.white, Color.black));
			System.out.println("Hovering over - ");
			// wid.getObject().getDescription());
		}
	}

}
