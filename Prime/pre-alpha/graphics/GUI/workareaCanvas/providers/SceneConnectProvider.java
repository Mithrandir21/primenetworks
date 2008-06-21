	/**
	 * 
	 */
	package graphics.GUI.workareaCanvas.providers;

	import java.awt.Point;
	import javax.swing.JOptionPane;

import managment.ConnectionManagment;
import objects.Object;

import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;
import connections.Connection;
import connections.NetworkConnection;
import connections.WidgetExtendedConnection;
import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain1;

	/**
	 * TODO - Description NEEDED!
	 *
	 * @author Bahram Malaekeh
	 * 
	 */
	public class SceneConnectProvider implements ConnectProvider
	{

		public void createConnection(Widget sourceWidget, Widget targetWidget)
		{
			// Either the source or the target widget is not an object, which would
			// result in a NullPOinterException.
			if(sourceWidget == null || targetWidget == null)
			{
				return;
			}
			
			WidgetObject SourceWidObj = (WidgetObject) sourceWidget;
			WidgetObject TargetWidObj = (WidgetObject) targetWidget;
			
			Connection con = null;
			try 
			{
				con = ConnectionManagment.makeConnection(
						PrimeMain1.currentCanvas.getConnections(),
						"Connection"+PrimeMain1.currentCanvas.getNumberOfWidgetsOnTheScene(),
						"Connection between " + SourceWidObj.getObject().getName() + " and " +
						TargetWidObj.getObject().getName() + ".",
						SourceWidObj.getObject(), TargetWidObj.getObject(), "RJ-45", NetworkConnection.class);
				

				WidgetExtendedConnection connection = new WidgetExtendedConnection(PrimeMain1.currentCanvas.getScene(), con);

				ConnectionManagment.addConnection(con,false);

				connection.setTargetAnchorShape(AnchorShape.NONE);
				connection.setToolTipText("This is a connection");
				connection.getActions().addAction(new AdapterExtended());
				connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
				connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
				PrimeMain1.currentCanvas.getConnectionLayer().addChild(connection);
				
			} catch (ConnectionDoesExist e) 
			{
				JOptionPane.showMessageDialog(null,
						"There already exists a connection between these two objects.", 
						"alert", JOptionPane.ERROR_MESSAGE);
			} catch (ConnectionsIsNotPossible e) 
			{
				JOptionPane.showMessageDialog(null,
						"A connection between these two objects is not possible.", 
						"alert", JOptionPane.ERROR_MESSAGE);
			}

			if(con != null)
			{

			}
			else
			{

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