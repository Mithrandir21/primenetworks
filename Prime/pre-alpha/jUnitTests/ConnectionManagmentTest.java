package jUnitTests;


import javax.swing.JOptionPane;

import junit.framework.TestCase;
import managment.ConnectionManagment;
import objects.Object;

import org.junit.Test;

import peripheral.ExternalHDD;
import servers.HTTPServer;
import clients.Desktop;
import connections.Connection;
import connections.InternalConnection;
import connections.NetworkConnection;
import exceptions.ConnectionDoesExist;
import exceptions.ConnectionDoesNotExist;
import exceptions.ConnectionsIsNotPossible;


public class ConnectionManagmentTest extends TestCase
{
	// Array of connections. Is null.
	Connection[] connections = new Connection[3];

	// A desktop object thats is to be added.
	Desktop newComponent1 = new Desktop("newComponent1", "Desc", new Object[1]);

	// A server object thats is to be added.
	HTTPServer newComponent2 = new HTTPServer("newComponent2", "Desc", "1",
			"2", "3");

	// A external hardware object thats is to be added.
	ExternalHDD newComponent3 = new ExternalHDD("newComponent2", "Desc", "1",
			2, new String[1]);



	@Test
	public void testMakeConnection()
	{

		// Tries to create a connection between a desktop and HTTPserver.
		Connection con1 = null;
		try
		{
			con1 = ConnectionManagment.makeConnection(connections, "ConName",
					"ConDesc", newComponent1, newComponent2, "RJ-45",
					NetworkConnection.class);
		}
		catch ( ConnectionDoesExist e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}
		catch ( ConnectionsIsNotPossible e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}

		// Checks to see if there really exists a connection.
		assertNotNull(con1);

		// Checks to see if the connection is a network connection.
		assertEquals(NetworkConnection.class, con1.getClass());

		// Adds the connection to the connections array.
		connections[0] = con1;



		// Tries to create a internal AGP connection between a desktop and
		// a HTTPserver. Should return null.
		Connection con2 = null;
		try
		{
			con2 = ConnectionManagment.makeConnection(connections, "ConName",
					"ConDesc", newComponent1, newComponent2, "AGP",
					InternalConnection.class);
		}
		catch ( ConnectionDoesExist e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}
		catch ( ConnectionsIsNotPossible e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}

		// Checks to see if there was a connection maid. Should not be a
		// connection maid.
		assertNull(con2);

		// Adds the connection to the connections array.
		connections[1] = con2;


		/*
		 * Tries to create a connection between a desktop and HTTPserver that
		 * already have a connection between them with a RJ-45.
		 */
		Connection con3 = null;
		try
		{
			con3 = ConnectionManagment.makeConnection(connections, "ConName",
					"ConDesc", newComponent1, newComponent2, "RJ-45",
					NetworkConnection.class);
		}
		catch ( ConnectionDoesExist e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}
		catch ( ConnectionsIsNotPossible e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}


		assertNull(con3);



		// Reset
		connections = null;
	}

	@Test
	public void testBreakConnection()
	{
		// Tries to create a connection between adesktop and HTTPserver.
		Connection con = null;
		try
		{
			con = ConnectionManagment.makeConnection(connections, "ConName",
					"ConDesc", newComponent1, newComponent2, "RJ-45",
					NetworkConnection.class);
		}
		catch ( ConnectionDoesExist e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}
		catch ( ConnectionsIsNotPossible e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}

		// Checks to see if there really exists a connection.
		assertNotNull(con);

		// Checks to see if the connection is a network connection.
		assertEquals(NetworkConnection.class, con.getClass());


		// Adds connection to the connections array.
		connections[0] = con;


		// Tries to break a connection between two devices.
		try
		{
			connections = ConnectionManagment.breakConnection(connections,
					newComponent1, newComponent2);
		}
		catch ( ConnectionDoesNotExist e )
		{
			System.out.println(e.getMessage());
		}


		/*
		 * Checks to see whether or not the RJ-45 connection between the desktop
		 * and the HTTPserver has been removed from the connections array.
		 */
		assertEquals(0, connections.length);


		// Reset
		connections = null;
	}

	@Test
	public void testChangeConnection()
	{
		// Tries to create a connection between a desktop and HTTPserver.
		Connection con1 = null;
		try
		{
			con1 = ConnectionManagment.makeConnection(connections, "ConName",
					"ConDesc", newComponent1, newComponent2, "RJ-45",
					NetworkConnection.class);
		}
		catch ( ConnectionDoesExist e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - There already exists a connection between these two objects.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}
		catch ( ConnectionsIsNotPossible e )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"JUnitTest - A connection between these two objects is not possible.",
							"alert", JOptionPane.ERROR_MESSAGE);
		}

		// Checks to see if there really exists a connection.
		assertNotNull(con1);

		// Checks to see if the connection is a network connection.
		assertEquals(NetworkConnection.class, con1.getClass());

		// Adds connection to the connections array.
		connections[0] = con1;



		try
		{
			ConnectionManagment.changeConnection(connections, newComponent1,
					newComponent2, newComponent3);
		}
		catch ( ConnectionDoesNotExist e )
		{
			e.printStackTrace();
		}
		catch ( ConnectionDoesExist e )
		{
			e.printStackTrace();
		}


		/*
		 * Checks to see whether the second object in the connection is the same
		 * object as in the original connection.
		 */
		assertNotSame(connections[0].getObject2(), newComponent2);

		/*
		 * Checks to see whether the second object in the connection is the
		 * object that was given to the change method.
		 */
		assertEquals(connections[0].getObject2(), newComponent3);



		// Reset
		connections = null;
	}
}
