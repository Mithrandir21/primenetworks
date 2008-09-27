package managment;


import logistical.cleanup;
import objects.Object;
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
	public static String[] addItems(String[] NewItems, String[] Items) throws Exception
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
				throw new Exception("The items " + NewItems[i] + " is already present.");
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
								+ "be deleted. Contact systemadminstrator.", ToBeRemoved[i]);

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
	public static String[] changeArrayItem(String NewItem, String OldItem, String[] Items)
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
}
