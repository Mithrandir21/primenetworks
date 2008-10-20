/**
 * 
 */
package graphics;


import hardware.CPU;
import hardware.Discdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Motherboard;
import hardware.Ram;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MakeStandardInternalComponents
{

	// GETTERS

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_MB
	 */
	public Motherboard getSt_MB()
	{
		Motherboard mb = new Motherboard("Standard Motherboard",
				"This is the system standard motherboard.", "Prime", "ATX", "Intel 775", "DDR2", 1,
				3, 2, 4, 4, "SATA", "AGP", true, true, true, 1);

		return mb;
	}




	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the hw_MB
	 */
	public Motherboard getHw_MB()
	{
		return new Motherboard("Standard hardware Motherboard",
				"This is the system standard hardware motherboard.");
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_CPU
	 */
	public CPU getSt_CPU()
	{
		return new CPU("Standard CPU", "This is the system standard CPU.", "Intel 775", 512);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_HDD
	 */
	public HDD getSt_HDD()
	{
		return new HDD("Standard HDD", "This is the system standard HDD.", "SATA", 160);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_RAM
	 */
	public Ram getSt_RAM()
	{
		return new Ram("Standard Ram", "This is the system standard RAM.", "DDR2", 1024);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_DVDRW
	 */
	public Discdrive getSt_DVDRW()
	{
		return new Discdrive("Standard discdrive", "This is the system standard discdrive.",
				"DVDRW", "SATA");
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public ExternalNetworksCard getSt_ExtNIC()
	{
		String count = "";
		int canvasCount = PrimeMain1.currentCanvas.getNumberOfWidgetsOnTheScene();

		if ( canvasCount < 10 )
		{
			count = "0" + canvasCount;
		}
		else
		{
			count = "" + canvasCount;
		}

		String mac = "00:00:00:00:00:" + count;

		PrimeMain1.currentCanvas.addNIC();

		return new ExternalNetworksCard("Standard external NIC",
				"This is the system standard external NIC.", "Realtek", mac, "Wireless", "USB");
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public InternalNetworksCard getSt_IntNIC()
	{
		String count = "";
		int canvasCount = PrimeMain1.currentCanvas.getNumberOfWidgetsOnTheScene();

		if ( canvasCount < 10 )
		{
			count = "0" + canvasCount;
		}
		else
		{
			count = "" + canvasCount;
		}

		String mac = "00:00:00:00:00:" + count;

		PrimeMain1.currentCanvas.addNIC();

		return new InternalNetworksCard("Standard internal NIC",
				"This is the system standard internal NIC.", "Realtek", mac, "Wired");
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public GraphicsCard getSt_GPU()
	{
		return new GraphicsCard("Standard GPU", "This is the system standard GPU.", "AGP", 128,
				"VGA", false);
	}
}
