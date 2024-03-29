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
package graphics.GUI.messageArea.HardwareTab;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain;
import managment.ArrayManagment;
import objects.Object;
import objects.clientObjects.ThinClient;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;


/**
 * This is the class where the hardware aspects of a given {@link Object} is
 * processed and messages are created for the user.
 * 
 * @author Bahram Malaekeh
 */
public class HardwareProcessing
{
	/**
	 * This method processes an Object for critical errors, warnings and
	 * notices. It then returns a multidimentional String array that contains
	 * the messages the user will be shown about the object.
	 * 
	 * @param curData
	 *            The current multidimentional String array with possible
	 *            previous data.
	 * @param obj
	 *            The Object that is to be examined.
	 * @param CheckCritical
	 *            A boolean saying if the Object should be checked for critical
	 *            errors.
	 * @param CheckWarnings
	 *            A boolean saying if the Object should be checked for warnings.
	 * @param CheckNotices
	 *            A boolean saying if the Object should be checked for notices.
	 * @return Returns a new multidimentional String array with possible
	 *         messages for the user about the given Object.
	 */
	public static String[][] processHardware(String[][] curData, Object obj,
			boolean CheckCritical, boolean CheckWarnings, boolean CheckNotices)
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
	 * to the given multidimentional String array which will then be used to
	 * populate a JTable.
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
		// Motherboard
		if ( !(containsComponentOfClass(obj, Motherboard.class)) )
		{
			String[] info = { obj.getObjectName(),
					PrimeMain.texts.getString("motherboard"),
					PrimeMain.texts.getString("noMBmsg"),
					PrimeMain.texts.getString("hardwareError") };
			data = addMessage(data, info);
		}


		// CPU
		if ( !(containsComponentOfClass(obj, CPU.class)) )
		{
			String[] info = { obj.getObjectName(),
					PrimeMain.texts.getString("cpu"),
					PrimeMain.texts.getString("noCPUmsg"),
					PrimeMain.texts.getString("hardwareError") };
			data = addMessage(data, info);
		}


		// RAM
		if ( !(containsComponentOfClass(obj, Ram.class)) )
		{
			String[] info = { obj.getObjectName(),
					PrimeMain.texts.getString("ram"),
					PrimeMain.texts.getString("noRAMmsg"),
					PrimeMain.texts.getString("hardwareError") };
			data = addMessage(data, info);
		}


		if ( !(obj instanceof ThinClient) )
		{
			// HDD
			if ( !(containsComponentOfClass(obj, HDD.class)) )
			{
				String[] info = { obj.getObjectName(),
						PrimeMain.texts.getString("hdd"),
						PrimeMain.texts.getString("noHDDmsg"),
						PrimeMain.texts.getString("hardwareError") };
				data = addMessage(data, info);
			}
		}


		return data;
	}




	/**
	 * Examines the given object for possible warnings and adds messages to the
	 * given multidimentional String array which will then be used to populate a
	 * JTable.
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
		return data;
	}



	/**
	 * Examines the given object for possible notices and adds messages to the
	 * given multidimentional String array which will then be used to populate a
	 * JTable.
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
		// CPU
		try
		{
			// Gets all the CPUs of the object, be it one or more
			Object[] cpus = ArrayManagment.getSpesificComponents(CPU.class, obj
					.getComponents(), obj.getComponents().length);

			// If there were any objects found
			if ( cpus.length != 0 )
			{
				// Goes through all the array
				for ( int i = 0; i < cpus.length; i++ )
				{
					// Converts the current array object to a CPU
					CPU temp = (CPU) cpus[i];

					if ( temp.getSpeed() == 0 )
					{
						String[] info = { obj.getObjectName(),
								PrimeMain.texts.getString("cpu"),
								PrimeMain.texts.getString("noCPUspeedSetMsg"),
								PrimeMain.texts.getString("hardwareNotice") };
						data = addMessage(data, info);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




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
				data[i][0] = info[0];
				data[i][1] = info[1];
				data[i][2] = info[2];
				data[i][3] = info[3];

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
