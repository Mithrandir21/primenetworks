/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;

import javax.swing.JOptionPane;

import managment.ConnectionManagment;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.Providers.ListDialog;
import widgetManipulation.Providers.SceneConnectProvider;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class GUIsceneConnectProvider extends SceneConnectProvider
{

	/**
	 * A constructor for the class that sets the {@link WorkareaCanvas} that an instance of this class will be applied
	 * to.
	 */
	public GUIsceneConnectProvider(WorkareaCanvas canvas)
	{
		super(canvas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.ConnectProvider#createConnection(org.netbeans .api.visual.widget.Widget,
	 * org.netbeans.api.visual.widget.Widget)
	 */
	@Override
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
					con = ConnectionManagment.makeConnection(getCanvas().getConnections(), "Connection"
							+ getCanvas().getNumberOfWidgetsOnTheScene(), "Connection between "
							+ SourceWidObj.getObject().getObjectName() + " and "
							+ TargetWidObj.getObject().getObjectName() + ".", SourceWidObj.getObject(), TargetWidObj
							.getObject(), conType, conClass);


					// Adds the connection to the connections array of each object.
					SourceWidObj.getObject().addConnection(con);
					TargetWidObj.getObject().addConnection(con);


					// Creates the connection between the two devices on the scene.
					WidgetExtendedConnection connection = new WidgetExtendedConnection(getCanvas().getScene(), con);


					// Creates the whole connection with all actions
					connection = ConnectionManagment.createWidgetExtendedConnection(getCanvas(), con, connection,
							SourceWidObj, TargetWidObj);

					// Adds the different actions
					ActionsAdder.makeWidgetConnectionReady(getCanvas(), connection);

					// Add the connection the connection layer
					getCanvas().getConnectionLayer().addChild(connection);
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
}
