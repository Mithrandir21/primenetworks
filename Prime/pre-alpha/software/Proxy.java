package software;
import java.io.Serializable;
import objects.Software;

public class Proxy extends Software implements Serializable 
{
	/*
	 * Datafields for an abstract webserver
	 * These will contain the values of any webserver object
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;
	
	
	// Has caching feature
	private boolean caching;
	
	// Has Web proxy feature
	private boolean webProxy;
	
	// Has Anonymizing proxy feature
	private boolean anonymizingProxy;
	
	// Has transparent proxy feature
	private boolean transparentProxy;
	
	// Has reverse proxy feature
	private boolean reverseProxy;
	
	
	//DIFFERENT SUPPORTED FEATURES
	// Supports IP version 6
	private boolean supportsIPv6;
	
	// Supports SSL
	private boolean supportsSSL;
	
	// Supports TLS
	private boolean supportsTSL;
	
	// Supports HTTPS
	private boolean supportsHTTPS;
	

	
	public Proxy(String Name, String Desc, String Version)
	{
		super(Name,Desc,Version);
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
	 * @return the anonymizingProxy
	 */
	public boolean isAnonymizingProxy()
	{
	
		return anonymizingProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @return the caching
	 */
	public boolean isCaching()
	{
	
		return caching;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @return the reverseProxy
	 */
	public boolean isReverseProxy()
	{
	
		return reverseProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @return the supportsHTTPS
	 */
	public boolean isSupportsHTTPS()
	{
	
		return supportsHTTPS;
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
	 * @return the transparentProxy
	 */
	public boolean isTransparentProxy()
	{
	
		return transparentProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @return the webProxy
	 */
	public boolean isWebProxy()
	{
	
		return webProxy;
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
	 * @param anonymizingProxy the anonymizingProxy to set
	 */
	public void setAnonymizingProxy(boolean anonymizingProxy)
	{
	
		this.anonymizingProxy = anonymizingProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @param caching the caching to set
	 */
	public void setCaching(boolean caching)
	{
	
		this.caching = caching;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @param reverseProxy the reverseProxy to set
	 */
	public void setReverseProxy(boolean reverseProxy)
	{
	
		this.reverseProxy = reverseProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @param supportsHTTPS the supportsHTTPS to set
	 */
	public void setSupportsHTTPS(boolean supportsHTTPS)
	{
	
		this.supportsHTTPS = supportsHTTPS;
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



	
	/**
	 * Description NEEDED!
	 *
	 * @param transparentProxy the transparentProxy to set
	 */
	public void setTransparentProxy(boolean transparentProxy)
	{
	
		this.transparentProxy = transparentProxy;
	}



	
	/**
	 * Description NEEDED!
	 *
	 * @param webProxy the webProxy to set
	 */
	public void setWebProxy(boolean webProxy)
	{
	
		this.webProxy = webProxy;
	}
}