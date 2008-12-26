package software;


import java.io.Serializable;

import objects.Software;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class OperatingSystem extends Software implements Serializable
{

	// Supported File systems
	private String[] supportedFS;


	// Has encrypted file system
	private boolean encryptedFileSystem;


	// Has GUI
	private boolean hasGUI;


	// Is 64-bit
	private boolean is64bit;



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param Version
	 */
	public OperatingSystem(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}


	// GETTERS


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the supportedFS
	 */
	public String[] getSupportedFS()
	{
		return supportedFS;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the encryptedFileSystem
	 */
	public boolean isEncryptedFileSystem()
	{
		return encryptedFileSystem;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the hasGUI
	 */
	public boolean isHasGUI()
	{
		return hasGUI;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the is64bit
	 */
	public boolean isIs64bit()
	{
		return is64bit;
	}



	// SETTERS


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param supportedFS
	 *            the supportedFS to set
	 */
	public void setSupportedFS(String[] supportedFS)
	{
		this.supportedFS = supportedFS;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param encryptedFileSystem
	 *            the encryptedFileSystem to set
	 */
	public void setEncryptedFileSystem(boolean encryptedFileSystem)
	{
		this.encryptedFileSystem = encryptedFileSystem;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param hasGUI
	 *            the hasGUI to set
	 */
	public void setHasGUI(boolean hasGUI)
	{
		this.hasGUI = hasGUI;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param is64bit
	 *            the is64bit to set
	 */
	public void setIs64bit(boolean is64bit)
	{
		this.is64bit = is64bit;
	}



}
