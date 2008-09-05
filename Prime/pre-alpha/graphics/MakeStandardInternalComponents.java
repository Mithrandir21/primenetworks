/**
 * 
 */
package graphics;


import hardware.*;


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
		return new Motherboard("Standard Motherboard",
				"This is the system standard motherboard.", "Prime", "ATX", "Intel 775", 1,
				3, 2, 4, 4, "SATA", "AGP", true, true, true, 1);
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
		return new CPU("Standard CPU", "This is the system standard CPU.",
				"Intel 775", 512);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_HDD
	 */
	public HDD getSt_HDD()
	{
		return new HDD("Standard HDD", "This is the system standard HDD.", "SATA",
				160);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_RAM
	 */
	public Ram getSt_RAM()
	{
		return new Ram("Standard Ram", "This is the system standard RAM.", "DDR2",
				1024);
	}





	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the st_DVDRW
	 */
	public Discdrive getSt_DVDRW()
	{
		return new Discdrive("Standard DVD-RW",
				"This is the system standard DVD-RW.", "DVD-RW");
	}
}
