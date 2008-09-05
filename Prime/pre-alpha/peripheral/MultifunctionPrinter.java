package peripheral;
import java.io.Serializable;

import objects.ExternalHardware;

/**
 * This class represents a MultifunctionPrinter.
 * This device can be connected to any device that supports the connection type, USB and so on, of this device. 
 * It contains information on what kind of capability the MultifunctionPrinter machin has and what 
 * kind of connection interface is allows.
 * It also includes whether or not the machine has integrated a scanner and a fax.
 * <br><br>
 * <b>Notation</b>: The MultifunctionPrinter will be referred to as "MFP" in 
 * the remainder of this document.
 * <br><br>
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class MultifunctionPrinter extends ExternalHardware implements Serializable 
{

	// Whether or not the MFP has a printer
	private boolean hasPrinter;
	
	// The connection between the MFP and a printer class.
	private Printer printerConnection;
	
	// Whether or not the MFP has a scanner
	private boolean hasScanner;
	
	// The connection between the MFP and a scanner class.
	private Scanner scannerConnection;
	
	// Whether or not the MFP has a fax
	private boolean hasFax;
	
	// The connection between the MFP and a fax class.
	private Fax faxConnection;
	
	

	/**
	 * Constructor of the MFP class.
	 *
	 * @param Name The name of the MFP.
	 * @param Desc The description of the MFP.
	 * @param MFPConnectionInterfaces Connection interfaces supported by a MFP. A array of Strings.
	 */
	public MultifunctionPrinter(String Name, String Desc, String[] MFPConnectionInterfaces)
	{
		super(Name,Desc,MFPConnectionInterfaces);
		
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES

	/**
	 * Get info on if the MFP has a printer.
	 * 
	 * @return Returns a boolean stating whether or not the machine has a printer integrated.
	 */
	public boolean getHasPrinter()
	{
		return hasPrinter;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @return the printerConnection
	 */
	public Printer getPrinterConnection()
	{
	
		if(printerConnection!=null)
		{
			return printerConnection;
		}
		
		return null;
	}


	/**
	 * Get info on if the MFP has a scanner.
	 * 
	 * @return Returns a boolean stating whether or not the machine has a scanner integrated.
	 */
	public boolean getHasScanner()
	{
		return hasScanner;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @return the scannerConnection
	 */
	public Scanner getScannerConnection()
	{
	
		if(scannerConnection!=null)
		{
			return scannerConnection;
		}
		
		return null;
	}


	/**
	 * Get info on if the MFP has a fax.
	 * 
	 * @return Returns a boolean stating whether or not the machine has a fax integrated.
	 */
	public boolean getHasFax()
	{
		return hasFax;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @return the faxConnection
	 */
	public Fax getFaxConnection()
	{
	
		if(faxConnection!=null)
		{
			return faxConnection;
		}
		
		return null;
	}

	
	// SET METHODES

	/**
	 * Get info on whether or not the MFP has a printer.
	 * 
	 */
	public void setHasPrinter(boolean MFPhasPrinter)
	{
		hasPrinter = MFPhasPrinter;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @param printerConnection the printerConnection to set
	 */
	public void setPrinterConnection(Printer printerConnection)
	{
	
		this.printerConnection = printerConnection;
	}


	/**
	 * Get info on whether or not the MFP has a scanner.
	 * 
	 */
	public void setHasScanner(boolean MFPhasScanner)
	{
		hasScanner = MFPhasScanner;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @param scannerConnection the scannerConnection to set
	 */
	public void setScannerConnection(Scanner scannerConnection)
	{
	
		this.scannerConnection = scannerConnection;
	}


	/**
	 * Get info on whether or not the MFP has a fax.
	 * 
	 */
	public void setHasFax(boolean MFPhasFax)
	{
		hasFax = MFPhasFax;
	}
	

	
	/**
	 * Description NEEDED!
	 *
	 * @param faxConnection the faxConnection to set
	 */
	public void setFaxConnection(Fax faxConnection)
	{
	
		this.faxConnection = faxConnection;
	}
}