package managment;


import javax.swing.*;

import clients.Desktop;

import logistical.cleanup;
import objects.*;
import objects.Object;
import widgetManipulation.WidgetObject;
import connections.*;
import exceptions.*;
import graphics.PrimeMain1;
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
	 * {@link  exceptions.ConnectionDoesNotExist  ConnectionDoesNotExist}
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
	public static Connection[] changeConnection(Connection[] existingConnections, Object objectA,
			Object objectToBeRemoved, Object objectC) throws ConnectionDoesNotExist,
			ConnectionDoesExist
	{

		// First checks to see if there exist a connection between object a and
		// "ToBeRemoved"
		if ( checkConnectionExistence(existingConnections, objectA, objectToBeRemoved) == false )
		{
			throw new ConnectionDoesNotExist(objectA.getObjectName(), objectToBeRemoved.getObjectName());
		}

		// Then checks to see if there already exists a connection between
		// object a and c
		if ( checkConnectionExistence(existingConnections, objectA, objectC) == true )
		{
			throw new ConnectionDoesExist(objectA.getObjectName(), objectToBeRemoved.getObjectName());
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
						// the
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


	public static int getConnectionClass(WidgetObject widObj)
	{
		if ( widObj.getObject() instanceof Clients )
		{
			System.out.println("This is a client");
		}
		else if ( widObj.getObject() instanceof Servers )
		{
			System.out.println("This is a server");
		}


		return -1;
	}



	public static String[] getCompatibleConnectionInterfaces(String[] a, String[] b)
	{
		String[] longestArray = null;
		String[] otherArray = null;

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

		String temp[] = new String[longestArray.length];

		int tempPlacesTaken = 0;


		for ( int i = 0; i < longestArray.length; i++ )
		{
			for ( int j = 0; j < otherArray.length; j++ )
			{
				if ( longestArray[i].equals(otherArray[j]) )
				{
					temp[tempPlacesTaken] = longestArray[i];
					tempPlacesTaken++;
					j = otherArray.length;
				}
			}
		}

		
		temp = stringArrayCrop(temp);
		

		return temp;
	}



	private static String[] stringArrayCrop(String[] array)
	{
		int indexes = 0;

		String[] temp;



		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				indexes++;
			}
		}


		temp = new String[indexes];


		for ( int i = 0; i < indexes; i++ )
		{
			temp[i] = array[i];
		}


		return temp;
	}
}
