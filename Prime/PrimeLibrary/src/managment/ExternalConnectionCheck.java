package managment;


import objects.Object;
import objects.hardwareObjects.Motherboard;
import objects.peripheralObjects.Keyboard;
import objects.peripheralObjects.Monitor;
import objects.peripheralObjects.Mouse;



/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ExternalConnectionCheck
{

	public static boolean checkMBexternalPorts(Motherboard MB, Object[] connectedDevices, Object a)
	{
		// FIXME - Check if this is needed
		return false;
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
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
	 * Javadoc-TODO - Description NEEDED!
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
	 * Javadoc-TODO - Description NEEDED!
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




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param MB
	 * @param connectedDevices
	 * @return
	 */
	public static boolean checkMB_USBportsAvailable(Motherboard MB, Object[] connectedDevices)
	{
		// TODO - Fix check number of free USB ports
		return false;
	}

}
