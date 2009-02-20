package objects.softwareObjects;


import java.io.Serializable;

import objects.Software;


/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class Backup extends Software implements Serializable
{

	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// The type of backup
	private String backupType;

	// Whether or not the software can use compression
	private boolean compression;

	// Whether or not the software can use encryption
	private boolean encryption;

	// The number of copies keeps
	private int duplicate;



	// FIXME - Fix backup.
	/**
	 * A constructor for the class that passes on the given parameters
	 * to the constructors of the super class.
	 *
	 * @param Name
	 * @param Desc
	 * @param Version
	 */
	public Backup(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



	/**
	 * Returns an array with the names of the supported Operating Systems.
	 * 
	 * @return the supportedOperatingSystems
	 */
	public String[] getSupportedOperatingSystems()
	{
		return supportedOperatingSystems;
	}



	/**
	 * Gets the type of backup the backup software keeps.
	 * 
	 * @return the backupType
	 */
	public String getBackupType()
	{
		return backupType;
	}



	/**
	 * Gets whether or not the backup supports compression.
	 * 
	 * @return the compression
	 */
	public boolean supportsCompression()
	{
		return compression;
	}



	/**
	 * Gets whether or not the backup supports encryption.
	 * 
	 * @return the encryption
	 */
	public boolean supportsEncryption()
	{
		return encryption;
	}



	/**
	 * Gets the number of duplicates the backup keeps.
	 * 
	 * @return the duplicate
	 */
	public int getDuplicate()
	{
		return duplicate;
	}

	
	// SET METHODES


	/**
	 * Sets an array with the names of the supported Operating Systems.
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}



	/**
	 * Sets the type of backup type. Like Full backup or incremental.
	 * 
	 * @param backupType
	 *            the backupType to set
	 */
	public void setBackupType(String backupType)
	{
		this.backupType = backupType;
	}



	/**
	 * Sets whether or not the backup supports compression.
	 * 
	 * @param compression
	 *            the compression to set
	 */
	public void setSupportsCompression(boolean compression)
	{
		this.compression = compression;
	}



	/**
	 * Sets whether or not the backup supports encryption.
	 * 
	 * @param encryption
	 *            the encryption to set
	 */
	public void setSupportsEncryption(boolean encryption)
	{
		this.encryption = encryption;
	}
	
	
	
	/**
	 * Sets the number of duplicates of the backup the software keeps.
	 */
	public void setDuplicate(int dup)
	{
		duplicate = dup;
	}
}
