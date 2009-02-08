/**
 * 
 */
package graphics.GUI.messageArea.SoftwareTab;


import managment.ArrayManagment;
import objects.Object;
import exceptions.ObjectNotFoundException;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SoftwareProcessing
{
	private int errorsFound = 0;

	/**
	 * TODO - Description
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
	 * TODO - Description
	 */
	private static String[][] getCriticalErrors(String[][] data, Object obj)
	{
		// TODO - SoftwareProcessing - Errors
		return data;
	}




	/**
	 * TODO - Description
	 */
	private static String[][] getWarnings(String[][] data, Object obj)
	{
		// TODO - SoftwareProcessing - Warnings
		return data;
	}



	/**
	 * TODO - Description
	 */
	private static String[][] getNotices(String[][] data, Object obj)
	{
		// TODO - SoftwareProcessing - Notices
		return data;
	}



	/**
	 * TODO - Description
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
			return true;
		}

		return false;
	}




	/**
	 * TODO - Description
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
