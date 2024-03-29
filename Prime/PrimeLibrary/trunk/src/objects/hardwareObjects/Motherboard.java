/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects.hardwareObjects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import objects.Hardware;
import connections.ConnectionUtils;


/**
 * This class represents a motherboard of a {@link objects.Servers server} or
 * {@link objects.Clients client} machine. It can be a
 * server, a desktop or a
 * laptop. It contains information on what kind of capability the motherboard
 * has, what kind of ports it has, how many slots it has for different cards,
 * how many CPUs can be installed and so on. <br>
 * <br>
 * <b>Notation</b>: The motherboard will be refereed to as "MB" in the remainder
 * of this document. <br>
 * <br>
 * <br>
 * Changelog 0.2
 * <p>
 * Changed HDD connections to DUC, Different Usage Connections, to better suit
 * the actual function of the port which is a port for both HDDs and CDROMs
 * among other things.
 * </p>
 * <br>
 * <br>
 * Changelog 0.3
 * <p>
 * Added Coax port.
 * </p>
 * <br>
 * <br>
 * Changelog 0.4
 * <p>
 * Added Fiber port.
 * </p>
 * 
 * @author Bahram Malaekeh
 * @version 0.3
 */
public class Motherboard extends Hardware implements Serializable
{

	// The company that produces the Motherboard. Asus, MSI and so on.
	private String producer;

	// The form of Motherboard. ATX, mini-ATX(mATX), E-ATX(eATX) and so on.
	private String form;

	// The subtype of the Motherboard type, Socket 775, Socket 939 and so on.
	private String socket;

	// The bus speed of the Motherboard. 1066 MHz, 800 MHz and so on.
	private int busSpeed;

	// The chipset on the MB
	private String chipset;

	// The number of CPU slots on the MB
	private int maxCPUs = 0;

	// The number of PCI slots on the MB
	private int maxPCIs = 0;

	// The number of RAM slots on the MB
	private int maxRAMs = 0;

	// The number of USB ports on the actual MB(not add-ons)
	private int maxUSBs = 0;

	// The number of DUC, Different Usage Connections(HDD,CDROM), ports on the
	// MB
	private int maxDUCs = 0;

	// The number of integrated LAN ports, if any, on the MB
	private int maxIntegLANs = 0;

	// The number of a coax ports.
	private int maxCoaxs = 0;

	// The number of a fiber ports.
	private int maxFiber = 0;

	// The type of graphical port on the MB
	private String graphicalPortType;

	// The type of DUC connection ports on the MB
	private String DUCconnectionType;

	// The type of RAM for the MB. DDR1, DDR2 and so on.
	private String RAMtype;

	// Boolean on whether or not the MB has an integrated Audio card
	private boolean audioCardIntegrated;

	// Boolean on whether or not the MB has an integrated graphical card
	private boolean graphicalCardIntegrated;

	// Boolean on whether or not the MB has an integrated LAN card
	private boolean LANcardIntegrated;

	/*
	 * Boolean on whether or not a graphics card is set on the AGP or PCI-E
	 * port, depending on what kind of port is set on the graphicalPortType.
	 */
	private boolean isGraphicsCardInstalled;


	// The number of available ports.
	private int CPUPortsAvailable;

	private int PCIPortsAvailable;

	private int RAMPortsAvailable;

	private int USBPortsAvailable;

	private int DUCPortsAvailable;

	private int IntegLANPortsAvailable;

	private int COAXPortsAvailable;

	private int FiberPortsAvailable;



	/**
	 * The arrayList containing the intergrated Internal network cards.
	 */
	private ArrayList<InternalNetworksCard> intNICs = new ArrayList<InternalNetworksCard>();

	// FIXME : Set up a number of connection to a network card object



