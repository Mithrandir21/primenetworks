package actions.canvasActions;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import logistical.checkLogic;
import managment.CanvasManagment;
import managment.ConnectionManagment;
import managment.DesktopCanvasManagment;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * This action deletes the currently selected {@link WidgetObject} on the
 * currently open {@link WorkareaCanvas}, including all the connection to and
 * from the {@link WidgetObject}.
 * 
 * @author Bahram Malaekeh
 */
public class ActionDeleteWidget extends AbstractSystemAction implements SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget object that is to be deleted
	private WidgetObject widObject = null;

	// The connections from the WidgetObject
	private Connection[] existingConnections = null;


	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteWidget(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteWidget(String text)
	{
		super(text);
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		performAction(true);
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
		widObject = null;
		existingConnections = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionDeleteObjectActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts.getString("actionDeleteObjectRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts.getString("actionDeleteObjectUndoPresNameText");
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
		WorkareaCanvasActions.removeObject(canvas, widObject, true);
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		// Adds the created WidgetObject to the classes canvas
		DesktopCanvasManagment.addWidgetToCanvas(widObject, widObject
				.getLocation(), canvas, true, true);


		if ( existingConnections != null )
		{
			for ( int i = 0; i < existingConnections.length; i++ )
			{
				try
				{
					// Find the two object which are to be connected on the
					// canvas
					WidgetObject sourceWidget = CanvasManagment
							.findWidgetObject(existingConnections[i]
									.getObject1(), canvas);
					WidgetObject targetWidget = CanvasManagment
							.findWidgetObject(existingConnections[i]
									.getObject2(), canvas);

					// Creates a new connection between the two widget objects
					Connection con = ConnectionManagment.makeConnection(canvas
							.getConnections(), PrimeMain.texts
							.getString("connection")
							+ canvas.getNumberOfWidgetsOnTheScene(),
							"Connection between "
									+ sourceWidget.getObject().getObjectName()
									+ " and "
									+ targetWidget.getObject().getObjectName()
									+ ".", sourceWidget.getObject(),
							targetWidget.getObject(), existingConnections[i]
									.getConnectionType(), checkLogic
									.getConClass(existingConnections[i]
											.getConnectionType()));

					// Creates the connection between the two devices on the
					// scene.
					WidgetExtendedConnection connection = new WidgetExtendedConnection(
							canvas.getScene(), con);


					// Adds the connection to the connections array of each
					// object.
					sourceWidget.getObject().addConnection(con);
					targetWidget.getObject().addConnection(con);

					// Creates the whole connection with all actions
					connection = ConnectionManagment
							.createWidgetExtendedConnection(canvas, con,
									connection, sourceWidget, targetWidget);


					// Adds the different actions
					ActionsAdder.makeWidgetConnectionReady(canvas, connection);

					// Add the connection the connection layer
					canvas.getConnectionLayer().addChild(connection);

					canvas.cleanUp();

				}
				// If there already exists a connection between the two given
				// objects.
				catch ( ConnectionDoesExist e )
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("connectionAlreadyExistsMsg"),
							PrimeMain.texts.getString("alert"),
							JOptionPane.ERROR_MESSAGE);
				}
				// If a connection between the two given objects is impossible.
				catch ( ConnectionsIsNotPossible e )
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("connectionNotPossibleMsg"),
							PrimeMain.texts.getString("alert"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Sets the current canvas as the actions canvas
		canvas = PrimeMain.currentCanvas;

		// Sets the currently selected widget on the current canvas as the
		// actions WidgetObject
		widObject = canvas.getCurrentWidgetObject();

		// Finds all the connections of the object(for the undo action)
		existingConnections = ConnectionManagment.findConnections(canvas
				.getConnections(), widObject.getObject());

		WorkareaCanvasActions.removeObject(canvas, widObject, true);

		if ( undoable )
		{
			canvas.addUndoableAction(this);
		}
	}
}
