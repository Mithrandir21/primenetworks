package objects;


import java.io.Serializable;


/**
 * An abstract super class for all hardware objects in the system, including
 * {@link software.Webserver Webserver}, {@link software.OperatingSystem
 * Operating System} and {@link software.Firewall Firewall}.
 * 
 * MUST ADD INFO
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Software extends Object implements Serializable
{


	// The software version
	private String version;

	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the software.
	 * @param Desc
	 *            The description of the software.
	 * @param SWversion
	 *            The version of the software. This can contain a NULL pointer,
	 *            which will then result in the version being set to "0.0.1".
	 */
	public Software(String Name, String Desc, String SWversion)
	{
		super(Name, Desc);
		if ( SWversion != null )
		{
			version = SWversion;
		}
		else
		{
			version = "0.0.1";
		}
	}


	/**
	 * Get the version of the software.
	 * 
	 */
	public String getVersion()
	{

		return version;
	}


	/**
	 * Set the version of the software.
	 * 
	 */
	public void setVersion(String SWversion)
	{

		version = SWversion;
	}


}
