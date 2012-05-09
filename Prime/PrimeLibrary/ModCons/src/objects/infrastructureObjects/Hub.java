/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects.infrastructureObjects;



import java.io.Serializable;

import managment.ArrayManagment;
import objects.Infrastructure;
import objects.hardwareObjects.Motherboard;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of the most simple networking device available. This device
 * simply replays any communication from
 * any one port to all other ports. This device is not often used in any kind of
 * large scale network because of the
 * flooding effect it has on that network. Once a communication is send by one
 * computer on the network, no other
 * communication will get through before the first one has reached its
 * destination.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class Hub extends Infrastructure implements Serializable
{

	// The transfer rate
	private int transferRate;

	// The different duplex types
	private String[] supportedDuplexType;

	// Boolean on whether or not it supports wireless connections
	private boolean supportsWireless;

	// Boolean on whether or not it supports wired conncetions
	private boolean supportsWired;

	// Number of integrated switch ports on the the device
	private int switchPorts;

	// Number of WAN ports, wide area network ports, on the device
	private int wanPorts;

	// THOUGHT - Wan subnet ip ranges.

	// The different link protocols supported by the device
	private String[] supportedLinkProtocols;

	// The different transport protocols supported by the device
	private String[] supportedTransportProtocols;

	// The different remote management protocols supported by the device
	private String[] supportedRemoteManageProtocols;

	// The different connection interfaces supported by the device
	private String[] conncetionInterfaces;



	// FIXME - Rating system for networking devices.


	/*
	 * NOTES - Connections between this device and the devices connected to it
	 * will be handled by the main system.
	 */



	/**
	 * Constructor for the hub class.
	 * 
	 * @param Name
	 *            The name of the device.
	 * @param Desc
	 *            The description of the device.
	 * @param outPorts
	 *            The number of ports that can be connected to different
	 *            computers or subnets.
	 * @param inPorts
	 *            The number of ports that are meant to be used providers of
	 *            internet access.
	 * @param DuplexSupport
	 *            The supported duplex type on the device.
	 */
	public Hub(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB, int outPorts, int inPorts,
			String[] DuplexSupport)
	{
		super(Name, Desc, SupConInt, objectMB);

		switchPorts = outPorts;

		wanPorts = inPorts;

		supportedDuplexType = DuplexSupport;
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get the different connection interfaces supported by the device.
	 * 
	 * @return The conncetionInterfaces in an array of strings.
	 */
	public String[] getConncetionInterfaces()
	{

		return conncetionInterfaces;
	}



	/**
	 * Get the different duplex types.
	 * 
	 * @return the supportedDuplexType in an array of strings.
	 */
	public String[] getSupportedDuplexType()
	{

		return supportedDuplexType;
	}



	/**
	 * Get the different link protocols supported by the device.
	 * 
	 * @return the supportedLinkProtocols in an array of strings.
	 */
	public String[] getSupportedLinkProtocols()
	{

		return supportedLinkProtocols;
	}



	/**
	 * Get the different remote management protocols supported by the device.
	 * 
	 * @return the supportedRemoteManageProtocols in an array of strings.
	 */
	public String[] getSupportedRemoteManageProtocols()
	{

		return supportedRemoteManageProtocols;
	}



	/**
	 * Get the different transport protocols supported by the device.
	 * 
	 * @return the supportedTransportProtocols in an array of strings.
	 */
	public String[] getSupportedTransportProtocols()
	{

		return supportedTransportProtocols;
	}



	/**
	 * Gets the boolean on whether or not it supports wired conncetions.
	 * 
	 * @return A boolean on whether or not the device supports wired
	 *         connections.
	 */
	public boolean isSupportsWired()
	{

		return supportsWired;
	}



	/**
	 * Get the boolean on whether or not it supports wireless connections.
	 * 
	 * @return A boolean on whether or not the device supports wireless
	 *         connections.
	 */
	public boolean isSupportsWireless()
	{

		return supportsWireless;
	}



	/**
	 * Gets the number of integrated switch ports on the the device.
	 * 
	 * @return The number of different network ports the device has.
	 */
	public int getSwitchPorts()
	{

		return switchPorts;
	}



	/**
	 * Gets the transfer rate.
	 * 
	 * @return The transfer rate on each of the switch ports.
	 */
	public int getTransferRate()
	{

		return transferRate;
	}



	/**
	 * Gets the number of WAN ports, wide area network ports, on the device.
	 * 
	 * @return The wan ports meant to connect to different subnets.
	 */
	public int getWanPorts()
	{

		return wanPorts;
	}



	// SET METHODES
	/**
	 * Sets the different connection interfaces supported by the device.
	 * 
	 * @param conncetionInterfaces
	 *            the conncetionInterfaces to set
	 */
	public void setConncetionInterfaces(String[] conncetionInterfaces)
	{

		this.conncetionInterfaces = conncetionInterfaces;
	}



	/**
	 * Sets the different duplex types.
	 * 
	 * @param supportedDuplexType
	 *            the supportedDuplexType to set
	 */
	public void setSupportedDuplexType(String[] supportedDuplexType)
	{

		this.supportedDuplexType = supportedDuplexType;
	}



	/**
	 * Sets the different link protocols supported by the device.
	 * 
	 * @param supportedLinkProtocols
	 *            the supportedLinkProtocols to set
	 */
	public void setSupportedLinkProtocols(String[] supportedLinkProtocols)
	{

		this.supportedLinkProtocols = supportedLinkProtocols;
	}



	/**
	 * Sets the different remote management protocols supported by the device.
	 * 
	 * @param supportedRemoteManageProtocols
	 *            the supportedRemoteManageProtocols to set
	 */
	public void setSupportedRemoteManageProtocols(
			String[] supportedRemoteManageProtocols)
	{

		this.supportedRemoteManageProtocols = supportedRemoteManageProtocols;
	}



	/**
	 * Sets the different transport protocols supported by the device.
	 * 
	 * @param supportedTransportProtocols
	 *            the supportedTransportProtocols to set
	 */
	public void setSupportedTransportProtocols(
			String[] supportedTransportProtocols)
	{

		this.supportedTransportProtocols = supportedTransportProtocols;
	}



	/**
	 * Sets the boolean on whether or not it supports wired conncetions.
	 * 
	 * @param supportsWired
	 *            A boolean on whether or not the device supports wired
	 *            connections.
	 */
	public void setSupportsWired(boolean supportsWired)
	{

		this.supportsWired = supportsWired;
	}



	/**
	 * Sets the boolean on whether or not it supports wireless connections.
	 * 
	 * @param supportsWireless
	 *            A boolean on whether or not the device supports wireless
	 *            connections.
	 */
	public void setSupportsWireless(boolean supportsWireless)
	{

		this.supportsWireless = supportsWireless;
	}



	/**
	 * Sets the number of integrated switch ports on the the device.
	 * 
	 * @param switchPorts
	 *            The number of different network ports the device has.
	 */
	public void setSwitchPorts(int switchPorts)
	{

		this.switchPorts = switchPorts;
	}



	/**
	 * Sets the transfer rate.
	 * 
	 * @param transferRate
	 *            The transfer rate on each of the switch ports.
	 */
	public void setTransferRate(int transferRate)
	{

		this.transferRate = transferRate;
	}



	/**
	 * Sets the number of WAN ports, wide area network ports, on the device.
	 * 
	 * @param wanPorts
	 *            The wan ports meant to connect to different subnets.
	 */
	public void setWanPorts(int wanPorts)
	{

		this.wanPorts = wanPorts;
	}



	// CLASS METHODES
	// Connection interfaces methodes.

	/**
	 * Function for replacing a spesific given connection interface with a given
	 * new interface.
	 * 
	 * @param NewItem
	 *            The interface to replace the previous one.
	 * @param OldItem
	 *            The interface to be replaced.
	 */
	public void changeConncetionInterfacesItem(String NewItem, String OldItem)
	{
		conncetionInterfaces = ArrayManagment.changeArrayItem(NewItem, OldItem,
				conncetionInterfaces);
	}


	/**
	 * Function to remove connection interfaces from the array of connection
	 * interfaces.
	 * 
	 * @param ToBeRemoved
	 *            Connection interfaces to be removed.
	 */
	public void removeConncetionInterfacesItem(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		conncetionInterfaces = ArrayManagment.removeItems(ToBeRemoved,
				conncetionInterfaces);
	}


	/**
	 * Function to add item(s) to the the conncetion interfaces list.
	 * 
	 * @param NewItems
	 *            An array of new conncetion interfaces.
	 */
	public void addConncetionInterfacesItem(String[] NewItems) throws Exception
	{
		conncetionInterfaces = ArrayManagment.addItems(NewItems,
				conncetionInterfaces);

	}



	// Link protocols methodes.

	/**
	 * Function for replacing a spesific given link protocol with a given new
	 * protocol.
	 * 
	 * @param NewItem
	 *            The protocol to replace the previous one.
	 * @param OldItem
	 *            The protocol to be replaced.
	 */
	public void changeLinkProtocolItem(String NewItem, String OldItem)
	{
		supportedLinkProtocols = ArrayManagment.changeArrayItem(NewItem,
				OldItem, supportedLinkProtocols);
	}


	/**
	 * Function to remove link protocols from the array of link protocols.
	 * 
	 * @param ToBeRemoved
	 *            Link protocol to be removed.
	 */
	public void removeLinkProtocolItem(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		supportedLinkProtocols = ArrayManagment.removeItems(ToBeRemoved,
				supportedLinkProtocols);
	}


	/**
	 * Function to add item(s) to the the link protocols list.
	 * 
	 * @param NewItems
	 *            An array of new link protocols.
	 */
	public void addLinkProtocolItem(String[] NewItems) throws Exception
	{
		supportedLinkProtocols = ArrayManagment.addItems(NewItems,
				supportedLinkProtocols);

	}


	// Remote management protocols methodes.

	/**
	 * Function for replacing a spesific given remote management protocol with a
	 * given new protocol.
	 * 
	 * @param NewItem
	 *            The protocol to replace the previous one.
	 * @param OldItem
	 *            The protocol to be replaced.
	 */
	public void changeRemoteManagementProtocolItem(String NewItem,
			String OldItem)
	{
		supportedRemoteManageProtocols = ArrayManagment.changeArrayItem(
				NewItem, OldItem, supportedRemoteManageProtocols);
	}


	/**
	 * Function to remove remote management protocols from the array of remote
	 * management protocols.
	 * 
	 * @param ToBeRemoved
	 *            Link protocol to be removed.
	 */
	public void removeRemoteManagementProtocolsItem(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		supportedRemoteManageProtocols = ArrayManagment.removeItems(
				ToBeRemoved, supportedRemoteManageProtocols);
	}


	/**
	 * Function to add item(s) to the the remote management protocols list.
	 * 
	 * @param NewItems
	 *            An array of new remote management protocols.
	 */
	public void addRemoteManagementProtocolsItem(String[] NewItems)
			throws Exception
	{
		supportedRemoteManageProtocols = ArrayManagment.addItems(NewItems,
				supportedRemoteManageProtocols);

	}



	// Transport protocols methodes.

	/**
	 * Function for replacing a spesific given transport protocol with a given
	 * new protocol.
	 * 
	 * @param NewItem
	 *            The protocol to replace the previous one.
	 * @param OldItem
	 *            The protocol to be replaced.
	 */
	public void changeTransportProtocolItems(String NewItem, String OldItem)
	{
		supportedRemoteManageProtocols = ArrayManagment.changeArrayItem(
				NewItem, OldItem, supportedRemoteManageProtocols);
	}


	/**
	 * Function to remove transport protocols from the array of transport
	 * protocols.
	 * 
	 * @param ToBeRemoved
	 *            Link protocol to be removed.
	 */
	public void removeTransportProtocolItems(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		supportedRemoteManageProtocols = ArrayManagment.removeItems(
				ToBeRemoved, supportedRemoteManageProtocols);
	}


	/**
	 * Function to add item(s) to the the transport protocols list.
	 * 
	 * @param NewItems
	 *            An array of new transport protocols.
	 */
	public void addTransportProtocolItems(String[] NewItems) throws Exception
	{
		supportedRemoteManageProtocols = ArrayManagment.addItems(NewItems,
				supportedRemoteManageProtocols);

	}





}
