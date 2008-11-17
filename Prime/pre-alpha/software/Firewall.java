package software;


import java.io.Serializable;

import objects.Software;


/**
 * A class representing a software firewall. This software is meant to run on a
 * specific machine with the goal of separating different networks which are at
 * different trust levels. Its goal is to allow, deny or proxy any connection
 * from one network to another according to the rules that are given to it by
 * the administrator.
 * 
 * FIXME - Description for all the class methods.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class Firewall extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;


	// The connection to the private network
	// NOTES - Connections to the different networks will be handled by the main
	// system.

	// The connection to the public network


	// The connection to the DMZ zone



	// Has network-layer firewall feature
	private boolean hasNetworkFirewall;

	// Has stateful firewall feature
	private boolean hasStatefulFirewall;

	// Has application-layer firewall feature
	private boolean hasApplicationFirewall;

	// Has DPI, deep package inspections, firewall feature
	private boolean hasDPI;






	// NON-FIREWALLING FEATURE
	// Has proxy feature
	private boolean hasProxy;

	// Has NAT feature
	private boolean hasNAT;

	// Has VPN feature
	private boolean hasVPN;

	// Has antivirus feature
	private boolean hasAntivirus;

	// Has IDS, Intrusion Detection System, feature
	private boolean hasIDS;





	// DIFFERENT SUPPORT FEATURES
	// Supports Modularity, third-party modules to extend functionality
	private boolean supportsModularity;

	// Supports IP version 6
	private boolean supportsIPv6;

	// Supports TTL, Transparent to traceroute
	private boolean supportsTTL;

	// Supports RWA, Reject-with-answer
	private boolean supportsRWA;

	// Supports a DMZ, de-militarized zone
	private boolean supportsDMZ;

	// Supports ToDFilter, Time of day filter
	private boolean supportsToD;

	// Supports forwarding
	private boolean supportsForwarding;

	// Supports port forwarding
	private boolean supportsPortForwarding;

	// Supports QoS, quality of service
	private boolean supportsQos;

	// Supports TP, tarpit
	private boolean supportsTarpit;








	public Firewall(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}


	// Get and Set methodes for retrieving all datafields.


	// GET METHODES



	/**
	 * Get the supported operating systems.
	 * 
	 * @return A array of strings with names of the Operating Systems.
	 */
	public String[] getSupportedOperatingSystems()
	{

		return supportedOperatingSystems;
	}



	/**
	 * Get a boolean telling whether or not the firewall has a integrated
	 * antivirus.
	 * 
	 */
	public boolean HasAntivirus()
	{

		return hasAntivirus;
	}





	/**
	 * Get a boolean that says if the firewall has an application level firewall
	 * feature.
	 * 
	 */
	public boolean HasApplicationFirewall()
	{

		return hasApplicationFirewall;
	}





	/**
	 * Get a boolean that says if the firewall has a deep package inspection
	 * firewall feature.
	 * 
	 */
	public boolean HasDPI()
	{

		return hasDPI;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasIDS()
	{

		return hasIDS;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasNAT()
	{

		return hasNAT;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasNetworkFirewall()
	{

		return hasNetworkFirewall;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasProxy()
	{

		return hasProxy;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasStatefulFirewall()
	{

		return hasStatefulFirewall;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean HasVPN()
	{

		return hasVPN;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsDMZ()
	{

		return supportsDMZ;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsForwarding()
	{

		return supportsForwarding;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsIPv6()
	{

		return supportsIPv6;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsPortForwarding()
	{

		return supportsPortForwarding;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsQos()
	{

		return supportsQos;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsRWA()
	{

		return supportsRWA;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsTarpit()
	{

		return supportsTarpit;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsToD()
	{

		return supportsToD;
	}





	/**
	 * Description NEEDED!
	 * 
	 */
	public boolean SupportsTTL()
	{

		return supportsTTL;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsModularity
	 */
	public boolean SupportsModularity()
	{

		return supportsModularity;
	}



	// SET METHODES



	/**
	 * Description NEEDED!
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{

		this.supportedOperatingSystems = supportedOperatingSystems;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param hasAntivirus
	 *            the hasAntivirus to set
	 */
	public void setHasAntivirus(boolean hasAntivirus)
	{

		this.hasAntivirus = hasAntivirus;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasApplicationFirewall
	 *            the hasApplicationFirewall to set
	 */
	public void setHasApplicationFirewall(boolean hasApplicationFirewall)
	{

		this.hasApplicationFirewall = hasApplicationFirewall;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasDPI
	 *            the hasDPI to set
	 */
	public void setHasDPI(boolean hasDPI)
	{

		this.hasDPI = hasDPI;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasIDS
	 *            the hasIDS to set
	 */
	public void setHasIDS(boolean hasIDS)
	{

		this.hasIDS = hasIDS;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasNAT
	 *            the hasNAT to set
	 */
	public void setHasNAT(boolean hasNAT)
	{

		this.hasNAT = hasNAT;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasNetworkFirewall
	 *            the hasNetworkFirewall to set
	 */
	public void setHasNetworkFirewall(boolean hasNetworkFirewall)
	{

		this.hasNetworkFirewall = hasNetworkFirewall;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasProxy
	 *            the hasProxy to set
	 */
	public void setHasProxy(boolean hasProxy)
	{

		this.hasProxy = hasProxy;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasStatefulFirewall
	 *            the hasStatefulFirewall to set
	 */
	public void setHasStatefulFirewall(boolean hasStatefulFirewall)
	{

		this.hasStatefulFirewall = hasStatefulFirewall;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param hasVPN
	 *            the hasVPN to set
	 */
	public void setHasVPN(boolean hasVPN)
	{

		this.hasVPN = hasVPN;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsDMZ
	 *            the supportsDMZ to set
	 */
	public void setSupportsDMZ(boolean supportsDMZ)
	{

		this.supportsDMZ = supportsDMZ;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsForwarding
	 *            the supportsForwarding to set
	 */
	public void setSupportsForwarding(boolean supportsForwarding)
	{

		this.supportsForwarding = supportsForwarding;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsIPv6
	 *            the supportsIPv6 to set
	 */
	public void setSupportsIPv6(boolean supportsIPv6)
	{

		this.supportsIPv6 = supportsIPv6;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsPortForwarding
	 *            the supportsPortForwarding to set
	 */
	public void setSupportsPortForwarding(boolean supportsPortForwarding)
	{

		this.supportsPortForwarding = supportsPortForwarding;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsQos
	 *            the supportsQos to set
	 */
	public void setSupportsQos(boolean supportsQos)
	{

		this.supportsQos = supportsQos;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsRWA
	 *            the supportsRWA to set
	 */
	public void setSupportsRWA(boolean supportsRWA)
	{

		this.supportsRWA = supportsRWA;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsTarpit
	 *            the supportsTarpit to set
	 */
	public void setSupportsTarpit(boolean supportsTarpit)
	{

		this.supportsTarpit = supportsTarpit;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsToD
	 *            the supportsToD to set
	 */
	public void setSupportsToD(boolean supportsToD)
	{

		this.supportsToD = supportsToD;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsTTL
	 *            the supportsTTL to set
	 */
	public void setSupportsTTL(boolean supportsTTL)
	{

		this.supportsTTL = supportsTTL;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param supportsModularity
	 *            the supportsModularity to set
	 */
	public void setSupportsModularity(boolean supportsModularity)
	{

		this.supportsModularity = supportsModularity;
	}



	// CLASS METHODES





}
