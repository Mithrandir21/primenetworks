package jUnitTests;


import junit.framework.TestCase;
import logistical.cleanup;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.peripheralObjects.ExternalHDD;
import objects.serverObjects.HTTPServer;

import org.junit.Test;



public class cleanupTest extends TestCase
{

	@Test
	public void testCleanObjectArrayObjectArrayInt()
	{
		// Array of components. Is null.
		Object[] components = new Object[5];

		// A desktop object thats is to be added.
		Desktop newComponent1 = new Desktop("newComponent1", "Desc", new Object[1]);

		// A server object thats is to be added.
		HTTPServer newComponent2 = new HTTPServer("newComponent2", "Desc", "1", "2", "3");

		// A external hardware object thats is to be added.
		ExternalHDD newComponent3 = new ExternalHDD("newComponent2", "Desc", "1", 2, new String[1]);

		// Adds a null pointer for clean up to handle.
		components[0] = newComponent1;
		components[1] = null;
		components[2] = newComponent3;
		components[3] = null;
		components[4] = newComponent2;


		// Checks the null pointers.
		assertNull(components[1]);
		assertNull(components[3]);

		// Checks the length of the array.
		assertEquals(5, components.length);


		// Moves the objects from
		components = cleanup.cleanObjectArray(components);

		// Checks the original null pointers.
		assertNotNull(components[1]);
		assertNotNull(components[2]);

		// Checks to see if the components match.
		assertEquals(newComponent1, components[0]);

		assertEquals(newComponent3, components[1]);

		assertEquals(newComponent2, components[2]);


		// Checks the new length of the array.
		assertEquals(3, components.length);

	}

	@Test
	public void testCleanObjectArrayStringArrayInt()
	{
		String[] array = new String[5];

		String a = "a";

		String b = "b";

		String c = "c";


		array[0] = a;
		array[1] = null;
		array[2] = c;
		array[3] = null;
		array[4] = b;

		// Checks the arrays null pointers.
		assertNull(array[1]);
		assertNull(array[3]);


		// Should clean up the array and give an array with no null pointers.
		array = cleanup.cleanObjectArray(array);

		// Checks the original null pointers.
		assertNotNull(array[1]);
		assertNotNull(array[2]);

		// Checks if the strings match.
		assertEquals(a, array[0]);
		assertEquals(c, array[1]);
		assertEquals(b, array[2]);


		assertEquals(3, array.length);

	}
}
