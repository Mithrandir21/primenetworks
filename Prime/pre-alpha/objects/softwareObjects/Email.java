package objects.softwareObjects;


import java.io.Serializable;

import objects.Software;


public class Email extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// Supports POP3
	private boolean supportsPOP3;


	// Supports SMTP
	private boolean supportsSMTP;


	// Supports IMAP
	private boolean supportsIMAP;


	// Supports NNTP
	private boolean supportsNNTP;


	// Supports SSL
	private boolean supportsSSL;


	// Supports webmail
	private boolean supportsWebmail;


	// FIXME - Authentication remains to be fixed.


	public Email(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportedOperatingSystems
	 */
	public String[] getSupportedOperatingSystems()
	{

		return supportedOperatingSystems;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsIMAP
	 */
	public boolean SupportsIMAP()
	{

		return supportsIMAP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsNNTP
	 */
	public boolean SupportsNNTP()
	{

		return supportsNNTP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsPOP3
	 */
	public boolean SupportsPOP3()
	{

		return supportsPOP3;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsSMTP
	 */
	public boolean SupportsSMTP()
	{

		return supportsSMTP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsSSL
	 */
	public boolean SupportsSSL()
	{

		return supportsSSL;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsWebmail
	 */
	public boolean SupportsWebmail()
	{

		return supportsWebmail;
	}



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
	 * @param supportsIMAP
	 *            the supportsIMAP to set
	 */
	public void setSupportsIMAP(boolean supportsIMAP)
	{

		this.supportsIMAP = supportsIMAP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportsNNTP
	 *            the supportsNNTP to set
	 */
	public void setSupportsNNTP(boolean supportsNNTP)
	{

		this.supportsNNTP = supportsNNTP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportsPOP3
	 *            the supportsPOP3 to set
	 */
	public void setSupportsPOP3(boolean supportsPOP3)
	{

		this.supportsPOP3 = supportsPOP3;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportsSMTP
	 *            the supportsSMTP to set
	 */
	public void setSupportsSMTP(boolean supportsSMTP)
	{

		this.supportsSMTP = supportsSMTP;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportsSSL
	 *            the supportsSSL to set
	 */
	public void setSupportsSSL(boolean supportsSSL)
	{

		this.supportsSSL = supportsSSL;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportsWebmail
	 *            the supportsWebmail to set
	 */
	public void setSupportsWebmail(boolean supportsWebmail)
	{

		this.supportsWebmail = supportsWebmail;
	}
}
