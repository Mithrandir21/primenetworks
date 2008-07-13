package logistical;


import objects.Object;
import connections.*;


/**
 * Class that contains different cleanup and support functions that are used in
 * the different parts of the program.
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

		// Makes an array with given lenght
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
	 * Function to remove null pointer from the an array of strings.
	 */
	public static String[] cleanObjectArray(String[] array)
	{

		// Temporary counter for the function
		int tempCounter = 0;

		// Makes an array with given lenght
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
	public static InternalConnection[] cleanObjectArray(InternalConnection[] array)
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
	 * Function to remove null pointer from the an array of booleans. Its
	 * arranged after the given boolean so that the given boolean is placed
	 * first and the rest is placed afterwards.
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
