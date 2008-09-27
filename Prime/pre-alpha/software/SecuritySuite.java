package software;


import java.io.Serializable;
import java.util.Date;

import objects.Software;


/**
 * This class represents an security suite program. It contains information on
 * whether or not the security suite contains a firewall, an antivirus and/or a
 * proxy. It also contains information about the activation of the software, the
 * license of the software and the activation/expiration date for the software.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class SecuritySuite extends Software implements Serializable
{
	/*
	 * Datafields for an abstract Security Suite These will contain the values
	 * of any Security Suite object
	 */

	// Whether or not the security suite has been activated
	private boolean activated;

	// The actual license of the program
	private String license;

	// Whether or not the security suite contains an antivirus
	private boolean hasAntivirus;

	// TO-DO: Set up connection between security suite and antivirus, firewall
	// and proxy

	// Whether or not the security suite contains an firewall
	private boolean hasFirewall;

	// Whether or not the security suite contains an proxy
	private boolean hasProxy;

	// The date of activation
	private Date actDate;

	// The date the license expires
	private Date expDate;


	// Constructor for the class
	/**
	 * Description NEEDED!
	 * 
	 * @param Name
	 *            The name of the security suite software.
	 * @param Desc
	 *            The description of the security suite software.
	 * @param Version
	 *            The version of the security suite software.
	 * @param SShasAntivirus
	 *            Whether or not the security suite contains an antivirus.
	 * @param SShasFirewall
	 *            Whether or not the security suite contains an firewall.
	 * @param SShasProxy
	 *            Whether or not the security suite contains an proxy.
	 */
	public SecuritySuite(String Name, String Desc, String Version, boolean SShasAntivirus,
			boolean SShasFirewall, boolean SShasProxy)
	{
		super(Name, Desc, Version);

		hasAntivirus = SShasAntivirus;
		hasFirewall = SShasFirewall;
		hasProxy = SShasProxy;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get whether or not the suite has been activated.
	 * 
	 */
	public boolean getIsActivated()
	{
		return activated;
	}


	/**
	 * Get the actual license string of the antivirus software.
	 * 
	 */
	public String getLicense()
	{
		return license;
	}


	/**
	 * Get whether or not the suite has an antivirus.
	 * 
	 */
	public boolean getHasAntivirus()
	{
		return hasAntivirus;
	}


	/**
	 * Get whether or not the suite has a firewall.
	 * 
	 */
	public boolean getHasFirewall()
	{
		return hasFirewall;
	}


	/**
	 * Get whether or not the suite has a proxy.
	 * 
	 */
	public boolean getHasProxy()
	{
		return hasProxy;
	}


	/**
	 * Get the activation date for the suite.
	 * 
	 */
	public Date getActivationDate()
	{
		return actDate;
	}


	/**
	 * Get the expiration date for the suite.
	 * 
	 */
	public Date getExpirationDate()
	{
		return expDate;
	}


	// SET METHODES

	/**
	 * Sets the suite to activated.
	 * 
	 */
	public void setActivated(boolean SSactivated)
	{
		activated = SSactivated;
	}


	/**
	 * Set the actual license string of the security suite software.
	 * 
	 */
	public void setLicense(String AVlicense)
	{
		license = AVlicense;
	}


	/**
	 * Get whether or not the suite has an antivirus.
	 * 
	 */
	public void setHasAntivirus(boolean SShasAntivirus)
	{
		hasAntivirus = SShasAntivirus;
	}


	/**
	 * Get whether or not the suite has an firewall.
	 * 
	 */
	public void sethasFirewall(boolean SShasFirewall)
	{
		hasFirewall = SShasFirewall;
	}


	/**
	 * Get whether or not the suite has an proxy.
	 * 
	 */
	public void sethasProxy(boolean SShasProxy)
	{
		hasProxy = SShasProxy;
	}


	/**
	 * Sets the suites activation date.
	 * 
	 */
	public void setActivationDate(Date SSactivattionDate)
	{
		actDate = SSactivattionDate;
	}


	/**
	 * Sets the suites expiration date.
	 * 
	 */
	public void setExpirationDate(Date SSexpirationDate)
	{
		expDate = SSexpirationDate;
	}
}
