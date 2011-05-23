package model.interfaces;


import java.net.InetAddress;
import java.util.Date;

import model.Connection;
import model.devices.Device;
import scan.Subnet;
import scan.status.PingMethod;
import scan.status.Status;


/**
 * Defines a network interface, consisting of (at least) an address, a type and
 * a method to determine its state.
 * 
 * @author rakudave
 */
public interface NetworkIF
{

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o);

	/**
	 * @return the address of this network interface
	 */
	public InetAddress getAddress();

	/**
	 * @return the canonical name reported by the address of this network
	 *         interface
	 */
	public String getCanonicalName();

	/**
	 * @return the connection this interface is associated with
	 */
	public Connection getConnection();

	/**
	 * @return the device that this network interface is attached to
	 * @see Device
	 */
	public Device getDevice();

	/**
	 * @return the default gateway of this interface.
	 */
	public InetAddress getGateway();

	/**
	 * @return when this link has been up the last time
	 * @see Date
	 */
	public Date getLastSeen();

	/**
	 * @return the name of this network interface, e.g. eth0
	 */
	public String getName();

	/**
	 * @return the status of this network interface
	 * @see Status
	 */
	public Status getStatus();

	/**
	 * @return the subnet mask of this network interface.
	 */
	public Subnet getSubnet();

	/**
	 * Sets a new network address for this network interface
	 * 
	 * @param addr
	 *            The network address to be set
	 * @return success, i.e. false if the address was invalid
	 */
	public boolean setAddress(String addr);

	/**
	 * Set a new default gateway for this network interface
	 * 
	 * @param gateway
	 *            the address of the new gateway
	 */
	public boolean setGateway(String gateway);

	/**
	 * set the name reported by the address of this network interface, e.g. eth0
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Set a new subnet mask for this network interface
	 * 
	 * @param subnet
	 *            the address of the subnet
	 */
	public boolean setSubnet(String subnet);

	/**
	 * Request this interface to check its status using a specified method
	 * 
	 * @see Status
	 * @see PingMethod
	 */
	public void updateStatus();
}