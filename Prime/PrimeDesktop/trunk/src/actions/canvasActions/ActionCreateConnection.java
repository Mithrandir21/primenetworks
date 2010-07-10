/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package actions.canvasActions;


import exceptions.ConnectionDoesExist;
import exceptions.ConnectionsIsNotPossible;
import graphics.PrimeMain;
import graphics.GUI.ListDialog;
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
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * This action creates a connection between two given {@link WidgetObject
 * WidgetObjects}. The connection is created is both added to each of the given {@link WidgetObject WidgetObjects} and placed on
 * the given {@link WorkareaCanvas}.
 * This action contains a undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionCreateConnection extends AbstractSystemAction implements SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget source object
	private WidgetObject SourceWidObj = null;

	// The widget target object
	private WidgetObject TargetWidObj = null;

	// The Widget connection between the two Widgets
	private WidgetExtendedConnection widCon = null;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon, a {@link WorkareaCanvas}, a {@link WidgetObject} that is the source
	 * of the connection and a {@link WidgetObject} that is the target of the
	 * connection.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the visual connection is to be
	 *            placed on.
	 * @param source
	 *            The {@link WidgetObject} where the connection emanates.
	 * @param target
	 *            The {@link WidgetObject} that is the target of the connection.
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
	 * A constructor for the class that takes a string, the action name, a {@link WorkareaCanvas}, a {@link WidgetObject} that is
	 * the source
	 * of the connection and a {@link WidgetObject} that is the target of the
	 * connection.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the visual connection is to be
	 *            placed on.
	 * @param source
	 *            The {@link WidgetObject} where the connection emanates.
	 * @param target
	 *            The {@link WidgetObject} that is the target of the connection.
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
		return PrimeMain.texts
				.getString("actionCreateConnectionActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionCreateConnectionRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionCreateConnectionUndoPresNameText");
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
						.getConnections(), PrimeMain.texts
						.getString("connection")
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
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("connectionAlreadyExistsMsg"),
						PrimeMain.texts.getString("alert"),
						JOptionPane.ERROR_MESSAGE);
			}
			// If a connection between the two given objects is impossible.
			catch ( ConnectionsIsNotPossible e )
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("connectionNotPossibleMsg"), PrimeMain.texts
						.getString("alert"), JOptionPane.ERROR_MESSAGE);
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
	public void performAction(boolean undoable)
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
			String conType = ListDialog.showDialog(null, null, PrimeMain.texts
					.getString("actionCreateConnectionTypeLabel"),
					PrimeMain.texts.getString("connection"), compInter, null,
					null);

			// If a connection type is not selected.
			if ( conType == null && compInter.length > 1 )
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("actionCreateConnectionChooseTypeText"),
						PrimeMain.texts.getString("alert"),
						JOptionPane.ERROR_MESSAGE);
			}
			// If the close or cancel butten is pressed.
			else if ( conType != null
					&& (conType.equals("Closed") || conType.equals("Cancelled")) )
			{
				// Does nothing on close or cancel
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
							.getConnections(), PrimeMain.texts
							.getString("connection")
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

					if ( undoable )
					{
						canvas.addUndoableAction(this);
					}
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
		else
		{
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("connectionNotPossibleBecauseNoPortsMatchMsg"),
					PrimeMain.texts.getString("alert"),
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
