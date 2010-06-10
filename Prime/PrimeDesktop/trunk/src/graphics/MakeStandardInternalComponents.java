/**
 * 
 */
package graphics;


import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import connections.ConnectionUtils;


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
		Motherboard mb = new Motherboard(PrimeMain1.texts
				.getString("standardMBName"), PrimeMain1.texts
				.getString("standardMBDescription"), PrimeMain1.texts
				.getString("standardMBProducer"), "ATX", "Intel 775", "DDR2",
				1, 3, 2, 4, 4, 0, "SATA", "AGP", true, true, true, 1);

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
		return new Motherboard(PrimeMain1.texts.getString("standardHwMBName"),
				PrimeMain1.texts.getString("standardHwMBDescription"));
	}





	/**
	 * Creates and returns what the system will regard as the standard CPU unit.
	 * 
	 * @return A new CPU unit
	 */
	public CPU getSt_CPU()
	{
		return new CPU(PrimeMain1.texts.getString("standardCPUName"),
				PrimeMain1.texts.getString("standardCPUDescription"),
				"Intel 775", 512);
	}





	/**
	 * Creates and returns what the system will regard as the standard hdd unit.
	 * 
	 * @return A new hdd unit
	 */
	public HDD getSt_HDD()
	{
		return new HDD(PrimeMain1.texts.getString("standardHDDName"),
				PrimeMain1.texts.getString("standardHDDDescription"), "SATA",
				160);
	}





	/**
	 * Creates and returns what the system will regard as the standard ram unit.
	 * 
	 * @return A new ram unit
	 */
	public Ram getSt_RAM()
	{
		return new Ram(PrimeMain1.texts.getString("standardRAMName"),
				PrimeMain1.texts.getString("standardRAMDescription"), "DDR2",
				1024);
	}





	/**
	 * Creates and returns what the system will regard as the standard DVD-RW.
	 * 
	 * @return A new DVD-RW
	 */
	public Discdrive getSt_DVDRW()
	{
		return new Discdrive(PrimeMain1.texts.getString("standardDVDRWName"),
				PrimeMain1.texts.getString("standardDVDRWUDescription"),
				"DVDRW", "SATA");
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
		int canvasCount = 0;

		if ( PrimeMain1.currentCanvas != null )
		{
			canvasCount = PrimeMain1.currentCanvas
					.getNumberOfWidgetsOnTheScene();
		}


		if ( canvasCount < 10 )
		{
			count = "0" + canvasCount;
		}
		else
		{
			count = "" + canvasCount;
		}

		String mac = "00:00:00:00:00:" + count;

		if ( PrimeMain1.currentCanvas != null )
		{
			PrimeMain1.currentCanvas.addNIC();
		}

		return new ExternalNetworksCard(PrimeMain1.texts
				.getString("standardExtNICName"), PrimeMain1.texts
				.getString("standardExtNICDescription"), PrimeMain1.texts
				.getString("standardExtNICProducer"), mac,
				ConnectionUtils.Wireless, ConnectionUtils.USB);
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
		int canvasCount = 0;

		if ( PrimeMain1.currentCanvas != null )
		{
			canvasCount = PrimeMain1.currentCanvas
					.getNumberOfWidgetsOnTheScene();
		}


		if ( canvasCount < 10 )
		{
			count = "0" + canvasCount;
		}
		else
		{
			count = "" + canvasCount;
		}

		String mac = "00:00:00:00:00:" + count;

		if ( PrimeMain1.currentCanvas != null )
		{
			PrimeMain1.currentCanvas.addNIC();
		}

		return new InternalNetworksCard(PrimeMain1.texts
				.getString("standardIntNICName"), PrimeMain1.texts
				.getString("standardIntNICDescription"), PrimeMain1.texts
				.getString("standardIntNICProducer"), ConnectionUtils.PCI, mac,
				ConnectionUtils.RJ45);
	}



	/**
	 * Creates and returns what the system will regard as the standard GPU.
	 * 
	 * @return A new GPU
	 */
	public GraphicsCard getSt_GPU()
	{
		return new GraphicsCard(PrimeMain1.texts.getString("standardGPUName"),
				PrimeMain1.texts.getString("standardGPUDescription"), "AGP",
				128, "VGA", false);
	}
}
