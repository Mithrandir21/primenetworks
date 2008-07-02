package hardware;
import java.io.Serializable;
import objects.Hardware;


/**
 * This class represents a motherboard of a {@link  objects.Servers  server} or 
 * {@link  objects.Clients  client} machine.  
 * It can be a server, a desktop or a laptop. It contains information on what kind 
 * of capability the motherboard has, what kind of ports it has, how many slots it has
 * for different cards, how many CPUs can be installed and so on.
 * <br><br>
 * <b>Notation</b>: The motherboard will be refered to as "MB" in the remainder of this document.
 * 
 * <br><br><br>
 * Changelog 0.2
 * <p>Changed HDD connections to DUC, Different Usage Connections, to better suet the
 * actual function of the port which is a port for both HDDs and CDROMs among other things.
 * </p>   
 *
 * @author Bahram Malaekeh
 * @version 0.2
 */
public class Motherboard extends Hardware implements Serializable 
{

	// The company that produces the Motherboard. Sony, Samsung and so on.
	private String producer;
	
	// The form of Motherboard. CDROM,DVDROM,DVDburner,Blu-Ray,HD-DVD and so on.
	private String form;
	
	// The subtype of the Motherboard type, Socket 775, Socket 939 and so on.
	private String socket;
	
	// The bus speed of the Motherboard. 1066 MHz, 800 MHz and so on.
	private int busSpeed;
	
	// The chipset on the MB
	private String chipset;
	
	// The number of CPU slots on the MB
	private int maxCPUs;
	
	// The number of PCI slots on the MB
	private int maxPCIs;
	
	// The number of RAM slots on the MB
	private int maxRAMs;
	
	// The number of USB ports on the actual MB(not add-ons)
	private int maxUSBs;
	
	// The number of DUC, Different Usage Connections(HDD,CDROM), ports on the MB
	private int maxDUCs;
	
	// The number of integrated LAN ports, if any, on the MB
	private int maxIntegLANs;
	
	// The type of graphical port on the MB
	private String graphicalPortType;
	
	// The type of DUC connection ports on the MB
	private String DUCconnectionType;
	
	// The type of RAM for the MB. DDR1, DDR2 and so on.
	private String RAMtype;
	
	// Boolean on whether or not the MB has an integrated Audio card
	private boolean integAudioCard;
	
	// Boolean on whether or not the MB has an integrated graphical card
	private boolean integGraphicalCard;
	
	// Boolean on whether or not the MB has an integrated LAN card
	private boolean integLANcard;
	
	/* 
	 * Boolean on whether or not a graphics card is set on the AGP or PCI-E port, depending on
	 * what kind of port is set on the graphicalPortType.
	 */ 
	private boolean graphicsCard;
	
	
	// TODO : Set up a number of connection to a network card object
	
	
	
