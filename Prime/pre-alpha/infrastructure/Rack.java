package infrastructure;


import java.io.Serializable;

import logistical.RackFunctions;
import managment.ComponentsManagment;
import objects.Infrastructure;
import objects.Object;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * This class represents a 19-inch rack. This rack is basicly a container for
 * different units, like switches and routers. It contains information abour how
 * many different units it can contain, depending on the unit sizes.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Rack extends Infrastructure implements Serializable
{

	// The number of spaces on a rack, usually refered to as "shelf" or "U".
	private int numberOfShelfs;


	// The number of curretly occupied shelfs
	private int occupiedShelfs;


	// The container for the individual units that this rack contains.
	private Object[] units;


	// The number of units currently contained by the rack.
	private int unitsCounter;



	/**
	 * Constructor for a rack class. This also creates an array that contains a
	 * given number of object entires to contain the different units placed
	 * within the rack. It also sets the number of currently contained units and
	 * the number of currently occupied shelfs to 0.
	 * 
	 * @param Name
	 *            The name of the rack.
	 * @param Desc
	 *            The description of the rack.
	 * @param rackShelfs
	 *            The number of shelfs, "U", on a rack.
	 */
	public Rack(String Name, String Desc, String[] SupConInt, int rackShelfs)
	{
		super(Name, Desc, SupConInt);

		numberOfShelfs = rackShelfs;

		units = new Object[rackShelfs];

		unitsCounter = 0;

		occupiedShelfs = 0;

	}





	// Get and Set methodes for retrieving all datafields.


	// GET METHODES


	/**
	 * Gets the number of shelfs.
	 * 
	 * @return the numberOfShelfs
	 */
	public int getNumberOfShelfs()
	{
		return numberOfShelfs;
	}


	/**
	 * Gets the array that points to all the units the rack contains.
	 * 
	 * @return the units
	 */
	public Object[] getUnits()
	{

		return units;
	}



	// SET METHODES


	/**
	 * Sets the number of shelfs on the rack.
	 * 
	 * @param numberOfShelfs
	 *            The number of shelfs the rack can hold.
	 */
	public void setNumberOfShelfs(int numberOfShelfs)
	{
		this.numberOfShelfs = numberOfShelfs;
	}


	/**
	 * Replaces the array that contains all the racks units with another array
	 * of units.
	 * 
	 * @param units
	 *            The units that will replace the previous units.
	 */
	public void setUnits(Object[] units)
	{
		this.units = units;
	}



	// CLASS METHODES

	/**
	 * Get spesific units, like routers and switches, by searching for units
	 * with the give class type.
	 * 
	 * @return Returns an array of units that match with the given class.
	 */
	public Object[] getSpesificUnits(Class<Object> unitClass) throws ObjectNotFoundException
	{
		Object[] unitsFound = ComponentsManagment.getSpesificComponents(unitClass, units,
				unitsCounter);

		return unitsFound;
	}


	/**
	 * Function to add a unit or units to the the units list.
	 * 
	 * @param NewUnits
	 *            An array of new units.
	 */
	public boolean addUnits(Object[] NewUnits) throws Exception
	{
		/*
		 * If the number of shelfs needed by the new units is not bigger then
		 * the number of shelfs available, the units will be installed.
		 */
		if ( !(RackFunctions.calculateShelfSpace(NewUnits) > (numberOfShelfs - occupiedShelfs)) )
		{
			units = ComponentsManagment.addComponents(NewUnits, units, unitsCounter);

			// Sets the new count for number of components in the array

			unitsCounter = units.length;

			return true;
		}

		return false;
	}



	/**
	 * Function for replacing a spesific given unit with a given new unit.
	 * 
	 * @param NewUnit
	 *            The unit to replace the previous one.
	 * @param OldUnit
	 *            The unit to be replaced.
	 */
	public boolean changeUnit(Object NewUnit, Object OldUnit)
	{
		/*
		 * If the number of shelfs needed by the new units is not bigger then
		 * the number of shelfs available, the units will be installed.
		 */
		if ( !(RackFunctions.calculateShelfSpace(NewUnit) > (numberOfShelfs - occupiedShelfs)) )
		{
			units = ComponentsManagment.changeComponent(NewUnit, OldUnit, units, unitsCounter);

			// Sets the new count for number of components in the array
			unitsCounter = units.length;

			return true;
		}
		return false;
	}


	/**
	 * Function to remove units from the array of units.
	 * 
	 * @param ToBeRemoved
	 *            Units to be removed.
	 */
	@Override
	public void removeComponent(Object[] ToBeRemoved) throws ObjectNotFoundInArrayException
	{
		units = ComponentsManagment.removeComponents(ToBeRemoved, units, unitsCounter);

		// Sets the new count for number of components in the array
		unitsCounter = units.length;
	}



}
