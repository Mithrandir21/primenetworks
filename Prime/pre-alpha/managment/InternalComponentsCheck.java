package managment;


import hardware.CPU;
import hardware.Discdrive;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Motherboard;
import hardware.Ram;
import objects.Object;

// FIXME -- Find out what to do with this.

/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class InternalComponentsCheck
{

	/**
	 * Searches an array of components and finds the, if present, Motherboard
	 * object.
	 */
	public static Motherboard getMB(Object[] components)
	{
		// An empty MB object to place the found one
		Motherboard MB = null;

		/*
		 * Searches for a Motherboard object until either the MB object is not
		 * null or it reaches the end of the array of components.
		 */
		for ( int i = 0; MB == null && i < components.length; i++ )
		{

			if ( components[i] != null )
			{
				if ( components[i].getClass().equals(Motherboard.class) )
				{
					MB = (Motherboard) components[i];
				}
			}
		}


		return MB;
	}





	/**
	 * Description
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkMBinternalPorts(Motherboard MB, Object[] components, Object a)
	{
		Class objectClass = a.getClass();


		// Checks to find out what kind of object is to be installed
		// If the component is a CPU component
		if ( objectClass.equals(CPU.class) )
		{
			// If the number returned it greater then 1, then there is room for
			// the object
			if ( checkMB_CPUports(MB.getMaxCPUs(), components) > 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// If the component is a Graphics Card component
		else if ( objectClass.equals(GraphicsCard.class) )
		{
			// Typecasts the object to a graphical card
			GraphicsCard card = (GraphicsCard) a;

			String type = card.getType();

			String MBgraphicalType = MB.getGraphicalPort();


			if ( type == "PCI" )
			{
				if ( MBgraphicalType == "PCI" )
				{
					// If the number returned it greater then 1, then there is
					// room for the object
					if ( checkMB_PCIports(MB.getMaxPCIs(), components) > 0 )
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			else if ( type == "PCI-E" )
			{
				if ( MBgraphicalType == "PCI-E" )
				{
					if ( MB.isGraphicsCardInstalled() != true )
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			else if ( type == "AGP" )
			{
				if ( MBgraphicalType == "AGP" )
				{
					if ( MB.isGraphicsCardInstalled() != true )
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}

			return true;

		}
		// If the component is a Internal Networks Card component
		else if ( objectClass.equals(InternalNetworksCard.class) )
		{
			// If the number returned it greater then 1, then there is room for
			// the object
			if ( checkMB_PCIports(MB.getMaxPCIs(), components) > 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// If the component is a RAM component
		else if ( objectClass.equals(Ram.class) )
		{
			// If the number returned it greater then 1, then there is room for
			// the object
			if ( checkMB_RAMports(MB.getMaxRAMs(), components) > 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// If the component is a HHD or Diskdrive component
		else if ( objectClass.equals(HDD.class) || objectClass.equals(Discdrive.class) )
		{
			// If the number returned it greater then 1, then there is room for
			// the object
			if ( checkMB_DUCports(MB.getMaxDUCs(), components) > 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}



		return false;
	}









	/**
	 * Description
	 */
	public static int checkMB_PCIports(int maxPCIs, Object[] components)
	{
		// Number of available PCI ports on the computer
		int portsAvailable = maxPCIs;


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] != null )
			{
				Object currentObject = components[i];

				// If the component is a internal networks card
				if ( currentObject.getClass().equals(InternalNetworksCard.class) )
				{
					portsAvailable--;
				}
				// If the component is a graphics card
				else if ( currentObject.getClass().equals(GraphicsCard.class) )
				{
					// A temporary graphics card object
					GraphicsCard card = (GraphicsCard) currentObject;

					// Gets the type of the card
					String type = card.getType();

					if ( type == "PCI" )
					{
						portsAvailable--;
					}
				}
			}
		}


		return portsAvailable;
	}



	/**
	 * Description
	 */
	public static int checkMB_CPUports(int maxCPUs, Object[] components)
	{
		// Number of available CPU ports on the computer
		int portsAvailable = maxCPUs;


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] != null )
			{
				// A temporary holder for the object under examination
				Object currentObject = components[i];

				// If the temporary object is an instance of a CPU class
				if ( currentObject.getClass().equals(CPU.class) )
				{
					portsAvailable--;
				}
			}
		}

		return portsAvailable;
	}



	/**
	 * Description
	 */
	@SuppressWarnings("unchecked")
	public static int checkMB_DUCports(int maxDUCs, Object[] components)
	{
		// Number of available DUC ports on the computer
		int portsAvailable = maxDUCs;


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] != null )
			{
				// A temporary holder for the object under examination
				Class objectClass = components[i].getClass();

				// If the temporary object is an instance of a HDD or a
				// diskdrive class
				if ( objectClass.equals(HDD.class) || objectClass.equals(Discdrive.class) )
				{
					portsAvailable--;
				}
			}
		}

		return portsAvailable;
	}



	/**
	 * Description
	 */
	public static int checkMB_RAMports(int maxRAMs, Object[] components)
	{
		// Number of available DUC ports on the computer
		int portsAvailable = maxRAMs;


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] != null )
			{
				// A temporary holder for the object under examination
				Object currentObject = components[i];

				// If the temporary object is an instance of a CPU class
				if ( currentObject.getClass().equals(Ram.class) )
				{
					portsAvailable--;
				}
			}
		}

		return portsAvailable;

	}
}
