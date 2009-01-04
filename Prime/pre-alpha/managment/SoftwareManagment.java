/**
 * 
 */
package managment;

import logistical.cleanup;
import objects.Object;
import objects.Software;
import software.Antivirus;
import software.Backup;
import software.Database;
import software.Email;
import software.Firewall;
import software.OfficeSuite;
import software.OperatingSystem;
import software.Proxy;
import software.SecuritySuite;
import software.Webserver;

/**
 * @author Bahram Malaekeh
 * @version 0.1
 * 
 */
public class SoftwareManagment 
{
	
	/**
	 * @param sw
	 * @param obj
	 */
	public static Software[] addSoftware(Software sw, Object obj)
	{
		Software[] curSW = obj.getSoftware();
		
		Software[] newSW = new Software[curSW.length + 1];
		
		for (int i = 0; i < curSW.length; i++)
		{
			newSW[i] = curSW[i];
		}
		
		
		// The index which is the same as the length of the current software array
		// will be exactly one index behind the end of all the current software.
		// Therefore the new software will be placed there.
		newSW[curSW.length] = sw;
		
		
		return newSW;
	}
	
	
	
	
	/**
	 * @param sw
	 * @param obj
	 * @return
	 */
	public static Software[] removeSoftware(Software sw, Object obj)
	{
		Software[] curSW = obj.getSoftware();
		
		// Gets the index of the software object
		int index = ArrayManagment.arrayContainsReturnsIndex(curSW, sw);
		
		
		// Removes the object at the given index
		if(index != -1)
		{
			curSW[index] = null;
		}
		
		// Removes the null pointer in the array
		curSW = cleanup.cleanObjectArray(curSW);
		
		
		// Returns the array
		return curSW;
	}
	
	
	
	/**
	 * @param sw
	 */
	public static boolean validateNewSoftware(Software sw, Object obj)
	{
		// Gets all the software of any object
		Software[] software = obj.getSoftware();
		
		// Gets the first object in the array of software(which will be the
		// operating system).
		OperatingSystem os = (OperatingSystem) software[0];
		
		// Gets the name of the Operating system.
		String osName = os.getObjectName();
		
		
		String[] swOS = null;
		
		
		if( sw instanceof Antivirus )
		{
			Antivirus swObj = (Antivirus) sw;

			swOS = swObj.getSupportedOperatingSystems();
		}
		else if( sw instanceof Backup )
		{
			Backup swObj = (Backup) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof Database )
		{
			Database swObj = (Database) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof Email )
		{
			Email swObj = (Email) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof Firewall )
		{
			Firewall swObj = (Firewall) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof OfficeSuite )
		{
			OfficeSuite swObj = (OfficeSuite) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof Proxy )
		{
			Proxy swObj = (Proxy) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof SecuritySuite )
		{
			SecuritySuite swObj = (SecuritySuite) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		else if( sw instanceof Webserver )
		{
			Webserver swObj = (Webserver) sw;

			swOS = swObj.getSupportedOperatingSystems();
			
		}
		
		
		if (swOS != null) 
		{
			// If the OS name is found to be an OS that the software supports
			if (ArrayManagment.arrayContains(swOS, osName) == true) 
			{
				return true;
			}
		}
		
		
		// If the methodes gets to this point it means that the software does
		// not support the operating system.
		return false;
	}
	
	
	
	
	/**
	 * @param os
	 * @param obj
	 */
	public static void changeOperatingSystem(OperatingSystem os, Object obj)
	{
		// Sets the OS object as the only object in the array
		Software[] temp = { os };
		
		// Sets the array to the software of the object
		obj.setSoftware(temp);
	}
}
