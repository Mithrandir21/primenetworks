package managment;


import logistical.cleanup;
import objects.Object;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
import exceptions.StringNotFoundInArrayException;


/**
 * Class that contains different function that add, remove and replace
 * components from a given array. It is used in the different parts of the
 * program, specially {@link clients.Desktop Desktops}, {@link clients.Laptop
 * Laptops}, {@link objects.Servers Servers} and {@link infrastructure.Rack
 * Racks}.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ArrayManagment
{

	/**
	 * Function to add items(s) to the an array of items(Strings).
	 * 
	 * @param NewItems
	 *            An array of new items.
	 * @param Items
	 *            The current items list.
	 * @throws Exception
	 *             Throws an exception with a message which says that the item
	 *             is already present.
	 */
	public static String[] addItems(String[] NewItems, String[] Items)
			throws Exception
	{
		// The number of new items to be added to the items array
		int numberOfNewItems = 0;

		// Checks to see whether any of the indexes in the new components array
		// is null.
		for ( int i = 0; i < NewItems.length; i++ )
		{
			if ( NewItems[i] != null )
			{
				numberOfNewItems++;
			}
		}


		// Makes a new items array with the added number of indexs
		String[] tempItems = new String[Items.length + numberOfNewItems];

		// The boolean array that tells whether or not any of the items already
		// are in the array.
		boolean[] areFound = arrayContains(Items, NewItems);

		// Checks to see if any of the items are found within the array
		for ( int i = 0; i < areFound.length; i++ )
		{
			if ( areFound[i] == true )
			{
				throw new Exception("The items " + NewItems[i]
						+ " is already present.");
			}
		}


		// Addes the old items to the new array
		for ( int i = 0; i < Items.length; i++ )
		{
			tempItems[i] = Items[i];
		}


		// Addes the new items to the end of the new array
		for ( int i = 0; i < numberOfNewItems; i++ )
		{
			tempItems[Items.length + i] = NewItems[i];
		}

		return tempItems;
	}



	/**
	 * Function to remove items from the array of items.
	 * 
	 * @param ToBeRemoved
	 *            Items to be removed.
	 * @param items
	 *            The current items list.
	 * @throws ObjectNotFoundInArrayException
	 *             Throws an exception which states that one or more of the
	 *             items that are to be removed do not exist in the items array
	 *             they are trying to be removed from.
	 */
	public static String[] removeItems(String[] ToBeRemoved, String[] items)
			throws StringNotFoundInArrayException
	{
		// booleans to check whether the objects to be removed are found or not
		boolean[] objectFound = new boolean[ToBeRemoved.length];

		// Number of items removed
		int itemsRemoved = 0;

		// Goes through all the items and removes the one(s) to be removed
		for ( int i = 0; i < items.length; i++ )
		{
			if ( items[i] != null )
			{
				for ( int j = 0; j < ToBeRemoved.length; j++ )
				{
					if ( items[i] != null )
					{
						if ( items[i].equals(ToBeRemoved[j]) )
						{
							items[i] = null;

							objectFound[j] = true;

							itemsRemoved++;
						}
					}
				}
			}
		}

		// Checks whether all the objects were found and removed
		for ( int i = 0; i < objectFound.length; i++ )
		{
			if ( objectFound[i] == false )
			{
				StringNotFoundInArrayException exception = new StringNotFoundInArrayException(
						"String was not found, hence cannot "
								+ "be deleted. Contact systemadminstrator.",
						ToBeRemoved[i]);

				throw exception;
			}
		}


		items = cleanup.cleanObjectArray(items);


		return items;
	}



	/**
	 * Function for replacing a spesific given item with a given new item.
	 * 
	 * @param NewItem
	 *            The string to replace the previous one.
	 * @param OldItem
	 *            The string to be replaced.
	 * @param Items
	 *            The current string list.
	 */
	public static String[] changeArrayItem(String NewItem, String OldItem,
			String[] Items)
	{
		// Goes through all the strings and replaces the old string with the new
		// one
		for ( int i = 0; i < Items.length; i++ )
		{

			if ( Items[i] != null )
			{
				if ( Items[i].equals(OldItem) )
				{
					Items[i] = NewItem;
				}
			}
		}

		return Items;
	}





	// SEARCH FUNCTIONS

	/**
	 * Get specific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current
	 *            components array.
	 * @param components
	 *            The current components list.
	 * @throws ObjectNotFoundException
	 *             Throws an exception which states that there were not objects
	 *             found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getSpesificComponents(Class ComponentClass,
			Object[] components, int componentCounter)
			throws ObjectNotFoundException
	{
		// boolean to check whether the object is found or not
		boolean objectNotFound = true;

		// Counter for number of components found
		int tempCounter = 0;

		// Container that will hold all the found components
		Object[] componentsFound = new Object[componentCounter];


		// Searches for components of the given class
		for ( int i = 0; i < componentCounter; i++ )
		{
			/*
			 * If the given components class matches the present components
			 * class, it will be added to the container
			 */
			if ( components[i].getClass().equals(ComponentClass) )
			{
				componentsFound[tempCounter] = components[i];

				tempCounter++;

				objectNotFound = false;
			}
		}


		// Checks whether all the objects were found and removed
		if ( objectNotFound == true )
		{
			ObjectNotFoundException exception = new ObjectNotFoundException(
					"Object(s) with the given component, "
							+ ComponentClass.getCanonicalName()
							+ " were not found.", ComponentClass);

			throw exception;
		}



		// Cleans the array of any null pointers at the end
		componentsFound = cleanup.cleanObjectArray(componentsFound);

		return componentsFound;
	}



	// CHECK FUNCTIONS
	/**
	 * Check function to determine whether or not the the given array contains
	 * the given object.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static boolean arrayContains(Object[] array, Object searchObject)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		boolean foundObject = false;


		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				if ( array[i].equals(searchObject) )
				{
					foundObject = true;

					// Sets i to array length to get out of the loop.
					i = array.length;
				}
			}
		}

		return foundObject;
	}


	/**
	 * Check function to determine whether or not the the given array contains
	 * the given object.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static int arrayContainsReturnsIndex(Object[] array,
			Object searchObject)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		int foundObject = -1;


		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				if ( array[i].equals(searchObject) )
				{
					foundObject = i;

					// Sets i to array length to get out of the loop.
					i = array.length;
				}
			}
		}

		return foundObject;
	}



	/**
	 * Check function to determine whether or not the the given array contains
	 * any of the given objects in the searchObjects array.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObjects
	 *            The objects that is to be searched for.
	 * @return The array of booleans that tells, by way of the index, which
	 *         object are found.
	 */
	public static boolean[] arrayContains(String[] array, String[] searchObjects)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		boolean[] foundObject = new boolean[searchObjects.length];


		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				for ( int j = 0; j < searchObjects.length; j++ )
				{
					if ( array[i] != null )
					{
						if ( array[i].equals(searchObjects[j]) )
						{
							foundObject[j] = true;
						}
					}
				}
			}
		}

		return foundObject;
	}



	/**
	 * Check function to determine whether or not the the given array contains
	 * the given searchObject.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 * @return The array of booleans that tells, by way of the index, which
	 *         object are found.
	 */
	public static boolean arrayContains(String[] array, String searchObject)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		boolean foundObject = false;


		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				if ( array[i].equals(searchObject) )
				{
					foundObject = true;
				}
			}
		}

		return foundObject;
	}




	/**
	 * This function creates a new java.lang.Object array with 5 more
	 * indexes then the given Object array. It then copies over the 
	 * information in the given array into the new array, which
	 * leaves an array with 5 free spaces at the end.
	 * 
	 * @param data
	 * 			The array that will be copied over to a new array
	 * 			with 5 additional empty indexes at the end.
	 * @return
	 * 			A new Object double array that will contain 5 new
	 * 			empty indexes at the end.
	 */
	public static java.lang.Object[][] add5ArraySpaces(java.lang.Object[][] data)
	{
		java.lang.Object[][] temp = null;

		if ( data != null )
		{
			// Makes a new objects array with 5 additional indexes.
			temp = new java.lang.Object[data.length + 5][data[0].length];

			for ( int i = 0; i < data.length; i++ )
			{
				if ( data[i] != null )
				{
					for ( int j = 0; j < data[i].length; j++ )
					{
						temp[i][j] = data[i][j].toString();
					}
				}
			}
		}
		// This means that the data received was null
		else
		{
			// Returns an empty array with 5 indexes
			return new java.lang.Object[5][4];
		}

		// Returns the newly made array with 5 extra indexes at the end
		return temp;

	}



	/**
	 * This function creates a new String array with 5 more
	 * indexes then the given String array. It then copies over the 
	 * information in the given array into the new array, which
	 * leaves an array with 5 free spaces at the end.
	 * 
	 * @param data
	 * 			The array that will be copied over to a new array
	 * 			with 5 additional empty indexes at the end.
	 * @return
	 * 			A new String double array that will contain 5 new
	 * 			empty indexes at the end.
	 */
	public static String[][] add5ArraySpaces(String[][] data)
	{
		String[][] temp = null;

		if ( data != null )
		{
			// Makes a new objects array with 5 additional indexes.
			temp = new String[data.length + 5][data[0].length];

			for ( int i = 0; i < data.length; i++ )
			{
				if ( data[i][0] != null )
				{
					for ( int j = 0; j < data[i].length; j++ )
					{
						temp[i][j] = data[i][j].toString();
					}
				}
			}
		}
		
		// This means that the data received was null
		else
		{
			// Returns an empty array with 5 indexes
			return new String[5][4];
		}

		// Returns the newly made array with 5 extra indexes at the end
		return temp;

	}
	
	
	
	/**
	 * This method removes any empty first level index from the given 
	 * data.
	 * 
	 */
	/**
	 * This method removes any empty first level index from the given 
	 * data.
	 * 
	 * @param data
	 * 			The array that will be checked for empty array
	 * 			indexes.
	 * @return
	 * 			Return a new array with no empty indexes.
	 */
	public static String[][] removeEmptyIndexes(String[][] data)
	{
		if ( data != null )
		{
			int notEmptySpaces = 0;
			
			
			for ( int i = 0; i < data.length; i++ )
			{
				if ( data[i][0] != null )
				{
					notEmptySpaces++;
				}
			}
			
			
			String[][] temp = new String[notEmptySpaces][data[0].length];
			
			
			for( int i = 0; i < temp.length; i++ )
			{
				for( int j = 0; j < temp[i].length; j++ )
				{
					temp[i][j] = data[i][j];
				}
			}
			
			return temp;
		}
		
		
		return data;
	}
}
