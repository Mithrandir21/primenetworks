package actions.canvasActions;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain1;
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
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;


public class ActionDeleteConnection extends AbstractSystemAction implements
		SystemActionInterface
{

	// The canvas where the deletion is taking place
	WorkareaCanvas canvas = null;

	// The connection that is to be deleted
	WidgetExtendedConnection existingConnections = null;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteConnection(String text, ImageIcon icon,
			WidgetExtendedConnection con)
	{
		super(text, icon);
		existingConnections = con;
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteConnection(String text, WidgetExtendedConnection con)
	{
		super(text);
		existingConnections = con;
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
		existingConnections = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return "Delete a Connection";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return "Re-delete a Connection";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return "Un-delete a Connection";
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
		// Removes the connection
		WorkareaCanvasActions.removeWidgetConnection(canvas,
				existingConnections);

		canvas.cleanUp();
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		if ( existingConnections != null )
		{
			try
			{
				Connection priorCon = existingConnections.getConnection();

				// Find the two object which are to be connected on the canvas
				WidgetObject sourceWidget = CanvasManagment.findWidgetObject(
						priorCon.getObject1(), canvas);
				WidgetObject targetWidget = CanvasManagment.findWidgetObject(
						priorCon.getObject2(), canvas);

				// Creates a new connection between the two widget objects
				Connection con = ConnectionManagment.makeConnection(canvas
						.getConnections(), "Connection"
						+ canvas.getNumberOfWidgetsOnTheScene(),
						"Connection between "
								+ sourceWidget.getObject().getObjectName()
								+ " and "
								+ targetWidget.getObject().getObjectName()
								+ ".", sourceWidget.getObject(), targetWidget
								.getObject(), priorCon.getConnectionType(),
						checkLogic.getConClass(priorCon.getConnectionType()));

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

				// Sets the newly created connection as the current connection
				existingConnections = connection;
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
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Sets the current canvas as the canvas
		canvas = PrimeMain1.currentCanvas;

		// Removes the connection
		WorkareaCanvasActions.removeWidgetConnection(canvas,
				existingConnections);

		canvas.cleanUp();

		if ( undoable )
		{
			canvas.addUndoableAction(this);
		}
	}
}
