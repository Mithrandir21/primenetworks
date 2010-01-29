package managment;


import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;


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
	 * Checks whether or not the given {@link Motherboard} has the available
	 * ports to enable connection to the given {@link Object}.
	 * 
	 * @param MB
	 *            The {@link Motherboard} that is to be tested for a available
	 *            ports.
	 * @param components
	 *            The {@link Object Objects} connected to the
	 *            {@link Motherboard} already.
	 * @param newComponent
	 *            The new component that wants to connect to the given
	 *            {@link Motherboard}.
	 * @return True or false depending on whether the {@link Motherboard} has
	 *         the necessary ports available.
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkMBinternalPorts(Motherboard MB,
			Object[] components, Object newComponent)
	{
		Class objectClass = newComponent.getClass();


		// Checks to find out what kind of object is to be installed if the
		// component is a CPU component
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
			GraphicsCard card = (GraphicsCard) newComponent;

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
		else if ( objectClass.equals(HDD.class)
				|| objectClass.equals(Discdrive.class) )
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
	 * Gets the number of available ports, from the given {@link Integer} that
	 * represents the maximum number of ports on the {@link Motherboard}.
	 * 
	 * @param maxPCIs
	 *            The maximum number of ports on the {@link Motherboard}.
	 * @param components
	 *            The components that are already connected to the
	 *            {@link Motherboard}, which will be checked for matching
	 *            devices that connect to a PCI port.
	 * @return The number of available ports after all the matching components
	 *         from the {@link Object Components} array.
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
				if ( currentObject.getClass()
						.equals(InternalNetworksCard.class) )
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
	 * Gets the number of available ports, from the given {@link Integer} that
	 * represents the maximum number of ports on the {@link Motherboard}.
	 * 
	 * @param maxCPUs
	 *            The maximum number of ports on the {@link Motherboard}.
	 * @param components
	 *            The components that are already connected to the
	 *            {@link Motherboard}, which will be checked for matching
	 *            devices that connect to a CPU port.
	 * @return The number of available ports after all the matching components
	 *         from the {@link Object Components} array.
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
	 * Gets the number of available ports, from the given {@link Integer} that
	 * represents the maximum number of ports on the {@link Motherboard}.
	 * 
	 * @param maxDUCs
	 *            The maximum number of ports on the {@link Motherboard}.
	 * @param components
	 *            The components that are already connected to the
	 *            {@link Motherboard}, which will be checked for matching
	 *            devices that connect to a DUC port.
	 * @return The number of available ports after all the matching components
	 *         from the {@link Object Components} array.
	 */
	public static int checkMB_DUCports(int maxDUCs, Object[] components)
	{
		// Number of available DUC ports on the computer
		int portsAvailable = maxDUCs;


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] != null )
			{
				// A temporary holder for the object under examination
				Class<? extends Object> objectClass = components[i].getClass();

				// If the temporary object is an instance of a HDD or a
				// diskdrive class
				if ( objectClass.equals(HDD.class)
						|| objectClass.equals(Discdrive.class) )
				{
					portsAvailable--;
				}
			}
		}

		return portsAvailable;
	}



	/**
	 * Gets the number of available ports, from the given {@link Integer} that
	 * represents the maximum number of ports on the {@link Motherboard}.
	 * 
	 * @param maxRAMs
	 *            The maximum number of ports on the {@link Motherboard}.
	 * @param components
	 *            The components that are already connected to the
	 *            {@link Motherboard}, which will be checked for matching
	 *            devices that connect to a RAM port.
	 * @return The number of available ports after all the matching components
	 *         from the {@link Object Components} array.
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
