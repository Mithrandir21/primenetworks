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


import java.util.ArrayList;
import java.util.Iterator;

import logistical.cleanup;
import objects.Object;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
import exceptions.StringNotFoundInArrayException;


/**
 * Class that contains different function that add, remove and replace
 * components from a given array. It is used in the different parts of the
 * program, specially {@link objects.clientObjects.Desktop Desktops},
 * {@link objects.clientObjects.Laptop Laptops}, {@link objects.Servers Servers}
 * and {@link objects.rackUnits.Rack Racks}.
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
			if ( areFound[i] )
			{
				throw new Exception("The items " + NewItems[i]
						+ " is already present.");
			}
		}


		// Addes the old items to the new array
		System.arraycopy(Items, 0, tempItems, 0, Items.length);

		// Addes the old items to the new array
		System.arraycopy(NewItems, 0, tempItems, Items.length, numberOfNewItems);

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
			if ( !(objectFound[i]) )
			{
				throw new StringNotFoundInArrayException(
						"String was not found, hence cannot "
								+ "be deleted. Contact systemadminstrator.",
						ToBeRemoved[i]);
			}
		}


		items = cleanup.cleanObjectArray(items);


		return items;
	}



	/**
	 * Function to remove a single object from the array of objects.
	 * 
	 * @param ToBeRemoved
	 *            Object to be removed.
	 * @param items
	 *            The current objects list.
	 */
	public static boolean removeItem(Object ToBeRemoved, Object[] items)
	{
		// Goes through all the items and removes the one to be removed
		for ( int i = 0; i < items.length; i++ )
		{
			if ( items[i] != null )
			{
				if ( items[i].equals(ToBeRemoved) )
				{
					items[i] = null;
					return true;
				}
			}
		}

		// If the object is not found in the given array of objects
		return false;
	}



	/**
	 * Function to remove a single object from the array of objects. (Returns
	 * "-1" if object not found in the given array).
	 * 
	 * @param ToBeRemoved
	 *            Object to be removed.
	 * @param items
	 *            The current objects list.
	 */
	public static int removeItemReturnIndex(Object ToBeRemoved, Object[] items)
	{
		// Goes through all the items and removes the one to be removed
		for ( int i = 0; i < items.length; i++ )
		{
			if ( items[i] != null )
			{
				if ( items[i].equals(ToBeRemoved) )
				{
					items[i] = null;
					return i;
				}
			}
		}

		// If the object is not found in the given array of objects
		return -1;
	}



	/**
	 * Function to remove a single object from the array of objects.
	 * 
	 * @param ToBeRemoved
	 *            Object to be removed.
	 * @param items
	 *            The current objects list.
	 */
	public static boolean removeItem(Object ToBeRemoved, ArrayList<Object> items)
	{
		// Iterates through the given ArrayList
		Iterator<Object> itr = items.iterator();
		while ( itr.hasNext() )
		{
			Object element = itr.next();

			if ( element != null )
			{
				// If the given class is the same as the element class
				if ( element.getObjectSerial() == ToBeRemoved.getObjectSerial() )
				{
					return items.remove(element);
				}
			}
		}

		// If the object is not found in the given array of objects
		return false;
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
		if ( ComponentClass != null && components != null )
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
				if ( components[i] != null
						&& components[i].getClass().equals(ComponentClass) )
				{
					componentsFound[tempCounter] = components[i];

					tempCounter++;

					objectNotFound = false;
				}
			}


			// Checks whether all the objects were found and removed
			if ( objectNotFound )
			{
				throw new ObjectNotFoundException(
						"Object(s) with the given component, "
								+ ComponentClass.getCanonicalName()
								+ " were not found.", ComponentClass);
			}



			// Cleans the array of any null pointers at the end
			componentsFound = cleanup.cleanObjectArray(componentsFound);

			return componentsFound;
		}


		return null;
	}

	/**
	 * Get specific component by searching for a component with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 * @param list
	 *            The current components list.
	 * @throws ObjectNotFoundException
	 *             Throws an exception which states that there were not objects
	 *             found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static Object getSpesificComponent(Class ComponentClass,
			ArrayList<Object> list) throws ObjectNotFoundException
	{
		// Iterates through the given ArrayList
		Iterator<Object> itr = list.iterator();
		while ( itr.hasNext() )
		{
			Object element = itr.next();

			// If the given class is the same as the element class
			if ( element.getClass().equals(ComponentClass) )
			{
				return element;
			}
		}


		// If the code gets here, it means that no object was found in the
		// arraylist with the given class
		throw new ObjectNotFoundException("Object with given class, "
				+ ComponentClass.getCanonicalName()
				+ ", was not found in the given ArrayList.", ComponentClass);
	}




	/**
	 * Get specific objects by searching for objects with the give class type.
	 * 
	 * @return Returns an array of objects that match with the given class.
	 * @param objectCounter
	 *            The counter that tells how many objects are in the current
	 *            objects array.
	 * @param objects
	 *            The current objects list.
	 * @throws ObjectNotFoundException
	 *             Throws an exception which states that there were not objects
	 *             found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getSpesificObjects(Class objectClass,
			Object[] objects) throws ObjectNotFoundException
	{
		if ( objects != null )
		{
			// boolean to check whether the object is found or not
			boolean objectNotFound = true;

			// Counter for number of components found
			int tempCounter = 0;

			// Container that will hold all the found object
			Object[] objectsFound = new Object[objects.length];


			// Searches for object of the given class
			for ( int i = 0; i < objects.length; i++ )
			{
				/*
				 * If the given object class matches the present object class,
				 * it
				 * will be added to the container
				 */
				if ( objects[i].getClass().equals(objectClass) )
				{
					objectsFound[tempCounter] = objects[i];

					tempCounter++;

					objectNotFound = false;
				}
			}


			// Checks whether all the objects were found and removed
			if ( objectNotFound )
			{
				ObjectNotFoundException exception = new ObjectNotFoundException(
						"Object(s) of the given type, "
								+ objectClass.getCanonicalName()
								+ " were not found.", objectClass);

				throw exception;
			}

			// Cleans the array of any null pointers at the end
			objectsFound = cleanup.cleanObjectArray(objectsFound);

			return objectsFound;
		}

		return null;
	}



	/**
	 * Get specific objects by searching for objects with the give name.
	 * 
	 * @param array
	 *            The arraylist that is to be checked.
	 * @param name
	 *            The object name that is to be searched for.
	 * @return The found object or NULL.
	 */
	public static Object getObjectWithGivenName(ArrayList<Object> array,
			String name)
	{
		return getObjectWithGivenName(array.toArray(new Object[1]), name);
	}



	/**
	 * Get specific objects by searching for objects with the give name.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param name
	 *            The object name that is to be searched for.
	 * @return The found object or NULL.
	 */
	public static Object getObjectWithGivenName(Object[] array, String name)
	{
		if ( array != null && name != null )
		{
			for ( int i = 0; i < array.length; i++ )
			{
				if ( array[i] != null )
				{
					if ( array[i].getObjectName().equals(name) )
					{
						return array[i];
					}
				}
			}
		}

		return null;
	}


	// CHECK FUNCTIONS
	/**
	 * Check function to determine whether or not the the given arraylist
	 * contains the given object. Compares {@link Object} serial numbers.
	 * 
	 * @param list
	 *            The arraylist that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static boolean arrayContains(ArrayList<Object> list,
			Object searchObject)
	{
		if ( list != null && searchObject != null )
		{
			return arrayContains(list.toArray(new Object[0]), searchObject);
		}

		return false;
	}


	/**
	 * Check function to determine whether or not the the given array contains
	 * the given object. Compares {@link Object} serial numbers.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static boolean arrayContains(Object[] array, Object searchObject)
	{
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				if ( array[i].getObjectSerial() == searchObject
						.getObjectSerial() )
				{
					return true;
				}
			}
		}

		return false;
	}


	/**
	 * Check function to determine whether or not the the given array contains
	 * the given object. Compares {@link Object} serial numbers.
	 * 
	 * @return -1 if the given searchObject is not found in the given
	 *         {@link Object} array. Returns the index of the searchObject
	 *         in the given {@link Object} array.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static int arrayContainsReturnsIndex(Object[] array,
			Object searchObject)
	{
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] != null )
			{
				if ( array[i].getObjectSerial() == searchObject
						.getObjectSerial() )
				{
					return i;
				}
			}
		}

		return -1;
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
		if ( array != null && searchObjects != null )
		{
			// Boolean to tell whether or not the given object is found within
			// the
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

		return null;
	}



	/**
	 * Check function to determine whether or not the the given array contains
	 * the given searchObject.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 * @return The boolean on whether the String was found.
	 */
	public static boolean arrayContains(String[] array, String searchObject)
	{
		if ( array != null && searchObject != null )
		{
			for ( int i = 0; i < array.length; i++ )
			{
				if ( array[i] != null )
				{
					if ( array[i].equals(searchObject) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}



	/**
	 * Check function to determine whether or not the the given array contains
	 * the given searchObject.
	 * 
	 * @param list
	 *            The arraylist that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 * @return The boolean on whether the String was found.
	 */
	public static boolean arrayContainsWithGivenName(ArrayList<Object> list,
			String name)
	{
		if ( list != null && name != null )
		{
			return arrayContainsWithGivenName(list.toArray(new Object[0]), name);
		}

		return false;
	}

	/**
	 * Check function to determine whether or not the the given array contains
	 * the given searchObject.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 * @return The boolean on whether the String was found.
	 */
	public static boolean arrayContainsWithGivenName(Object[] array, String name)
	{
		if ( array != null && name != null )
		{
			for ( int i = 0; i < array.length; i++ )
			{
				if ( array[i] != null )
				{
					if ( array[i].getObjectName().equals(name) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * This function creates a new java.lang.Object array with 5 more indexes
	 * then the given Object array. It then copies over the information in the
	 * given array into the new array, which leaves an array with 5 free spaces
	 * at the end.
	 * 
	 * @param data
	 *            The array that will be copied over to a new array with 5
	 *            additional empty indexes at the end.
	 * @return A new Object double array that will contain 5 new empty indexes
	 *         at the end.
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
						if ( data[i][j] != null )
						{
							temp[i][j] = data[i][j].toString();
						}
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
	 * This function creates a new String array with 5 more indexes then the
	 * given String array. It then copies over the information in the given
	 * array into the new array, which leaves an array with 5 free spaces at the
	 * end.
	 * 
	 * @param data
	 *            The array that will be copied over to a new array with 5
	 *            additional empty indexes at the end.
	 * @return A new String double array that will contain 5 new empty indexes
	 *         at the end.
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
						if ( data[i][j] != null )
						{
							temp[i][j] = data[i][j];
						}
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
	 * This method removes any empty first level index from the given data.
	 * 
	 * @param data
	 *            The array that will be checked for empty array indexes.
	 * @return Return a new array with no empty indexes.
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


			for ( int i = 0; i < temp.length; i++ )
			{
				for ( int j = 0; j < temp[i].length; j++ )
				{
					if ( data[i][j] != null )
					{
						temp[i][j] = data[i][j];
					}
				}
			}

			return temp;
		}


		return data;
	}




	/**
	 * Copies the pointers in the given array into a new array. (No search is
	 * made for empty indexes.)
	 * 
	 * @return A new array with the same pointers as the given array.
	 */
	public static Object[] shallowCopyArray(Object[] original)
	{
		Object[] newArray = new Object[original.length];

		// Addes the old items to the new array
		for ( int i = 0; i < original.length; i++ )
		{
			if ( original[i] != null )
			{
				newArray[i] = original[i];
			}
		}

		return newArray;
	}
}
