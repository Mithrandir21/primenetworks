/**
 * 
 */
package graphics.GUI.messageArea.SoftwareTab;


import hardware.Motherboard;
import managment.ArrayManagment;
import objects.Object;
import software.OperatingSystem;
import exceptions.ObjectNotFoundException;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SoftwareProcessing
{
	/**
	 * This method processes an Object for critical errors, warnings and notices
	 * in regards to the software on it. It then returns a multidimentional String 
	 * array that contains the messages the user will be shown about the network.  
	 * 
	 * @param curData
	 * 			The current multidimentional String array with possible previous
	 * 			data.
	 * @param obj
	 * 			The Object that is to be examined.
	 * @param CheckCritical
	 * 			A boolean saying if the Object should be checked for
	 * 			critical errors.
	 * @param CheckWarnings
	 * 			A boolean saying if the Object should be checked for
	 * 			warnings.
	 * @param CheckNotices
	 * 			A boolean saying if the Object should be checked for
	 * 			notices.
	 * @return
	 * 			Returns a new multidimentional String array with possible
	 * 			messages for the user about the given Object.
	 */
	public static String[][] processSoftware(String[][] curData, Object obj,
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
	 * Examines the given object for possible critical errors and 
	 * adds messages to the given multidimentional String array 
	 * which will then be used to populate a JTable.
	 * 
	 * @param data
	 * 			The data container with possible previous messages
	 * 			for the user. 
	 * @param obj
	 * 			The object that is to be examined.
	 * @return
	 * 			The data container with possible new messages
	 * 			for the user in addition to the old messages.
	 */
	private static String[][] getCriticalErrors(String[][] data, Object obj)
	{
		// Operaing System
		if ( !(containsSoftwareOfClass(obj, OperatingSystem.class)) )
		{
			String[] info = { obj.getObjectName(), "Operaring System",
					"This object does not have an Operating System.",
					"Software Error" };
			data = addError(data, info);
		}
		
		
		return data;
	}




	/**
	 * Examines the given object for possible warnings and 
	 * adds messages to the given multidimentional String array 
	 * which will then be used to populate a JTable.
	 * 
	 * @param data
	 * 			The data container with possible previous messages
	 * 			for the user. 
	 * @param obj
	 * 			The object that is to be examined.
	 * @return
	 * 			The data container with possible new messages
	 * 			for the user in addition to the old messages.
	 */
	private static String[][] getWarnings(String[][] data, Object obj)
	{
		// TODO - SoftwareProcessing - Warnings
		return data;
	}



	/**
	 * Examines the given object for possible notices and 
	 * adds messages to the given multidimentional String array 
	 * which will then be used to populate a JTable.
	 * 
	 * @param data
	 * 			The data container with possible previous messages
	 * 			for the user. 
	 * @param obj
	 * 			The object that is to be examined.
	 * @return
	 * 			The data container with possible new messages
	 * 			for the user in addition to the old messages.
	 */
	private static String[][] getNotices(String[][] data, Object obj)
	{
		// TODO - SoftwareProcessing - Notices
		return data;
	}



	/**
	 * Adds the given String array to the given multidimentional String
	 * array that contains all the messages the user will be shown.
	 * 
	 * @param data
	 * 			The data container with possible previous messages
	 * 			for the user. 
	 * @param info
	 * 			The new information that will be added to the data
	 * 			array with messages.
	 * @return
	 * 			The data container with the new message
	 * 			for the user in addition to the possible old messages.
	 */
	private static String[][] addError(String[][] data, String[] info)
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

		return addError(data, info);
	}


	/**
	 * Checks if the given object lacks a software object in its get software
	 * array of the given class.
	 */
	public static boolean containsSoftwareOfClass(Object obj, Class<?> Class)
	{
		// This test does nothing else then see if the function called throws an
		// exception that means there was
		// no object found with the given class.
		try
		{
			ArrayManagment.getSpesificComponents(Class, obj.getSoftware(), obj
					.getSoftware().length);
		}
		catch ( ObjectNotFoundException e )
		{
			return false;
		}

		return true;
	}




	/**
	 * This function checks whether the given object contains more then
	 * one software object with the same class.
	 * Like if the object contains two Antivirus Objects or Firewall Objects.
	 * 
	 * @param obj
	 * 			The object that is to be examined.
	 * @param Class
	 * 			The class of the object that are to be searched for.
	 * @return
	 * 			Returns true if there is more then one software object
	 * 			found with the given class. Else it returns false.
	 */
	public static boolean moreThenOneSoftware(Object obj, Class<?> Class)
	{
		Object[] array = null;


		try
		{
			array = ArrayManagment.getSpesificComponents(Class, obj
					.getSoftware(), obj.getSoftware().length);
		}
		catch ( ObjectNotFoundException e )
		{
			return false;
		}

		// If the lenght of the array return is longer then 1,true is returned.
		if ( array.length > 1 )
		{
			return true;
		}

		return false;
	}
}
