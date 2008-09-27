package logistical;


import infrastructure.RackHub;
import infrastructure.RackRouter;
import infrastructure.RackSwitch;
import objects.Object;


/**
 * Functions class that contains functions to manage and manipulate the content
 * of a rack. Functions like calculating shelf space.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class RackFunctions
{

	/**
	 * Calculates the number of shelfs needed to contain all the units in the
	 * given array of units.
	 * 
	 * @return The number of shelfs needed.
	 */
	public static int calculateShelfSpace(Object[] RackUnits)
	{
		// The number of shelfs needed
		int numberOfShelfs = 0;


		for ( int i = 0; i < RackUnits.length; i++ )
		{
			if ( RackUnits[i].getClass().equals(RackRouter.class) )
			{
				RackRouter temp = (RackRouter) RackUnits[i];

				numberOfShelfs += temp.getUnitSize();
			}
			else if ( RackUnits[i].getClass().equals(RackHub.class) )
			{
				RackHub temp = (RackHub) RackUnits[i];

				numberOfShelfs += temp.getUnitSize();
			}
			else if ( RackUnits[i].getClass().equals(RackSwitch.class) )
			{
				RackSwitch temp = (RackSwitch) RackUnits[i];

				numberOfShelfs += temp.getUnitSize();
			}
		}

		return numberOfShelfs;
	}



	/**
	 * Returns the number of shelfs needed to contain the given unit.
	 * 
	 * @return The number of shelfs needed.
	 */
	public static int calculateShelfSpace(Object RackUnits)
	{
		// The number of shelfs needed
		int numberOfShelfs = 0;

		if ( RackUnits.getClass().equals(RackRouter.class) )
		{
			RackRouter temp = (RackRouter) RackUnits;

			numberOfShelfs += temp.getUnitSize();
		}
		else if ( RackUnits.getClass().equals(RackHub.class) )
		{
			RackHub temp = (RackHub) RackUnits;

			numberOfShelfs += temp.getUnitSize();
		}
		else if ( RackUnits.getClass().equals(RackSwitch.class) )
		{
			RackSwitch temp = (RackSwitch) RackUnits;

			numberOfShelfs += temp.getUnitSize();
		}


		return numberOfShelfs;
	}
}
