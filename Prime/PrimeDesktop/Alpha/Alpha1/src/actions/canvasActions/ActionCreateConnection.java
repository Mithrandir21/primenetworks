package actions.canvasActions;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import logistical.checkLogic;
import managment.ConnectionManagment;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgetManipulation.Providers.ListDialog;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ActionCreateConnection extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget source object
	private WidgetObject SourceWidObj = null;

	// The widget target object
	private WidgetObject TargetWidObj = null;

	// The Wiget connection between the two Widgets
	private WidgetExtendedConnection widCon = null;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionCreateConnection(String text, ImageIcon icon,
			WorkareaCanvas canvas, WidgetObject source, WidgetObject target)
	{
		super(text, icon);
		this.canvas = canvas;
		SourceWidObj = source;
		TargetWidObj = target;
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionCreateConnection(String text, WorkareaCanvas canvas,
			WidgetObject source, WidgetObject target)
	{
		super(text);
		this.canvas = canvas;
		SourceWidObj = source;
		TargetWidObj = target;
	}




	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		performAction();
	}



	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canRedo()
	 */
	@Override
	public boolean canRedo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canUndo()
	 */
	@Override
	public boolean canUndo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#die()
	 */
	@Override
	public void die()
	{
		canvas = null;
		SourceWidObj = null;
		TargetWidObj = null;
		widCon = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return "Create a connection between two objects";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return "Re-Create a connection between two objects";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return "Remove a newly created connection between two objects";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#isSignificant()
	 */
	@Override
	public boolean isSignificant()
	{
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#redo()
	 */
	@Override
	public void redo() throws CannotRedoException
	{
		if ( widCon != null )
		{
			try
			{
				Connection priorCon = widCon.getConnection();

				// Creates a new connection between the two widget objects
				Connection con = ConnectionManagment.makeConnection(canvas
						.getConnections(), "Connection"
						+ canvas.getNumberOfWidgetsOnTheScene(),
						"Connection between "
								+ SourceWidObj.getObject().getObjectName()
								+ " and "
								+ TargetWidObj.getObject().getObjectName()
								+ ".", SourceWidObj.getObject(), TargetWidObj
								.getObject(), priorCon.getConnectionType(),
						checkLogic.getConClass(priorCon.getConnectionType()));

				// Creates the connection between the two devices on the
				// scene.
				WidgetExtendedConnection connection = new WidgetExtendedConnection(
						canvas.getScene(), con);


				// Adds the connection to the connections array of each
				// object.
				SourceWidObj.getObject().addConnection(con);
				TargetWidObj.getObject().addConnection(con);

				// Creates the whole connection with all actions
				connection = ConnectionManagment
						.createWidgetExtendedConnection(canvas, con,
								connection, SourceWidObj, TargetWidObj);


				// Adds the different actions
				ActionsAdder.makeWidgetConnectionReady(canvas, connection);

				// Add the connection the connection layer
				canvas.getConnectionLayer().addChild(connection);

				canvas.cleanUp();

				// Sets the newly created connection as the current connection
				widCon = connection;
			}
			// If there already exists a connection between the two given
			// objects.
			catch ( ConnectionDoesExist e )
			{
				JOptionPane
						.showMessageDialog(
								null,
								"There already exists a connection between these two objects.",
								"alert", JOptionPane.ERROR_MESSAGE);
			}
			// If a connection between the two given objects is impossible.
			catch ( ConnectionsIsNotPossible e )
			{
				JOptionPane
						.showMessageDialog(
								null,
								"A connection between these two objects is not possible.",
								"alert", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		// Removes the connection
		WorkareaCanvasActions.removeWidgetConnection(canvas, widCon);

		canvas.cleanUp();
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction()
	{
		// Either the source or the target widget is not an object, which would
		// result in a NullPOinterException.
		if ( SourceWidObj == null || TargetWidObj == null )
		{
			return;
		}

		// Gets the compatible interfaces between the two devices.
		String[] compInter = ConnectionManagment
				.getCompatibleConnectionInterfaces(SourceWidObj.getObject()
						.getSupportedConnectionInterfaces(), TargetWidObj
						.getObject().getSupportedConnectionInterfaces());


		// If the compatible interfaces between the two devices are not 0.
		if ( compInter.length > 0 )
		{

			// Creates a dialog that shows the different available connection
			// types.
			String conType = ListDialog.showDialog(null, null,
					"Connection type", "Connection", compInter, null, null);


			// If a connection type is not selected.
			if ( conType == null && compInter.length > 1 )
			{
				JOptionPane
						.showMessageDialog(
								null,
								"You must choose a connection type for a connection to be made.",
								"alert", JOptionPane.ERROR_MESSAGE);
			}
			// If the cancel butten is pressed.
			else if ( conType == "Cancelled" )
			{

			}
			// Else a connection type is chosen and the "Create connection"
			// button is pressed.
			else
			{
				// If there is only one connection type and the user has chosen
				// to create a connection
				// that one connection type will be set as the conType.
				if ( compInter.length == 1 )
				{
					conType = compInter[0];
				}

				Connection con = null;
				try
				{
					// Gets the connection class, NetworkConnection,
					// DeviceConnection, etc.
					Class<?> conClass = checkLogic.getConClass(conType);

					// Creates the connection between the two devices(Not on the
					// scene).
					con = ConnectionManagment.makeConnection(canvas
							.getConnections(), "Connection"
							+ canvas.getNumberOfWidgetsOnTheScene(),
							"Connection between "
									+ SourceWidObj.getObject().getObjectName()
									+ " and "
									+ TargetWidObj.getObject().getObjectName()
									+ ".", SourceWidObj.getObject(),
							TargetWidObj.getObject(), conType, conClass);


					// Adds the connection to the connections array of each
					// object.
					SourceWidObj.getObject().addConnection(con);
					TargetWidObj.getObject().addConnection(con);


					// Creates the connection between the two devices on the
					// scene.
					WidgetExtendedConnection connection = new WidgetExtendedConnection(
							canvas.getScene(), con);


					// Creates the whole connection with all actions
					connection = ConnectionManagment
							.createWidgetExtendedConnection(canvas, con,
									connection, SourceWidObj, TargetWidObj);

					// Adds the different actions
					ActionsAdder.makeWidgetConnectionReady(canvas, connection);

					// Add the connection the connection layer
					canvas.getConnectionLayer().addChild(connection);

					// Sets the widget connection as the actions widget
					// connection
					widCon = connection;

					canvas.addUndoableAction(this);
				}
				// If there already exists a connection between the two given
				// objects.
				catch ( ConnectionDoesExist e )
				{
					JOptionPane
							.showMessageDialog(
									null,
									"There already exists a connection between these two objects.",
									"alert", JOptionPane.ERROR_MESSAGE);
				}
				// If a connection between the two given objects is impossible.
				catch ( ConnectionsIsNotPossible e )
				{
					JOptionPane
							.showMessageDialog(
									null,
									"A connection between these two objects is not possible.",
									"alert", JOptionPane.ERROR_MESSAGE);
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
}