	/**
	 * Constructor of a Motherboard hardware.
	 *
	 * @param Name The name of the MB.
	 * @param Desc The description of the MB.
	 * @param MBproducer The company that produces the Motherboard. Sony, Samsung and so on.
	 * @param MBform The form of Motherboard. CDROM,DVDROM,DVDburner,Blu-Ray,HD-DVD and so on.
	 * @param MBsocket The subtype of the Motherboard type, Socket 775, Socket 939 and so on.
	 * @param MBmaxCPUs The number of CPU slots on the MB.
	 * @param MBmaxPCIs The number of PCI slots on the MB.
	 * @param MBgraphicalPort The type of graphical port on the MB.
	 * @param MBmaxRAMs The number of RAM slots on the MB.
	 * @param MBDUCconnectionType The type of DUC connection ports on the MB.
	 * @param MBintegLANcard Boolean on whether or not the MB has an integrated LAN card.
	 * @param MBintegGraphicalCard Boolean on whether or not the MB has an integrated GPU card.
	 * @param MBintegAudioCard Boolean on whether or not the MB has an integrated Audio card.
	 */
	public Motherboard(String Name, String Desc, String MBproducer, String MBform,
			String MBsocket, int MBmaxCPUs, int MBmaxPCIs, String MBgraphicalPort, int MBmaxRAMs, 
			String MBDUCconnectionType, boolean MBintegLANcard, boolean MBintegGraphicalCard
			, boolean MBintegAudioCard)
	{
		super(Name,Desc);

		producer = MBproducer;
		form = MBform;
		socket = MBsocket;
		maxCPUs = MBmaxCPUs;
		maxPCIs = MBmaxPCIs;
		graphicalPortType = MBgraphicalPort;
		maxRAMs = MBmaxRAMs;
		DUCconnectionType = MBDUCconnectionType;
		integLANcard = MBintegLANcard;
		integGraphicalCard = MBintegGraphicalCard;
		integAudioCard = MBintegAudioCard;
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES

	/**
	 * Get producer of the Motherboard.
	 * 
	 */
	public String getProducer()
	{
		return producer;
	}
	

	/**
	 * Get the form of the Motherboard. ATX, m-ATX and so on.
	 * 
	 */
	public String getForm()
	{
		return form;
	}	
	

	/**
	 * Get the socket type of Motherboard. Socket 775, Socket 939 and so on.
	 * 
	 */
	public String getSocket()
	{
		return socket;
	}	
	

	/**
	 * Get the bus speed of the Motherboard. 1066 MHz, 800 MHz and so on.
	 * 
	 */
	public int getBusSpeed()
	{
		return busSpeed;
	}
	

	/**
	 * Get the chipset of the Motherboard..
	 * 
	 */
	public String getChipset()
	{
		return chipset;
	}
	

	/**
	 * Get the number of CPU slots on the MB.
	 * 
	 */
	public int getMaxCPUs()
	{
		return maxCPUs;
	}
	

	/**
	 * Get the number of PCI slots on the MB.
	 * 
	 */
	public int getMaxPCIs()
	{
		return maxPCIs;
	}
	

	/**
	 * Get the number of RAM slots on the MB.
	 * 
	 */
	public int getMaxRAMs()
	{
		return maxRAMs;
	}
	

	/**
	 * Get the number of USB slots on the MB
	 * 
	 */
	public int getMaxUSBs()
	{
		return maxUSBs;
	}	
	

	/**
	 * Get the number of DUC slots on the MB.
	 * 
	 */
	public int getMaxDUCs()
	{
		return maxDUCs;
	}
	

	/**
	 * Get the number of integrated LAN ports on the MB.
	 * 
	 */
	public int getMaxLANs()
	{
		return maxIntegLANs;
	}
	

	/**
	 * Get the type DUC connection on the MB. IDE, ATA, SATA and so on.
	 * 
	 */
	public String getDUCconnectionType()
	{
		return DUCconnectionType;
	}
	

	/**
	 * Get the graphical port type on the MB. AGP, PCI, PCI-Express and so on.
	 * 
	 */
	public String getGraphicalPort()
	{
		return graphicalPortType;
	}	
	

	/**
	 * Get the RAM type on the MB. DDR, DDR2 and so on.
	 * 
	 */
	public String getRAMtype()
	{
		return RAMtype;
	}
	

	/**
	 * Get the whether or not there is an integrated audio card on the MB.
	 * 
	 */
	public boolean getAudioCardIsIntegrated()
	{
		return integAudioCard;
	}
	

	/**
	 * Get the whether or not there is an integrated graphical card on the MB.
	 * 
	 */
	public boolean getGraphicalPortIsIntegrated()
	{
		return integGraphicalCard;
	}	
	

	/**
	 * Get the whether or not there is an integrated LAN card on the MB.
	 * 
	 */
	public boolean getLANcardIsIntegrated()
	{
		return integLANcard;
	}
	
	
	
	// SET METHODES

	
	/**
	 * Description NEEDED!
	 *
	 * @return the graphicsCard
	 */
	public boolean isGraphicsCard()
	{
	
		return graphicsCard;
	}


	/**
	 * Set method for producers of the MB.
	 * 
	 */
	public void setProducer(String MBProducer)
	{
		producer = MBProducer;
	}
	

	/**
	 * Set method for form of the MB.
	 * 
	 */
	public void setForm(String MBform)
	{
		form = MBform;
	}
	

	/**
	 * Set method for socket type of the MB.
	 * 
	 */
	public void setSocket(String MBsocket)
	{
		socket = MBsocket;
	}
	

	/**
	 * Set method for bus speed of the MB
	 * 
	 */
	public void setBusSpeed(int MBbusSpeed)
	{
		busSpeed = MBbusSpeed;
	}
	

	/**
	 * Set method for chipset of the MB.
	 * 
	 */
	public void setChipset(String MBchipset)
	{
		chipset = MBchipset;
	}
	

	/**
	 * Set method for number of CPU ports of the MB.
	 * 
	 */
	public void setMaxCPUs(int MBmaxCPUs)
	{
		maxCPUs = MBmaxCPUs;
	}
	

	/**
	 * Set method for number of PCI slots of the MB.
	 * 
	 */
	public void setMaxPCIs(int MBmaxPCIs)
	{
		maxPCIs = MBmaxPCIs;
	}
	

	/**
	 * Set method for number of RAM slots of the MB.
	 * 
	 */
	public void setMaxRAMs(int MBmaxRAMs)
	{
		maxRAMs = MBmaxRAMs;
	}
	

	/**
	 * Set method for number of DUC ports of the MB.
	 * 
	 */
	public void setMaxDUCs(int MBmaxDUCs)
	{
		maxDUCs = MBmaxDUCs;
	}
	

	/**
	 * Set method for number of USB slots of the MB.
	 * 
	 */
	public void setMaxUSBs(int MBmaxUSBs)
	{
		maxUSBs = MBmaxUSBs;
	}
	

	/**
	 * Set method for number of LAN ports of the MB.
	 * 
	 */
	public void setMaxIntegratedLANs(int MBmaxIntegLANs)
	{
		maxIntegLANs = MBmaxIntegLANs;
	}
	

	/**
	 * Set method for type of graphical port on the MB.
	 * 
	 */
	public void setGraphicalPort(String MBgraphicalPortType)
	{
		graphicalPortType = MBgraphicalPortType;
	}
	

	/**
	 * Set method for type of DUC port on the MB.
	 * 
	 */
	public void setDUCconnectionType(String MBDUCconnectionType)
	{
		DUCconnectionType = MBDUCconnectionType;
	}
	
	
	/**
	 * Set method for type of RAM port on the MB.
	 * 
	 */
	public void setRAMtype(String MBRAMtype)
	{
		RAMtype = MBRAMtype;
	}
	

	/**
	 * Set method for whether or not the is present a integrated audio card on the MB.
	 * 
	 */
	public void setIntegAudioCard(boolean MBhasIntegratedAudio)
	{
		integAudioCard = MBhasIntegratedAudio;
	}
	

	/**
	 * Set method for whether or not the is present a integrated graphical card on the MB.
	 * 
	 */
	public void setIntegGraphicalCard(boolean MBhasIntegratedGraphicalCard)
	{
		integGraphicalCard = MBhasIntegratedGraphicalCard;
	}
	

	/**
	 * Set method for whether or not the is present a integrated LAN card on the MB.
	 * 
	 */
	public void setIntegLANcard(boolean MBhasIntegratedLANcard)
	{
		integLANcard = MBhasIntegratedLANcard;
	}


	
	/**
	 * Description NEEDED!
	 *
	 * @param graphicsCard the graphicsCard to set
	 */
	public void setGraphicsCard(boolean graphicsCard)
	{
	
		this.graphicsCard = graphicsCard;
	}
}
