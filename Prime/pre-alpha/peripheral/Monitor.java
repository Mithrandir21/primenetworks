package peripheral;
import java.io.Serializable;

import objects.ExternalHardware;
import objects.Hardware;

/**
 * This class represents a monitor.
 * This device can be connected to any device that supports the conncetion type of this device. 
 * It contains information on what kind of capability the monitor has and what 
 * kind of connection interface it has.
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Monitor extends ExternalHardware implements Serializable 
{

	// Monitor size. inches
	private int inches;
	
	// Type of monitor. CRT or LCD.
	private String monitorType;
	
	// Boolean to check whether or not a monitor is a laptop monitor.
	private boolean isLaptopMonitor = false;
	
	
	// Constructor of the class
	/**
	 * Description NEEDED!
	 *
	 * @param Name The name of the monitor.
	 * @param Desc The description of the monitor.
	 * @param MonitorInches Monitor size. In inches.
	 * @param type Type of monitor. CRT or LCD.
	 * @param MonitorConnectionInterfaces Monitor connection interface. A array of strings.
	 */
	public Monitor(String Name, String Desc, int MonitorInches,
			String type, String[] MonitorConnectionInterfaces)
	{
		super(Name,Desc,MonitorConnectionInterfaces);
		
		inches = MonitorInches;
		monitorType = type;
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES

	/**
	 * Get monitor size.
	 * 
	 */
	public int getMonitorSize()
	{
		return inches;
	}
	
	
	/**
	 * Gets the type of monitor. LCD or CRT.
	 *
	 */
	public String getMonitorType()
	{
		return monitorType;
	}

	
	
	/**
	 * Get boolean on whether or not the monitor is a laptop monitor.
	 * 
	 */
	public boolean isLaptopMonitor()
	{
		return isLaptopMonitor;
	}
	
	
	
	// SET METHODES

	/**
	 * Sets monitor size.
	 * 
	 */
	public void setMonitorSize(int MonitorInches)
	{
		inches = MonitorInches;
	}
	
	
	/**
	 * Sets the type of monitor. CRT or LCD.
	 *
	 */
	public void setMonitorType(String Type)
	{
		monitorType = Type;
	}

	
	/**
	 * Sets info on whether or not the monitor is a laptop monitor.
	 * 
	 */
	public void setLaptopMonitor(boolean info)
	{
		isLaptopMonitor = info;
	}
	
	

}