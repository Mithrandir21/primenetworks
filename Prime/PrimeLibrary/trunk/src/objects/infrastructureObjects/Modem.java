/**
 * 
 */
package objects.infrastructureObjects;


import java.io.Serializable;

import objects.Infrastructure;
import objects.hardwareObjects.Motherboard;


/**
 * A representation of a networking device called a modem. A modem (modulator-demodulator) is a device that modulates an analog
 * carrier signal to encode digital information, and also demodulates such a carrier signal to decode the transmitted information.
 * The goal is to produce a signal that can be transmitted easily and decoded to reproduce the original digital data. Modems can
 * be used over any means of transmitting analog signals, from driven diodes to radio.
 * 
 * @author Bahram Malaekeh
 */
public class Modem extends Infrastructure implements Serializable
{
	// The transfer rate
	private int transferRate;


	/**
	 * Constructor for the Modem class.
	 * 
	 * @param Name
	 *            The name of the device.
	 * @param Desc
	 *            The description of the device.
	 * @param SupConInt
	 *            The supported interfaces by the Modem.
	 * @param objectMB
	 *            The objects Motherboard.(Normally standard hardware
	 *            motherboard.)
	 */
	public Modem(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB)
	{
		super(Name, Desc, SupConInt, objectMB);
	}




	// GET METHODS


	/**
	 * Gets the transfer rate.
	 * 
	 * @return The transfer rate for the out port.
	 */
	public int getTransferRate()
	{

		return transferRate;
	}






	// SET METHODS


	/**
	 * Sets the transfer rate.
	 * 
	 * @param transferRate
	 *            The transfer rate for the out port.
	 */
	public void setTransferRate(int transferRate)
	{

		this.transferRate = transferRate;
	}
}
