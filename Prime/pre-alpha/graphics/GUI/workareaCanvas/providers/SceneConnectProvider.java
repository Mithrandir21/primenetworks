/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain1;

import java.awt.Point;

import managment.ConnectionManagment;

import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import connections.WidgetExtendedConnection;

import widgetManipulation.WidgetObject;
import objects.Object;


/**
 * @author Bam
 */
public class SceneConnectProvider implements ConnectProvider
{

	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
		WidgetObject SourceWidObj = (WidgetObject) sourceWidget;
		WidgetObject TargetWidObj = (WidgetObject) targetWidget;


		// Checks if connection is possible.
		boolean possible = checkSupportsConnection(SourceWidObj.getObject(), TargetWidObj
				.getObject());


		if ( possible == false )
		{
			try
			{
				throw new ConnectionsIsNotPossible(SourceWidObj.getObject().getName(), TargetWidObj
						.getObject().getName(), "the 2 objects dont support the connection type.");
			}
			catch ( ConnectionsIsNotPossible e )
			{
				System.out.println("SceneConnectProvide - " + e.toString());
			}
		}
		else
		{
			ConnectionWidget connection = new ConnectionWidget(PrimeMain1.scene);
			connection.setTargetAnchorShape(AnchorShape.NONE);
			connection.setToolTipText("This is a connection");
			connection.getActions().addAction(new AdapterExtended());
			connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
			connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
			PrimeMain1.connectionLayer.addChild(connection);
		}
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



	private boolean checkSupportsConnection(Object a, Object b)
	{
		return ConnectionManagment.checkDeviceConnectiontypeSupport(a, b, "RJ-45");
	}



	private Class<?> getObjectClass(WidgetObject widget)
	{
		Class<?> type = widget.getObject().getClass();
		return type;
	}
}
