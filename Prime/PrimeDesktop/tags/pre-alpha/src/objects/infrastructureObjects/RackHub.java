package objects.infrastructureObjects;



import java.io.Serializable;

import objects.hardwareObjects.Motherboard;


/**
 * A representation of a hub that fits into a {@link objects.infrastructureObjects.Rack 19"-rack}. This class will
 * represent the exact same information that the {@link objects.infrastructureObjects.Hub Hub} class represents, but
 * with some additional information regarding the unit, like size.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class RackHub extends Hub implements Serializable
{
	// The number of "Shelfs" or "Us" a unit takes up.
	private int unitSize;


	/**
	 * Constructor for the RackHub class.
	 * 
	 * @param Name
	 *            The name of the RackHub.
	 * @param Desc
	 *            The description of the RackHub.
	 */
	public RackHub(String Name, String Desc, String[] SupConInt, Motherboard objectMB, int outPorts, int inPorts,
			String[] DuplexSupport)
	{
		super(Name, Desc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);
	}




	/**
	 * Gets the size, shelfs space need, for the unit.
	 * 
	 * @return the unitSize
	 */
	public int getUnitSize()
	{

		return unitSize;
	}




	/**
	 * Sets the size, shelfs space need, for the unit.
	 * 
	 * @param unitSize
	 *            the unitSize to set
	 */
	public void setUnitSize(int unitSize)
	{

		this.unitSize = unitSize;
	}
}
