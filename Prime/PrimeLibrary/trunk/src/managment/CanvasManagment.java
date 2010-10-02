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


import java.util.Iterator;
import java.util.List;

import logistical.cleanup;
import objects.Object;

import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This class contains functions that handle mainly {@link WorkareaCanvas
 * WorkareaCanvases}. There are functions that search, validate and check
 * different aspects of a {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class CanvasManagment
{
	/**

	 */
	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given name is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(String canvasName,
			WorkareaCanvas[] canvases)
	{
		if ( canvases != null )
		{
			for ( int i = 0; i < canvases.length; i++ )
			{
				if ( canvases[i] != null )
				{
					if ( canvases[i].getCanvasName().equals(canvasName) )
					{
						return canvases[i];
					}
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given name.
	 * 
	 * @param scene
	 *            The {@link Scene} that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given name is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(Scene scene,
			WorkareaCanvas[] canvases)
	{
		if ( canvases != null )
		{
			for ( int i = 0; i < canvases.length; i++ )
			{
				if ( canvases[i] != null )
				{
					if ( canvases[i].getScene().equals(scene) )
					{
						return canvases[i];
					}
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given {@link Object}.
	 * 
	 * @param obj
	 *            The {@link Object} that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given
	 *         {@link Object} is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(Object obj,
			WorkareaCanvas[] canvases)
	{
		if ( canvases != null && obj != null )
		{
			WidgetObject widObj = findWidgetObject(obj, canvases);

			if ( widObj != null )
			{
				return findCanvas(widObj.getScene(), canvases);
			}
		}

		return null;
	}


	/**
	 * Finds and returns the WidgetObject that contains an Object that is equal
	 * to the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj,
			WorkareaCanvas canvas)
	{
		if ( obj != null && canvas != null )
		{
			List<Widget> children = canvas.getMainLayer().getChildren();


			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();

				if ( temp.getObject().getObjectSerial() == obj
						.getObjectSerial() )
				{
					return temp;
				}
			}
		}

		return null;
	}



	/**
	 * Finds and returns the WidgetObject that contains an Object based on the
	 * serial number given.
	 * 
	 * @param serial
	 *            The serial number searched for.
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObjectByObjectSerial(long serial,
			WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			List<Widget> children = canvas.getMainLayer().getChildren();


			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();

				if ( temp.getObject().getObjectSerial() == serial )
				{
					return temp;
				}
			}
		}

		return null;
	}



	/**
	 * Finds and returns the WidgetObject that contains an Object based on the
	 * name of the object and the given object.
	 * 
	 * @param name
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObjectByObjectName(String name,
			WorkareaCanvas canvas)
	{
		if ( name.length() > 0 && canvas != null )
		{
			List<Widget> children = canvas.getMainLayer().getChildren();


			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();

				if ( temp.getObject().getObjectName().equalsIgnoreCase(name) )
				{
					return temp;
				}
			}
		}

		return null;
	}



	/**
	 * Finds and returns the WidgetObject that contains an Object that is equal
	 * to the given object. This method checks an array of canvases.
	 * 
	 * @param obj
	 * @param canvaser
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj,
			WorkareaCanvas[] canvaser)
	{
		if ( obj != null && canvaser != null )
		{
			for ( int i = 0; i < canvaser.length; i++ )
			{
				if ( canvaser[i] != null )
				{
					List<Widget> children = canvaser[i].getMainLayer()
							.getChildren();


					for ( Iterator<Widget> iter = children.iterator(); iter
							.hasNext(); )
					{
						WidgetObject temp = (WidgetObject) iter.next();

						if ( temp.getObject().getObjectSerial() == obj
								.getObjectSerial() )
						{
							return temp;
						}
					}
				}
			}
		}

		return null;
	}




	/**
	 * Finds a WorkareaCanvas in the systems canvas array. Compares
	 * {@link WorkareaCanvas} serial numbers.
	 * 
	 * @param canvas
	 *            The searched for WorkareaCanvas.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(WorkareaCanvas canvas,
			WorkareaCanvas[] canvases)
	{
		if ( canvas != null && canvases != null )
		{
			for ( int i = 0; i < canvases.length; i++ )
			{
				if ( canvases[i] != null )
				{
					// If the difference between the two doubles are smaller
					// then Epsilon
					if ( Math.abs(canvases[i].getSerial() - canvas.getSerial()) < 0.0000001 )
					{
						return true;
					}
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}

	/**
	 * Finds a WorkareaCanvas with the given name in the systems canvas array.
	 * 
	 * @param canvasName
	 *            The name of the searched for WorkareaCanvas.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(String canvasName,
			WorkareaCanvas[] canvases)
	{
		if ( canvasName.length() > 0 && canvases != null )
		{
			for ( int i = 0; i < canvases.length; i++ )
			{
				if ( canvases[i] != null )
				{
					if ( canvases[i].getCanvasName().equalsIgnoreCase(
							canvasName) )
					{
						return true;
					}
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}




	/**
	 * The method goes through all the given WorkareaCanvases given and returns
	 * a WorkareaCanvas array containing the WorkareaCanvases that have been
	 * changed and not been saved. If no workareaCanvases are found, it will
	 * return a null pointer.
	 * 
	 * @param canvases
	 *            The WorkareaCanvases to be searched.
	 * @return The WorkareaCanvases that have changed, but not been saved. Or a
	 *         null pointer if no WorkareaCanvases are found.
	 *         NULL if the given array is null.
	 */
	public static WorkareaCanvas[] canvasesHaveChanged(WorkareaCanvas[] canvases)
	{
		if ( canvases != null )
		{
			WorkareaCanvas[] change = new WorkareaCanvas[canvases.length];


			for ( int i = 0; i < canvases.length; i++ )
			{
				if ( canvases[i] != null )
				{
					if ( !(canvases[i].isSaved()) && canvases[i].isChanged() )
					{
						change[i] = canvases[i];
					}
				}
			}

			change = cleanup.cleanObjectArray(change);


			// If no canvases were changed(and not saved)
			if ( change.length < 1 )
			{
				return null;
			}


			return change;
		}

		return null;
	}



	/**
	 * The method checks the given WorkareaCanvas and returns a boolean on
	 * whether or not the {@link WorkareaCanvas} has been
	 * changed and not been saved. If the given workareaCanvas is null, false
	 * will be returned.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} to be examined.
	 * @return A boolean on whether or not the {@link WorkareaCanvas} has been
	 *         changed and not saved.
	 */
	public static boolean canvasHasChanged(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			if ( !(canvas.isSaved()) && canvas.isChanged() )
			{
				return true;
			}
		}

		return false;
	}
}
