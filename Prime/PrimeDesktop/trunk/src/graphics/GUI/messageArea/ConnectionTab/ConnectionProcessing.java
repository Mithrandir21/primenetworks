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
package graphics.GUI.messageArea.ConnectionTab;


import managment.ArrayManagment;
import objects.Object;
import widgets.WorkareaCanvas;
import exceptions.ObjectNotFoundException;


/**
 * This is the class where the connection aspects of a given {@link Object} and
 * {@link WorkareaCanvas} is processed and messages
 * are created for the user.
 * 
 * @author Bahram Malaekeh
 */
public class ConnectionProcessing
{
	/**
	 * This method processes a connection for information critical errors,
	 * warnings and notices. It then returns a
	 * multidimentional String array that contains the messages the user will be
	 * shown about the connection.
	 * 
	 * @param curData
	 *            The current multidimentional String array with possible
	 *            previous data.
	 * @param obj
	 *            The connection that is to be examined.
	 * @param CheckCritical
	 *            A boolean saying if the connection should be checked for
	 *            critical errors.
	 * @param CheckWarnings
	 *            A boolean saying if the connection should be checked for
	 *            warnings.
	 * @param CheckNotices
	 *            A boolean saying if the connection should be checked for
	 *            notices.
	 * @return Returns a new multidimentional String array with possible
	 *         messages for the user about the given
	 *         connection.
	 */
	public static String[][] processConnections(String[][] curData, Object obj,
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
			data = getCriticalErrors(data, obj);
		}


		if ( CheckWarnings )
		{
			data = getWarnings(data, obj);
		}


		if ( CheckNotices )
		{
			data = getNotices(data, obj);
		}

		return data;
	}



	/**
	 * Examines the given object for possible critical errors and adds messages
	 * to the given multidimentional String
	 * array which will then be used to populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The object that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getCriticalErrors(String[][] data, Object obj)
	{
		// FIXME - ConnectionProcessing - Errors
		return data;
	}




	/**
	 * Examines the given object for possible warnings and adds messages to the
	 * given multidimentional String array
	 * which will then be used to populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The object that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getWarnings(String[][] data, Object obj)
	{
		// FIXME - ConnectionProcessing - Warnings
		return data;
	}



	/**
	 * Examines the given object for possible notices and adds messages to the
	 * given multidimentional String array which
	 * will then be used to populate a JTable.
	 * 
	 * @param data
	 *            The data container with possible previous messages for the
	 *            user.
	 * @param obj
	 *            The object that is to be examined.
	 * @return The data container with possible new messages for the user in
	 *         addition to the old messages.
	 */
	private static String[][] getNotices(String[][] data, Object obj)
	{
		// FIXME - ConnectionProcessing - Notices
		return data;
	}



	/**
	 * Adds the given String array to the given multidimentional String array
	 * that contains all the messages the user
	 * will be shown.
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
				data[i][0] = info[0];
				data[i][1] = info[1];
				data[i][2] = info[2];
				data[i][3] = info[3];

				return data;
			}
		}


		/**
		 * If the method gets to this part, it means that there was not enough
		 * space in the array to add another data
		 * field. So it will be expanded with 5 indexes and this function will
		 * be run again.
		 */
		data = ArrayManagment.add5ArraySpaces(data);

		return addMessage(data, info);
	}



	/**
	 * Checks if the given object lacks a hardware object in its get components
	 * array of the given class.
	 */
	private static boolean containsComponentOfClass(Object obj, Class<?> Class)
	{
		// This test does nothing else then see if the function called throws an
		// exception that means there was no object found with the given class.
		try
		{
			ArrayManagment.getSpesificComponents(Class, obj.getComponents(),
					obj.getComponents().length);
		}
		catch ( ObjectNotFoundException e )
		{
			return false;
		}

		return true;
	}
}
