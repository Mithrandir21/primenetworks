/**
 * 
 */
package widgetManipulation;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.io.Serializable;
import java.util.regex.Pattern;

import managment.NetworkManagment;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WorkareaCanvasNetworkInfo implements Serializable
{
	private double canvasSerial;
//
//	// The networks netmask
//	private String netmask = null;


	// The IP range start IP
	private String ipRangeFrom = null;


	// The IP range end IP
	private String ipRangeTo = null;


	// The widgets notes
	private String networkNotes = null;


	




	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 *            The WorkareaCanvas this information class belongs to.
	 */
	public WorkareaCanvasNetworkInfo(WorkareaCanvas canvas)
	{
		canvasSerial = canvas.getSerial();
	}



	// GETTERS

//
//	/**
//	 * @return Gets the network mask set for this classes WorkareaCanvas.
//	 */
//	public String getNetmask()
//	{
//		return netmask;
//	}




	/**
	 * Gets the IP range that this classes WorkareaCanvas starts with.
	 * 
	 * @return IP range start.
	 */
	public String getIpRangeFrom()
	{
		return ipRangeFrom;
	}



	/**
	 * Gets the IP range that this classes WorkareaCanvas ends with.
	 * 
	 * @return IP range ends.
	 */
	public String getIpRangeTo()
	{
		return ipRangeTo;
	}



	/**
	 * @return Gets the network notes.
	 */
	public String getNetworkNotes()
	{
		return networkNotes;
	}



	// SETTERS
//
//
//
//	/**
//	 * Javadoc-TODO - Description NEEDED!
//	 * 
//	 * @param netmask
//	 *            the netmask to set
//	 */
//	public boolean setNetmask(String netmask)
//	{
//		// If the string given matches the given pattern.
//		if ( NetworkManagment.getIPpattern().matcher(netmask).matches() )
//		{
//			this.netmask = netmask;
//			return true;
//		}
//
//		return false;
//	}




	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param ipRangeFrom
	 *            the ipRangeFrom to set
	 */
	public void setIpRangeFrom(String ipRangeFrom)
	{
		this.ipRangeFrom = ipRangeFrom;
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param ipRangeTo
	 *            the ipRangeTo to set
	 */
	public void setIpRangeTo(String ipRangeTo)
	{
		this.ipRangeTo = ipRangeTo;
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param networkNotes
	 *            the networkNotes to set
	 */
	public void setNetworkNotes(String networkNotes)
	{
		this.networkNotes = networkNotes;
	}
	
	
	
	

}
