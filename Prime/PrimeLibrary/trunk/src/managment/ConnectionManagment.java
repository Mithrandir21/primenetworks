package managment;


import java.awt.BasicStroke;

import javax.swing.JOptionPane;

import logistical.cleanup;
import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;

import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;
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


		// Checks to see if there is any previous connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == true )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(), objectB
					.getObjectName());
		}


		// Checks to see if both the devices support the type of connection
		if ( checkDeviceConnectiontypeSupport(objectA, objectB, type) == false )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(), objectB
					.getObjectName(), "");
		}


		if ( checkAndSetPortAvailability(objectA, objectB, type) == false )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(), objectB
					.getObjectName(), "");
		}


		/*
		 * If there is no previous connection between A and B, and they both
		 * support the connection type, a connection is made between them.
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
	 * @param sourceWidget
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

		if ( con.getConnectionType().equals("Wireless") )
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
		connection.setToolTipText("This is a connection");
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
			Connection[] existingConnections, Object objectA, Object objectB)
			throws ConnectionDoesNotExist
	{
		// Checks to see if there really is a connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(), objectB
					.getObjectName());
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
						// Removes the both objects from each others internal
						// connected device array.
						objectA.removeConnection(existingConnections[i]);
						objectB.removeConnection(existingConnections[i]);

						try
						{
							// If the connections is a wireless connection
							if ( existingConnections[i].getConnectionType()
									.equals("Wireless") )
							{
								removeWirelessNICconnection(objectA, objectB);
							}
							objectA.removeConnectedDevices(objectB);
							objectB.removeConnectedDevices(objectA);
						}
						catch ( ObjectNotFoundInArrayException e )
						{
							System.out.println(e.getMessage());
						}
						catch ( ObjectNotFoundException e )
						{
							System.out.println(e.getMessage());
						}

						existingConnections[i] = null;

						// Indicates that the connection has been found and
						// removed.
						foundCon = true;
					}
				}
			}
		}

		if ( foundCon == false )
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
						// If the first object is found at the same index as the
						// first
						// one
						if ( existingConnections[i].getObject2()
								.getObjectSerial() == objectA.getObjectSerial() )
						{
							// Removes the both objects from each others
							// internal connected device array.
							objectA.removeConnection(existingConnections[i]);
							objectB.removeConnection(existingConnections[i]);

							try
							{
								// If the connections is a wireless connection
								if ( existingConnections[i].getConnectionType()
										.equals("Wireless") )
								{
									removeWirelessNICconnection(objectA,
											objectB);
								}
								objectA.removeConnectedDevices(objectB);
								objectB.removeConnectedDevices(objectA);
							}
							catch ( ObjectNotFoundInArrayException e )
							{
								System.out.println(e.getMessage());
							}
							catch ( ObjectNotFoundException e )
							{
								System.out.println(e.getMessage());
							}

							existingConnections[i] = null;
						}
					}
				}
			}
		}

		// Removes any null-pointers in the array of connections
		existingConnections = cleanup.cleanObjectArray(existingConnections);

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
		if ( checkConnectionExistence(existingConnections, objectA,
				objectToBeRemoved) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(),
					objectToBeRemoved.getObjectName());
		}

		// Then checks to see if there already exists a connection between
		// object a and c
		if ( checkConnectionExistence(existingConnections, objectA, objectC) == true )
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
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == false )
		{
			String a = objectA.getObjectName();
			String b = objectB.getObjectName();
			throw new ConnectionDoesNotExist(a, b);
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

		if ( foundCon == false )
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
		if ( AsupportsType == true && BsupportsType == true )
		{
			return true;
		}

		// Else it will return false, to show that one or both of the devices
		// dont support the type
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
	 * In this function the availability of the ports to which the connection is
	 * to be made is checked and, if available, set.
	 */
	public static boolean checkAndSetPortAvailability(Object objectA,
			Object objectB, String conType) throws ConnectionsIsNotPossible
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


		if ( conType.equals("RJ-45") )
		{
			// Pointers to network objects in cases where the motherboard has no
			// port available or has not lan ports integrated.
			Object ObjectAnic = null;
			Object ObjectBnic = null;

			/*
			 * These two values will be changed or the function will get some
			 * exceptions and will not move on.
			 */
			int indexA = 0;
			int indexB = 0;

			// Checks if the motherboard of the first object has an integrated
			// LAN port.
			if ( objectAmotherboard.LANcardIsIntegrated() )
			{
				// Gets the first available LAN port index.
				indexA = objectAmotherboard.getIntegLANPortsAvailable();
			}

			if ( indexA < 1 )
			{
				try
				{
					// Gets all the InternalNICs
					Object[] intNICs = objectA
							.getSpesificComponents(InternalNetworksCard.class);

					// Goes through all the gotten InternalNICs
					for ( int i = 0; i < intNICs.length; i++ )
					{
						InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

						// If there is no Object connected to this InternalNIC
						if ( temp.getConnectedObject() == null )
						{
							// If the connection type of the network card is
							// wired
							if ( temp.getConnectionType().equals("Wired") )
							{
								// Sets the found InternalNetworksCard with no
								// objects connected to it
								ObjectAnic = temp;

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
					try
					{
						// Gets all the ExternalNICs
						Object[] extNICs = objectA
								.getSpesificComponents(ExternalNetworksCard.class);

						// Goes through all the gotten ExternalNICs
						for ( int i = 0; i < extNICs.length; i++ )
						{
							ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];

							// If there is no Object connected to this
							// ExternalNICs
							if ( temp.getConnectedObject() == null )
							{
								// If the connection type of the network card is
								// wired
								if ( temp.getConnectionType().equals("Wired") )
								{
									// Sets the found ExternalNetworksCard with
									// no objects connected to it
									ObjectAnic = temp;

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
			}

			// No integrated lan port was present and no NIC card was found.
			if ( indexA < 1 && ObjectAnic == null )
			{
				JOptionPane.showMessageDialog(null,
						"No available network port was found on "
								+ objectA.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}



			// Checks if the motherboard of the second object has an integrated
			// LAN port.
			if ( objectBmotherboard.LANcardIsIntegrated() )
			{
				// Gets the first available LAN port index.
				indexB = objectBmotherboard.getIntegLANPortsAvailable();
			}

			if ( indexB < 1 )
			{
				try
				{
					// Gets all the InternalNICs
					Object[] intNICs = objectB
							.getSpesificComponents(InternalNetworksCard.class);

					// Goes through all the gotten InternalNICs
					for ( int i = 0; i < intNICs.length; i++ )
					{
						InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

						// If there is no Object connected to this InternalNIC
						if ( temp.getConnectedObject() == null )
						{
							// If the connection type of the network card is
							// wired
							if ( temp.getConnectionType().equals("Wired") )
							{
								// Sets the found InternalNetworksCard with no
								// objects connected to it
								ObjectBnic = temp;

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
					try
					{
						// Gets all the ExternalNICs
						Object[] extNICs = objectB
								.getSpesificComponents(ExternalNetworksCard.class);

						// Goes through all the gotten ExternalNICs
						for ( int i = 0; i < extNICs.length; i++ )
						{
							ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];



							// If there is no Object connected to this
							// ExternalNICs
							if ( temp.getConnectedObject() == null )
							{
								// If the connection type of the network card is
								// wired
								if ( temp.getConnectionType().equals("Wired") )
								{
									// Sets the found ExternalNetworksCard with
									// no objects connected to it
									ObjectBnic = temp;

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
			}



			// No integrated lan port was present and no NIC card was found.
			if ( indexB < 1 && ObjectBnic == null )
			{
				JOptionPane.showMessageDialog(null,
						"No available network port was found on "
								+ objectB.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}




			/**
			 * If the function gets to this point it means that either both the
			 * motherboards on the objects have available LAN ports or they have
			 * and they have NICs that are available. Indexes or pointers to
			 * objects are retrieved.
			 */

			// ObjectA - Has available integrated Lan port
			if ( indexA > 0 )
			{
				// Sets the arrays on the actual motherboard components.
				objectAmotherboard.makeOneIntLANportTaken();

				// ObjectB - Has available integrated Lan port
				if ( indexB > 0 )
				{
					// Both the motherboards have available lan ports
					objectBmotherboard.makeOneIntLANportTaken();
				}
				else
				{
					// Makes sure that the nic pointer for object B is not null
					assert ObjectBnic != null;


					// Sets the connected object of the nic on object B
					if ( ObjectBnic instanceof InternalNetworksCard )
					{
						InternalNetworksCard temp = (InternalNetworksCard) ObjectBnic;

						// Sets objectA as the connected object of the network
						// card
						temp.setConnectedObject(objectA);
					}
					else if ( ObjectBnic instanceof ExternalNetworksCard )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) ObjectBnic;

						// Sets objectA as the connected object of the network
						// card
						temp.setConnectedObject(objectA);
					}
				}

			}
			// ObjectA - Has not available integrated Lan port
			else
			{
				// Makes sure that the nic pointer for object A is not null
				assert ObjectAnic != null;

				// ObjectB - Has available integrated Lan port
				if ( indexB > 0 )
				{
					// Both the motherboards have available lan ports
					objectBmotherboard.makeOneIntLANportTaken();


					// Sets the objects as the connected objects on the network
					// cards
					if ( ObjectAnic instanceof InternalNetworksCard )
					{
						InternalNetworksCard temp = (InternalNetworksCard) ObjectAnic;

						// Sets objectB as the connected object of the network
						// card
						temp.setConnectedObject(objectB);
					}
					else if ( ObjectAnic instanceof ExternalNetworksCard )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) ObjectAnic;

						// Sets objectB as the connected object of the network
						// card
						temp.setConnectedObject(objectB);
					}
				}
				// ObjectB - Has not available integrated Lan port
				else
				{
					// Makes sure that the nic pointer for object B is not null
					assert ObjectBnic != null;

					// Sets the objects as the connected objects on the network
					// cards
					if ( ObjectAnic instanceof InternalNetworksCard )
					{
						InternalNetworksCard temp = (InternalNetworksCard) ObjectAnic;

						// Sets objectB as the connected object of the network
						// card
						temp.setConnectedObject(objectB);
					}
					else if ( ObjectAnic instanceof ExternalNetworksCard )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) ObjectAnic;

						// Sets objectB as the connected object of the network
						// card
						temp.setConnectedObject(objectB);
					}


					// Sets the connected object of the nic on object B
					if ( ObjectBnic instanceof InternalNetworksCard )
					{
						InternalNetworksCard temp = (InternalNetworksCard) ObjectBnic;

						// Sets objectA as the connected object of the network
						// card
						temp.setConnectedObject(objectA);
					}
					else if ( ObjectBnic instanceof ExternalNetworksCard )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) ObjectBnic;

						// Sets objectA as the connected object of the network
						// card
						temp.setConnectedObject(objectA);
					}
				}
			}



			// Adds each object to the other objects array of connection
			// objects.
			objectA.addConnectedDevices(objectB);
			objectB.addConnectedDevices(objectA);
		}
		else if ( conType.equals("Wireless") )
		{
			Object ObjectAnic = null;
			Object ObjectBnic = null;


			// If objectA is not a infrastructure object
			if ( !(objectA instanceof Infrastructure) )
			{
				try
				{
					// Gets all the InternalNICs
					Object[] intNICs = objectA
							.getSpesificComponents(InternalNetworksCard.class);

					// Goes through all the gotten InternalNICs
					for ( int i = 0; i < intNICs.length; i++ )
					{
						InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

						// If there is no Object connected to this InternalNIC
						if ( temp.getConnectedObject() == null )
						{
							// If the connection type of the network card is
							// Wireless
							if ( temp.getConnectionType().equals("Wireless") )
							{
								// Sets the found InternalNetworksCard with no
								// objects connected to it
								ObjectAnic = temp;

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
					try
					{
						// Gets all the ExternalNICs
						Object[] extNICs = objectA
								.getSpesificComponents(ExternalNetworksCard.class);

						// Goes through all the gotten ExternalNICs
						for ( int i = 0; i < extNICs.length; i++ )
						{
							ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];

							// If there is no Object connected to this
							// ExternalNICs
							if ( temp.getConnectedObject() == null )
							{
								// If the connection type of the network card is
								// Wireless
								if ( temp.getConnectionType()
										.equals("Wireless") )
								{
									// Sets the found ExternalNetworksCard with
									// no objects connected to it
									ObjectAnic = temp;

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
						JOptionPane.showMessageDialog(null,
								"No available network card was found on "
										+ objectA.getObjectName() + ".",
								"alert", JOptionPane.ERROR_MESSAGE);

						return false;
					}
				}
			}



			if ( !(objectB instanceof Infrastructure) )
			{
				try
				{
					// Gets all the InternalNICs
					Object[] intNICs = objectB
							.getSpesificComponents(InternalNetworksCard.class);

					// Goes through all the gotten InternalNICs
					for ( int i = 0; i < intNICs.length; i++ )
					{
						InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

						// If there is no Object connected to this InternalNIC
						if ( temp.getConnectedObject() == null )
						{
							// If the connection type of the network card is
							// Wireless
							if ( temp.getConnectionType().equals("Wireless") )
							{
								// Sets the found InternalNetworksCard with no
								// objects connected to it
								ObjectBnic = temp;

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
					try
					{
						// Gets all the ExternalNICs
						Object[] extNICs = objectB
								.getSpesificComponents(ExternalNetworksCard.class);

						// Goes through all the gotten ExternalNICs
						for ( int i = 0; i < extNICs.length; i++ )
						{
							ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];



							// If there is no Object connected to this
							// ExternalNICs
							if ( temp.getConnectedObject() == null )
							{
								// If the connection type of the network card is
								// Wireless
								if ( temp.getConnectionType()
										.equals("Wireless") )
								{
									// Sets the found ExternalNetworksCard with
									// no objects connected to it
									ObjectBnic = temp;

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
						JOptionPane.showMessageDialog(null,
								"No available network card was found on "
										+ objectB.getObjectName() + ".",
								"alert", JOptionPane.ERROR_MESSAGE);

						return false;
					}
				}
			}


			// If either of the objects are null there cannot exist a connection
			// between the two given objects
			if ( ObjectAnic == null && !(objectA instanceof Infrastructure) )
			{
				JOptionPane.showMessageDialog(null,
						"No available network card was found on "
								+ objectA.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}


			// If either of the objects are null there cannot exist a connection
			// between the two given objects
			if ( ObjectBnic == null && !(objectB instanceof Infrastructure) )
			{
				JOptionPane.showMessageDialog(null,
						"No available network card was found on "
								+ objectB.getObjectName() + ".", "alert",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}



			/**
			 * If the function gets here, both the objects have contained a
			 * network card that is available and has the connection type of
			 * Wireless.
			 */




			// Sets the objects as the connected objects on the network cards
			if ( ObjectAnic instanceof InternalNetworksCard )
			{
				InternalNetworksCard temp = (InternalNetworksCard) ObjectAnic;

				// Sets objectB as the connected object of the network card
				temp.setConnectedObject(objectB);
			}
			else if ( ObjectAnic instanceof ExternalNetworksCard )
			{
				ExternalNetworksCard temp = (ExternalNetworksCard) ObjectAnic;

				// Sets objectB as the connected object of the network card
				temp.setConnectedObject(objectB);
			}


			if ( ObjectBnic instanceof InternalNetworksCard )
			{
				InternalNetworksCard temp = (InternalNetworksCard) ObjectBnic;

				// Sets objectA as the connected object of the network card
				temp.setConnectedObject(objectA);
			}
			else if ( ObjectBnic instanceof ExternalNetworksCard )
			{
				ExternalNetworksCard temp = (ExternalNetworksCard) ObjectBnic;

				// Sets objectA as the connected object of the network card
				temp.setConnectedObject(objectA);
			}



			// Adds each object to the other objects array of connection
			// objects.
			objectA.addConnectedDevices(objectB);
			objectB.addConnectedDevices(objectA);
		}
		else if ( conType.equals("USB") )
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
					JOptionPane.showMessageDialog(null,
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
					JOptionPane.showMessageDialog(null,
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
	 * This function add a connection to the connection array of the currently
	 * selected canvas. The function also gives the developer the opportunity to
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
		Connection currentCons[] = canvas.getConnections();

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

		for ( int i = 0; i < currentCons.length; i++ )
		{
			temp[i] = currentCons[i];
		}

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
	 * Removes the connected Object from the first NIC(internal and external)
	 * found on both the given Objects.
	 */
	private static void removeWirelessNICconnection(Object objectA,
			Object objectB) throws ObjectNotFoundException
	{
		// If objectA is not a infrastructure object
		if ( !(objectA instanceof Infrastructure) )
		{
			try
			{
				// Gets all the InternalNICs
				Object[] intNICs = objectA
						.getSpesificComponents(InternalNetworksCard.class);

				// Goes through all the gotten InternalNICs
				for ( int i = 0; i < intNICs.length; i++ )
				{
					InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

					// If there is no Object connected to this InternalNIC
					if ( temp.getConnectedObject() != null )
					{
						// If the connection type of the network card is
						// Wireless
						if ( temp.getConnectionType().equals("Wireless") )
						{
							temp.setConnectedObject(null);

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
				try
				{
					// Gets all the ExternalNICs
					Object[] extNICs = objectA
							.getSpesificComponents(ExternalNetworksCard.class);

					// Goes through all the gotten ExternalNICs
					for ( int i = 0; i < extNICs.length; i++ )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];

						// If there is no Object connected to this
						// ExternalNICs
						if ( temp.getConnectedObject() != null )
						{
							// If the connection type of the network card is
							// Wireless
							if ( temp.getConnectionType().equals("Wireless") )
							{
								temp.setConnectedObject(null);

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
					JOptionPane.showMessageDialog(null,
							"No available network card was found on "
									+ objectA.getObjectName() + ".", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}



		if ( !(objectB instanceof Infrastructure) )
		{
			try
			{
				// Gets all the InternalNICs
				Object[] intNICs = objectB
						.getSpesificComponents(InternalNetworksCard.class);

				// Goes through all the gotten InternalNICs
				for ( int i = 0; i < intNICs.length; i++ )
				{
					InternalNetworksCard temp = (InternalNetworksCard) intNICs[i];

					// If there is no Object connected to this InternalNIC
					if ( temp.getConnectedObject() != null )
					{
						// If the connection type of the network card is
						// Wireless
						if ( temp.getConnectionType().equals("Wireless") )
						{
							temp.setConnectedObject(null);

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
				try
				{
					// Gets all the ExternalNICs
					Object[] extNICs = objectB
							.getSpesificComponents(ExternalNetworksCard.class);

					// Goes through all the gotten ExternalNICs
					for ( int i = 0; i < extNICs.length; i++ )
					{
						ExternalNetworksCard temp = (ExternalNetworksCard) extNICs[i];

						// If there is no Object connected to this
						// ExternalNICs
						if ( temp.getConnectedObject() != null )
						{
							// If the connection type of the network card is
							// Wireless
							if ( temp.getConnectionType().equals("Wireless") )
							{
								temp.setConnectedObject(null);

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
					JOptionPane.showMessageDialog(null,
							"No available network card was found on "
									+ objectB.getObjectName() + ".", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}













}
