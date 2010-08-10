/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.messageArea.NetworkTab;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain;
import managment.ArrayManagment;
import objects.Object;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import widgets.WorkareaCanvas;


/**
 * This is the class where the network aspects of a given {@link WorkareaCanvas} is processed and messages are created for the
 * user.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkProcessing
{
	/**
	 * This method processes an entire network, ie a WorkareaCanvas, for
	 * critical errors, warnings and notices. It then returns a multidimentional
	 * String array that contains the messages the user will be shown about the
	 * network.
	 * 
	 * @param curData
	 *            The current multidimentional String array with possible
	 *            previous data.
	 * @param canvas
	 *            The network that is to be examined.
	 * @param CheckCritical
	 *            A boolean saying if the network should be checked for critical
	 *            errors.
	 * @param CheckWarnings
	 *            A boolean saying if the network should be checked for
	 *            warnings.
	 * @param CheckNotices
	 *            A boolean saying if the network should be checked for notices.
	 * @return Returns a new multidimentional String array with possible
	 *         messages for the user about the given network.
	 */
	public static String[][] processNetwork(String[][] curData,
			WorkareaCanvas canvas, boolean CheckCritical,
			boolean CheckWarnings, boolean CheckNotices)
	{
		String[][] data = curData;


		if ( data == null )
		{
			data = new String[5][4];
		}
		if ( CheckCritical )
		{
			data = getCriticalErrors(data, canvas);
		}


		if ( CheckWarnings )
		{
			data = getWarnings(data, canvas);
		}


		if ( CheckNotices )
		{
			data = getNotices(data, canvas);
		}

		return data;
	}



	/**
	 * Examines the given network for possible critical errors and adds messages
	 * to the given multidimentional String array which will then be used to
	 * populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The network that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getCriticalErrors(String[][] data,
			WorkareaCanvas canvas)
	{
		// Gets all the objects on the given canvas
		Object[] objects = canvas.getObjectsOnTheScene();


		// Checks whether or not the Internet object is directly connected to a
		// another Internet object
		for ( int i = 0; i < objects.length; i++ )
		{
			if ( objects[i] instanceof Internet )
			{
				// Gets all the connected objects
				Object[] connectedObject = objects[i].getConnectedDevices();

				if ( connectedObject != null )
				{
					// Goes through all the connected objects
					for ( int j = 0; j < connectedObject.length; j++ )
					{
						// If the connected object is a Internet object
						if ( connectedObject[j] instanceof Internet )
						{
							String[] info = { objects[i].getObjectName(),
									PrimeMain.texts.getString("netToNetName"),
									PrimeMain.texts.getString("netToNetMsg"),
									PrimeMain.texts.getString("networkError") };
							data = addMessage(data, info);
						}
					}
				}
			}
		}
		return data;
	}




	/**
	 * Examines the given network for possible critical errors and adds messages
	 * to the given multidimentional String array which will then be used to
	 * populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The network that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getWarnings(String[][] data, WorkareaCanvas canvas)
	{
		// Gets all the objects on the given canvas
		Object[] objects = canvas.getObjectsOnTheScene();


		// Check to see if the network contains a Internet Object
		if ( !(containsObjectOfClass(objects, Internet.class)) )
		{
			String[] info = { canvas.getCanvasName(),
					PrimeMain.texts.getString("internet"),
					PrimeMain.texts.getString("noNetMsg"),
					PrimeMain.texts.getString("networkWarning") };
			data = addMessage(data, info);
		}




		// Check to see if the network contains a router/hub/switch/wireless
		// Router Object
		if ( !(containsObjectOfClass(objects, Router.class)) )
		{
			// HUB
			if ( !(containsObjectOfClass(objects, Hub.class)) )
			{
				// Switch
				if ( !(containsObjectOfClass(objects, Switch.class)) )
				{
					// WirelessRouter
					if ( !(containsObjectOfClass(objects, WirelessRouter.class)) )
					{
						String[] info = {
								canvas.getCanvasName(),
								PrimeMain.texts.getString("router"),
								PrimeMain.texts
										.getString("noInfrastructureMsg"),
								PrimeMain.texts.getString("networkWarning") };
						data = addMessage(data, info);
					}
				}
			}
		}



		// Goes through all objects and checks whether or not any object is not
		// connected to anything
		for ( int i = 0; i < objects.length; i++ )
		{
			if ( (objects[i].getConnectedDevices() == null)
					|| (objects[i].getConnectedDevices().length < 1) )
			{
				String[] info = { objects[i].getObjectName(),
						PrimeMain.texts.getString("notConnectedName"),
						PrimeMain.texts.getString("notConnectedMsg"),
						PrimeMain.texts.getString("networkWarning") };
				data = addMessage(data, info);
			}
		}






		return data;
	}



	/**
	 * Examines the given network for possible critical errors and adds messages
	 * to the given multidimentional String array which will then be used to
	 * populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The network that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getNotices(String[][] data, WorkareaCanvas canvas)
	{
		// TODO - NetworkProcessing - Notices
		return data;
	}



	/**
	 * Adds the given String array to the given multidimentional String array
	 * that contains all the messages the user will be shown.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param info
	 *            The new information that will be added to the data array with
	 *            messages.
	 * @return The data container with the new message for the user in addition
	 *         to the possible old messages.
	 */
	private static String[][] addMessage(String[][] data, String[] info)
	{
		for ( int i = 0; i < data.length; i++ )
		{
			if ( data[i][0] == null )
			{
				data[i][0] = info[0].toString();
				data[i][1] = info[1].toString();
				data[i][2] = info[2].toString();
				data[i][3] = info[3].toString();

				return data;
			}
		}


		/**
		 * If the method gets to this part, it means that there was not enough
		 * space in the array to add another data field. So it will be expanded
		 * with 5 indexes and this function will be run again.
		 */
		data = ArrayManagment.add5ArraySpaces(data);

		return addMessage(data, info);
	}



	/**
	 * Checks if the given objects contains an object with the given class.
	 */
	private static boolean containsObjectOfClass(Object[] objects,
			Class<?> Class)
	{
		// This test does nothing else then see if the function called throws an
		// exception that means there was no object found with the given class.
		try
		{
			if ( ArrayManagment.getSpesificObjects(Class, objects) == null )
			{
				return false;
			}
		}
		catch ( ObjectNotFoundException e )
		{
			return false;
		}

		return true;
	}
}
