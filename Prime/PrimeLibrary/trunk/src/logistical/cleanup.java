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
package logistical;


import objects.Object;
import objects.Room;
import objects.Software;
import objects.softwareObjects.OperatingSystem;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.DeviceConnection;
import connections.InternalConnection;
import connections.NetworkConnection;


/**
 * Class that contains different cleanup and support functions that are used in the different parts of the program.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class cleanup
{

	/**
	 * Function to remove null pointer from the an array of objects.
	 */
	public static Object[] cleanObjectArray(Object[] array)
	{
		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given length
		Object[] Tempresults = new Object[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[tempCounter] = array[i];

				tempCounter++;
			}
		}


		// Makes the array that is to hold the results
		Object[] results = new Object[tempCounter];


		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < tempCounter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}


	/**
	 * Function to remove null pointer from the an array of objects.
	 */
	public static WorkareaCanvas[] cleanObjectArray(WorkareaCanvas[] array)
	{
		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given length
		WorkareaCanvas[] Tempresults = new WorkareaCanvas[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[tempCounter] = array[i];

				tempCounter++;
			}
		}


		// Makes the array that is to hold the results
		WorkareaCanvas[] results = new WorkareaCanvas[tempCounter];


		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < tempCounter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of software objects.
	 */
	public static Software[] cleanObjectArray(Software[] array)
	{
		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given length
		Software[] Tempresults = new Software[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[tempCounter] = array[i];

				tempCounter++;
			}
		}


		// Makes the array that is to hold the results
		Software[] results = new Software[tempCounter];


		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < tempCounter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of objects.
	 */
	public static OperatingSystem[] cleanObjectArray(OperatingSystem[] array)
	{
		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given length
		OperatingSystem[] Tempresults = new OperatingSystem[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[tempCounter] = array[i];

				tempCounter++;
			}
		}


		// Makes the array that is to hold the results
		OperatingSystem[] results = new OperatingSystem[tempCounter];


		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < tempCounter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}




	/**
	 * Function to remove null pointer from the an array of strings.
	 */
	public static String[] cleanObjectArray(String[] array)
	{
		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given length
		String[] Tempresults = new String[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[tempCounter] = array[i];

				tempCounter++;
			}
		}



		// Makes the array that is to hold the results
		String[] results = new String[tempCounter];


		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < tempCounter; i++ )
		{
			results[i] = Tempresults[i];
		}



		return results;
	}


	/**
	 * Function to remove null pointer from the an array of Connections.
	 */
	public static Connection[] cleanObjectArray(Connection[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		Connection[] Tempresults = new Connection[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		Connection[] results = new Connection[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}


	/**
	 * Function to remove null pointer from the an array of NetworkConnections.
	 */
	public static NetworkConnection[] cleanObjectArray(NetworkConnection[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		NetworkConnection[] Tempresults = new NetworkConnection[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		NetworkConnection[] results = new NetworkConnection[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of DeviceConnections.
	 */
	public static DeviceConnection[] cleanObjectArray(DeviceConnection[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		DeviceConnection[] Tempresults = new DeviceConnection[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		DeviceConnection[] results = new DeviceConnection[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of InternalConnections.
	 */
	public static InternalConnection[] cleanObjectArray(
			InternalConnection[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		InternalConnection[] Tempresults = new InternalConnection[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		InternalConnection[] results = new InternalConnection[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of WidgetRoom.
	 */
	public static WidgetRoom[] cleanObjectArray(WidgetRoom[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		WidgetRoom[] Tempresults = new WidgetRoom[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		WidgetRoom[] results = new WidgetRoom[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}


	/**
	 * Function to remove null pointer from the an array of Room.
	 */
	public static Room[] cleanObjectArray(Room[] array)
	{
		// Temporary counter for the function
		int counter = 0;

		// Makes an array with given length
		Room[] Tempresults = new Room[array.length];

		// Goes through all of the found components array and moves those that
		// are not null
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				Tempresults[counter] = array[i];

				counter++;
			}
		}

		// Makes the array that is to hold the results
		Room[] results = new Room[counter];

		// Creates a new array that will copy all the non-null objects.
		for ( int i = 0; i < counter; i++ )
		{
			results[i] = Tempresults[i];
		}

		return results;
	}



	/**
	 * Function to remove null pointer from the an array of booleans. Its arranged after the given boolean so that the
	 * given boolean is placed first and the rest is placed afterwards.
	 */
	public static boolean[] cleanObjectArray(boolean[] array, boolean given)
	{
		// Temporary counter for the function
		int counterGiven = 0;

		// Goes through all of the array and counts all the indexes that hold
		// the given boolean.
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] == given )
			{
				counterGiven++;
			}
		}

		// Makes the array that is to hold the results
		boolean[] results = new boolean[array.length];

		// Creates a new array that will copy all the given booleans first and
		// then the non-given booleans.
		for ( int i = 0; i < results.length; i++ )
		{
			if ( i < counterGiven )
			{
				results[i] = given;
			}
			else
			{
				results[i] = !given;
			}
		}

		return results;
	}
}
