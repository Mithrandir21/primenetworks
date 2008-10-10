package managment;


import graphics.PrimeMain1;
import hardware.InternalNetworksCard;
import hardware.Motherboard;

import javax.swing.JOptionPane;

import logistical.cleanup;
import objects.Object;
import connections.Connection;
import connections.DeviceConnection;
import connections.InternalConnection;
import connections.NetworkConnection;
import containers.ConnectionContainer;
import exceptions.ConnectionDoesExist;
import exceptions.ConnectionDoesNotExist;
import exceptions.ConnectionsIsNotPossible;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * A method class for the managment of any type of connection within the system.
 * A connection is a physical link, be it wired or wireless, between two
 * components in the system. Examples of this are connections between switches
 * and computers, swithes and other switches, computers and printers, servers
 * and routers and so on. <br>
 * The class contains methodes like making connections, breaking connection or
 * changing connection. There are additional methodes that help with the
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
	public static Connection makeConnection(Connection[] existingConnections, String conName,
			String conDesc, Object objectA, Object objectB, String type, Class conClass)
			throws ConnectionDoesExist, ConnectionsIsNotPossible
	{
		Connection connection = null;


		// Checks to see if there is any previous connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == true )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(), objectB.getObjectName());
		}


		// Checks to see if both the devices support the type of connection
		if ( checkDeviceConnectiontypeSupport(objectA, objectB, type) == false )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(), objectB.getObjectName(), "");
		}


		if ( checkAndSetPortAvailability(objectA, objectB, type) == false )
		{
			throw new ConnectionsIsNotPossible(objectA.getObjectName(), objectB.getObjectName(), "");
		}


		/*
		 * If there is no previous connection between A and B, and they both
		 * support the connection type, a connection is made between them.
		 */
		if ( conClass.equals(DeviceConnection.class) )
		{
			connection = new DeviceConnection(conName, conDesc, objectA, objectB, type);
		}
		else if ( conClass.equals(InternalConnection.class) )
		{
			connection = new InternalConnection(conName, conDesc, objectA, objectB, type);
		}
		else
		{
			connection = new NetworkConnection(conName, conDesc, objectA, objectB, type);
		}


		return connection;
	}


	/**
	 * Breaks connections between two components in the system. It removes the
	 * connection from the array of existing connections. This methode throws
	 * {@link exceptions.ConnectionDoesNotExist ConnectionDoesNotExist}
	 * exception, if there is no connection between the two given objects.
	 * 
	 * @return Returns the given connections array without the connection
	 *         between the two given object. The array is cleaned for any empty
	 *         indexs.
	 */
	public static Connection[] breakConnection(Connection[] existingConnections, Object objectA,
			Object objectB) throws ConnectionDoesNotExist
	{

		// Checks to see if there really is a connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(), objectB.getObjectName());
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
				if ( existingConnections[i].getObject1().equals(objectA) )
				{
					// If the second object is found at the same index as the
					// first one
					if ( existingConnections[i].getObject2().equals(objectB) )
					{
						// Removes the both objects from each others internal
						// connected device array.
						objectA.removeConnection(existingConnections[i]);
						objectB.removeConnection(existingConnections[i]);
						existingConnections[i] = null;

						try
						{
							objectA.removeConnectedDevices(objectB);
							objectB.removeConnectedDevices(objectA);
						}
						catch ( ObjectNotFoundInArrayException e )
						{
							System.out.println(e.getMessage());
						}
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
					if ( existingConnections[i].getObject1().equals(objectB) )
					{
						// If the first object is found at the same index as the
						// first
						// one
						if ( existingConnections[i].getObject2().equals(objectA) )
						{
							// Removes the both objects from each others
							// internal
							// connected device array.
							objectA.removeConnection(existingConnections[i]);
							objectB.removeConnection(existingConnections[i]);
							existingConnections[i] = null;

							try
							{
								objectA.removeConnectedDevices(objectB);
								objectB.removeConnectedDevices(objectA);
							}
							catch ( ObjectNotFoundInArrayException e )
							{
								System.out.println(e.getMessage());
							}
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
	 * This method changes a connection between A and B, to be a connection
	 * between A and C. This is done by changing the second object in the
	 * connection to point to object C instead of object B. There is also checks
	 * done to see if there really is a connection between A and B, and if there
	 * is any prior connections between A and C.
	 * 
	 * @return Returns an array of connections where the connection between A
	 *         and B has been changed to a connection between A and C.
	 */
	public static Connection[] changeConnection(Connection[] existingConnections, Object objectA,
			Object objectToBeRemoved, Object objectC) throws ConnectionDoesNotExist,
			ConnectionDoesExist
	{

		// First checks to see if there exist a connection between object a and
		// "ToBeRemoved"
		if ( checkConnectionExistence(existingConnections, objectA, objectToBeRemoved) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(), objectToBeRemoved
					.getObjectName());
		}

		// Then checks to see if there already exists a connection between
		// object a and c
		if ( checkConnectionExistence(existingConnections, objectA, objectC) == true )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(), objectToBeRemoved
					.getObjectName());
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
				if ( existingConnections[i].getObject1().equals(objectA) )
				{
					// If the second object is found at the same index as the
					// first
					// one
					if ( existingConnections[i].getObject2().equals(objectToBeRemoved) )
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
				if ( existingConnections[i].getObject1().equals(objectToBeRemoved) )
				{
					// If the first object is found at the same index as the
					// first
					// one
					if ( existingConnections[i].getObject1().equals(objectA) )
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
	public static Connection getConnection(Connection[] existingConnections, Object objectA,
			Object objectB) throws ConnectionDoesNotExist
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
				if ( existingConnections[i].getObject1().equals(objectA) )
				{
					// If the second object is found at the same index as the
					// first one
					if ( existingConnections[i].getObject2().equals(objectB) )
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
					if ( existingConnections[i].getObject1().equals(objectB) )
					{
						return existingConnections[i];
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
	public static boolean checkDeviceConnectiontypeSupport(Object objectA, Object objectB,
			String type)
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
	public static boolean checkConnectionExistence(Connection[] existingConnections,
			Object objectA, Object objectB)
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
					if ( existingConnections[i].getObject1().equals(objectA) )
					{
						// If the second object is found at the same index as
						// the first one.
						if ( existingConnections[i].getObject2().equals(objectB) )
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
					if ( existingConnections[i].getObject1().equals(objectB) )
					{
						// If the first object is found at the same index as the
						// first
						// one
						if ( existingConnections[i].getObject2().equals(objectA) )
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
	public static boolean checkConnectionExistence(ConnectionContainer[] connectionContainer,
			Object objectA, Object objectB)
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
				int objectAindex = connectionContainer[i].getContainer().indexOf(objectA);

				// If the first object is found
				if ( objectAindex != -1 )
				{
					// A temporary Connection object to see if Object A is
					// connected
					// to object B.
					Connection temp = (Connection) connectionContainer[i].getContainer().get(
							objectAindex);


					// If the second object is found as the second object in the
					// connection
					if ( temp.getObject2().equals(objectB) )
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
			int objectBindex = connectionContainer[i].getContainer().indexOf(objectB);

			// If the first object is found
			if ( objectBindex != -1 )
			{
				// A temporary Connection object to see if Object A is connected
				// to object B.
				Connection temp = (Connection) connectionContainer[i].getContainer().get(
						objectBindex);


				// If the second object is found as the second object in the
				// connection
				if ( temp.getObject2().equals(objectA) )
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
	public static boolean checkAndSetPortAvailability(Object objectA, Object objectB, String conType)
			throws ConnectionsIsNotPossible
	{
		Motherboard objectAmotherboard = null;
		Motherboard objectBmotherboard = null;

		try
		{
			// Since any object only has one motherboard this is a safe bet.
			objectAmotherboard = (Motherboard) objectA.getSpesificComponents(Motherboard.class)[0];
			objectBmotherboard = (Motherboard) objectB.getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			JOptionPane.showMessageDialog(null, "One of the devices does not have a motherboard.",
					"alert", JOptionPane.ERROR_MESSAGE);

			return false;
		}


		if ( conType == "RJ-45" )
		{
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
				indexA = objectAmotherboard.firstAvailableIndex(objectAmotherboard
						.getIntegLANPortsAvailable());

				// If the index is below 0, it means that there are no indexes
				// available.
				if ( indexA < 0 )
				{
					JOptionPane.showMessageDialog(null,
							"There are no available integrated LAN ports on "
									+ objectA.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				// TODO - Search for correct component, InternalNetworksCard.
				try
				{
					objectA.getSpesificComponents(InternalNetworksCard.class);
				}
				catch ( ObjectNotFoundException e )
				{
					JOptionPane.showMessageDialog(null, "A networkscard was not found inside "
							+ objectA.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}

			// Checks if the motherboard of the second object has an integrated
			// LAN port.
			if ( objectBmotherboard.LANcardIsIntegrated() )
			{
				// Gets the first available LAN port index.
				indexB = objectBmotherboard.firstAvailableIndex(objectBmotherboard
						.getIntegLANPortsAvailable());

				// If the index is below 0, it means that there are no indexes
				// available.
				if ( indexB < 0 )
				{
					JOptionPane.showMessageDialog(null,
							"There are no available integrated LAN ports on "
									+ objectB.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				// TODO - Search for correct component, InternalNetworksCard.
				try
				{
					objectB.getSpesificComponents(InternalNetworksCard.class);
				}
				catch ( ObjectNotFoundException e )
				{
					JOptionPane.showMessageDialog(null, "A networkscard was not found inside "
							+ objectB.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}


			/**
			 * If the function gets to this point it means that both the
			 * motherboards on the objects have available LAN ports and the
			 * indexes are retrieved.
			 */

			// Gets the arrays for with the booleans telling if the ports are
			// available.
			boolean[] objectAarray = objectAmotherboard.getIntegLANPortsAvailable();
			boolean[] objectBarray = objectBmotherboard.getIntegLANPortsAvailable();

			// Sets the indexes at both the arrays to true;
			objectAarray[indexA] = true;
			objectBarray[indexB] = true;

			// Sets the arrays on the actual motherboard components.
			objectAmotherboard.setIntegLANPortsAvailable(objectAarray);
			objectBmotherboard.setIntegLANPortsAvailable(objectBarray);

			// Adds each object to the other objects array of connection
			// objects.
			objectA.addConnectedDevices(objectB);
			objectB.addConnectedDevices(objectA);
		}
		else if ( conType == "USB" )
		{
			/**
			 * These two values will be changed or the function will get some
			 * exceptions and will not move on.
			 */
			int indexA = 0;
			int indexB = 0;


			if ( objectAmotherboard.getMaxUSBs() > 0 )
			{
				indexA = objectAmotherboard.firstAvailableIndex(objectAmotherboard
						.getUSBPortsAvailable());

				if ( indexA < 0 )
				{
					JOptionPane.showMessageDialog(null, "There are no available USB ports on "
							+ objectA.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, objectA.getObjectName()
						+ " has no USB ports available.", "alert", JOptionPane.ERROR_MESSAGE);

				return false;
			}


			if ( objectBmotherboard.getMaxUSBs() > 0 )
			{
				indexB = objectBmotherboard.firstAvailableIndex(objectBmotherboard
						.getUSBPortsAvailable());

				if ( indexB < 0 )
				{
					JOptionPane.showMessageDialog(null, "There are no available USB ports on "
							+ objectB.getObjectName(), "alert", JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, objectB.getObjectName()
						+ " has no USB ports available.", "alert", JOptionPane.ERROR_MESSAGE);

				return false;
			}


			/**
			 * The function gets to this point it means that both the
			 * motherboards on the objects have available ports and the indexes
			 * are retrieved.
			 */

			// Gets the arrays for with the booleans telling if the ports are
			// available.
			boolean[] objectAarray = objectAmotherboard.getUSBPortsAvailable();
			boolean[] objectBarray = objectBmotherboard.getUSBPortsAvailable();

			// Sets the indexes at both the arrays to true;
			objectAarray[indexA] = true;
			objectBarray[indexB] = true;

			// Sets the arrays on the actual motherboard components.
			objectAmotherboard.setUSBPortsAvailable(objectAarray);
			objectBmotherboard.setUSBPortsAvailable(objectBarray);

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
	public static boolean addConnection(Connection newCon, boolean withCheck)
	{
		// Gets the current canvas connections array.
		Connection currentCons[] = PrimeMain1.currentCanvas.getConnections();

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

		// Goes through the entier array of connections and set the connection
		// in
		// at the first empty space.
		for ( int i = 0; i < currentCons.length; i++ )
		{
			if ( currentCons[i] == null )
			{
				currentCons[i] = newCon;
				return true;
			}
		}

		// Extends the array with 5 spaces.
		extendConnectionArray();

		// Reruns the whole method with 5 added spaces in the connection array.
		return addConnection(newCon, withCheck);
	}




	/**
	 * This function extends the connection array of the currently selected
	 * canvas with 5 indexes.
	 */
	private static void extendConnectionArray()
	{
		Connection currentCons[] = PrimeMain1.currentCanvas.getConnections();
		Connection temp[] = new Connection[currentCons.length + 5];

		for ( int i = 0; i < currentCons.length; i++ )
		{
			temp[i] = currentCons[i];
		}

		PrimeMain1.currentCanvas.setConnections(temp);
	}



	/**
	 * This function returns an array of strings that contain strings found in
	 * both the given arrays. This method is use to find compatible user
	 * interfaces supported by two objects.
	 */
	public static String[] getCompatibleConnectionInterfaces(String[] a, String[] b)
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
}
