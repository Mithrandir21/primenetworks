/**
 * 
 */
package graphics;


import hardware.*;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MakeStandardInternalComponents
{
	private static Motherboard st_client_MB = null;

	private static Motherboard st_server_MB = null;


	private static CPU st_client_CPU = null;

	private static CPU st_server_CPU = null;


	private static HDD st_client_HDD = null;

	private static HDD st_server_HDD = null;


	private static Ram st_client_RAM = null;

	private static Ram st_server_RAM = null;


	private static Diskdrive st_client_DVDRW = null;

	private static Diskdrive st_server_DVDRW = null;





	public MakeStandardInternalComponents()
	{
		makeMotherBoards();

		makeCPUs();

		makeHDDs();

		makeRAM();

		makeDiskdrives();
	}





	private void makeMotherBoards()
	{
		st_client_MB = new Motherboard("Standard client Motherboard",
				"This is the system standard client motherboard.", "Prime", "ATX", "Intel 775", 1,
				3, "AGP", 2, "SATA", true, true, true);
		
		st_server_MB = new Motherboard("Standard Server Motherboard",
				"This is the system standard server motherboard.", "Prime", "ATX", "Intel 775", 1,
				3, "AGP", 2, "SATA", true, true, true);
	}


	private void makeCPUs()
	{
		st_client_CPU = new CPU("Standard client CPU",
				"This is the system standard client CPU.", "Intel 775", 512);

		st_server_CPU = new CPU("Standard Server CPU", "This is the system standard server CPU.",
				"Intel 775", 512);
	}

	private void makeHDDs()
	{
		st_client_HDD = new HDD("Standard client HDD",
				"This is the system standard client HDD.", "SATA", 160);

		st_server_HDD = new HDD("Standard Server HDD", "This is the system standard server HDD.",
				"SATA", 160);
	}


	private void makeRAM()
	{
		st_client_RAM = new Ram("Standard client Ram",
				"This is the system standard client RAM.", "DDR2", 1024);

		st_server_RAM = new Ram("Standard Server Ram", "This is the system standard Server RAM.",
				"DDR2", 1024);
	}


	private void makeDiskdrives()
	{
		st_client_DVDRW = new Diskdrive("Standard client DVD-RW",
				"This is the system standard client DVD-RW.", "DVD-RW");

		st_server_DVDRW = new Diskdrive("Standard Server DVD-RW",
				"This is the system standard server DVD-RW.", "DVD-RW");
	}





	// GETTERS

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_client_MB
	 */
	public Motherboard getSt_client_MB()
	{
		return st_client_MB;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_server_MB
	 */
	public Motherboard getSt_server_MB()
	{
		return st_server_MB;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_client_CPU
	 */
	public CPU getSt_client_CPU()
	{
		return st_client_CPU;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_server_CPU
	 */
	public CPU getSt_server_CPU()
	{
		return st_server_CPU;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_client_HDD
	 */
	public HDD getSt_client_HDD()
	{
		return st_client_HDD;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_server_HDD
	 */
	public HDD getSt_server_HDD()
	{
		return st_server_HDD;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_client_RAM
	 */
	public Ram getSt_client_RAM()
	{
		return st_client_RAM;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_server_RAM
	 */
	public Ram getSt_server_RAM()
	{
		return st_server_RAM;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_client_DVDRW
	 */
	public Diskdrive getSt_client_DVDRW()
	{
		return st_client_DVDRW;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_server_DVDRW
	 */
	public Diskdrive getSt_server_DVDRW()
	{
		return st_server_DVDRW;
	}





	// SETTERS


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_client_MB
	 *            the st_client_MB to set
	 */
	public void setSt_client_MB(Motherboard st_client_MB)
	{
		this.st_client_MB = st_client_MB;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_server_MB
	 *            the st_server_MB to set
	 */
	public void setSt_server_MB(Motherboard st_server_MB)
	{
		this.st_server_MB = st_server_MB;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_client_CPU
	 *            the st_client_CPU to set
	 */
	public void setSt_client_CPU(CPU st_client_CPU)
	{
		this.st_client_CPU = st_client_CPU;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_server_CPU
	 *            the st_server_CPU to set
	 */
	public void setSt_server_CPU(CPU st_server_CPU)
	{
		this.st_server_CPU = st_server_CPU;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_client_HDD
	 *            the st_client_HDD to set
	 */
	public void setSt_client_HDD(HDD st_client_HDD)
	{
		this.st_client_HDD = st_client_HDD;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_server_HDD
	 *            the st_server_HDD to set
	 */
	public void setSt_server_HDD(HDD st_server_HDD)
	{
		this.st_server_HDD = st_server_HDD;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_client_RAM
	 *            the st_client_RAM to set
	 */
	public void setSt_client_RAM(Ram st_client_RAM)
	{
		this.st_client_RAM = st_client_RAM;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_server_RAM
	 *            the st_server_RAM to set
	 */
	public void setSt_server_RAM(Ram st_server_RAM)
	{
		this.st_server_RAM = st_server_RAM;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_client_DVDRW
	 *            the st_client_DVDRW to set
	 */
	public void setSt_client_DVDRW(Diskdrive st_client_DVDRW)
	{
		this.st_client_DVDRW = st_client_DVDRW;
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param st_server_DVDRW
	 *            the st_server_DVDRW to set
	 */
	public void setSt_server_DVDRW(Diskdrive st_server_DVDRW)
	{
		this.st_server_DVDRW = st_server_DVDRW;
	}
}
