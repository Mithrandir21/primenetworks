package jUnitTests;


import junit.framework.TestCase;
import managment.ArrayManagment;

import org.junit.Test;

import exceptions.StringNotFoundInArrayException;


public class ArrayManagmentTest extends TestCase
{
	String[] array = new String[0];

	String[] newStrings = new String[3];

	String a = "a";

	String b = "b";

	String c = "c";
	


	/**
	 * This testes the addItem function that should add a String to an array of Strings. The function checks whether or
	 * not the String already exists in the array.
	 */
	@Test
	public void testAddItems()
	{
		newStrings[0] = a;
		newStrings[1] = b;


		try
		{
			array = ArrayManagment.addItems(newStrings, array);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		// Checks that the array is not null, as it was original.
		assertNotNull(array);

		// Checks the newly added strings against the array indexes.
		assertEquals(a, array[0]);
		assertEquals(b, array[1]);

		// Checks the array length after addition of the 2 strings.
		assertEquals(2, array.length);


		// Reset
		array = null;
		newStrings = null;
	}


	/**
	 * This testes the function to remove a String from an array of Strings. The function also removes null pointers
	 * from the array so that there are no null pointers left after the String removal.
	 */
	@Test
	public void testRemoveItems()
	{
		newStrings[0] = a;
		newStrings[1] = b;


		try
		{
			array = ArrayManagment.addItems(newStrings, array);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}


		// Checks to see whether the objects have been added.
		assertNotNull(array);


		// Checks to see the length of the components array.
		assertEquals(array.length, 2);


		// Creates the objects to be removed array.
		String[] toBeRemoved = new String[1];

		toBeRemoved[0] = a;


		try
		{
			array = ArrayManagment.removeItems(toBeRemoved, array);
		}
		catch ( StringNotFoundInArrayException e )
		{
			e.printStackTrace();
		}


		// Check if the first component is added to the list.
		assertEquals(b, array[0]);

		// Checks to see length of the components array.
		assertEquals(1, array.length);


		// Reset
		array = null;
		newStrings = null;
	}


	/**
	 * This testes the function that changes one String at and index in an array with another.
	 */
	@Test
	public void testChangeArrayItem()
	{
		newStrings[0] = a;
		newStrings[1] = b;


		try
		{
			array = ArrayManagment.addItems(newStrings, array);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}


		// Checks to see whether the String has been added.
		assertNotNull(array);


		// Checks to see the length of the String array.
		assertEquals(array.length, 2);



		ArrayManagment.changeArrayItem(c, a, array);



		// Checks to see whether the String has been added.
		assertNotNull(array);

		// Checks to see the length of the String array.
		assertEquals(array.length, 2);

		// Check if the first String is added to the list.
		assertEquals(c, array[0]);

		// Check if the second String is still present in the list.
		assertEquals(b, array[1]);
	}
}
