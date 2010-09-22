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
package managment;


import groups.Group;
import groups.Permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class GroupManagment
{
	/**
	 * This function returns a multi dimensional string array that will contain
	 * the names and descriptions of the groups in the given
	 * {@link WorkareaCanvas}.
	 */
	public static String[][] getGroupDataWithDesription(WorkareaCanvas canvas)
	{
		// If the canvas is not null and the network contains at least one
		// Group.
		if ( canvas != null && !canvas.getNetworkGroups().isEmpty() )
		{
			// Creates a new String array of arrays with the size of the canvas
			// groups
			String[][] data = new String[canvas.getNetworkGroups().size()][2];

			// Goes through all the canvases groups
			for ( int i = 0; i < canvas.getNetworkGroups().size(); i++ )
			{
				Group tempGroup = canvas.getNetworkGroups().get(i);

				if ( tempGroup.getGroupName() != null )
				{
					data[i][0] = tempGroup.getGroupName();
				}

				if ( tempGroup.getGroupDescription() != null )
				{
					data[i][1] = tempGroup.getGroupDescription();
				}
			}

			return data;
		}


		// If either canvas is null or has no groups
		return new String[0][2];
	}



	/**
	 * This function returns a multi dimensional string array that will contain
	 * the names of the group and the name of the given {@link WorkareaCanvas}.
	 */
	public static String[][] getGroupDataWithNetworkName(WorkareaCanvas canvas)
	{
		// If the canvas is not null and the network contains at least one
		// Group.
		if ( canvas != null && !canvas.getNetworkGroups().isEmpty() )
		{
			// Creates a new String array of arrays with the size of the canvas
			// groups
			String[][] data = new String[canvas.getNetworkGroups().size()][2];

			// Goes through all the canvases groups
			for ( int i = 0; i < canvas.getNetworkGroups().size(); i++ )
			{
				Group tempGroup = canvas.getNetworkGroups().get(i);

				if ( tempGroup.getGroupName() != null )
				{
					data[i][0] = tempGroup.getGroupName();
				}

				if ( tempGroup.getGroupDescription() != null )
				{
					data[i][1] = canvas.getCanvasName();
				}
			}

			return data;
		}


		// If either canvas is null or has no groups
		return new String[0][2];
	}



	/**
	 * This function creates a new {@link Group} in the {@link Group}
	 * {@link ArrayList} of the given {@link WorkareaCanvas}.
	 * Note: permissions CAN be null.
	 */
	public static boolean createNewGroup(WorkareaCanvas canvas, String name,
			String desc, HashMap<Long, Permissions> permissions)
	{
		if ( canvas != null && name != null && desc != null )
		{
			// If there exists no group in the network with the same name
			if ( !groupNameExistsInNetwork(canvas, name) )
			{
				// A new Group
				Group newGroup;

				if ( permissions != null )
				{
					newGroup = new Group(name, desc, permissions);
				}
				else
				{
					newGroup = new Group(name, desc);
				}

				// Adds the new group to the Network
				canvas.getNetworkGroups().add(newGroup);

				return true;
			}
		}

		return false;
	}



	/**
	 * This function attempts to remove the given {@link Group} from the
	 * given {@link WorkareaCanvas}.
	 */
	public static boolean removeGroup(WorkareaCanvas canvas, Group group)
	{
		if ( canvas != null && group != null )
		{
			// Adds the new group to the Network
			return canvas.getNetworkGroups().remove(group);
		}

		return false;
	}



	/**
	 * This function attempts to find and then remove a {@link Group} from the
	 * given {@link WorkareaCanvas} with the same name as the given string.
	 */
	public static boolean removeGroupWithName(WorkareaCanvas canvas,
			String groupName)
	{
		if ( canvas != null && groupName != null )
		{
			// Attempts to get the Group with the name
			Group group = GroupManagment.getGroupWithName(canvas, groupName);

			if ( group != null )
			{
				// Adds the new group to the Network
				return canvas.getNetworkGroups().remove(group);
			}
		}

		return false;
	}




	/**
	 * This function determines whether or not the given name exists as a
	 * {@link Group} in the given {@link WorkareaCanvas}.
	 * Note: This function is not case sensitive.
	 */
	public static boolean groupNameExistsInNetwork(WorkareaCanvas canvas,
			String groupName)
	{
		if ( canvas != null && groupName != null )
		{
			// If the canvas has at least one group
			if ( !canvas.getNetworkGroups().isEmpty() )
			{
				// Goes through all the canvases groups
				for ( int i = 0; i < canvas.getNetworkGroups().size(); i++ )
				{
					// If the name of the group is the same as the given name
					if ( canvas.getNetworkGroups().get(i).getGroupName()
							.equalsIgnoreCase(groupName) )
					{
						return true;
					}
				}
			}
		}

		// No group was found with the same name as the given string
		return false;
	}



	/**
	 * This function determines whether or not the given name exists as a
	 * {@link Group} in the given {@link WorkareaCanvas}, and returns the Group
	 * Note: This function is not case sensitive.
	 */
	public static Group getGroupWithName(WorkareaCanvas canvas, String groupName)
	{
		if ( canvas != null && groupName != null )
		{
			// If the canvas has at least one group
			if ( !canvas.getNetworkGroups().isEmpty() )
			{
				// Goes through all the canvases groups
				for ( int i = 0; i < canvas.getNetworkGroups().size(); i++ )
				{
					// If the name of the group is the same as the given name
					if ( canvas.getNetworkGroups().get(i).getGroupName()
							.equalsIgnoreCase(groupName) )
					{
						return canvas.getNetworkGroups().get(i);
					}
				}
			}
		}

		// No group was found with the same name as the given string
		return null;
	}



	/**
	 * This function determines whether or not the given name exists as a
	 * {@link Group} in a {@link WorkareaCanvas} with the given name, and
	 * returns the Group
	 * Note: This function is not case sensitive.
	 */
	public static Group getGroupWithName(String canvasName,
			WorkareaCanvas[] canvases, String groupName)
	{
		if ( canvasName != null && canvases != null && groupName != null )
		{
			// Attempts to find the canvas with the given name in the array
			// of WorkareaCanvases given.
			WorkareaCanvas canvas = CanvasManagment.findCanvas(canvasName,
					canvases);

			// If a canvas has been found with the given canvasName
			if ( canvas != null )
			{
				// If the canvas has at least one group
				if ( !canvas.getNetworkGroups().isEmpty() )
				{
					// Goes through all the canvases groups
					for ( int i = 0; i < canvas.getNetworkGroups().size(); i++ )
					{
						// If the name of the group is the same as the given
						// name
						if ( canvas.getNetworkGroups().get(i).getGroupName()
								.equalsIgnoreCase(groupName) )
						{
							return canvas.getNetworkGroups().get(i);
						}
					}
				}
			}
		}

		// No group was found with the same name as the given string
		return null;
	}



	public static String[][] getGroupNamesWithNetworkName(
			WorkareaCanvas[] canvases)
	{
		String[][] data = new String[0][2];

		if ( canvases != null )
		{
			if ( canvases[0] != null )
			{
				// Canvas index(to be able to skip the test for the canvases
				// without groups.)
				int canvasIndex = 0;

				// Gets the data for the first canvas
				for ( int i = 0; i < canvases.length && data.length == 0; i++ )
				{
					data = getGroupDataWithNetworkName(canvases[i]);
					// Increments the canvas index
					canvasIndex++;
				}

				if ( data.length > 0 )
				{
					// Adds the data from the other canvases
					for ( int i = canvasIndex; i < canvases.length; i++ )
					{
						// Adds the new group data
						data = addGroupsData(data,
								getGroupDataWithNetworkName(canvases[i]));
					}

					return data;
				}
			}
		}


		// No groups.
		return new String[0][2];
	}

	/**
	 * This function adds the two given multi dimension arrays. It returns a new
	 * multi dimensions array consisting of only the first array an the second
	 * array, no empty indexes.
	 * 
	 * If either of the given arrays are NULL, the other array will be returned.
	 * 
	 */
	public static String[][] addGroupsData(String[][] mainGroup,
			String[][] newGroup)
	{
		String[][] temp = null;

		// The arrays are not null
		if ( mainGroup != null && newGroup != null )
		{
			// The first indexes in both the arrays are not null
			if ( mainGroup.length > 0 && newGroup.length > 0 )
			{
				// The first indexes in both the arrays are not null
				if ( mainGroup[0] != null && newGroup[0] != null )
				{
					// If the lengths of the first indexes are the same
					if ( mainGroup[0].length == newGroup[0].length )
					{
						// Makes a new objects array with additional indexes
						temp = new String[mainGroup.length + newGroup.length][mainGroup[0].length];

						// ADDS THE MAINGROUP TO THE TEMP ARRAY
						for ( int i = 0; i < mainGroup.length; i++ )
						{
							if ( mainGroup[i][0] != null )
							{
								for ( int j = 0; j < mainGroup[i].length; j++ )
								{
									if ( mainGroup[i][j] != null )
									{
										temp[i][j] = mainGroup[i][j];
									}
								}
							}
						}


						int newGroupCounter = 0;

						// ADDS THE NEWGROUP TO THE END OF THE TEMP ARRAY
						for ( int i = mainGroup.length; i < temp.length; i++ )
						{
							if ( newGroup[newGroupCounter][0] != null )
							{
								for ( int j = 0; j < newGroup[newGroupCounter].length; j++ )
								{
									if ( newGroup[newGroupCounter][j] != null )
									{
										temp[i][j] = newGroup[newGroupCounter][j];
									}
								}
							}

							// Goes to the next index in the new group
							newGroupCounter++;
						}
					}
				}
			}
		}

		// This means that the one of the arrays given is NULL or the second
		// array point is not of equal length.
		if ( temp == null || temp.length == 0 )
		{
			if ( mainGroup != null && mainGroup.length > 0 )
			{
				return mainGroup;
			}
			else if ( newGroup != null && newGroup.length > 0 )
			{
				return newGroup;
			}
			else
			{
				// Returns an empty array with 2 indexes
				return new String[0][2];
			}
		}

		// Returns the newly made array with extra indexes at the end
		return temp;

	}



	/**
	 * This function performers a deep copy of the given {@link HashMap}.
	 * This is done by calling the {@link Cloneable} clone() function inside the
	 * {@link Permissions} class.
	 * 
	 * Will return NULL if the given map is NULL, or a new empty {@link HashMap}
	 * is the size of the given map is 0.
	 */
	public static HashMap<Long, Permissions> deepCopyPermissionsMap(
			HashMap<Long, Permissions> original)
	{
		if ( original != null )
		{
			if ( original.size() == 0 )
			{
				return new HashMap<Long, Permissions>();
			}

			// The new map with the copied keys and values
			HashMap<Long, Permissions> newPermissions = new HashMap<Long, Permissions>();

			Iterator<Long> itr = original.keySet().iterator();

			while ( itr.hasNext() )
			{
				// Creates a new long
				Long newLong = new Long(itr.next());

				// Gets the permission with key from the original map
				Permissions orginialPermission = original.get(newLong);

				/**
				 * Puts the new Long created and a clone of the original
				 * permissions into the new map.
				 */
				newPermissions.put(newLong, orginialPermission.clone());
			}


			return newPermissions;
		}

		return null;
	}
}
