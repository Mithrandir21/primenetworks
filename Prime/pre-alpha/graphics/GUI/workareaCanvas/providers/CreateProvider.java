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
//		WidgetObject widget = new WidgetObject(PrimeMain1.scene, new HTTPServer("newComponent2",
//				"Desc", "1", "2", "3"), ImageLocator.getImageIconObject("Print-server").getImage());
//
//
//		widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
//
//		widget.getActions().addAction(
//				ActionFactory.createExtendedConnectAction(PrimeMain1.interactionLayer,
//						new SceneConnectProvider()));
//
//		widget.getActions().addAction(
//				ActionFactory.createAlignWithMoveAction(PrimeMain1.mainLayer,
//						PrimeMain1.interactionLayer, null));
//
//		widget.getActions().addAction(
//				ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor()));
//
//		widget.getActions().addAction(new AdapterExtended());
//
//		widget.getActions().addAction(ActionFactory.createAddRemoveControlPointAction());
//
//
//		widget.setLabel("Biatch");
//
//
//		PrimeMain1.mainLayer.addChild(widget);
//		PrimeMain1.numberOfWidgetsOnTheScene++;
//
//		cleanUp();
	}

}
