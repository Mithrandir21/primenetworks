package software;


import java.io.Serializable;

import objects.Software;


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
	public Backup(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the supportedOperatingSystems
	 */
	public String[] getSupportedOperatingSystems()
	{
		return supportedOperatingSystems;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the backupType
	 */
	public String getBackupType()
	{
		return backupType;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the compression
	 */
	public boolean supportsCompression()
	{
		return compression;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the encryption
	 */
	public boolean supportsEncryption()
	{
		return encryption;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the duplicate
	 */
	public int getDuplicate()
	{
		return duplicate;
	}

	
	// SET METHODES


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param backupType
	 *            the backupType to set
	 */
	public void setBackupType(String backupType)
	{
		this.backupType = backupType;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param compression
	 *            the compression to set
	 */
	public void setSupportsCompression(boolean compression)
	{
		this.compression = compression;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param encryption
	 *            the encryption to set
	 */
	public void setSupportsEncryption(boolean encryption)
	{
		this.encryption = encryption;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param duplicate
	 *            the duplicate to set
	 */
	public void setSupportsDuplicate(int duplicate)
	{
		this.duplicate = duplicate;
	}
	
	
	
	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the duplicate
	 */
	public void setDuplicate(int dup)
	{
		duplicate = dup;
	}
}
