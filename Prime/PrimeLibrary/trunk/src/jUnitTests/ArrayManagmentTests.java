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
package jUnitTests;


import static org.junit.Assert.fail;

import java.util.ArrayList;

import junit.framework.Assert;
import managment.ArrayManagment;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.hardwareObjects.Motherboard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import exceptions.ObjectNotFoundException;


/**
 * This class contains different {@link JUnit4} tests. The tests validate
 * functions in the {@link ArrayManagment} class.
 * 
 * @author Bahram Malaekeh
 */
public class ArrayManagmentTests
{
	@Before
	public void setUp()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();

		Assert.assertEquals(22, objects.length);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#addItems(java.lang.String[], java.lang.String[])}
	 * .
	 */
	@Test
	public final void testAddItems()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };

		String[] newStringArray = { "Eleven", "Twelve", "Thirteen", "Fourteen" };

		String[] testStringArray = null;
		try
		{
			testStringArray = ArrayManagment.addItems(newStringArray,
					givenStringArray);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}


		Assert.assertEquals(14, testStringArray.length);
		Assert.assertEquals("Twelve", testStringArray[11]);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#removeItems(java.lang.String[], java.lang.String[])}
	 * .
	 */
	@Test
	public final void testRemoveItems()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };

		String[] removeStringArray = { "Three", "Four", "Five", "Six" };

		String[] testStringArray = null;
		try
		{
			testStringArray = ArrayManagment.removeItems(removeStringArray,
					givenStringArray);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		Assert.assertEquals(6, testStringArray.length);
		Assert.assertEquals("Nine", testStringArray[4]);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#removeItem(objects.Object, objects.Object[])}
	 * .
	 */
	@Test
	public final void testRemoveItemObjectObjectArray()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		int removeIndex = 8;

		Object toBeRemoved = objects[removeIndex];

		Assert.assertNotNull(toBeRemoved);


		// Removes the object
		boolean removedIndex = ArrayManagment.removeItem(toBeRemoved, objects);

		Assert.assertTrue(removedIndex);

		Assert.assertNull(objects[removeIndex]);


		// Attempts to remove a removed object
		removedIndex = ArrayManagment.removeItem(toBeRemoved, objects);

		Assert.assertTrue(!removedIndex);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#removeItemReturnIndex(objects.Object, objects.Object[])}
	 * .
	 */
	@Test
	public final void testRemoveItemReturnIndex()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		int removeIndex = 8;

		Object toBeRemoved = objects[removeIndex];

		Assert.assertNotNull(toBeRemoved);


		// Removes the object
		int removedIndex = ArrayManagment.removeItemReturnIndex(toBeRemoved,
				objects);

		Assert.assertEquals(removeIndex, removedIndex);

		Assert.assertNull(objects[removeIndex]);


		// Attempts to remove a removed object
		removedIndex = ArrayManagment.removeItemReturnIndex(toBeRemoved,
				objects);

		Assert.assertEquals(-1, removedIndex);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#removeItem(objects.Object, java.util.ArrayList)}
	 * .
	 */
	@Test
	public final void testRemoveItemObjectArrayListOfObject()
	{
		ArrayList<Object> list = ObjectCreationClass
				.ObjectCreationClassArrayList();


		int removeIndex = 8;

		Object toBeRemoved = list.get(removeIndex);

		Assert.assertNotNull(toBeRemoved);




		// Removes the object
		boolean removedIndex = ArrayManagment.removeItem(toBeRemoved, list);

		Assert.assertTrue(removedIndex);

		// Attempts to remove a removed object
		removedIndex = ArrayManagment.removeItem(toBeRemoved, list);

		Assert.assertTrue(!removedIndex);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#changeArrayItem(java.lang.String, java.lang.String, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testChangeArrayItem()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };

		String oldItem = givenStringArray[3]; // "Four"

		String newItem = "Fifty";


		givenStringArray = ArrayManagment.changeArrayItem(newItem, oldItem,
				givenStringArray);


		Assert.assertEquals(newItem, givenStringArray[3]);

	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#getSpesificComponents(java.lang.Class, objects.Object[], int)}
	 * .
	 */
	@Test
	public final void testGetSpesificComponents()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		try
		{
			ArrayManagment.getSpesificComponents(Motherboard.class, objects,
					objects.length);
			fail("ArrayManagment.getSpesificComponents  found a motherboard in an array of Devices(not hardware)");
		}
		catch ( ObjectNotFoundException e )
		{
			// Should be thrown
		}



		try
		{
			ArrayManagment.getSpesificComponents(Motherboard.class,
					objects[0].getComponents(),
					objects[0].getComponents().length);
		}
		catch ( ObjectNotFoundException e )
		{
			fail("ArrayManagment.getSpesificComponents  did not find a motherboard in an array of hardware.");
		}
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#getSpesificComponent(java.lang.Class, java.util.ArrayList)}
	 * .
	 */
	@Test
	public final void testGetSpesificComponent()
	{
		ArrayList<Object> list = ObjectCreationClass
				.ObjectCreationClassArrayList();


		try
		{
			ArrayManagment.getSpesificComponent(Motherboard.class, list);
			fail("ArrayManagment.getSpesificComponents  found a motherboard in an array of Devices(not hardware)");
		}
		catch ( ObjectNotFoundException e )
		{
			// Should be thrown
		}

		Object[] compArray = list.get(0).getComponents();

		ArrayList<Object> comp = new ArrayList<Object>();

		for ( int i = 0; i < compArray.length; i++ )
		{
			comp.add(compArray[i]);
		}


		try
		{
			ArrayManagment.getSpesificComponent(Motherboard.class, comp);
		}
		catch ( ObjectNotFoundException e )
		{
			fail("ArrayManagment.getSpesificComponents  did not find a motherboard in an array of hardware.");
		}
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#getSpesificObjects(java.lang.Class, objects.Object[], int)}
	 * .
	 */
	@Test
	public final void testGetSpesificObjects()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		try
		{
			ArrayManagment.getSpesificObjects(Desktop.class, objects);
		}
		catch ( ObjectNotFoundException e )
		{
			e.printStackTrace();
			fail("ArrayManagment.getSpesificComponents  found a motherboard in an array of Devices(not hardware)");
		}



		try
		{
			ArrayManagment.getSpesificObjects(Motherboard.class, objects);
			fail("ArrayManagment.getSpesificComponents  found a motherboard in an array of Devices(not hardware)");
		}
		catch ( ObjectNotFoundException e )
		{
			// Should be thrown
		}
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#arrayContains(objects.Object[], objects.Object)}
	 * .
	 */
	@Test
	public final void testArrayContainsObjectArrayObject()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		int removeIndex = 8;

		Object searchedFor = objects[removeIndex];

		Assert.assertNotNull(searchedFor);


		boolean found = ArrayManagment.arrayContains(objects, searchedFor);

		Assert.assertTrue(found);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#arrayContainsReturnsIndex(objects.Object[], objects.Object)}
	 * .
	 */
	@Test
	public final void testArrayContainsReturnsIndex()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();


		int removeIndex = 8;

		Object searchedFor = objects[removeIndex];

		Assert.assertNotNull(searchedFor);


		int found = ArrayManagment.arrayContainsReturnsIndex(objects,
				searchedFor);

		Assert.assertEquals(removeIndex, found);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#arrayContains(java.lang.String[], java.lang.String[])}
	 * .
	 */
	@Test
	public final void testArrayContainsStringArrayStringArray()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };

		String[] searchFor = { givenStringArray[3], givenStringArray[7],
				givenStringArray[4], "Fifty" };


		boolean[] found = ArrayManagment.arrayContains(givenStringArray,
				searchFor);

		Assert.assertEquals(4, found.length);
		Assert.assertTrue(found[0]);
		Assert.assertTrue(found[1]);
		Assert.assertTrue(found[2]);
		Assert.assertTrue(!found[3]);


		// Attempting NULL pointers
		String[] emptyArray = {};

		found = ArrayManagment.arrayContains(emptyArray, emptyArray);
		Assert.assertEquals(0, found.length);


		// Attempting NULL pointers
		String[] nullarray = null;

		found = ArrayManagment.arrayContains(nullarray, nullarray);
		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#arrayContains(java.lang.String[], java.lang.String)}
	 * .
	 */
	@Test
	public final void testArrayContainsStringArrayString()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };

		String validSearch = givenStringArray[5];
		String notValidSearch = "Fifty";


		boolean found = ArrayManagment.arrayContains(givenStringArray,
				validSearch);
		Assert.assertTrue(found);


		found = ArrayManagment.arrayContains(givenStringArray, notValidSearch);
		Assert.assertTrue(!found);


		String emptyString = null;
		found = ArrayManagment.arrayContains(givenStringArray, emptyString);
		Assert.assertTrue(!found);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#add5ArraySpaces(java.lang.Object[][])}.
	 */
	@Test
	public final void testAdd5ArraySpacesObjectArrayArray()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();
		Assert.assertNotNull(objects);


		java.lang.Object[][] obj = new java.lang.Object[3][2];

		obj[0][0] = objects[0];
		obj[0][1] = objects[1];
		obj[1][0] = objects[2];
		obj[1][1] = objects[3];
		obj[2][0] = objects[4];
		obj[2][1] = objects[5];


		java.lang.Object[][] returnedMultiArray = ArrayManagment
				.add5ArraySpaces(obj);

		Assert.assertEquals(8, returnedMultiArray.length);
		Assert.assertEquals(2, returnedMultiArray[1].length);



		java.lang.Object[][] emptyMultiArray = null;
		java.lang.Object[][] newEmptyMultiArray = ArrayManagment
				.add5ArraySpaces(emptyMultiArray);

		Assert.assertEquals(5, newEmptyMultiArray.length);
		Assert.assertEquals(4, newEmptyMultiArray[1].length);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#add5ArraySpaces(java.lang.String[][])}.
	 */
	@Test
	public final void testAdd5ArraySpacesStringArrayArray()
	{
		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };


		String[][] obj = new String[3][2];

		obj[0][0] = givenStringArray[0];
		obj[0][1] = givenStringArray[1];
		obj[1][0] = givenStringArray[2];
		obj[1][1] = givenStringArray[3];
		obj[2][0] = givenStringArray[4];
		obj[2][1] = givenStringArray[5];


		String[][] returnedMultiArray = ArrayManagment.add5ArraySpaces(obj);

		Assert.assertEquals(8, returnedMultiArray.length);
		Assert.assertEquals(2, returnedMultiArray[1].length);



		String[][] emptyMultiArray = null;
		String[][] newEmptyMultiArray = ArrayManagment
				.add5ArraySpaces(emptyMultiArray);

		Assert.assertEquals(5, newEmptyMultiArray.length);
		Assert.assertEquals(4, newEmptyMultiArray[1].length);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#removeEmptyIndexes(java.lang.String[][])}
	 * .
	 */
	@Test
	public final void testRemoveEmptyIndexes()
	{

		String[] givenStringArray = { "One", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten" };


		String[][] obj = new String[3][2];

		obj[0][0] = givenStringArray[0];
		obj[0][1] = null;
		obj[1][0] = null;
		obj[1][1] = givenStringArray[3];
		obj[2][0] = givenStringArray[4];
		obj[2][1] = givenStringArray[5];

		String[][] returnedMultiArray = ArrayManagment.removeEmptyIndexes(obj);

		Assert.assertEquals(2, returnedMultiArray.length);
	}

	/**
	 * Test method for
	 * {@link managment.ArrayManagment#copyArray(objects.Object[])}.
	 */
	@Test
	public final void testCopyArray()
	{
		Object[] objects = ObjectCreationClass.ObjectCreationClass();
		Assert.assertNotNull(objects);


		Object[] newArray = ArrayManagment.shallowCopyArray(objects);
		Assert.assertNotNull(newArray);


		Object[] newEmptyArray = ArrayManagment.shallowCopyArray(new Object[0]);
		Assert.assertNotNull(newEmptyArray);
		Assert.assertEquals(0, newEmptyArray.length);
	}
}
