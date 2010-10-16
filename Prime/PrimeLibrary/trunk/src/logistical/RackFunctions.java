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
package logistical;


import objects.Object;
import objects.rackUnits.RackUnit;


/**
 * Functions class that contains functions to manage and manipulate the content
 * of a rack. Functions like calculating
 * shelf space.
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
			if ( RackUnits[i] instanceof RackUnit )
			{
				RackUnit temp = (RackUnit) RackUnits[i];

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

		if ( RackUnits instanceof RackUnit )
		{
			RackUnit temp = (RackUnit) RackUnits;

			numberOfShelfs += temp.getUnitSize();
		}


		return numberOfShelfs;
	}
}
