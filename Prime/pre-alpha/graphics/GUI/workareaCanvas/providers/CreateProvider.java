package graphics.GUI.workareaCanvas.providers;


import java.awt.Point;

import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.widget.Widget;


public class CreateProvider implements SelectProvider
{
	public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return false;
	}

	public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return true;
	}

	public void select(Widget relatedWidget, Point localLocation, boolean invertSelection)
	{
	}

}
