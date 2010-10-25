package jUnitTests;


import junit.framework.TestCase;
import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.peripheralObjects.ExternalHDD;
import objects.serverObjects.HTTPServer;

import org.junit.Test;

import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


public class ComponentsManagmentTest extends TestCase
{
	// Array of components. Is null.
	Object[] components = new Object[0];

	// Array of components that are to be added to the array above.
	Object[] newComponents = new Object[3];

	// A desktop object thats is to be added.
	Desktop newComponent1 = new Desktop("newComponent1", "Desc", new Object[1]);

	// A server object thats is to be added.
	HTTPServer newComponent2 = new HTTPServer("newComponent2", "Desc", "1", "2", "3");

	// A external hardware object thats is to be added.
	ExternalHDD newComponent3 = new ExternalHDD("newComponent2", "Desc", "1", 2, new String[1]);



	/**
	 * Test to see whether or not several objects can be added to an object array or not. Also it checks if the object
	 * is the same before and after it is added to see if there are any changes made to the object while adding it to
	 * the array.
	 */
	@Test
	public void testAddComponents()
	{
		// Adds both of the newly created objects to an components array
		newComponents[0] = newComponent1;
		newComponents[1] = newComponent2;


		try
		{
			components = ComponentsManagment.addComponents(newComponents, components, components.length);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}


		// See if the components are added to the components list.
		assertNotNull(components);


		// Check if the first component is added to the list.
		assertEquals(newComponent1, components[0]);


		// Check if the second component is added to the list.
		assertEquals(newComponent2, components[1]);


		// Reset
		components = null;
		newComponents = null;
	}

	/**
	 * Test to see whether or not an object can be removed from an array of components.
	 */
	@Test
	public void testRemoveComponents()
	{
		// Adds both of the newly created objects to an components array
		newComponents[0] = newComponent1;
		newComponents[1] = newComponent2;

		// Adds the newly created objects to the components array.
		try
		{
			components = ComponentsManagment.addComponents(newComponents, components, components.length);
		}
		catch ( Exception e1 )
		{
			e1.printStackTrace();
		}


		// Checks to see whether the objects have been added.
		assertNotNull(components);


		// Checks to see the length of the components array.
		assertEquals(components.length, 2);



		// Creates the objects to be removed array.
		Object[] toBeRemoved = new Object[1];

		toBeRemoved[0] = newComponent2;


		try
		{
			components = ComponentsManagment.removeComponents(toBeRemoved, components, components.length);
		}
		catch ( ObjectNotFoundInArrayException e )
		{
			e.printStackTrace();
		}



		// Check if the first component is still present in the list.
		assertEquals(newComponent1, components[0]);

		// Checks to see length of the components array.
		assertEquals(components.length, 1);


		// Reset
		components = null;
		newComponents = null;
	}


	/**
	 * Test the function that changes out one component with another.
	 */
	@Test
	public void testChangeComponent()
	{
		// Adds both of the newly created objects to an components array
		newComponents[0] = newComponent1;
		newComponents[1] = newComponent2;


		// Adds the newly created objects to the components array.
		try
		{
			components = ComponentsManagment.addComponents(newComponents, components, components.length);
		}
		catch ( Exception e1 )
		{
			e1.printStackTrace();
		}


		// Checks to see whether the objects have been added.
		assertNotNull(components);

		// Checks to see the length of the components array.
		assertEquals(components.length, 2);

		// Check if the first component is added to the list.
		assertEquals(newComponent1, components[0]);

		// Check if the second component is added to the list.
		assertEquals(newComponent2, components[1]);



		components = ComponentsManagment.changeComponent(newComponent3, newComponent1, components, components.length);



		// Checks to see whether the objects have been added.
		assertNotNull(components);

		// Checks to see the length of the components array.
		assertEquals(components.length, 2);

		// Check if the first component is added to the list.
		assertEquals(newComponent3, components[0]);

		// Check if the second component is added to the list.
		assertEquals(newComponent2, components[1]);




		// Reset
		components = null;
		newComponents = null;
	}

	@Test
	public void testGetSpesificComponents()
	{
		// Adds both of the newly created objects to an components array
		newComponents[0] = newComponent1;
		newComponents[1] = newComponent2;
		newComponents[2] = newComponent3;


		// Adds the newly created objects to the components array.
		try
		{
			components = ComponentsManagment.addComponents(newComponents, components, components.length);
		}
		catch ( Exception e1 )
		{
			e1.printStackTrace();
		}


		// Checks to see whether the objects have been added.
		assertNotNull(components);

		// Checks to see the length of the components array.
		assertEquals(components.length, 3);


		assertEquals(HTTPServer.class, newComponent2.getClass());


		Object[] found = null;

		try
		{
			found = ArrayManagment.getSpesificComponents(Desktop.class, components, components.length);
		}
		catch ( ObjectNotFoundException e )
		{
			System.out.println(e.getMessage());
		}


		// Checks to see whether if any objects have been found.
		assertNotNull(found);

		// Checks to see the length of the found array
		assertEquals(1, found.length);


		// Reset
		components = null;
		newComponents = null;
	}
}
