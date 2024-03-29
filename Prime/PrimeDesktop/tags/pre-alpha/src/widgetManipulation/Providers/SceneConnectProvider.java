/**
 * 
 */
package widgetManipulation.Providers;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.ListDialog;

import java.awt.Point;

import javax.swing.JOptionPane;

import managment.ConnectionManagment;
import objects.Object;

import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;
import connections.Connection;
import connections.DeviceConnection;
import connections.NetworkConnection;
import connections.WidgetExtendedConnection;


/**
 * This class controls the creation of graphical connections on a {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class SceneConnectProvider implements ConnectProvider
{
	/**
	 * The WorkareaCanvas this ConnectProvider belongs to.
	 */
	private WorkareaCanvas canvas;


	/**
	 * A constructor for the class that sets the {@link WorkareaCanvas} that an instance of this class will be applied
	 * to.
	 */
	public SceneConnectProvider(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#createConnection(org.netbeans .api.visual.widget.Widget,
	 * org.netbeans.api.visual.widget.Widget)
	 */
	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
		// Either the source or the target widget is not an object, which would result in a NullPOinterException.
		if ( sourceWidget == null || targetWidget == null )
		{
			return;
		}

		// Casts the widgets to WidgetObjects because all of the widgets on the
		// scene are widgetObjects
		WidgetObject SourceWidObj = (WidgetObject) sourceWidget;
		WidgetObject TargetWidObj = (WidgetObject) targetWidget;

		// Gets the compatible interfaces between the two devices.
		String[] compInter = ConnectionManagment.getCompatibleConnectionInterfaces(SourceWidObj.getObject()
				.getSupportedConnectionInterfaces(), TargetWidObj.getObject().getSupportedConnectionInterfaces());


		// If the compatible interfaces between the two devices are not 0.
		if ( compInter.length > 0 )
		{

			// Creates a dialog that shows the different available connection types.
			String conType = ListDialog.showDialog(null, null, "Connection type", "Connection", compInter, null, null);


			// If a connection type is not selected.
			if ( conType == null && compInter.length > 1 )
			{
				JOptionPane.showMessageDialog(null, "You must choose a connection type for a connection to be made.",
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			// If the cancel butten is pressed.
			else if ( conType == "Cancelled" )
			{

			}
			// Else a connection type is chosen and the "Create connection" button is pressed.
			else
			{
				// If there is only one connection type and the user has chosen to create a connection
				// that one connection type will be set as the conType.
				if ( compInter.length == 1 )
				{
					conType = compInter[0];
				}

				Connection con = null;
				try
				{
					// Gets the connection class, NetworkConnection, DeviceConnection, etc.
					Class<?> conClass = getConClass(conType);

					// Creates the connection between the two devices(Not on the scene).
					con = ConnectionManagment.makeConnection(canvas.getConnections(), "Connection"
							+ canvas.getNumberOfWidgetsOnTheScene(), "Connection between "
							+ SourceWidObj.getObject().getObjectName() + " and "
							+ TargetWidObj.getObject().getObjectName() + ".", SourceWidObj.getObject(), TargetWidObj
							.getObject(), conType, conClass);


					// Adds the connection to the connections array of each object.
					SourceWidObj.getObject().addConnection(con);
					TargetWidObj.getObject().addConnection(con);


					// Creates the connection between the two devices on the scene.
					WidgetExtendedConnection connection = new WidgetExtendedConnection(canvas.getScene(), con);


					// Creates the whole connection with all actions
					connection = ConnectionManagment.createWidgetExtendedConnection(canvas, con, connection,
							SourceWidObj, TargetWidObj);

					// Add the connection the connection layer
					canvas.getConnectionLayer().addChild(connection);
					PrimeMain1.updateCanvasAndObjectInfo();
				}
				// If there already exists a connection between the two given
				// objects.
				catch ( ConnectionDoesExist e )
				{
					JOptionPane.showMessageDialog(null, "There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
				}
				// If a connection between the two given objects is impossible.
				catch ( ConnectionsIsNotPossible e )
				{
					JOptionPane.showMessageDialog(null, "A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "These two objects cannot connect to eachother because they dont"
					+ " support the same ports.", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#hasCustomTargetWidgetResolver
	 * (org.netbeans.api.visual.widget.Scene)
	 */
	public boolean hasCustomTargetWidgetResolver(Scene scene)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#isSourceWidget(org.netbeans .api.visual.widget.Widget)
	 */
	public boolean isSourceWidget(Widget sourceWidget)
	{
		if ( sourceWidget instanceof WidgetObject )
		{
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#isTargetWidget(org.netbeans .api.visual.widget.Widget,
	 * org.netbeans.api.visual.widget.Widget)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#resolveTargetWidget(org .netbeans.api.visual.widget.Scene,
	 * java.awt.Point)
	 */
	public Widget resolveTargetWidget(Scene sourceWidget, Point sceneLocation)
	{
		return null;
	}



	/**
	 * Checks whether or not the two given {@link Object Objects} support a connection of {@link NetworkConnection}.
	 */
	private boolean checkSupportsNetworkConnection(Object a, Object b)
	{
		return ConnectionManagment.checkDeviceConnectiontypeSupport(a, b, "RJ-45");
	}



	/**
	 * Gets the Class of the given {@link WidgetObject}.
	 * 
	 * @return The {@link Class} of the given {@link WidgetObject}.
	 */
	private Class<?> getObjectClass(WidgetObject widget)
	{
		Class<?> type = widget.getObject().getClass();
		return type;
	}


	// FIXME - Add user-added connection types
	/**
	 * Returns a connection class depending on the given string. MUST BE WORKED ON.
	 * 
	 * @param type
	 * @return
	 */
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