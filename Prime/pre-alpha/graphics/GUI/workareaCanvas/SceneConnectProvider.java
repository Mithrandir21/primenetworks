/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;

import java.awt.Point;

import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * @author Bam
 */
public class SceneConnectProvider implements ConnectProvider
{

	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
		ConnectionWidget connection = new ConnectionWidget(PrimeMain1.scene);
		connection.setTargetAnchorShape(AnchorShape.NONE);
		connection.setToolTipText("This is a connection");
		connection.getActions().addAction(new AdapterExtended());
		connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
		connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
		PrimeMain1.connectionLayer.addChild(connection);
	}

	public boolean hasCustomTargetWidgetResolver(Scene scene)
	{
		return false;
	}

	public boolean isSourceWidget(Widget sourceWidget)
	{
		if ( sourceWidget instanceof WidgetObject )
		{
			return true;
		}

		return false;
	}

	public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget)
	{
		if ( sourceWidget != targetWidget && targetWidget instanceof WidgetObject )
		{
			return ConnectorState.ACCEPT;
		}
		else
		{
			return ConnectorState.REJECT_AND_STOP;
		}
	}

	public Widget resolveTargetWidget(Scene sourceWidget, Point sceneLocation)
	{
		return null;
	}
}
