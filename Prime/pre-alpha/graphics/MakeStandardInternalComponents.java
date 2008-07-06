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

	// GETTERS

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_MB
	 */
	public Motherboard getSt_MB()
	{
		return new Motherboard("Standard client Motherboard",
				"This is the system standard client motherboard.", "Prime", "ATX", "Intel 775", 1,
				3, 2, 4, 4, "SATA", "AGP", true, true, true, 1);
	}




	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the hw_MB
	 */
	public Motherboard getHw_MB()
	{
		return new Motherboard("Standard hardware Motherboard",
				"This is the system standard hardware motherboard.");
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_CPU
	 */
	public CPU getSt_CPU()
	{
		return new CPU("Standard client CPU", "This is the system standard client CPU.",
				"Intel 775", 512);
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_HDD
	 */
	public HDD getSt_HDD()
	{
		return new HDD("Standard client HDD", "This is the system standard client HDD.", "SATA",
				160);
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_RAM
	 */
	public Ram getSt_RAM()
	{
		return new Ram("Standard client Ram", "This is the system standard client RAM.", "DDR2",
				1024);
	}





	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the st_DVDRW
	 */
	public Diskdrive getSt_DVDRW()
	{
		return new Diskdrive("Standard client DVD-RW",
				"This is the system standard client DVD-RW.", "DVD-RW");
	}
}
