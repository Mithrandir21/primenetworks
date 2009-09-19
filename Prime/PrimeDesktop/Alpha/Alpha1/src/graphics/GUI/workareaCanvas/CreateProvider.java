package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;

import java.awt.Point;

import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CreateProvider implements SelectProvider
{
	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#isAimingAllowed(org.netbeans
	 * .api.visual.widget.Widget, java.awt.Point, boolean)
	 */
	public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#isSelectionAllowed(org.
	 * netbeans.api.visual.widget.Widget, java.awt.Point, boolean)
	 */
	public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#select(org.netbeans.api
	 * .visual.widget.Widget, java.awt.Point, boolean)
	 */
	public void select(Widget relatedWidget, Point localLocation,
			boolean invertSelection)
	{
		if ( relatedWidget instanceof WidgetObject )
		{
			relatedWidget.bringToFront();

			WidgetObject widgetobj = (WidgetObject) relatedWidget;

			// Sets the current WidgetObject for the systems current canvas
			PrimeMain1.currentCanvas.setCurrentWidgetObject(widgetobj);

			// Updates the information panel with information from the selected
			// object.
			PrimeMain1.updatePropertiesObjectArea(widgetobj.getObject(), false);

			// for ( int i = 0; i <
			// widgetobj.getObject().getConnectedDevices().length; i++ )
			// {
			// if ( widgetobj.getObject().getConnectedDevices()[i] != null )
			// {
			// System.out.println(widgetobj.getObject()
			// .getConnectedDevices()[i].getObjectName()
			// + " - "
			// + widgetobj.getObject().getConnectedDevices()[i]
			// .getObjectSerial());
			// }
			// else
			// {
			// System.out.println("Number " + i + " is empty");
			// }
			// }
		}
	}
}
