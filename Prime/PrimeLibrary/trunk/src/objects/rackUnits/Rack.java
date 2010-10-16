/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects.rackUnits;


import java.io.Serializable;

import logistical.RackFunctions;
import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Infrastructure;
import objects.Object;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * This class represents a 19-inch rack. This rack is basically a container for
 * different units, like switches and
 * routers. It contains information about how many different units it can
 * contain, depending on the unit sizes.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Rack extends Infrastructure implements Serializable
{

	// The number of spaces on a rack, usually referred to as "shelf" or "U".
	private int numberOfShelfs;


	// The number of currently occupied shelfs
	private int occupiedShelfs;


	// The container for the individual units that this rack contains.
	private Object[] units;



	/**
	 * Constructor for a rack class. This also creates an array that contains a
	 * given number of object entries to
	 * contain the different units placed within the rack. It also sets the
	 * number of currently contained units and the
	 * number of currently occupied shelfs to 0.
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

		this.numberOfShelfs = rackShelfs;

		this.units = new Object[rackShelfs];

		this.occupiedShelfs = 0;
	}





	// Get and Set methods for retrieving all data fields.


	// GET METHODES


	/**
	 * Gets the number of shelfs.
	 * 
	 * @return the numberOfShelfs
	 */
	public int getNumberOfShelfs()
	{
		return this.numberOfShelfs;
	}


	/**
	 * Gets the array that points to all the units the rack contains.
	 * 
	 * @return the units
	 */
	public Object[] getUnits()
	{

		return this.units;
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
	 * Get specific units, like routers and switches, by searching for units
	 * with the give class type.
	 * 
	 * @return Returns an array of units that match with the given class.
	 */
	public Object[] getSpesificUnits(Class<Object> unitClass)
			throws ObjectNotFoundException
	{
		Object[] unitsFound = ArrayManagment.getSpesificComponents(unitClass,
				this.units, this.units.length);

		return unitsFound;
	}


	/**
	 * Function to add a unit or units to the the units list.
	 * 
	 * @param NewUnits
	 *            An array of new units.
	 */
	public boolean addUnits(Object[] NewUnits)
	{
		/*
		 * If the number of shelfs needed by the new units is not bigger then
		 * the number of shelfs available, the units
		 * will be installed.
		 */
		if ( !(RackFunctions.calculateShelfSpace(NewUnits) > (this.numberOfShelfs - this.occupiedShelfs)) )
		{
			this.units = ComponentsManagment.addComponents(NewUnits,
					this.units, this.units.length);

			return true;
		}

		return false;
	}



	/**
	 * Function for replacing a specific given unit with a given new unit.
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
		 * the number of shelfs available, the units
		 * will be installed.
		 */
		if ( !(RackFunctions.calculateShelfSpace(NewUnit) > (this.numberOfShelfs - this.occupiedShelfs)) )
		{
			this.units = ComponentsManagment.changeComponent(NewUnit, OldUnit,
					this.units, this.units.length);

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
	public void removeComponent(Object[] ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		this.units = ComponentsManagment.removeComponents(ToBeRemoved,
				this.units, this.units.length);
	}
}
