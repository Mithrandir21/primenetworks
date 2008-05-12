package logistical;
import objects.Object;
import connections.*;

/**
 * Class that contains different cleanup and support functions that are
 * used in the different parts of the program. 
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class cleanup
{
	
	/**
	 * Function to remove null pointer from the an array of objects.
	 * 
	 */
	public static Object[] cleanObjectArray(Object[] array)
	{
		
		// Temporary counter for the function
		int tempCounter = 0;
		
		// Makes an array with given lenght
		Object[] Tempresults = new Object[array.length];
		
		// Goes through all of the found components array and moves those that are not null
		for(int i = 0;i<array.length;i++)
		{
			if(array[i] != null)
			{
				Tempresults[tempCounter] = array[i];
				
				tempCounter++;
			}
		}
		
		
		// Makes the array that is to hold the results
		Object[] results = new Object[tempCounter];
		
		
		// Creates a new array that will copy all the non-null objects.
		for(int i = 0;i<tempCounter;i++)
		{
			results[i] = Tempresults[i];
		}
		
		
		
		
		return results;
	}
	
	
	
	/**
	 * Function to remove null pointer from the an array of strings.
	 * 
	 */
	public static String[] cleanObjectArray(String[] array)
	{
		
		// Temporary counter for the function
		int tempCounter = 0;
		
		// Makes an array with given lenght
		String[] Tempresults = new String[array.length];
		
		// Goes through all of the found components array and moves those that are not null
		for(int i = 0;i<array.length;i++)
		{
			if(array[i] != null)
			{
				Tempresults[tempCounter] = array[i];
				
				tempCounter++;
			}
		}
		
		
		
		// Makes the array that is to hold the results
		String[] results = new String[tempCounter];
		
		
		// Creates a new array that will copy all the non-null objects.
		for(int i = 0;i<tempCounter;i++)
		{
			results[i] = Tempresults[i];
		}
		
		
		
		return results;
	}
	
	
	/**
	 * Function to remove null pointer from the an array of Connections.
	 * 
	 */
	public static Connection[] cleanObjectArray(Connection[] array)
	{
		
		// Temporary counter for the function
		int tempCounter = 0;
		
		// Makes an array with given lenght
		Connection[] Tempresults = new Connection[array.length];
		
		// Goes through all of the found components array and moves those that are not null
		for(int i = 0;i<array.length;i++)
		{
			if(array[i] != null)
			{
				Tempresults[tempCounter] = array[i];
				
				tempCounter++;
			}
		}
		
	
		// Makes the array that is to hold the results
		Connection[] results = new Connection[tempCounter];
		
		
		// Creates a new array that will copy all the non-null objects.
		for(int i = 0;i<tempCounter;i++)
		{
			results[i] = Tempresults[i];
		}
		
		
		
		return results;
	}
}
