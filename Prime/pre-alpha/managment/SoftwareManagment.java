/**
 * 
 */
package managment;

import objects.Object;
import objects.Software;
import software.OperatingSystem;

/**
 * @author Bahram Malaekeh
 * @version 0.1
 * 
 */
public class SoftwareManagment 
{
//	public static Software[] addSoftware()
//	{
//		
//	}
//	
//	
//	public static Software[] removeSoftware()
//	{
//		
//	}
//	
//	
//	public static Software[] changeSoftware()
//	{
//		
//	}
	
	
	
	
	/**
	 * @param sw
	 */
	public static boolean validateNewSoftware(Software sw, Object obj)
	{
		Software[] software = obj.getSoftware();
		
		OperatingSystem os = (OperatingSystem) software[0];
		
		String osName = os.getObjectName();
		
		
		
		return true;
	}
}
