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
package widgetManipulation;


import java.io.Serializable;

import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.WirelessRouter;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import widgets.WorkareaCanvas;


/**
 * This class represents the rules that is to be applied to any given {@link WorkareaCanvas}.
 * The rules can contain such things as how many, if any, ports can be available on a computer, like USB or RJ-45.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkRules implements Serializable
{

	/**
	 * The serial number of the {@link WorkareaCanvas} this set of rules applies to.
	 */
	private double canvasSerial;



	/**
	 * HARDWARE RULES
	 */

	/**
	 * Whether or not the network allows USB ports on its computers.
	 */
	private boolean USBnotAllowed = false;


	/**
	 * A number representing the number of allowed USB ports.
	 * -1 means the numbers is not set.
	 */
	private int USBportsAllowed = -1;


	/**
	 * Whether or not the network allows LAN ports on its computers.
	 */
	private boolean LANnotAllowed = false;


	/**
	 * A number representing the number of allowed LAN ports.
	 * -1 means the numbers is not set.
	 */
	private int LANportsAllowed = -1;


	/**
	 * SOFTWARE RULES
	 */

	/**
	 * Whether or not the computers in a network are restricted to a specific {@link OperatingSystem}.
	 */
	private boolean OSrestriction = false;


	/**
	 * The name of the {@link OperatingSystem} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	private String OSrestrictedName = "";


	/**
	 * Whether or not the computers in a network are restricted to a specific {@link Antivirus}.
	 */
	private boolean AVrestriction = false;


	/**
	 * The name of the {@link Antivirus} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	private String AVrestrictedName = "";


	/**
	 * Whether or not the computers in a network are restricted to a specific {@link Firewall}.
	 */
	private boolean FWrestriction = false;


	/**
	 * The name of the {@link Firewall} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	private String FWrestrictedName = "";


	/**
	 * Whether or not the computers in a network are restricted to a specific {@link Email}.
	 */
	private boolean EMailRestriction = false;


	/**
	 * The name of the {@link Email} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	private String EMailRestrictedName = "";


	/**
	 * Whether or not the computers in a network are restricted to a specific {@link OfficeSuite}.
	 */
	private boolean OfficeSuiteRestriction = false;


	/**
	 * The name of the {@link OfficeSuite} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	private String OfficeSuiteRestrictedName = "";



	/**
	 * INFRASTRUCTURE RULES
	 */

	/**
	 * Whether the network can connect to the Internet, ie an {@link Internet} object.
	 */
	private boolean canConnectToInternet = true;


	/**
	 * Whether there must be a {@link Firewall} between the Internet, ie an {@link Internet} object, and the internal network.
	 */
	private boolean mustHaveFWbeforeInternet = false;


	/**
	 * Whether there must be a {@link Antivirus} between the Internet, ie an {@link Internet} object, and the internal network.
	 */
	private boolean mustHaveAVbeforeInternet = false;


	/**
	 * Whether or not the network can contain a {@link Hub}.
	 */
	private boolean canContainHub = true;


	/**
	 * Whether or not the network can contain a {@link WirelessRouter}.
	 */
	private boolean canContainWirelessRouter = true;




	/**
	 * A constructor for this class that sets the serial number of this class from the given {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} that contains the serial number set.
	 */
	public NetworkRules(WorkareaCanvas canvas)
	{
		canvasSerial = canvas.getSerial();
	}



	/**
	 * A constructor for this class that is used to make a standard rules object.
	 */
	public NetworkRules()
	{
		canvasSerial = -1;
	}


	// GETTERS


	/**
	 * Gets the serial number of the {@link WorkareaCanvas} this set of rules applies to.
	 */
	public double getCanvasSerial()
	{
		return canvasSerial;
	}




	/**
	 * Gets whether or not the network allows USB ports on its computers.
	 */
	public boolean isUSBnotAllowed()
	{
		return USBnotAllowed;
	}




	/**
	 * Gets the number representing the number of allowed USB ports.
	 * -1 means the numbers is not set.
	 */
	public int getUSBportsAllowed()
	{
		return USBportsAllowed;
	}




	/**
	 * Gets whether or not the network allows LAN ports on its computers.
	 */
	public boolean isLANnotAllowed()
	{
		return LANnotAllowed;
	}




	/**
	 * Gets the number representing the number of allowed LAN ports.
	 * -1 means the numbers is not set.
	 */
	public int getLANportsAllowed()
	{
		return LANportsAllowed;
	}




	/**
	 * Gets whether or not the computers in a network are restricted to a specific {@link OperatingSystem}.
	 */
	public boolean isOSrestriction()
	{
		return OSrestriction;
	}




	/**
	 * Gets the name of the {@link OperatingSystem} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public String getOSrestrictedName()
	{
		return OSrestrictedName;
	}




	/**
	 * Gets whether or not the computers in a network are restricted to a specific {@link Antivirus}.
	 */
	public boolean isAVrestriction()
	{
		return AVrestriction;
	}




	/**
	 * Gets the name of the {@link Antivirus} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public String getAVrestrictedName()
	{
		return AVrestrictedName;
	}




	/**
	 * Gets whether or not the computers in a network are restricted to a specific {@link Firewall}.
	 */
	public boolean isFWrestriction()
	{
		return FWrestriction;
	}




	/**
	 * Gets the name of the {@link Firewall} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public String getFWrestrictedName()
	{
		return FWrestrictedName;
	}




	/**
	 * Gets whether or not the computers in a network are restricted to a specific {@link Email}.
	 */
	public boolean isEMailRestriction()
	{
		return EMailRestriction;
	}




	/**
	 * Gets the name of the {@link Email} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public String getEMailRestrictedName()
	{
		return EMailRestrictedName;
	}




	/**
	 * Gets whether or not the computers in a network are restricted to a specific {@link OfficeSuite}.
	 */
	public boolean isOfficeSuiteRestriction()
	{
		return OfficeSuiteRestriction;
	}




	/**
	 * Gets the name of the {@link OfficeSuite} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public String getOfficeSuiteRestrictedName()
	{
		return OfficeSuiteRestrictedName;
	}




	/**
	 * Gets whether the network can connect to the Internet, ie can contain an {@link Internet} object.
	 */
	public boolean isCanConnectToInternet()
	{
		return canConnectToInternet;
	}




	/**
	 * Gets whether there must be a {@link Firewall} between the Internet, ie an {@link Internet} object, and the internal
	 * network.
	 */
	public boolean isMustHaveFWbeforeInternet()
	{
		return mustHaveFWbeforeInternet;
	}




	/**
	 * Gets whether there must be a {@link Antivirus} between the Internet, ie an {@link Internet} object, and the internal
	 * network.
	 */
	public boolean isMustHaveAVbeforeInternet()
	{
		return mustHaveAVbeforeInternet;
	}




	/**
	 * Gets whether or not the network can contain a {@link Hub}.
	 */
	public boolean isCanContainHub()
	{
		return canContainHub;
	}




	/**
	 * Gets whether or not the network can contain a {@link WirelessRouter}.
	 */
	public boolean isCanContainWirelessRouter()
	{
		return canContainWirelessRouter;
	}



	// SETTERS


	/**
	 * Sets whether or not the network allows USB ports on its computers.
	 */
	public void setUSBnotAllowed(boolean uSBallowed)
	{
		USBnotAllowed = uSBallowed;
	}




	/**
	 * A number representing the number of allowed USB ports.
	 * -1 means the numbers is not set.
	 */
	public void setUSBportsAllowed(int uSBportsAllowed)
	{
		USBportsAllowed = uSBportsAllowed;
	}




	/**
	 * Sets whether or not the network allows LAN ports on its computers.
	 */
	public void setLANnotAllowed(boolean lANallowed)
	{
		LANnotAllowed = lANallowed;
	}




	/**
	 * A number representing the number of allowed LAN ports.
	 * -1 means the numbers is not set.
	 */
	public void setLANportsAllowed(int lANportsAllowed)
	{
		LANportsAllowed = lANportsAllowed;
	}




	/**
	 * Sets whether or not the computers in a network are restricted to a specific {@link OperatingSystem}.
	 */
	public void setOSrestriction(boolean oSrestriction)
	{
		OSrestriction = oSrestriction;
	}




	/**
	 * Sets the name of the {@link OperatingSystem} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public void setOSrestrictedName(String oSrestrictedName)
	{
		OSrestrictedName = oSrestrictedName;
	}




	/**
	 * Sets whether or not the computers in a network are restricted to a specific {@link Antivirus}.
	 */
	public void setAVrestriction(boolean aVrestriction)
	{
		AVrestriction = aVrestriction;
	}




	/**
	 * Sets the name of the {@link Antivirus} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public void setAVrestrictedName(String aVrestrictedName)
	{
		AVrestrictedName = aVrestrictedName;
	}




	/**
	 * Sets whether or not the computers in a network are restricted to a specific {@link Firewall}.
	 */
	public void setFWrestriction(boolean fWrestriction)
	{
		FWrestriction = fWrestriction;
	}




	/**
	 * Sets the name of the {@link Firewall} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public void setFWrestrictedName(String fWrestrictedName)
	{
		FWrestrictedName = fWrestrictedName;
	}




	/**
	 * Sets whether or not the computers in a network are restricted to a specific {@link Email}.
	 */
	public void setEMailRestriction(boolean eMailRestriction)
	{
		EMailRestriction = eMailRestriction;
	}




	/**
	 * Sets the name of the {@link Email} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public void setEMailRestrictedName(String eMailRestrictedName)
	{
		EMailRestrictedName = eMailRestrictedName;
	}




	/**
	 * Sets whether or not the computers in a network are restricted to a specific {@link OfficeSuite}.
	 */
	public void setOfficeSuiteRestriction(boolean officeSuiteRestriction)
	{
		OfficeSuiteRestriction = officeSuiteRestriction;
	}




	/**
	 * Sets the name of the {@link OfficeSuite} the network is restricted to.
	 * An empty string("") means no name is set.
	 */
	public void setOfficeSuiteRestrictedName(String officeSuiteRestrictedName)
	{
		if ( officeSuiteRestrictedName != null )
		{
			OfficeSuiteRestrictedName = officeSuiteRestrictedName;
		}
	}




	/**
	 * Sets whether the network can connect to the Internet, ie an {@link Internet} object.
	 */
	public void setCanConnectToInternet(boolean canConnectToInternet)
	{
		this.canConnectToInternet = canConnectToInternet;
	}




	/**
	 * Sets whether there must be a {@link Firewall} between the Internet, ie an {@link Internet} object, and the internal
	 * network.
	 */
	public void setMustHaveFWbeforeInternet(boolean mustHaveFWbeforeInternet)
	{
		this.mustHaveFWbeforeInternet = mustHaveFWbeforeInternet;
	}




	/**
	 * Sets whether there must be a {@link Antivirus} between the Internet, ie an {@link Internet} object, and the internal
	 * network.
	 */
	public void setMustHaveAVbeforeInternet(boolean mustHaveAVbeforeInternet)
	{
		this.mustHaveAVbeforeInternet = mustHaveAVbeforeInternet;
	}




	/**
	 * Sets whether or not the network can contain a {@link Hub}.
	 */
	public void setCanContainHub(boolean canContainHub)
	{
		this.canContainHub = canContainHub;
	}




	/**
	 * Sets whether or not the network can contain a {@link WirelessRouter}.
	 */
	public void setCanContainWirelessRouter(boolean canContainWirelessRouter)
	{
		this.canContainWirelessRouter = canContainWirelessRouter;
	}

}