	/**
	 * Constructor of a Motherboard hardware.
	 * 
	 * @param Name
	 *            The name of the MB.
	 * @param Desc
	 *            The description of the MB.
	 * @param MBproducer
	 *            The company that produces the Motherboard.
	 * @param MBform
	 *            The form of Motherboard.
	 * @param MBsocket
	 *            The subtype of the Motherboard type, Socket 775, Socket 939
	 *            and so on.
	 * @param MBramType
	 *            The ram type of the motherboard.
	 * @param MBmaxCPUs
	 *            The number of CPU slots on the MB.
	 * @param MBmaxPCIs
	 *            The number of PCI slots on the MB.
	 * @param MBmaxRAMs
	 *            The number of RAM slots on the MB.
	 * @param MBmaxUSBs
	 *            The number of USB slots on the MB.
	 * @param MBmaxDUCs
	 *            The number of DUC slots on the MB.
	 * @param MBmaxCOAXs
	 *            The number of COAX slots on the MB.
	 * @param MBmaxFibers
	 *            The number of Fiber slots on the MB.
	 * @param MBDUCconnectionType
	 *            The type of DUC connection ports on the MB.
	 * @param MBgraphicalPort
	 *            The type of graphical port on the MB.
	 * @param MBintegLANcard
	 *            Boolean on whether or not the MB has an integrated LAN card.
	 * @param MBintegGraphicalCard
	 *            Boolean on whether or not the MB has an integrated GPU card.
	 * @param MBintegAudioCard
	 *            Boolean on whether or not the MB has an integrated Audio card.
	 * @param MBmaxIntegLanPorts
	 *            The number of integrated LAN ports on the MB.
	 */
	public Motherboard(String Name, String Desc, String MBproducer,
			String MBform, String MBsocket, String MBramType, int MBmaxCPUs,
			int MBmaxPCIs, int MBmaxRAMs, int MBmaxUSBs, int MBmaxDUCs,
			int MBmaxCOAXs, int MBmaxFibers, String MBDUCconnectionType,
			String MBgraphicalPort, boolean MBintegLANcard,
			boolean MBintegGraphicalCard, boolean MBintegAudioCard,
			int MBmaxIntegLanPorts)
	{
		super(Name, Desc);

		producer = MBproducer;
		form = MBform;
		socket = MBsocket;
		RAMtype = MBramType;
		maxCPUs = MBmaxCPUs;
		maxPCIs = MBmaxPCIs;
		maxRAMs = MBmaxRAMs;
		maxUSBs = MBmaxUSBs;
		maxDUCs = MBmaxDUCs;
		maxIntegLANs = MBmaxIntegLanPorts;
		maxCoaxs = MBmaxCOAXs;
		maxFiber = MBmaxFibers;
		graphicalPortType = MBgraphicalPort;
		DUCconnectionType = MBDUCconnectionType;
		LANcardIntegrated = MBintegLANcard;
		graphicalCardIntegrated = MBintegGraphicalCard;
		audioCardIntegrated = MBintegAudioCard;

		CPUPortsAvailable = MBmaxCPUs;
		PCIPortsAvailable = MBmaxPCIs;
		RAMPortsAvailable = MBmaxRAMs;
		USBPortsAvailable = MBmaxUSBs;
		DUCPortsAvailable = MBmaxDUCs;
		IntegLANPortsAvailable = MBmaxIntegLanPorts;
		COAXPortsAvailable = MBmaxCOAXs;
		FiberPortsAvailable = MBmaxFibers;


		for ( int i = 0; i < MBmaxIntegLanPorts; i++ )
		{
			intNICs.add(constructIntNIC());
		}
	}



