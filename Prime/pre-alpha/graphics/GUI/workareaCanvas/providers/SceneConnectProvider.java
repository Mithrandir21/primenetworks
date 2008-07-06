/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain1;

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
import connections.DeviceConnection;
import connections.NetworkConnection;
import connections.WidgetExtendedConnection;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SceneConnectProvider implements ConnectProvider
{

	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
		// Either the source or the target widget is not an object, which would
		// result in a NullPOinterException.
		if ( sourceWidget == null || targetWidget == null )
		{
			return;
		}

		// Casts the widgets to WidgetObjects because all of the widgets on the
		// scene are widgetObjects
		WidgetObject SourceWidObj = (WidgetObject) sourceWidget;
		WidgetObject TargetWidObj = (WidgetObject) targetWidget;

		// Gets the compatible interfaces between the two devices.
		String[] compInter = ConnectionManagment.getCompatibleConnectionInterfaces(SourceWidObj
				.getObject().getSupportedConnectionInterfaces(), TargetWidObj.getObject()
				.getSupportedConnectionInterfaces());


		// If the compatible interfaces between the two devices are not 0.
		if ( compInter.length > 0 )
		{
			
			// Creates a dialog that shows the different available connection types. 
			String conType = ListDialog.showDialog(null, null, "Connection type", "Connection",
					compInter, null, null);


			// If a connection type is not selected.
			if ( conType == null )
			{
				JOptionPane.showMessageDialog(null,
						"You must choose a connection type for a connection to be made.", "alert",
						JOptionPane.ERROR_MESSAGE);
			}
			// If the cancel butten is pressed.
			else if ( conType == "Cancelled" )
			{
				
			}
			// Else a connection type is choosen and the "Create connection" button is pressed.
			else
			{
				
				Connection con = null;
				try
				{
					// Gets the connection class, Networkconnection, deviceconnection, etc. 
					Class<?> conClass = getConClass(conType);

					// Creates the connection between the two devices(Not on the scene).
					con = ConnectionManagment.makeConnection(PrimeMain1.currentCanvas
							.getConnections(), "Connection"
							+ PrimeMain1.currentCanvas.getNumberOfWidgetsOnTheScene(),
							"Connection between " + SourceWidObj.getObject().getObjectName() + " and "
									+ TargetWidObj.getObject().getObjectName() + ".", SourceWidObj
									.getObject(), TargetWidObj.getObject(), conType, conClass);


					// Creates the coonecion between the two devices on the scene.
					WidgetExtendedConnection connection = new WidgetExtendedConnection(
							PrimeMain1.currentCanvas.getScene(), con);

					
					// Adds the connection to the connection array for the workareacanvas.
					ConnectionManagment.addConnection(con, false);

					// The array anchor
					connection.setTargetAnchorShape(AnchorShape.NONE);
					connection.setToolTipText("This is a connection");
					connection.getActions().addAction(new AdapterExtended());
					connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
					connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
					PrimeMain1.currentCanvas.getConnectionLayer().addChild(connection);

				}
				// If there already exists a connection between the two given objects.
				catch ( ConnectionDoesExist e )
				{
					JOptionPane.showMessageDialog(null,
							"There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
				}
				// If a connection between the two given objects is impossible.
				catch ( ConnectionsIsNotPossible e )
				{
					JOptionPane.showMessageDialog(null,
							"A connection between these two objects is not possible.", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					"These two objects cannot connect to eachother because they dont"
					+ " support the same ports.", "alert",
					JOptionPane.ERROR_MESSAGE);
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


	// TODO - Add user-added connection types
	private Class<?> getConClass(String type)
	{
		if ( type.equals("RJ-45") )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals("Wireless") )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals("USB") )
		{
			return DeviceConnection.class;
		}
		else if ( type.equals("FireWire") )
		{
			return DeviceConnection.class;
		}


		return null;
	}
}