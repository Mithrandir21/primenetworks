/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package objects.softwareObjects;


import java.io.Serializable;

import objects.Software;


public class Proxy extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any webserver object
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


	// DIFFERENT SUPPORTED FEATURES
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
	 * @return the anonymizingProxy
	 */
	public boolean hasAnonymizingProxy()
	{

		return anonymizingProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the caching
	 */
	public boolean hasCaching()
	{

		return caching;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the reverseProxy
	 */
	public boolean hasReverseProxy()
	{

		return reverseProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsHTTPS
	 */
	public boolean supportsHTTPS()
	{

		return supportsHTTPS;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsIPv6
	 */
	public boolean supportsIPv6()
	{

		return supportsIPv6;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsSSL
	 */
	public boolean supportsSSL()
	{

		return supportsSSL;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the supportsTSL
	 */
	public boolean supportsTSL()
	{

		return supportsTSL;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the transparentProxy
	 */
	public boolean hasTransparentProxy()
	{

		return transparentProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @return the webProxy
	 */
	public boolean hasWebProxy()
	{

		return webProxy;
	}



	// SETTERS

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
	 * @param anonymizingProxy
	 *            the anonymizingProxy to set
	 */
	public void setSupportsAnonymizingProxy(boolean anonymizingProxy)
	{

		this.anonymizingProxy = anonymizingProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param caching
	 *            the caching to set
	 */
	public void setSupportsCaching(boolean caching)
	{

		this.caching = caching;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param reverseProxy
	 *            the reverseProxy to set
	 */
	public void setSupportsReverseProxy(boolean reverseProxy)
	{

		this.reverseProxy = reverseProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param supportsHTTPS
	 *            the supportsHTTPS to set
	 */
	public void setSupportsHTTPS(boolean supportsHTTPS)
	{

		this.supportsHTTPS = supportsHTTPS;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param supportsIPv6
	 *            the supportsIPv6 to set
	 */
	public void setSupportsIPv6(boolean supportsIPv6)
	{

		this.supportsIPv6 = supportsIPv6;
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
	 * @param supportsTSL
	 *            the supportsTSL to set
	 */
	public void setSupportsTSL(boolean supportsTSL)
	{

		this.supportsTSL = supportsTSL;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param transparentProxy
	 *            the transparentProxy to set
	 */
	public void setSupportsTransparentProxy(boolean transparentProxy)
	{

		this.transparentProxy = transparentProxy;
	}




	/**
	 * Description NEEDED!
	 * 
	 * @param webProxy
	 *            the webProxy to set
	 */
	public void setSupportsWebProxy(boolean webProxy)
	{

		this.webProxy = webProxy;
	}
}