	/**
	 * Constructor of a Motherboard hardware.
	 * 
	 * @param Name
	 *            The name of the MB.
	 * @param Desc
	 */
	public Motherboard(String Name, String Desc)
	{
		super(Name, Desc);

		reset();
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the Motherboard.
	 */
	public String getProducer()
	{
		return producer;
	}


	/**
	 * Get the form of the Motherboard. ATX, m-ATX and so on.
	 */
	public String getForm()
	{
		return form;
	}


	/**
	 * Get the socket type of Motherboard. Socket 775, Socket 939 and so on.
	 */
	public String getSocket()
	{
		return socket;
	}


	/**
	 * Get the bus speed of the Motherboard. 1066 MHz, 800 MHz and so on.
	 */
	public int getBusSpeed()
	{
		return busSpeed;
	}


	/**
	 * Get the chipset of the Motherboard.
	 */
	public String getChipset()
	{
		return chipset;
	}


	/**
	 * Get the number of CPU slots on the motherboard.
	 */
	public int getMaxCPUs()
	{
		return maxCPUs;
	}


	/**
	 * Get the number of PCI slots on the motherboard.
	 */
	public int getMaxPCIs()
	{
		return maxPCIs;
	}


	/**
	 * Get the number of RAM slots on the motherboard.
	 */
	public int getMaxRAMs()
	{
		return maxRAMs;
	}


	/**
	 * Get the number of USB slots on the motherboard.
	 */
	public int getMaxUSBs()
	{
		return maxUSBs;
	}


	/**
	 * Get the number of DUC slots on the motherboard.
	 */
	public int getMaxDUCs()
	{
		return maxDUCs;
	}


	/**
	 * Gets the number of integrated LAN ports.
	 * 
	 * @return the maxIntegLANs
	 */
	public int getMaxIntegLANs()
	{
		return maxIntegLANs;
	}


	/**
	 * Gets the number of integrated LAN ports.
	 * 
	 * @return the maxIntegLANs
	 */
	public int getInstalledIntegLANs()
	{
		return intNICs.size();
	}


	/**
	 * Gets the number of COAX ports.
	 */
	public int getMaxCoaxs()
	{
		return maxCoaxs;
	}


	/**
	 * Gets the number of Fiber ports.
	 */
	public int getMaxFibers()
	{
		return maxFiber;
	}


	/**
	 * Get the type DUC connection on the motherboard. IDE, ATA, SATA and so on.
	 */
	public String getDUCconnectionType()
	{
		return DUCconnectionType;
	}


	/**
	 * Get the graphical port type on the motherboard. AGP, PCI, PCI-Express and
	 * so on.
	 */
	public String getGraphicalPort()
	{
		return graphicalPortType;
	}


	/**
	 * Get the RAM type on the motherboard. DDR, DDR2 and so on.
	 */
	public String getRAMtype()
	{
		return RAMtype;
	}


	/**
	 * Get the whether or not there is an integrated audio card on the
	 * motherboard.
	 */
	public boolean audioCardIsIntegrated()
	{
		return audioCardIntegrated;
	}


	/**
	 * Get the whether or not there is an integrated graphical card on the
	 * motherboard.
	 */
	public boolean graphicalCardIsIntegrated()
	{
		return graphicalCardIntegrated;
	}


	/**
	 * Get a boolean on whether or not there is an integrated LAN card on the
	 * motherboard.
	 * FIXME - Consider removing this function
	 */
	public boolean LANcardIsIntegrated()
	{
		return LANcardIntegrated;
	}


	/**
	 * Get a boolean on whether or not there is an integrated Audio card on the
	 * motherboard.
	 * 
	 * @return the integAudioCard
	 */
	public boolean isIntegAudioCard()
	{
		return audioCardIntegrated;
	}


	/**
	 * Get a boolean on whether or not there is an integrated Graphical card on
	 * the motherboard.
	 * 
	 * @return the integGraphicalCard
	 */
	public boolean isIntegGraphicalCard()
	{
		return graphicalCardIntegrated;
	}


	/**
	 * Get a boolean on whether or not there is an integrated LAN card on the
	 * motherboard.
	 * 
	 * @return the integLANcard
	 */
	public boolean isIntegLANcard()
	{
		return LANcardIntegrated;
	}


	/**
	 * Get a boolean on whether or not there is an integrated LAN card on the
	 * motherboard.
	 * 
	 * @return the integLANcard
	 */
	public boolean isIntegLANcardInstalled()
	{
		return intNICs.size() > 0;
	}


	/**
	 * Gets the number of CPU ports that are available.
	 * 
	 * @return the cPUPortsAvailable
	 */
	public int getCPUPortsAvailable()
	{
		return CPUPortsAvailable;
	}


	/**
	 * Gets the number of PCI ports that are available.
	 * 
	 * @return the pCIPortsAvailable
	 */
	public int getPCIPortsAvailable()
	{
		return PCIPortsAvailable;
	}


	/**
	 * Gets the number of RAM ports that are available.
	 * 
	 * @return the rAMPortsAvailable
	 */
	public int getRAMPortsAvailable()
	{
		return RAMPortsAvailable;
	}


	/**
	 * Gets the number of USB ports that are available.
	 * 
	 * @return the uSBPortsAvailable
	 */
	public int getUSBPortsAvailable()
	{
		return USBPortsAvailable;
	}


	/**
	 * Gets the number of DUC ports that are available.
	 * 
	 * @return the dUCPortsAvailable
	 */
	public int getDUCPortsAvailable()
	{
		return DUCPortsAvailable;
	}


	/**
	 * Gets the number of LAN ports that are available.
	 * 
	 * @return the integLANPortsAvailable
	 */
	public int getIntegLANPortsAvailable()
	{
		return IntegLANPortsAvailable;
	}


	/**
	 * Gets the number of LAN ports that are available on installed NIC
	 * (none-MB).
	 * 
	 * @return the integLANPortsAvailable
	 */
	public int getInstalledIntegLANPortsAvailable()
	{
		int availablePorts = IntegLANPortsAvailable;

		for ( Iterator<InternalNetworksCard> i = intNICs.iterator(); i
				.hasNext(); )
		{
			InternalNetworksCard nic = (InternalNetworksCard) i.next();
			if ( nic.getConnectedObject() == null )
			{
				availablePorts--;
			}
		}

		return availablePorts;
	}


	/**
	 * Gets the number of Coax ports that are available.
	 */
	public int getCoaxPortsAvailable()
	{
		return COAXPortsAvailable;
	}


	/**
	 * Gets the number of Fiber ports that are available.
	 */
	public int getFiberPortsAvailable()
	{
		return FiberPortsAvailable;
	}


	/**
	 * Get a boolean on whether or not there is a Graphical Card installed on
	 * the motherboard.
	 * 
	 * @return the graphicsCard
	 */
	public boolean isGraphicsCardInstalled()
	{

		return isGraphicsCardInstalled;
	}






	// SET METHODES

	/**
	 * Set method for producers of the motherboard.
	 */
	public void setProducer(String MBProducer)
	{
		producer = MBProducer;
	}


	/**
	 * Set method for form of the motherboard.
	 */
	public void setForm(String MBform)
	{
		form = MBform;
	}


	/**
	 * Set method for socket type of the motherboard.
	 */
	public void setSocket(String MBsocket)
	{
		socket = MBsocket;
	}


	/**
	 * Set method for bus speed of the motherboard.
	 */
	public void setBusSpeed(int MBbusSpeed)
	{
		busSpeed = MBbusSpeed;
	}


	/**
	 * Set method for chipset of the motherboard.
	 */
	public void setChipset(String MBchipset)
	{
		chipset = MBchipset;
	}


	/**
	 * Set method for number of CPU ports of the motherboard.
	 */
	public void setMaxCPUs(int MBmaxCPUs)
	{
		maxCPUs = MBmaxCPUs;
	}


	/**
	 * Set method for number of PCI slots of the motherboard.
	 */
	public void setMaxPCIs(int MBmaxPCIs)
	{
		maxPCIs = MBmaxPCIs;
	}


	/**
	 * Set method for number of RAM slots of the motherboard.
	 */
	public void setMaxRAMs(int MBmaxRAMs)
	{
		maxRAMs = MBmaxRAMs;
	}


	/**
	 * Set method for number of DUC ports of the motherboard.
	 */
	public void setMaxDUCs(int MBmaxDUCs)
	{
		maxDUCs = MBmaxDUCs;
	}


	/**
	 * Set method for number of USB slots of the motherboard.
	 */
	public void setMaxUSBs(int MBmaxUSBs)
	{
		maxUSBs = MBmaxUSBs;
	}


	/**
	 * Set method for number of LAN ports of the motherboard.
	 */
	public void setMaxIntegratedLANs(int MBmaxIntegLANs)
	{
		maxIntegLANs = MBmaxIntegLANs;
	}


	/**
	 * Set method for number of Coax ports of the motherboard.
	 */
	public void setMaxCoaxs(int MBmaxCoaxs)
	{
		maxCoaxs = MBmaxCoaxs;
	}


	/**
	 * Set method for number of Fiber ports of the motherboard.
	 */
	public void setMaxFibers(int MBmaxFibers)
	{
		maxFiber = MBmaxFibers;
	}


	/**
	 * Set method for type of DUC port on the motherboard.
	 */
	public void setDUCconnectionType(String MBDUCconnectionType)
	{
		DUCconnectionType = MBDUCconnectionType;
	}


	/**
	 * Set method for type of RAM port on the motherboard.
	 */
	public void setRAMtype(String MBRAMtype)
	{
		RAMtype = MBRAMtype;
	}


	/**
	 * Set method for whether or not the is present a integrated audio card on
	 * the motherboard.
	 */
	public void setIntegAudioCard(boolean MBhasIntegratedAudio)
	{
		audioCardIntegrated = MBhasIntegratedAudio;
	}


	/**
	 * Set method for whether or not the is present a integrated graphical card
	 * on the v.
	 */
	public void setIntegGraphicalCard(boolean MBhasIntegratedGraphicalCard)
	{
		graphicalCardIntegrated = MBhasIntegratedGraphicalCard;
	}


	/**
	 * Set method for whether or not the is present a integrated LAN card on the
	 * motherboard.
	 */
	public void setIntegLANcard(boolean MBhasIntegratedLANcard)
	{
		LANcardIntegrated = MBhasIntegratedLANcard;
	}



	/**
	 * Set method for whether or not the is present a graphical card on the
	 * motherboard.
	 * 
	 * @param graphicsCard
	 *            the graphicsCard to set
	 */
	public void setGraphicsCardInstalled(boolean graphicsCard)
	{

		this.isGraphicsCardInstalled = graphicsCard;
	}


	/**
	 * Sets the graphical port type on the motherboard.
	 * 
	 * @param graphicalPortType
	 *            the graphicalPortType to set
	 */
	public void setGraphicalPortType(String graphicalPortType)
	{
		this.graphicalPortType = graphicalPortType;
	}


	/**
	 * Sets the number CPU ports available.
	 * 
	 * @param portsAvailable
	 *            the cPUPortsAvailable to set
	 */
	public void setCPUPortsAvailable(int portsAvailable)
	{
		CPUPortsAvailable = portsAvailable;
	}



	/**
	 * Makes one CPU port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOneCPUportAvailable()
	{
		CPUPortsAvailable++;
	}



	/**
	 * Makes one CPU port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneCPUportTaken()
	{
		CPUPortsAvailable--;
	}


	/**
	 * Sets the number PCI ports available.
	 * 
	 * @param portsAvailable
	 *            the pCIPortsAvailable to set
	 */
	public void setPCIPortsAvailable(int portsAvailable)
	{
		PCIPortsAvailable = portsAvailable;
	}



	/**
	 * Makes one PCI port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOnePCIportAvailable()
	{
		PCIPortsAvailable++;
	}



	/**
	 * Makes one PCI port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOnePCIportTaken()
	{
		PCIPortsAvailable--;
	}


	/**
	 * Sets the number RAM ports available.
	 * 
	 * @param portsAvailable
	 *            the rAMPortsAvailable to set
	 */
	public void setRAMPortsAvailable(int portsAvailable)
	{
		RAMPortsAvailable = portsAvailable;
	}



	/**
	 * Makes one RAM port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOneRAMportAvailable()
	{
		RAMPortsAvailable++;
	}



	/**
	 * Makes one RAM port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneRAMportTaken()
	{
		RAMPortsAvailable--;
	}

	/**
	 * Sets the number USB ports available.
	 * 
	 * @param portsAvailable
	 *            the uSBPortsAvailable to set
	 */
	public void setUSBPortsAvailable(int portsAvailable)
	{
		USBPortsAvailable = portsAvailable;
	}



	/**
	 * Makes one USB port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOneUSBportAvailable()
	{
		USBPortsAvailable++;
	}



	/**
	 * Makes one USB port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneUSBportTaken()
	{
		USBPortsAvailable--;
	}

	/**
	 * Sets the number DUC ports available.
	 * 
	 * @param portsAvailable
	 *            the dUCPortsAvailable to set
	 */
	public void setDUCPortsAvailable(int portsAvailable)
	{
		DUCPortsAvailable = portsAvailable;
	}



	/**
	 * Makes one DUC port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOneDUCportAvailable()
	{
		DUCPortsAvailable++;
	}



	/**
	 * Makes one DUC port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneDUCportTaken()
	{
		DUCPortsAvailable--;
	}


	/**
	 * Sets the number LAN ports available.
	 * 
	 * @param integLANPortsAvailable
	 *            the integLANPortsAvailable to set
	 */
	public void setIntegLANPortsAvailable(int integLANPortsAvailable)
	{
		IntegLANPortsAvailable = integLANPortsAvailable;
	}



	/**
	 * Makes one LAN port available by add to the integer that keep track of how
	 * make port are available.
	 */
	public void makeOneIntLANportAvailable()
	{
		IntegLANPortsAvailable++;
	}



	/**
	 * Makes one LAN port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneIntLANportTaken()
	{
		IntegLANPortsAvailable--;
	}


	/**
	 * Sets the number Coax ports available.
	 * 
	 * @param CoaxPortsAvailable
	 *            the CoaxPortsAvailable to set
	 */
	public void setCoaxPortsAvailable(int CoaxPortsAvailable)
	{
		COAXPortsAvailable = CoaxPortsAvailable;
	}



	/**
	 * Makes one Coax port available by add to the integer that keep track of
	 * how make port are available.
	 */
	public void makeOneCoaxPortAvailable()
	{
		COAXPortsAvailable++;
	}



	/**
	 * Makes one Coax port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneCoaxPortTaken()
	{
		COAXPortsAvailable--;
	}


	/**
	 * Sets the number Fiber ports available.
	 * 
	 * @param FiberPortsAvailable
	 *            the CoaxPortsAvailable to set
	 */
	public void setFiberPortsAvailable(int FiberPortsAvailable)
	{
		this.FiberPortsAvailable = FiberPortsAvailable;
	}



	/**
	 * Makes one Fiber port available by add to the integer that keep track of
	 * how make port are available.
	 */
	public void makeOneFiberPortAvailable()
	{
		FiberPortsAvailable++;
	}



	/**
	 * Makes one Fiber port unavailable by removing from the integer that keep
	 * track of how make port are available.
	 */
	public void makeOneFiberPortTaken()
	{
		FiberPortsAvailable--;
	}



	// CLASS METHODES

	/**
	 * Resets all the fields for this Motherboard. All {@link String} fields
	 * will be set to "", all {@link Integer} will be set to 0 and all
	 * {@link Boolean Booleans} will be set to false.
	 */
	private void reset()
	{
		producer = "";
		form = "";
		socket = "";
		maxCPUs = 0;
		maxPCIs = 0;
		maxRAMs = 0;
		maxUSBs = 0;
		maxDUCs = 0;
		maxCoaxs = 0;
		maxFiber = 0;
		graphicalPortType = "";
		DUCconnectionType = "";
		LANcardIntegrated = false;
		graphicalCardIntegrated = false;
		audioCardIntegrated = false;

		CPUPortsAvailable = 0;
		PCIPortsAvailable = 0;
		RAMPortsAvailable = 0;
		USBPortsAvailable = 0;
		DUCPortsAvailable = 0;
		IntegLANPortsAvailable = 0;
		COAXPortsAvailable = 0;
		FiberPortsAvailable = 0;
	}


	/**
	 * Resets all the ports so that all ports are available. Sets the port
	 * availability to the max port.
	 */
	public void resetAllComponents()
	{
		CPUPortsAvailable = maxCPUs;
		PCIPortsAvailable = maxPCIs;
		RAMPortsAvailable = maxRAMs;
		USBPortsAvailable = maxUSBs;
		DUCPortsAvailable = maxDUCs;
		IntegLANPortsAvailable = maxIntegLANs;
		COAXPortsAvailable = maxCoaxs;
		FiberPortsAvailable = maxFiber;
	}




	/**
	 * This method returns the first available index in an array of booleans
	 * that is set to false. If none are found, it will return -1.
	 */
	public int firstAvailableIndex(boolean[] array)
	{
		for ( int i = 0; i < array.length; i++ )
		{
			if ( !(array[i]) )
			{
				return i;
			}
		}
		return -1;
	}


	/**
	 * Creates a standard intergrated NIC.
	 */
	private InternalNetworksCard constructIntNIC()
	{
		String count = "";
		int intNICcount = intNICs.size();

		if ( intNICcount < 10 )
		{
			count = "0" + intNICcount;
		}
		else
		{
			count = "" + intNICcount;
		}

		String mac = "00:00:00:00:00:" + count;

		return new InternalNetworksCard("Int NIC", "Int NIC", "",
				ConnectionUtils.Integrated, mac, ConnectionUtils.RJ45);
	}
}
