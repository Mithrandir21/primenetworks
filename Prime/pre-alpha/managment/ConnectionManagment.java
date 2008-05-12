package managment;


import logistical.cleanup;
import objects.ExternalHardware;
import objects.Object;
import connections.Connection;
import connections.DeviceConnection;
import connections.InternalConnection;
import connections.NetworkConnection;
import exceptions.*;
import containers.*;


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
	public static Connection makeConnection(Connection[] existingConnections,
			String conName, String conDesc, Object objectA, Object objectB,
			String type, Class conClass)
	{
		Connection connection = null;


		// Checks to see if there is any previous connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == false )
		{
			// Checks to see if both the devices support the type of connection
			if ( checkDeviceConnectiontypeSupport(objectA, objectB, type) == true )
			{
				/*
				 * If there is no previous connection between A and B, and they
				 * both support the connection type, a connection is made
				 * between them.
				 */
				if ( conClass.equals(DeviceConnection.class) )
				{
					connection = new DeviceConnection(conName, conDesc,
							objectA, objectB, type);
				}
				else if ( conClass.equals(InternalConnection.class) )
				{
					connection = new InternalConnection(conName, conDesc,
							objectA, objectB, type);
				}
				else
				{
					connection = new NetworkConnection(conName, conDesc,
							objectA, objectB, type);
				}
			}
		}

		return connection;
	}


	/**
	 * Breaks connections between two components in the system. It removes the
	 * connection from the array of existing connections. This methode throws
	 * {@link  exceptions.ConnectionDoesNotExist  ConnectionDoesNotExist}
	 * exception, if there is no connection between the two given objects.
	 * 
	 * @return Returns the given connections array without the connection
	 *         between the two given object. The array is cleaned for any empty
	 *         indexs.
	 */
	public static Connection[] breakConnection(
			Connection[] existingConnections, Object objectA, Object objectB)
			throws ConnectionDoesNotExist
	{

		// Checks to see if there really is a connection between A and B
		if ( checkConnectionExistence(existingConnections, objectA, objectB) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getName(), objectB
					.getName());
		}



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
					// first
					// one
					if ( existingConnections[i].getObject2().equals(objectB) )
					{
						existingConnections[i] = null;
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
				if ( existingConnections[i].getObject1().equals(objectB) )
				{
					// If the first object is found at the same index as the
					// first
					// one
					if ( existingConnections[i].getObject1().equals(objectA) )
					{
						existingConnections[i] = null;
					}
				}
			}
		}

		// Removes any null-pointers in the array of connections
		existingConnections = cleanup.cleanObjectArray(existingConnections);

		return existingConnections;
	}




	/**
	 * This methode changes a connection between A and B, to be a connection
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
			throw new ConnectionDoesNotExist(objectA.getName(),
					objectToBeRemoved.getName());
		}

		// Then checks to see if there already exists a connection between
		// object a and c
		if ( checkConnectionExistence(existingConnections, objectA, objectC) == true )
		{
			throw new ConnectionDoesExist(objectA.getName(), objectToBeRemoved
					.getName());
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
					if ( existingConnections[i].getObject2().equals(
							objectToBeRemoved) )
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
				if ( existingConnections[i].getObject1().equals(
						objectToBeRemoved) )
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
	 * Checks to see if both the devices that are to be connected support the
	 * connecting interface.
	 * 
	 * @return Returns true if both the devices support the interface, and false
	 *         if one or both do not support the interface.
	 */
	static boolean checkDeviceConnectiontypeSupport(Object objectA,
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
	static boolean checkConnectionExistence(Connection[] existingConnections,
			Object objectA, Object objectB)
	{
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
					if ( existingConnections[i].getObject2().equals(objectB) )
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
					if ( existingConnections[i].getObject1().equals(objectA) )
					{
						return true;
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
	static boolean checkConnectionExistence(
			ConnectionContainer[] connectionContainer, Object objectA,
			Object objectB)
	{
		/*
		 * Checks to see if object A is the first object in any connection. Then
		 * if it find object A as the first object, it looks for object B at the
		 * same index as object A.
		 */
		for ( int i = 0; i < connectionContainer.length; i++ )
		{
			// Finds the index of object A in the arraylist. Or returns -1 if
			// not found.
			int objectAindex = connectionContainer[i].getContainer().indexOf(
					objectA);

			// If the first object is found
			if ( objectAindex != -1 )
			{
				// A temporary Connection object to see if Object A is connected
				// to object B.
				Connection temp = (Connection) connectionContainer[i]
						.getContainer().get(objectAindex);


				// If the second object is found as the second object in the
				// connection
				if ( temp.getObject2().equals(objectB) )
				{
					return true;
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
				if ( temp.getObject2().equals(objectA) )
				{
					return true;
				}
			}
		}

		return false;
	}
}
