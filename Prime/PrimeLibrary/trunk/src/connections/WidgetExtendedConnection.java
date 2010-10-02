/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package connections;


import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.modules.visual.action.FreeMoveControlPointProvider;

import widgets.WidgetObject;


/**
 * This class is an extension of the {@link ConnectionWidget ConnectionWidget}
 * class. It is used to represent a
 * connection between two {@link WidgetObject WidgetObjects} on a canvas. It
 * contains a pointer to an actual {@link Connection
 * Connection} which is the actual connection between two objects in the system.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetExtendedConnection extends ConnectionWidget
{
	private Connection connection = null;

	/**
	 * A constructor which takes a scene and a connection as parameters. The
	 * scene is necessary to create a {@link ConnectionWidget} and the
	 * connection is the actual connection between the two connected objects.
	 * 
	 * @param scene
	 *            The scene where the visual connection is to be located.
	 * @param con
	 *            The actual system connection between the two objects.
	 */
	public WidgetExtendedConnection(Scene scene, Connection con)
	{
		super(scene);
		this.setControlPointShape(PointShape.SQUARE_FILLED_BIG);
		this.setRoutingPolicy(ConnectionWidget.RoutingPolicy.DISABLE_ROUTING_UNTIL_END_POINT_IS_MOVED);
		this.setRouter(RouterFactory.createFreeRouter());
		this.getActions().addAction(
				ActionFactory.createAddRemoveControlPointAction());
		this.getActions()
				.addAction(
						ActionFactory
								.createMoveControlPointAction(new FreeMoveControlPointProvider()));
		this.connection = con;
	}




	/**
	 * Gets the actual connection between the two objects.
	 * 
	 * @return the connection
	 */
	public Connection getConnection()
	{
		return this.connection;
	}

	/**
	 * Sets the actual connection between the two objects.
	 * 
	 * @param connection
	 *            The connection between two system objects.
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}



	/**
	 * This function sets the description of the connection. It sets both the
	 * {@link WidgetExtendedConnection#setToolTipText(String)} and the
	 * {@link Connection} description.
	 */
	public void setConnectionDescription(String text)
	{
		// If the text is not null or empty
		if ( text != null && !text.equals("") )
		{
			this.setToolTipText(text);
			connection.setDescription(text);
		}
	}
}
