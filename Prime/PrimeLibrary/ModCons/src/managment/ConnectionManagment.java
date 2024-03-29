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
package managment;


import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import logistical.cleanup;
import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;

import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.ConnectionUtils;
import connections.DeviceConnection;
import connections.InternalConnection;
import connections.NetworkConnection;
import connections.WidgetExtendedConnection;
import containers.ConnectionContainer;
import exceptions.ConnectionDoesExist;
import exceptions.ConnectionDoesNotExist;
import exceptions.ConnectionsIsNotPossible;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * A method class for the management of any type of connection within the
 * system. A connection is a physical link, be it wired or wireless, between two
 * components in the system. Examples of this are connections between switches
 * and computers, swithes and other switches, computers and printers, servers
 * and routers and so on. <br>
 * The class contains methodes like making connections, breaking connection or
 * changing connection. There are additional methods that help with the
 * managment of connections.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionManagment
{

	/**
	 * Creates connections between two components. This can be both device
	 * connections, network connections or internal connections.
	 * 
	 * @return Returns, if possible, a connection object that represents a
	 *         connection between the two objects.
	 */
	@SuppressWarnings("unchecked")
	public static Connection makeConnection(Connection[] existingConnections,
			String conName, String conDesc, Object objectA, Object objectB,
			String type, Class conClass) throws ConnectionDoesExist,
			ConnectionsIsNotPossible
	{
		Connection connection = null;


		/*
		 * A connection is made between them.
		 */
		if ( conClass.equals(DeviceConnection.class) )
		{
			connection = new DeviceConnection(conName, conDesc, objectA,
					objectB, type);
		}
		else if ( conClass.equals(InternalConnection.class) )
		{
			connection = new InternalConnection(conName, conDesc, objectA,
					objectB, type);
		}
		else
		{
			connection = new NetworkConnection(conName, conDesc, objectA,
					objectB, type);
		}



		// Checks to see if there is any previous connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(),
					objectB.getObjectName());
		}


		// Checks to see if both the devices support the type of connection
		if ( !(checkDeviceConnectiontypeSupport(objectA, objectB, type)) )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(),
					objectB.getObjectName(), "");
		}

		// Checks ports in both objects and sets if possible
		if ( !(checkAndSetPortAvailability(objectA, objectB, type, connection)) )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(),
					objectB.getObjectName(), "");
		}


		return connection;
	}



	/**
	 * Modifies a {@link WidgetExtendedConnection} object to be represented by a
	 * specific {@link BasicStroke}. It also sets the source and target anchors
	 * of the WidgetExtendedConnection. The BasicStroke depends on the type of
	 * connection between the two objects. If the it is a wireless connection,
	 * the stroke will be dotted. If the connection is by a physical cable, it
	 * will be a straight line.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas network} that the connection exists
	 *            in.
	 * @param con
	 *            The system {@link Connection} that is the actual connection
	 *            between the Objects.
	 * @param connection
	 *            The {@link WidgetExtendedConnection} widget representing the
	 *            connection in the the network.
	 * @param SourceWidObj
	 *            The {@link WidgetObject} the connection originates at.
	 * @param TargetWidObj
	 *            The {@link WidgetObject} the connection is destined for.
	 * @return A {@link WidgetExtendedConnection} with a specific
	 *         {@link BasicStroke}.
	 */
	public static WidgetExtendedConnection createWidgetExtendedConnection(
			WorkareaCanvas canvas, Connection con,
			WidgetExtendedConnection connection, WidgetObject SourceWidObj,
			WidgetObject TargetWidObj)
	{
		// Adds the connection to the connection array for the WorkareaCanvas.
		ConnectionManagment.addConnection(con, false, canvas);

		BasicStroke stroke = null;

		if ( con.getConnectionType().equals(ConnectionUtils.Wireless) )
		{
			stroke = new BasicStroke(1.0f, // Width
					BasicStroke.JOIN_BEVEL, // End cap
					BasicStroke.CAP_BUTT, // Join style
					5.0f, // Miter limit
					new float[] { 21.0f, 13.0f }, // Dash pattern
					0.0f); // Dash phase
		}
		else
		{
			stroke = new BasicStroke(1.0f,// Width
					BasicStroke.JOIN_BEVEL, // End cap
					BasicStroke.CAP_BUTT, // Join style
					5.0f, // Miter limit
					new float[] { 1.0f }, // Straight pattern
					0.0f); // Dash phase
		}

		// The array anchor
		connection.setTargetAnchorShape(AnchorShape.NONE);
		connection.setStroke(stroke);
		connection.setSourceAnchor(AnchorFactory
				.createRectangularAnchor(SourceWidObj));
		connection.setTargetAnchor(AnchorFactory
				.createRectangularAnchor(TargetWidObj));


		return connection;
	}


	/**
	 * Breaks connections between two components in the system. It removes the
	 * connection from the array of existing connections. This method throws
	 * {@link exceptions.ConnectionDoesNotExist ConnectionDoesNotExist}
	 * exception, if there is no connection between the two given objects.
	 * 
	 * @return Returns the given connections array without the connection
	 *         between the two given object. The array is cleaned for any empty
	 *         indexes.
	 */
	public static Connection[] breakConnection(
			Connection[] existingConnections, Connection con)
			throws ConnectionDoesNotExist
	{
		if ( con != null )
		{
			Object objectA = con.getObject1();
			Object objectB = con.getObject2();

			// Checks to see if there really is a connection between A and B
			if ( !(checkConnectionExistence(existingConnections, objectA,
					objectB)) )
			{
				throw new ConnectionDoesNotExist(objectA.getObjectName(),
						objectB.getObjectName());
			}


			// If the connection is either LAN, COAX or FIBER
			if ( con.getConnectionType().equals(ConnectionUtils.RJ45)
					|| con.getConnectionType().equals(ConnectionUtils.Coax)
					|| con.getConnectionType().equals(ConnectionUtils.Fiber)
					|| con.getConnectionType().equals(ConnectionUtils.Wireless) )
			{
				// Pointers to network objects, either integrated or external.
				Object ObjectAnic = null;
				Object ObjectBnic = null;


				/**
				 * Checks the external NICs of the first object
				 */
				// Object 1
				try
				{
					// Gets all the ExternalNICs
					Object[] extNICsObject1 = objectA
							.getSpesificComponents(ExternalNetworksCard.class);

					/*
					 * As long as the array is not null and there is at least on
					 * Object in the array, the array will be stepped through
					 * and every ExternalNIC will be check to see if it contains
					 * a pointer to the connection given.
					 */
					if ( extNICsObject1 != null && (extNICsObject1.length > 0) )
					{
						for ( int i = 0; i < extNICsObject1.length; i++ )
						{
							ExternalNetworksCard extNIC = (ExternalNetworksCard) extNICsObject1[i];

							/*
							 * Checks if the connections list contains the given
							 * connection and if the NIC has the 2. object in
							 * its connected list.
							 */
							if ( extNIC.getConnections().contains(con)
									&& (extNIC
											.getConnectedObjectBySerial(objectB
													.getObjectSerial()) != null) )
							{
								ObjectAnic = extNIC;
								// Ends the loop.
								i = extNICsObject1.length;
							}
						}
					}
				}
				catch ( ObjectNotFoundException e1 )
				{
					// Log entry
				}


				// Object 2
				try
				{
					// Gets all the ExternalNICs
					Object[] extNICsObject2 = objectB
							.getSpesificComponents(ExternalNetworksCard.class);

					/*
					 * As long as the array is not null and there is at least on
					 * Object in the array, the array will be stepped through
					 * and every ExternalNIC will be check to see if it contains
					 * a pointer to the connection given.
					 */
					if ( extNICsObject2 != null && (extNICsObject2.length > 0) )
					{
						for ( int i = 0; i < extNICsObject2.length; i++ )
						{
							ExternalNetworksCard extNIC = (ExternalNetworksCard) extNICsObject2[i];

							/*
							 * Checks if the connections list contains the given
							 * connection and if the NIC has the 2. object in
							 * its connected list.
							 */
							if ( extNIC.getConnections().contains(con)
									&& (extNIC
											.getConnectedObjectBySerial(objectA
													.getObjectSerial()) != null) )
							{
								ObjectBnic = extNIC;
								// Ends the loop.
								i = extNICsObject2.length;
							}
						}
					}
				}
				catch ( ObjectNotFoundException e1 )
				{
					// Log entry
				}


				/**
				 * Checks the internal NICs NON-MOTHERBOARD.
				 */
				// Object 1
				try
				{
					// Gets all the InternalNIC
					Object[] intNICsObject1 = objectA
							.getSpesificComponents(InternalNetworksCard.class);

					/*
					 * As long as the array is not null and there is at least on
					 * Object in the array, the array will be stepped through
					 * and every InternalNIC will be check to see if it contains
					 * a pointer to the connection given.
					 */
					if ( intNICsObject1 != null && (intNICsObject1.length > 0) )
					{
						for ( int i = 0; i < intNICsObject1.length; i++ )
						{
							InternalNetworksCard intNIC = (InternalNetworksCard) intNICsObject1[i];

							/*
							 * Checks if the connections list contains the given
							 * connection and if the NIC has the 2. object in
							 * its connected list.
							 */
							if ( intNIC.getConnections().contains(con)
									&& (intNIC
											.getConnectedObjectBySerial(objectB
													.getObjectSerial()) != null) )
							{
								ObjectAnic = intNIC;
								// Ends the loop.
								i = intNICsObject1.length;
							}
						}
					}
				}
				catch ( ObjectNotFoundException e1 )
				{
					// Log entry
				}


				// Object 2
				try
				{
					// Gets all the InternalNIC
					Object[] intNICsObject2 = objectB
							.getSpesificComponents(InternalNetworksCard.class);

					/*
					 * As long as the array is not null and there is at least on
					 * Object in the array, the array will be stepped through
					 * and every InternalNIC will be check to see if it contains
					 * a pointer to the connection given.
					 */
					if ( intNICsObject2 != null && (intNICsObject2.length > 0) )
					{
						for ( int i = 0; i < intNICsObject2.length; i++ )
						{
							InternalNetworksCard intNIC = (InternalNetworksCard) intNICsObject2[i];

							/*
							 * Checks if the connections list contains the given
							 * connection and if the NIC has the 2. object in
							 * its connected list.
							 */
							if ( intNIC.getConnections().contains(con)
									&& (intNIC
											.getConnectedObjectBySerial(objectA
													.getObjectSerial()) != null) )
							{
								ObjectBnic = intNIC;
								// Ends the loop.
								i = intNICsObject2.length;
							}
						}
					}
				}
				catch ( ObjectNotFoundException e1 )
				{
					// Log entry
				}


				/**
				 * Checks the internal NICs on Motherboard.
				 */
				if ( ObjectAnic == null )
				{
					Motherboard objectAmotherboard = null;

					try
					{
						// Since any object only has one motherboard this is a
						// safe bet.
						objectAmotherboard = (Motherboard) objectA
								.getSpesificComponents(Motherboard.class)[0];
					}
					catch ( ObjectNotFoundException e )
					{
						JOptionPane.showMessageDialog(null, con.getObject1()
								.getObjectName()
								+ " does not have a motherboard.", "alert",
								JOptionPane.ERROR_MESSAGE);

						return null;
					}


					ObjectAnic = ComponentsManagment.getIntNICconnectedBy(
							objectAmotherboard, con);
				}


				if ( ObjectBnic == null )
				{
					Motherboard objectBmotherboard = null;

					try
					{
						// Since any object only has one motherboard this is a
						// safe bet.
						objectBmotherboard = (Motherboard) objectB
								.getSpesificComponents(Motherboard.class)[0];
					}
					catch ( ObjectNotFoundException e )
					{
						JOptionPane.showMessageDialog(null, con.getObject2()
								.getObjectName()
								+ " does not have a motherboard.", "alert",
								JOptionPane.ERROR_MESSAGE);

						return null;
					}


					ObjectBnic = ComponentsManagment.getIntNICconnectedBy(
							objectBmotherboard, con);
				}


				if ( ObjectAnic == null || ObjectBnic == null )
				{
					throw new ConnectionDoesNotExist(objectA.getObjectName(),
							objectB.getObjectName());
				}


				/**
				 * When the function gets to this point it should have found two
				 * NICs (internal or external) which point to each other by a
				 * Connection.
				 */

				// Removes the connections in the first NIC.
				if ( ObjectAnic instanceof ExternalNetworksCard )
				{
					ExternalNetworksCard exNIC = (ExternalNetworksCard) ObjectAnic;

					exNIC.removeConnectedObject(objectB);
					exNIC.removeConnection(con);
				}
				else if ( ObjectAnic instanceof InternalNetworksCard )
				{
					InternalNetworksCard intNIC = (InternalNetworksCard) ObjectAnic;

					intNIC.removeConnectedObject(objectB);
					intNIC.removeConnection(con);
				}


				// Removes the connections in the second NIC.
				if ( ObjectBnic instanceof ExternalNetworksCard )
				{
					ExternalNetworksCard exNIC = (ExternalNetworksCard) ObjectBnic;

					exNIC.removeConnectedObject(objectA);
					exNIC.removeConnection(con);
				}
				else if ( ObjectBnic instanceof InternalNetworksCard )
				{
					InternalNetworksCard intNIC = (InternalNetworksCard) ObjectBnic;

					intNIC.removeConnectedObject(objectA);
					intNIC.removeConnection(con);
				}



				try
				{
					// Removing the objects from the Connected Objects list of
					// the Object (NOT NIC object, but Object object).
					objectA.removeConnectedDevices(objectB);
					objectB.removeConnectedDevices(objectA);

					// Removes the connection from the list of the Object
					objectA.removeConnection(con);
					objectB.removeConnection(con);
				}
				catch ( ObjectNotFoundInArrayException e )
				{
					System.out.println(e.getMessage());
				}


				return ArrayManagment.removeGivenConnectionFromConArray(
						existingConnections, con);
			}
			/**
			 * This might be anything from USB to FIREWIRE.
			 */
			else
			{
				for ( int i = 0; i < existingConnections.length; i++ )
				{
					if ( existingConnections[i] != null )
					{
						long obj1Serial = existingConnections[i].getObject1()
								.getObjectSerial();
						long obj2Serial = existingConnections[i].getObject2()
								.getObjectSerial();


						/*
						 * Checks to see if either object A or object B is the
						 * first object in any connection. Then if it finds
						 * object A or object B as the first object, it looks
						 * for object A or object B at the same index as object
						 * A or object B.
						 */
						if ( ((obj1Serial == objectA.getObjectSerial()) && (obj2Serial == objectB
								.getObjectSerial()))
								|| ((obj1Serial == objectB.getObjectSerial()) && (obj2Serial == objectA
										.getObjectSerial())) )
						{
							// Removes the both objects from each others
							// internal connected device array.
							objectA.removeConnection(existingConnections[i]);
							objectB.removeConnection(existingConnections[i]);

							try
							{
								objectA.removeConnectedDevices(objectB);
								objectB.removeConnectedDevices(objectA);
							}
							catch ( ObjectNotFoundInArrayException e )
							{
								System.out.println(e.getMessage());
							}

							existingConnections[i] = null;
						}
					}
				}
			}

			// Removes any null-pointers in the array of connections
			existingConnections = cleanup.cleanObjectArray(existingConnections);
		}

		return existingConnections;
	}




	/**
	 * Finds and returns all connections, in the given connection array, where
	 * the given Object is involved.
	 */
	public static Connection[] findConnections(
			Connection[] existingConnections, Object object)
	{
		// If the existing connection array is not null or empty
		if ( existingConnections != null && (existingConnections.length != 0) )
		{
			// Creates an array with the length of the existing connections
			Connection[] foundConnections = new Connection[existingConnections.length];

			for ( int i = 0; i < existingConnections.length; i++ )
			{
				if ( existingConnections[i] != null )
				{
					// If the object is found as any part of the connection
					if ( (existingConnections[i].getObject1().getObjectSerial() == object
							.getObjectSerial())
							|| (existingConnections[i].getObject2()
									.getObjectSerial() == object
									.getObjectSerial()) )
					{
						foundConnections[i] = existingConnections[i];
					}
				}
			}

			// Removes any null-pointers in the array of connections
			foundConnections = cleanup.cleanObjectArray(foundConnections);

			return foundConnections;
		}

		return null;
	}



	/**
	 * Finds and returns all {@link WidgetExtendedConnection} that contain a
	 * {@link Connection} in the given {@link Connection} array where
	 * the given Object is involved.
	 */
	public static WidgetExtendedConnection[] findWidgetConnections(
			WorkareaCanvas canvas, Connection[] existingConnections,
			Object object)
	{
		// If the existing connection array is not null or empty
		if ( existingConnections != null && existingConnections.length != 0 )
		{
			// Gets all the connections involving the given object
			Connection[] foundConnections = findConnections(
					existingConnections, object);


			// If there are any connections found
			if ( foundConnections != null && foundConnections.length != 0 )
			{
				WidgetExtendedConnection[] foundWidgetConnections = new WidgetExtendedConnection[foundConnections.length];

				for ( int i = 0; i < foundConnections.length; i++ )
				{
					if ( foundConnections[i] != null )
					{
						// Attempts to find the WidgetExtendedConnection that
						// contains the current connection
						WidgetExtendedConnection widConTemp = findWidgetConnection(
								canvas, foundConnections[i]);

						// If the object is found as any part of the connection
						if ( widConTemp != null )
						{

							foundWidgetConnections[i] = widConTemp;
						}
					}
				}

				// Removes any null-pointers in the array of connections
				foundWidgetConnections = cleanup
						.cleanObjectArray(foundWidgetConnections);

				return foundWidgetConnections;
			}
		}

		return null;
	}





	/**
	 * This method changes a connection between A and B, to be a connection
	 * between A and C. This is done by changing the second object in the
	 * connection to point to object C instead of object B. There is also checks
	 * done to see if there really is a connection between A and B, and if there
	 * is any prior connections between A and C.
	 * 
	 * @return Returns an array of connections where the connection between A
	 *         and B has been changed to a connection between A and C.
	 */
	public static Connection[] changeConnection(
			Connection[] existingConnections, Object objectA,
			Object objectToBeRemoved, Object objectC)
			throws ConnectionDoesNotExist, ConnectionDoesExist
	{

		// First checks to see if there exist a connection between object a and
		// "ToBeRemoved"
		if ( !(checkConnectionExistence(existingConnections, objectA,
				objectToBeRemoved)) )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(),
					objectToBeRemoved.getObjectName());
		}

		// Then checks to see if there already exists a connection between
		// object a and c
		if ( checkConnectionExistence(existingConnections, objectA, objectC) )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(),
					objectToBeRemoved.getObjectName());
		}


		/*
		 * Checks to see if object A is the first object in any connection. Then
		 * if it find object A as the first object, it looks for object B at the
		 * same index as object A.
		 */
		for ( int i = 0; i < existingConnections.length; i++ )
		{
			if ( existingConnections[i] != null )
			{
				// If the first object is found
				if ( existingConnections[i].getObject1().getObjectSerial() == objectA
						.getObjectSerial() )
				{
					// If the second object is found at the same index as the
					// first one
					if ( existingConnections[i].getObject2().getObjectSerial() == objectToBeRemoved
							.getObjectSerial() )
					{
						existingConnections[i].setObject2(objectC);
					}
				}
			}
		}

		/*
		 * Checks to see if object B is the first object in any connection. Then
		 * if it find object B as the first object, it looks for object A at the
		 * same index as object B.
		 */
		for ( int i = 0; i < existingConnections.length; i++ )
		{
			if ( existingConnections[i] != null )
			{
				// If the second object is found
				if ( existingConnections[i].getObject1().getObjectSerial() == objectToBeRemoved
						.getObjectSerial() )
				{
					// If the first object is found at the same index as the
					// first
					// one
					if ( existingConnections[i].getObject1().getObjectSerial() == objectA
							.getObjectSerial() )
					{
						existingConnections[i].setObject2(objectC);
					}
				}
			}
		}

		return existingConnections;
	}



	/**
	 * Searches and, if found, returns a {@link Connection Connection} between
	 * the two given objects.
	 * 
	 * @param existingConnections
	 *            The array of existing connections.
	 * @param objectA
	 *            One of the objects that is searched for.
	 * @param objectB
	 *            The other object in the search.
	 * @return The connection found between object a and b.
	 * @throws ConnectionDoesNotExist
	 *             If thing is found this exception is thrown.
	 */
	public static Connection getConnection(Connection[] existingConnections,
			Object objectA, Object objectB) throws ConnectionDoesNotExist
	{
		// Checks to see if there really is a connection between A and B
		if ( !(checkConnectionExistence(existingConnections, objectA, objectB)) )
		{
			String objA = objectA.getObjectName();
			String objB = objectB.getObjectName();
			throw new ConnectionDoesNotExist(objA, objB);
		}

		boolean foundCon = false;


		/*
		 * Checks to see if object A is the first object in any connection. Then
		 * if it finds object A as the first object, it looks for object B at
		 * the same index as object A.
		 */
		for ( int i = 0; i < existingConnections.length; i++ )
		{
			if ( existingConnections[i] != null )
			{
				// If the first object is found
				if ( existingConnections[i].getObject1().getObjectSerial() == objectA
						.getObjectSerial() )
				{
					// If the second object is found at the same index as the
					// first one
					if ( existingConnections[i].getObject2().getObjectSerial() == objectB
							.getObjectSerial() )
					{
						return existingConnections[i];
					}
				}
			}
		}

		if ( !(foundCon) )
		{
			/*
			 * Checks to see if object B is the first object in any connection.
			 * Then if it find object B as the first object, it looks for object
			 * A at the same index as object B.
			 */
			for ( int i = 0; i < existingConnections.length; i++ )
			{
				if ( existingConnections[i] != null )
				{
					// If the second object is found
					if ( existingConnections[i].getObject1().getObjectSerial() == objectB
							.getObjectSerial() )
					{
						// If the first object is found
						if ( existingConnections[i].getObject2()
								.getObjectSerial() == objectA.getObjectSerial() )
						{
							return existingConnections[i];
						}
					}
				}
			}
		}

		return null;
	}



	/**
	 * Checks to see if both the devices that are to be connected support the
	 * connecting interface.
	 * 
	 * @return Returns true if both the devices support the interface, and false
	 *         if one or both do not support the interface.
	 */
	public static boolean checkDeviceConnectiontypeSupport(Object objectA,
			Object objectB, String type)
	{
		// Gets the supported connection interfaces of both the devices
		String[] connectionTypesA = objectA.getSupportedConnectionInterfaces();
		String[] connectionTypesB = objectB.getSupportedConnectionInterfaces();

		// The booleans that say if the device supports the connection type
		boolean AsupportsType = false;
		boolean BsupportsType = false;

		// Checks to see if object A supports the connection type
		for ( int i = 0; i < connectionTypesA.length; i++ )
		{
			if ( type.equals(connectionTypesA[i]) )
			{
				AsupportsType = true;
			}
		}

		// Checks to see if object B supports the connection type
		for ( int i = 0; i < connectionTypesB.length; i++ )
		{
			if ( type.equals(connectionTypesB[i]) )
			{
				BsupportsType = true;
			}
		}

		// If both devices support the connection type, the function returns
		// true
		if ( AsupportsType && BsupportsType )
		{
			return true;
		}

		// Else it will return false, to show that one or both of the devices
		// dont support the type
		return false;

	}



	/**
	 * Checks to see if the given device support the connecting interface.
	 * 
	 * @return Returns true if both the devices support the interface, and false
	 *         if one or both do not support the interface.
	 */
	public static boolean checkDeviceConnectiontypeSupport(Object objectA,
			String type)
	{
		// Gets the supported connection interfaces of the devices
		String[] connectionTypesA = objectA.getSupportedConnectionInterfaces();

		// Checks to see if object A supports the connection type
		for ( int i = 0; i < connectionTypesA.length; i++ )
		{
			if ( type.equals(connectionTypesA[i]) )
			{
				return true;
			}
		}

		// Else it will return false, to show the given devices does not support
		// the type
		return false;
	}



	/**
	 * Checks to see if a connections array contains any connections between
	 * object A and object B.
	 * 
	 * @return Returns true if a connection is found and false if not.
	 */
	public static boolean checkConnectionExistence(
			Connection[] existingConnections, Object objectA, Object objectB)
	{
		if ( existingConnections != null )
		{
			/*
			 * Checks to see if object A is the first object in any connection.
			 * Then if it find object A as the first object, it looks for object
			 * B at the same index as object A.
			 */
			for ( int i = 0; i < existingConnections.length; i++ )
			{
				if ( existingConnections[i] != null )
				{
					// If the first object is found
					if ( existingConnections[i].getObject1().getObjectSerial() == objectA
							.getObjectSerial() )
					{
						// If the second object is found at the same index as
						// the first one.
						if ( existingConnections[i].getObject2()
								.getObjectSerial() == objectB.getObjectSerial() )
						{
							return true;
						}
					}
				}
			}

			/*
			 * Checks to see if object B is the first object in any connection.
			 * Then if it find object B as the first object, it looks for object
			 * A at the same index as object B.
			 */
			for ( int i = 0; i < existingConnections.length; i++ )
			{
				if ( existingConnections[i] != null )
				{
					// If the second object is found
					if ( existingConnections[i].getObject1().getObjectSerial() == objectB
							.getObjectSerial() )
					{
						// If the first object is found at the same index as the
						// first
						// one
						if ( existingConnections[i].getObject2()
								.getObjectSerial() == objectA.getObjectSerial() )
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}


	/**
	 * Checks to see if a connections container contains any connections between
	 * object A and object B.
	 * 
	 * @return Returns true if a connection is found and false if not.
	 */
	public static boolean checkConnectionExistence(
			ConnectionContainer[] connectionContainer, Object objectA,
			Object objectB)
	{
		if ( connectionContainer != null )
		{
			/*
			 * Checks to see if object A is the first object in any connection.
			 * Then if it find object A as the first object, it looks for object
			 * B at the same index as object A.
			 */
			for ( int i = 0; i < connectionContainer.length; i++ )
			{
				// Finds the index of object A in the arraylist. Or returns -1
				// if
				// not found.
				int objectAindex = connectionContainer[i].getContainer()
						.indexOf(objectA);

				// If the first object is found
				if ( objectAindex != -1 )
				{
					// A temporary Connection object to see if Object A is
					// connected
					// to object B.
					Connection temp = (Connection) connectionContainer[i]
							.getContainer().get(objectAindex);


					// If the second object is found as the second object in the
					// connection
					if ( temp.getObject2().getObjectSerial() == objectB
							.getObjectSerial() )
					{
						return true;
					}
				}
			}
		}
		/*
		 * Checks to see if object B is the first object in any connection. Then
		 * if it find object B as the first object, it looks for object A at the
		 * same index as object B.
		 */
		for ( int i = 0; i < connectionContainer.length; i++ )
		{
			// Finds the index of object A in the arraylist. Or returns -1 if
			// not found.
			int objectBindex = connectionContainer[i].getContainer().indexOf(
					objectB);

			// If the first object is found
			if ( objectBindex != -1 )
			{
				// A temporary Connection object to see if Object A is connected
				// to object B.
				Connection temp = (Connection) connectionContainer[i]
						.getContainer().get(objectBindex);


				// If the second object is found as the second object in the
				// connection
				if ( temp.getObject2().getObjectSerial() == objectA
						.getObjectSerial() )
				{
					return true;
				}
			}
		}

		return false;
	}



	/**
	 * TODO - Description
	 * 
	 * 
	 * @param multiConNIC
	 *            A boolean on whether the NIC allows multiple connection, like
	 *            Wireless NICs on {@link Infrastructure} objects.
	 */
	private static Object getNonMotherboardNic(String conType, Object object,
			boolean multiConNIC)
	{
		Object ObjectNic = null;

		try
		{
			// Gets all the InternalNICs
			Object[] intNICs = object
					.getSpesificComponents(InternalNetworksCard.class);

			// Goes through all the gotten InternalNICs
			for ( int i = 0; i < intNICs.length; i++ )
			{
				InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

				// If there is no Object connected to this InternalNIC
				if ( multiConNIC || temp.getConnectedObject().isEmpty() )
				{
					// If the connection type of the network card is
					// conType
					if ( temp.getConnectionType().equals(conType) )
					{
						// Sets the found InternalNetworksCard with no
						// objects connected to it
						ObjectNic = temp;

						// Ends loop
						i = intNICs.length;
					}
				}
			}
		}
		// No InternalNetworksCard was found, search for
		// ExternalNetworksCard
		catch ( ObjectNotFoundException internalException )
		{
			// WILL BE CHECKED FURTHER DOWN
		}


		// No InternalNIC was found, either with conType or at all
		if ( ObjectNic == null )
		{
			try
			{
				// Gets all the ExternalNICs
				Object[] extNICs = object
						.getSpesificComponents(ExternalNetworksCard.class);

				// Goes through all the gotten ExternalNICs
				for ( int i = 0; i < extNICs.length; i++ )
				{
					ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];

					// If there is no Object connected to this
					// ExternalNICs
					if ( multiConNIC || temp.getConnectedObject().isEmpty() )
					{
						// If the connection type of the network card is
						// conType
						if ( temp.getConnectionType().equals(conType) )
						{
							// Sets the found ExternalNetworksCard with
							// no objects connected to it
							ObjectNic = temp;

							// Ends loop
							i = extNICs.length;
						}
					}
				}

			}
			// No InternalNetworksCard or ExternalNetworksCard was found
			// which was available
			catch ( ObjectNotFoundException externalException )
			{
				// WILL BE CHECKED FURTHER DOWN
			}
		}


		return ObjectNic;
	}


	/**
	 * Sets the connected object of the nic on object.
	 * 
	 * @param newlyCreatedConnection
	 */
	private static void setConnectedNic(Object nic, Object Object,
			Connection newlyCreatedConnection)
	{
		// Sets the connected object of the nic on object B
		if ( nic instanceof InternalNetworksCard )
		{
			InternalNetworksCard temp = (InternalNetworksCard) nic;

			// Sets objectA as the connected object of the network card
			temp.addConnectedObject(Object);
			temp.addConnection(newlyCreatedConnection);
		}
		else if ( nic instanceof ExternalNetworksCard )
		{
			ExternalNetworksCard temp = (ExternalNetworksCard) nic;

			// Sets objectA as the connected object of the network card
			temp.addConnectedObject(Object);
			temp.addConnection(newlyCreatedConnection);
		}
	}


	/**
	 * In this function the availability of the ports to which the connection is
	 * to be made is checked and, if available, set.
	 * 
	 * @param newlyCreatedConnection
	 */
	public static boolean checkAndSetPortAvailability(Object objectA,
			Object objectB, String conType, Connection newlyCreatedConnection)
	{
		Motherboard objectAmotherboard = null;
		Motherboard objectBmotherboard = null;

		try
		{
			// Since any object only has one motherboard this is a safe bet.
			objectAmotherboard = (Motherboard) objectA
					.getSpesificComponents(Motherboard.class)[0];
			objectBmotherboard = (Motherboard) objectB
					.getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			JOptionPane.showMessageDialog(null,
					"One of the devices does not have a motherboard.", "alert",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}


		// If the connection is either LAN, COAX or FIBER
		if ( conType.equals(ConnectionUtils.RJ45)
				|| conType.equals(ConnectionUtils.Coax)
				|| conType.equals(ConnectionUtils.Fiber)
				|| conType.equals(ConnectionUtils.Wireless) )
		{
			// Pointers to network objects, either integrated or external.
			Object ObjectAnic = null;
			Object ObjectBnic = null;

			// Booleans on multi connection Wireless NIC
			boolean ObjectAmultiConnectionNIC = false;
			boolean ObjectBmultiConnectionNIC = false;

			/**
			 * If the objects are instances of infrastructure, like wireless
			 * routers, and the connection type is Wireless.
			 */

			if ( objectA instanceof Infrastructure
					&& conType.equals(ConnectionUtils.Wireless) )
			{
				ObjectAmultiConnectionNIC = true;
			}

			if ( objectB instanceof Infrastructure
					&& conType.equals(ConnectionUtils.Wireless) )
			{
				ObjectBmultiConnectionNIC = true;
			}



			// Attempts to get the index of available port and sets the
			// ObjectAnic if no port is available.
			ObjectAnic = objectAmotherboard.getFirstAvailableNIC(conType,
					ObjectAmultiConnectionNIC);
			if ( ObjectAnic == null )
			{
				ObjectAnic = getNonMotherboardNic(conType, objectA,
						ObjectAmultiConnectionNIC);
			}

			// No compatible NIC card was found.
			if ( ObjectAnic == null )
			{
				JOptionPane.showMessageDialog(
						null,
						"No available network port was found on "
								+ objectA.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}



			// Attempts to get the index of available port and sets the
			// ObjectBnic if no port is available.
			ObjectBnic = objectBmotherboard.getFirstAvailableNIC(conType,
					ObjectBmultiConnectionNIC);
			if ( ObjectBnic == null )
			{
				ObjectBnic = getNonMotherboardNic(conType, objectB,
						ObjectBmultiConnectionNIC);
			}

			// No compatible NIC card was found.
			if ( ObjectBnic == null )
			{
				JOptionPane.showMessageDialog(
						null,
						"No available network port was found on "
								+ objectB.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}


			/**
			 * If the function gets to this point it means that either both the
			 * motherboards on the objects have available LAN ports or they have
			 * and they have NICs that are available. Pointers to objects are
			 * retrieved.
			 */

			// Makes sure that the nic pointer for object A is not null
			assert ObjectAnic != null;

			// Makes sure that the nic pointer for object B is not null
			assert ObjectBnic != null;

			setConnectedNic(ObjectAnic, objectB, newlyCreatedConnection);

			setConnectedNic(ObjectBnic, objectA, newlyCreatedConnection);


			// Adds each object to the other objects array of connection
			// objects.
			objectA.addConnectedDevices(objectB);
			objectB.addConnectedDevices(objectA);
		}
		else if ( conType.equals(ConnectionUtils.USB) )
		{
			/**
			 * These two values will be changed or the function will get some
			 * exceptions and will not move on.
			 */
			int indexA = 0;
			int indexB = 0;


			if ( objectAmotherboard.getMaxUSBs() > 0 )
			{
				// Gets the first available LAN port index.
				indexA = objectAmotherboard.getUSBPortsAvailable();

				if ( indexA < 0 )
				{
					JOptionPane.showMessageDialog(
							null,
							"There are no available USB ports on "
									+ objectA.getObjectName(), "alert",
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, objectA.getObjectName()
						+ " has no USB ports available.", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}


			if ( objectBmotherboard.getMaxUSBs() > 0 )
			{
				// Gets the first available LAN port index.
				indexB = objectBmotherboard.getUSBPortsAvailable();

				if ( indexB < 0 )
				{
					JOptionPane.showMessageDialog(
							null,
							"There are no available USB ports on "
									+ objectB.getObjectName(), "alert",
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, objectB.getObjectName()
						+ " has no USB ports available.", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}


			/**
			 * The function gets to this point it means that both the
			 * motherboards on the objects have available ports and the indexes
			 * are retrieved.
			 */

			// Sets the arrays on the actual motherboard components.
			objectAmotherboard.makeOneUSBportTaken();
			objectBmotherboard.makeOneUSBportTaken();

			objectA.addConnectedDevices(objectB);
			objectB.addConnectedDevices(objectA);
		}

		return true;
	}


	/**
	 * This function add a connection to the connection array of the given
	 * canvas. The function also gives the developer the opportunity to
	 * add a check function which checks to see whether or not the connection
	 * that is to be added already exists in the connection array of the
	 * currently selected canvas.
	 * 
	 * @param newCon
	 *            The connection to be added to the currently selected canvas.
	 * @param withCheck
	 *            The boolean which tells the function whether or not to run a
	 *            check as to see if the connection thats is to be added already
	 *            exists in the connection array of the currently selected
	 *            canvas.
	 */
	public static boolean addConnection(Connection newCon, boolean withCheck,
			WorkareaCanvas canvas)
	{
		// Gets the current canvas connections array.
		Connection[] currentCons = canvas.getConnections();

		// Gets the objects that the user wants to connect
		Object a1 = newCon.getObject1();
		Object a2 = newCon.getObject2();

		// If there should be run a check to see if any connection exist.
		if ( withCheck )
		{
			// Checks if there is a connection between them.
			if ( checkConnectionExistence(currentCons, a1, a2) )
			{
				return false;
			}
		}

		// Goes through the entire array of connections and set the connection
		// in at the first empty space.
		for ( int i = 0; i < currentCons.length; i++ )
		{
			if ( currentCons[i] == null )
			{
				currentCons[i] = newCon;

				canvas.setConnections(cleanup.cleanObjectArray(currentCons));

				return true;
			}
		}

		// Extends the array with 5 spaces.
		extendConnectionArray(canvas);

		// Reruns the whole method with 5 added spaces in the connection array.
		return addConnection(newCon, withCheck, canvas);
	}




	/**
	 * This function extends the connection array of the currently selected
	 * canvas with 5 indexes.
	 */
	private static void extendConnectionArray(WorkareaCanvas canvas)
	{
		Connection currentCons[] = canvas.getConnections();
		Connection temp[] = new Connection[currentCons.length + 5];

		System.arraycopy(currentCons, 0, temp, 0, currentCons.length);

		canvas.setConnections(temp);
	}



	/**
	 * This function returns an array of strings that contain strings found in
	 * both the given arrays. This method is use to find compatible user
	 * interfaces supported by two objects.
	 */
	public static String[] getCompatibleConnectionInterfaces(String[] a,
			String[] b)
	{
		// The array that is the longest.
		String[] longestArray = null;

		// The shortest array.
		String[] otherArray = null;

		// Determines which of the given arrays is the longest.
		if ( a.length > b.length )
		{
			longestArray = a;
			otherArray = b;
		}
		else
		{
			longestArray = b;
			otherArray = a;
		}

		// Makes a new string array with the length of the longest array.
		String temp[] = new String[longestArray.length];

		// The index of the last supported interface found in both the string
		// arrays.
		int tempPlacesTaken = 0;

		// Goes through the longest array.
		for ( int i = 0; i < longestArray.length; i++ )
		{
			// Goes through the shortest array one by one for each index of the
			// longest array.
			for ( int j = 0; j < otherArray.length; j++ )
			{
				// Checks to see if any of the indexes in the shortest array
				// match up with any of the indexes in the longest array.
				if ( longestArray[i].equals(otherArray[j]) )
				{
					// If it matches up it is placed in the temp array.
					temp[tempPlacesTaken] = longestArray[i];
					tempPlacesTaken++;
					j = otherArray.length;
				}
			}
		}

		// Cleans up any null pointers at the end of the temp string array.
		temp = logistical.cleanup.cleanObjectArray(temp);

		return temp;
	}



	/**
	 * This function attempts to remove the connection between the given
	 * {@link Object} and the given {@link InternalNetworksCard}. This
	 * connection must be inside the given {@link WorkareaCanvas} to be removed.
	 * 
	 * Does nothing if any of the given parameters are null.
	 * 
	 * @throws ConnectionDoesNotExist
	 */
	public static void removeConnectionFromInternalNIC(WorkareaCanvas canvas,
			Object obj, InternalNetworksCard nic) throws ConnectionDoesNotExist
	{
		if ( canvas != null && obj != null && nic != null )
		{
			// If the connections list is not empty
			if ( !nic.getConnections().isEmpty() )
			{
				ArrayList<Connection> cons = nic.getConnections();

				for ( int i = 0; i < cons.size(); i++ )
				{
					if ( cons.get(i) != null )
					{
						WorkareaCanvasActions.removeConnection(canvas,
								cons.get(i));
					}
				}
			}
		}
	}


	/**
	 * This function attempts to remove the connection between the given
	 * {@link Object} and the given {@link ExternalNetworksCard}. This
	 * connection must be inside the given {@link WorkareaCanvas} to be removed.
	 * 
	 * Does nothing is any of the given parameters are null.
	 * 
	 * @throws ConnectionDoesNotExist
	 */
	public static void removeConnectionFromExternalNIC(WorkareaCanvas canvas,
			Object obj, ExternalNetworksCard nic) throws ConnectionDoesNotExist
	{
		if ( canvas != null && obj != null && nic != null )
		{
			// If the connections list is not empty
			if ( !nic.getConnections().isEmpty() )
			{
				ArrayList<Connection> cons = nic.getConnections();

				for ( int i = 0; i < cons.size(); i++ )
				{
					if ( cons.get(i) != null )
					{
						WorkareaCanvasActions.removeConnection(canvas,
								cons.get(i));
					}
				}
			}
		}
	}



	/**
	 * Finds and returns all the {@link Object Objects} connected to the given
	 * {@link Object};
	 */
	public static Object[] getConnectedObjects(Object obj)
	{
		// Gets all the connections to and from the given object
		Connection[] existingConnections = obj.getAllConnections();

		// An array with all the Objects that are connected to the given Object
		Object[] foundObjects = new Object[existingConnections.length];


		if ( existingConnections[0] != null )
		{
			/*
			 * Checks to see if object A is the first object in any connection.
			 * Then if it find object A as the first object, it looks for object
			 * B at the same index as object A.
			 */
			for ( int i = 0; i < existingConnections.length; i++ )
			{
				if ( existingConnections[i] != null )
				{
					// If the first object is found
					if ( existingConnections[i].getObject1().getObjectSerial() == obj
							.getObjectSerial() )
					{
						foundObjects[i] = existingConnections[i].getObject2();
					}
					else
					{
						foundObjects[i] = existingConnections[i].getObject1();
					}
				}
			}
		}
		else
		{
			// The object is not connected to anything
			return null;
		}


		return foundObjects;
	}




	/**
	 * Finds and returns the {@link WidgetExtendedConnection} that represents
	 * the connection between the two given {@link Object Objects}.
	 */
	public static WidgetExtendedConnection findWidgetConnections(
			WorkareaCanvas canvas, Object A, Object B)
			throws ConnectionDoesNotExist
	{
		// Gets the connection between the two objects if there exists any such
		// connection
		Connection con = getConnection(canvas.getConnections(), A, B);

		// The widgetCon variable
		WidgetExtendedConnection widgetCon = null;


		List<Widget> list = canvas.getConnectionLayer().getChildren();


		WidgetExtendedConnection testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetExtendedConnection) iter.next();

			if ( testingWidget.getConnection().equals(con) )
			{
				widgetCon = testingWidget;
			}
		}


		return widgetCon;
	}



	/**
	 * Finds and returns the {@link WidgetExtendedConnection} that represents
	 * the connection between the two given {@link Object Objects}.
	 */
	public static WidgetExtendedConnection findWidgetConnection(
			WorkareaCanvas canvas, Connection con)
	{
		// The widgetCon variable
		WidgetExtendedConnection widgetCon = null;


		List<Widget> list = canvas.getConnectionLayer().getChildren();


		WidgetExtendedConnection testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetExtendedConnection) iter.next();

			if ( testingWidget.getConnection().equals(con) )
			{
				widgetCon = testingWidget;
			}
		}


		return widgetCon;
	}




	/**
	 * Finds and returns all the connection from the given object
	 * with the given connection type. Returns an array of connections , if any
	 * are found.
	 * 
	 * @param connectedTo
	 *            The object that will be examined for connections to other
	 *            objects.
	 * @param conType
	 *            The type of connection between the two objects.
	 * @return Returns all the connections with given connection type
	 */
	public static Connection[] connectedToBy(Object connectedTo, String conType)
	{
		// The array that will hold all the matching objects.
		Connection[] foundCons = null;

		Connection[] cons = null;

		int index = 0;


		Connection[] netCons = connectedTo.getNetworkConnections();

		Connection[] devCons = connectedTo.getDeviceConnections();


		// Finds the number of overall connections.
		if ( netCons != null )
		{
			index = index + netCons.length;
		}

		if ( devCons != null )
		{
			index = index + devCons.length;
		}


		// The array that will hold all of the objects connections.
		cons = new Connection[index];


		if ( netCons != null )
		{
			// Adding the networkconnections to the array.
			System.arraycopy(netCons, 0, cons, 0, netCons.length);
		}



		if ( devCons != null )
		{
			if ( netCons != null )
			{
				// Adding the deviceconnections to the array.
				System.arraycopy(devCons, 0, cons, netCons.length,
						devCons.length);
			}
			else
			{
				// Adding the deviceconnections to the array.
				System.arraycopy(devCons, 0, cons, 0, devCons.length);
			}
		}

		// Creates an array with the length of the cons array
		foundCons = new Connection[cons.length];


		// Matching the connection types to the given conType.
		for ( int i = 0; i < cons.length; i++ )
		{
			if ( cons[i] != null )
			{
				if ( cons[i].getConnectionType().equals(conType) )
				{
					foundCons[i] = cons[i];
				}
			}
		}


		if ( foundCons != null )
		{
			// Removes all the empty indexes from the array.
			foundCons = cleanup.cleanObjectArray(foundCons);
		}


		return foundCons;
	}



}