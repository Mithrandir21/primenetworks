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
 * In this class the systems standard internal components are made and served to
 * the rest of the system. When any new object is made, the internal components
 * such as motherboard or CPU will be provided by the functions in this class.
 * 
 * @author Bahram Malaekeh
 */
public class MakeStandardInternalComponents
{

	// GETTERS

	/**
	 * Creates and returns what the system will regard as the standard
	 * Motherboard. It is this motherboard that will be the base for all the
	 * newly created computers in the system. It is given a certain set of ports
	 * and sockets.
	 * 
	 * @return A new motherboard
	 */
	public Motherboard getSt_MB()
	{
		Motherboard mb = new Motherboard("Standard Motherboard",
				"This is the system standard motherboard.", "Prime", "ATX",
				"Intel 775", "DDR2", 1, 3, 2, 4, 4, "SATA", "AGP", true, true,
				true, 1);

		return mb;
	}




	/**
	 * Creates and returns what the system will regard as the standard
	 * Motherboard for hardware that require a motherboard but do not require
	 * all the ports and sockets that a normal motherboard would provide, such
	 * as infrastructure or peripherals.
	 * 
	 * @return A new motherboard
	 */
	public Motherboard getHw_MB()
	{
		return new Motherboard("Standard hardware Motherboard",
				"This is the system standard hardware motherboard.");
	}





	/**
	 * Creates and returns what the system will regard as the standard CPU unit.
	 * 
	 * @return A new CPU unit
	 */
	public CPU getSt_CPU()
	{
		return new CPU("Standard CPU", "This is the system standard CPU.",
				"Intel 775", 512);
	}





	/**
	 * Creates and returns what the system will regard as the standard hdd unit.
	 * 
	 * @return A new hdd unit
	 */
	public HDD getSt_HDD()
	{
		return new HDD("Standard HDD", "This is the system standard HDD.",
				"SATA", 160);
	}





	/**
	 * Creates and returns what the system will regard as the standard ram unit.
	 * 
	 * @return A new ram unit
	 */
	public Ram getSt_RAM()
	{
		return new Ram("Standard Ram", "This is the system standard RAM.",
				"DDR2", 1024);
	}





	/**
	 * Creates and returns what the system will regard as the standard DVD-RW.
	 * 
	 * @return A new DVD-RW
	 */
	public Discdrive getSt_DVDRW()
	{
		return new Discdrive("Standard discdrive",
				"This is the system standard discdrive.", "DVDRW", "SATA");
	}



	/**
	 * Creates and returns what the system will regard as the standard external
	 * NIC. This nic will also contain a mac address. This mac address will
	 * mostly contain "0", but the last digits will be the number of components
	 * on the canvas. This is done so that each individual nic can have a
	 * different mac address.
	 * 
	 * @return A new external NIC
	 */
	public ExternalNetworksCard getSt_ExtNIC()
	{
		String count = "";
		int canvasCount = PrimeMain1.currentCanvas
				.getNumberOfWidgetsOnTheScene();

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
				"This is the system standard external NIC.", "Realtek", mac,
				"Wireless", "USB");
	}




	/**
	 * Creates and returns what the system will regard as the standard internal
	 * NIC. This nic will also contain a mac address. This mac address will
	 * mostly contain "0", but the last digits will be the number of components
	 * on the canvas. This is done so that each individual nic can have a
	 * different mac address.
	 * 
	 * @return A new internal NIC
	 */
	public InternalNetworksCard getSt_IntNIC()
	{
		String count = "";
		int canvasCount = PrimeMain1.currentCanvas
				.getNumberOfWidgetsOnTheScene();

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
				"This is the system standard internal NIC.", "Realtek", mac,
				"Wired");
	}



	/**
	 * Creates and returns what the system will regard as the standard GPU.
	 * 
	 * @return A new GPU
	 */
	public GraphicsCard getSt_GPU()
	{
		return new GraphicsCard("Standard GPU",
				"This is the system standard GPU.", "AGP", 128, "VGA", false);
	}
}
