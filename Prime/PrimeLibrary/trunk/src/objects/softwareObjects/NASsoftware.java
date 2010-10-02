/**
 * 
 */
package objects.softwareObjects;


import objects.Clients;
import objects.Servers;
import objects.Software;
import objects.serverObjects.NASServer;


/**
 * This class represents a software that will perform the actions of a
 * {@link NASServer}, but will be on any {@link Clients Client} or
 * {@link Servers Server}.
 * 
 * @author Bahram Malaekeh
 */
public class NASsoftware extends Software
{

	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// A boolean on whether the software supports RAID 5
	private boolean supRaid5;

	// A boolean on whether the software supports encryption
	private boolean supEncryption;

	// A boolean on whether the software supports S.M.A.R.T. monitoring
	private boolean supSMARTmon;

	// A boolean on whether the software supports a web interface
	private boolean supWebInterface;


	// FIXME - Fix NASsoftware.
	/**
	 * A constructor for the values needed for the super class({@link Software}
	 * ).
	 * 
	 * @param Name
	 *            The name of the Generic software.
	 * @param Desc
	 *            The description of the software.
	 * @param SWversion
	 *            The version of the software.
	 */
	public NASsoftware(String Name, String Desc, String SWversion)
	{
		super(Name, Desc, SWversion);
	}



	// GET METHODES



	/**
	 * Get the supported operating systems.
	 * 
	 * @return A array of strings with names of the Operating Systems.
	 */
	public String[] getSupportedOperatingSystems()
	{

		return this.supportedOperatingSystems;
	}



	/**
	 * Gets a boolean on whether the software supports RAID 5.
	 */
	public boolean supportsRaid5()
	{
		return this.supRaid5;
	}



	/**
	 * Gets a boolean on whether the software supports encryption.
	 */
	public boolean supportsEncryption()
	{
		return this.supEncryption;
	}



	/**
	 * Gets a boolean on whether the software supports S.M.A.R.T. monitoring.
	 */
	public boolean supportsSMARTmon()
	{
		return this.supSMARTmon;
	}



	/**
	 * Gets a boolean on whether the software supports a web interface.
	 */
	public boolean supportsWebInterface()
	{
		return this.supWebInterface;
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
	 * Sets a boolean on whether the software supports RAID 5.
	 */
	public void setSupportsRaid5(boolean supRaid5)
	{
		this.supRaid5 = supRaid5;
	}



	/**
	 * Sets a boolean on whether the software supports encryption.
	 */
	public void setSupportsEncryption(boolean supEncryption)
	{
		this.supEncryption = supEncryption;
	}



	/**
	 * Sets a boolean on whether the software supports encryption.
	 */
	public void setSupportsSMARTmon(boolean supSMARTmon)
	{
		this.supSMARTmon = supSMARTmon;
	}



	/**
	 * Sets a boolean on whether the software supports a web interface.
	 */
	public void setSupportsWebInterface(boolean supWebInterface)
	{
		this.supWebInterface = supWebInterface;
	}
}
