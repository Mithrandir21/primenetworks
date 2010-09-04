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
public class RemoteDesktop extends Software
{

	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// A boolean on whether the software supports encryption
	private boolean supEncryption;

	// A boolean on whether the software supports file transfer
	private boolean supFileTransfer;

	// A boolean on whether the software supports audio
	private boolean supAudio;

	// A boolean on whether the software supports Multiple sessions
	private boolean supMultiSessions;

	// A boolean on whether the software supports seamless windows
	private boolean supSeamlessWindows;



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
	public RemoteDesktop(String Name, String Desc, String SWversion)
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
	 * Gets a boolean on whether the software supports encryption.
	 */
	public boolean supportsEncryption()
	{
		return supEncryption;
	}



	/**
	 * Gets a boolean on whether the software supports file transfer.
	 */
	public boolean supportsFileTransfer()
	{
		return supFileTransfer;
	}



	/**
	 * Gets a boolean on whether the software supports audio.
	 */
	public boolean supportsAudio()
	{
		return supAudio;
	}



	/**
	 * Gets a boolean on whether the software supports Multiple sessions.
	 */
	public boolean supportsMultiSessions()
	{
		return supMultiSessions;
	}



	/**
	 * Gets a boolean on whether the software supports seamless windows.
	 */
	public boolean supportsSeamlessWindows()
	{
		return supSeamlessWindows;
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
	 * Sets a boolean on whether the software supports encryption.
	 */
	public void setSupportsEncryption(boolean supEncryption)
	{
		this.supEncryption = supEncryption;
	}



	/**
	 * Sets a boolean on whether the software supports file transfer.
	 */
	public void setSupportsFileTransfer(boolean supFileTransfer)
	{
		this.supFileTransfer = supFileTransfer;
	}



	/**
	 * Sets a boolean on whether the software supports audio.
	 */
	public void setSupportsAudio(boolean supAudio)
	{
		this.supAudio = supAudio;
	}



	/**
	 * Sets a boolean on whether the software supports Multiple sessions.
	 */
	public void setSupportsMultiSessions(boolean supMultiSessions)
	{
		this.supMultiSessions = supMultiSessions;
	}



	/**
	 * Sets a boolean on whether the software supports seamless windows.
	 */
	public void setSupportsSeamlessWindows(boolean supSeamlessWindows)
	{
		this.supSeamlessWindows = supSeamlessWindows;
	}
}
