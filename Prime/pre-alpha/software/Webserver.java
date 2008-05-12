package software;
import java.io.Serializable;
import objects.Software;

public class Webserver extends Software implements Serializable 
{
	/*
	 * Datafields for an abstract webserver
	 * These will contain the values of any webserver object
	 */
	
	// Supported Operating systems
	private String[] supportedOperatingSystems;
	// TODO - Create function to add supported OSs
	
	
	// Supports Virtual hosting feature
	private boolean hasVirtualHosting;
	
	// Supports HTTP compression
	private boolean hasCompression;
	
	
	
	// DIFFERENT SECURITY FEATURES
	// Supports basic access authentication
	private boolean supportsBasic;
	
	// Supports digest access authentication
	private boolean supportsDigest;
	
	
	
	
	// SUPPORT FOR HTTPS
	// Supports SSL
	private boolean supportsSSL;
	
	// Supports TSL 
	private boolean supportsTSL;
	
	
	// DIFFERENT SUPPORTED FEATURES
	// Supports IPv6
	private boolean supportsIPv6;
	
	// Supports SSI
	private boolean supportsSSI;
	
	// Supports CGI
	private boolean supportsCGI;
	
	// Supports SCGI
	private boolean supportsSCGI;
	
	// Supports FastCGI
	private boolean supportsFastCGI;
	
	// Supports JSP
	private boolean supportsJSP;
	
	// Supports PHP
	private boolean supportsPHP;
	
	// Supports ASP
	private boolean supportsASP;
	
	// Supports ASP .net
	private boolean supportsASPnet;
	

	public Webserver(String Name, String Desc, String Version)
	{
		super(Name,Desc,Version);
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the hasCompression
	 */
	public boolean HasCompression()
	{
	
		return hasCompression;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the hasVirtualHosting
	 */
	public boolean HasVirtualHosting()
	{
	
		return hasVirtualHosting;
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
	 * @return the supportsASP
	 */
	public boolean isSupportsASP()
	{
	
		return supportsASP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsASPnet
	 */
	public boolean isSupportsASPnet()
	{
	
		return supportsASPnet;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsBasic
	 */
	public boolean isSupportsBasic()
	{
	
		return supportsBasic;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsCGI
	 */
	public boolean isSupportsCGI()
	{
	
		return supportsCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsDigest
	 */
	public boolean isSupportsDigest()
	{
	
		return supportsDigest;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsFastCGI
	 */
	public boolean isSupportsFastCGI()
	{
	
		return supportsFastCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsIPv6
	 */
	public boolean isSupportsIPv6()
	{
	
		return supportsIPv6;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsJSP
	 */
	public boolean isSupportsJSP()
	{
	
		return supportsJSP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsPHP
	 */
	public boolean isSupportsPHP()
	{
	
		return supportsPHP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsSCGI
	 */
	public boolean isSupportsSCGI()
	{
	
		return supportsSCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsSSI
	 */
	public boolean isSupportsSSI()
	{
	
		return supportsSSI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsSSL
	 */
	public boolean isSupportsSSL()
	{
	
		return supportsSSL;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsTSL
	 */
	public boolean isSupportsTSL()
	{
	
		return supportsTSL;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param hasCompression the hasCompression to set
	 */
	public void setHasCompression(boolean hasCompression)
	{
	
		this.hasCompression = hasCompression;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param hasVirtualHosting the hasVirtualHosting to set
	 */
	public void setHasVirtualHosting(boolean hasVirtualHosting)
	{
	
		this.hasVirtualHosting = hasVirtualHosting;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportedOperatingSystems the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
	
		this.supportedOperatingSystems = supportedOperatingSystems;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsASP the supportsASP to set
	 */
	public void setSupportsASP(boolean supportsASP)
	{
	
		this.supportsASP = supportsASP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsASPnet the supportsASPnet to set
	 */
	public void setSupportsASPnet(boolean supportsASPnet)
	{
	
		this.supportsASPnet = supportsASPnet;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsBasic the supportsBasic to set
	 */
	public void setSupportsBasic(boolean supportsBasic)
	{
	
		this.supportsBasic = supportsBasic;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsCGI the supportsCGI to set
	 */
	public void setSupportsCGI(boolean supportsCGI)
	{
	
		this.supportsCGI = supportsCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsDigest the supportsDigest to set
	 */
	public void setSupportsDigest(boolean supportsDigest)
	{
	
		this.supportsDigest = supportsDigest;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsFastCGI the supportsFastCGI to set
	 */
	public void setSupportsFastCGI(boolean supportsFastCGI)
	{
	
		this.supportsFastCGI = supportsFastCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsIPv6 the supportsIPv6 to set
	 */
	public void setSupportsIPv6(boolean supportsIPv6)
	{
	
		this.supportsIPv6 = supportsIPv6;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsJSP the supportsJSP to set
	 */
	public void setSupportsJSP(boolean supportsJSP)
	{
	
		this.supportsJSP = supportsJSP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsPHP the supportsPHP to set
	 */
	public void setSupportsPHP(boolean supportsPHP)
	{
	
		this.supportsPHP = supportsPHP;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsSCGI the supportsSCGI to set
	 */
	public void setSupportsSCGI(boolean supportsSCGI)
	{
	
		this.supportsSCGI = supportsSCGI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsSSI the supportsSSI to set
	 */
	public void setSupportsSSI(boolean supportsSSI)
	{
	
		this.supportsSSI = supportsSSI;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsSSL the supportsSSL to set
	 */
	public void setSupportsSSL(boolean supportsSSL)
	{
	
		this.supportsSSL = supportsSSL;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsTSL the supportsTSL to set
	 */
	public void setSupportsTSL(boolean supportsTSL)
	{
	
		this.supportsTSL = supportsTSL;
	}
}
