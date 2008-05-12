package managment;


import objects.Object;
import hardware.*;
import peripheral.*;



/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ExternalConnectionCheck
{

	public static boolean checkMBexternalPorts(Motherboard MB,
			Object[] connectedDevices, Object a)
	{
		// TODO - Check if this is needed
		return false;
	}



	/**
	 * Description
	 */
	public static boolean checkMB_KeyboardConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Keyboard.class) )
				{
					return true;
				}
			}
		}


		return false;
	}


	/**
	 * Description
	 */
	public static boolean checkMB_MouseConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Mouse.class) )
				{
					return true;
				}
			}
		}


		return false;
	}


	/**
	 * Description
	 */
	public static boolean checkMB_MonitorConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Monitor.class) )
				{
					return true;
				}
			}
		}


		return false;
	}




	public static boolean checkMB_USBportsAvailable(Motherboard MB,
			Object[] connectedDevices)
	{
		// TODO - Fix check number of free USB ports
		return false;
	}

}
