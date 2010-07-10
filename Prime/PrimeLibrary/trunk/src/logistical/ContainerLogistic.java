/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package logistical;


import java.util.ArrayList;

import objects.Object;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ContainerLogistic
{

	/**
	 * Checks to see if a specific object exist in the container with the same name as the given object.
	 */
	public static boolean checkObjectNameExistence(ArrayList<Object> container,
			Object obj)
	{
		// The size of the container
		int arraySize = container.size();

		// Goes through all of the container
		for ( int i = 0; i < arraySize; i++ )
		{
			// Checks to see if it is a null pointer
			if ( container.get(i) != null )
			{
				/*
				 * If the name of the container object is the same as the given object, true is returned.
				 */
				if ( container.get(i).getObjectName().equals(
						obj.getObjectName()) )
				{
					return true;
				}
			}
		}

		// The name does not exist elsewhere in the container
		return false;
	}
}
