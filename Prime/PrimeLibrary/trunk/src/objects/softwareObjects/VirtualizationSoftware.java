/**
 * 
 */
package objects.softwareObjects;


import objects.Software;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class VirtualizationSoftware extends Software
{

	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// A boolean on whether the virtualization software has to work without a
	// host( will not remove OS is set to true )
	private boolean noHost;

	// A boolean on whether the software supports SMP(symmetric multiprocessing)
	private boolean supSMP;

	// A boolean on whether the software supports 3D acceleration
	private boolean sup3Dacceleration;

	// A boolean on whether the software supports Live Snapshots of the OS
	// running
	private boolean supLiveSnapshots;



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
	public VirtualizationSoftware(String Name, String Desc, String SWversion)
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

		return supportedOperatingSystems;
	}



	/**
	 * Gets a boolean on whether the virtualization software has to work without
	 * a host( will not remove OS is set to true ).
	 */
	public boolean isNoHost()
	{
		return noHost;
	}



	/**
	 * Gets a boolean on whether the software supports SMP(symmetric
	 * multiprocessing).
	 */
	public boolean supportsSMP()
	{
		return supSMP;
	}



	/**
	 * Gets a boolean on whether the software supports 3D acceleration.
	 */
	public boolean supports3Dacceleration()
	{
		return sup3Dacceleration;
	}



	/**
	 * Gets a boolean on whether the software supports Live Snapshots of the OS
	 * running.
	 */
	public boolean supportsLiveSnapshots()
	{
		return supLiveSnapshots;
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
	 * Sets a boolean on whether the virtualization software has to work without
	 * a host( will not remove OS is set to true ).
	 */
	public void setNoHost(boolean noHost)
	{
		this.noHost = noHost;
	}



	/**
	 * Sets a boolean on whether the software supports SMP(symmetric
	 * multiprocessing).
	 */
	public void setSupSMP(boolean supSMP)
	{
		this.supSMP = supSMP;
	}



	/**
	 * Sets a boolean on whether the software supports 3D acceleration.
	 */
	public void setSup3Dacceleration(boolean sup3Dacceleration)
	{
		this.sup3Dacceleration = sup3Dacceleration;
	}



	/**
	 * Sets a boolean on whether the software supports Live Snapshots of the OS
	 * running.
	 */
	public void setSupLiveSnapshots(boolean supLiveSnapshots)
	{
		this.supLiveSnapshots = supLiveSnapshots;
	}
}
