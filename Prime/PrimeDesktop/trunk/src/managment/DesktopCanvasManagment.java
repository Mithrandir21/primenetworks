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
package managment;


import exceptions.CanvasNotFound;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import objects.Object;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.WirelessRouter;
import widgetManipulation.NetworkRules;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * In this class the method to maintain and process canvases are placed. Here are methods that can add or remove canvas
 * 
 * @author Bahram Malaekeh
 */
public class DesktopCanvasManagment
{
	// The log for this class
	public static Logger log = Logger.getLogger(DesktopCanvasManagment.class
			.getName());

	/**
	 * Adds the given canvas to the array of currently active canvas in the system. It also sets the given name as the
	 * name of the canvas object.
	 * 
	 * @param newCanvas
	 *            The new canvas that is to be added to systems array of canvases.
	 * @param name
	 *            The name that is to be set as the name for the canvas object.
	 */
	public static void addCanvas(WorkareaCanvas newCanvas, String name)
	{

		// If the first canvas location is not null, which means that there are
		// previous created canvases.
		if ( PrimeMain.canvases[0] != null )
		{
			// Goes through the entire canvas array.
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				// if any index is null.
				if ( PrimeMain.canvases[i] == null )
				{
					newCanvas.setCanvasName(name);

					// Sets the newCanvas in at that index.
					PrimeMain.canvases[i] = newCanvas;

					// Ends the method by returning.
					return;
				}
			}

			// If the method gets to this point it means that there were no
			// empty indexes found and there needs to be added an empty index at
			// the end of the array
			extendCanvasArray();

			addCanvas(newCanvas, name);
		}
		else
		{
			newCanvas.setCanvasName(name);
			PrimeMain.canvases[0] = newCanvas;
			PrimeMain.currentCanvas = newCanvas;

		}
	}



	/**
	 * Extends the array that holds the system canvases with one index.
	 */
	public static void extendCanvasArray()
	{
		WorkareaCanvas[] temp = new WorkareaCanvas[PrimeMain.canvases.length + 1];

		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			temp[i] = PrimeMain.canvases[i];
		}

		PrimeMain.canvases = temp;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 */
	public static WorkareaCanvas findCanvas(String canvasName)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equals(canvasName) )
				{
					return PrimeMain.canvases[i];
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}


	/**
	 * Removes the given canvas from the systems array of open canvases. It does this by comparing the names of the
	 * canvas(ignoring case).
	 * 
	 * @param canvas
	 * @throws CanvasNotFound
	 */
	public static void removeWorkareaCanvas(WorkareaCanvas canvas)
			throws CanvasNotFound
	{
		// If the first canvas location is not null, which means that there are
		// previous created canvases.
		if ( PrimeMain.canvases[0] != null )
		{
			// Goes through the entire canvas array.
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				// if any index not equal null.
				if ( PrimeMain.canvases[i] != null )
				{
					// If the canvas at index i has the same name as the gievn
					// canvas
					if ( PrimeMain.canvases[i].getCanvasName()
							.equalsIgnoreCase(canvas.getCanvasName()) )
					{
						// Removes the canvas from the array
						PrimeMain.canvases[i] = null;
						log
								.fine("Canvas,"
										+ canvas.getCanvasName()
										+ ", has been removed from the main WorkareaCanvas register.");

						// Removes the null pointer in the array
						PrimeMain.canvases = logistical.cleanup
								.cleanObjectArray(PrimeMain.canvases);

						// If all the canvases have been removed, a new array with one index is created
						if ( PrimeMain.canvases.length == 0 )
						{
							PrimeMain.canvases = new WorkareaCanvas[1];
						}

						return;
					}
				}
			}
		}

		// If the function gets to this point it means that the WorkareaCanvas was not found
		throw new CanvasNotFound(canvas);
	}


	/**
	 * This function removes WorkareaCanvas with the given name from the systems WorkareaCanvas array.
	 * 
	 * @param canvasName
	 *            The name of the workareaCanvas that is to be removed.
	 * @throws CanvasNotFound
	 */
	public static void removeWorkareaCanvas(String canvasName)
			throws CanvasNotFound
	{
		// Find the canvas that has the given name
		WorkareaCanvas canvas = findCanvas(canvasName);


		if ( canvas != null )
		{
			// If the first canvas location is not null, which means that there
			// are
			// previous created canvases.
			if ( PrimeMain.canvases[0] != null )
			{
				// Goes through the entire canvas array.
				for ( int i = 0; i < PrimeMain.canvases.length; i++ )
				{
					// if any index is null.
					if ( PrimeMain.canvases[i] == null )
					{
						// If the canvas at index i has the same name as the
						// gievn canvas
						if ( PrimeMain.canvases[i].getCanvasName()
								.equalsIgnoreCase(canvas.getCanvasName()) )
						{
							// Removes the canvas from the array
							PrimeMain.canvases[i] = null;
							log
									.fine("Canvas,"
											+ canvas.getCanvasName()
											+ ", has been removed from the main WorkareaCanvas register.");

							// Removes the null pointer in the array
							PrimeMain.canvases = logistical.cleanup
									.cleanObjectArray(PrimeMain.canvases);

							// If all the canvases have been removed, a new array with one index is created
							if ( PrimeMain.canvases.length == 0 )
							{
								PrimeMain.canvases = new WorkareaCanvas[1];
							}

							return;
						}
					}
				}
			}
		}

		// If the function gets to this point it means that the WorkareaCanvas was not found
		throw new CanvasNotFound(canvas);
	}



	/**
	 * Finds a WorkareaCanvas in the systems canvas array.
	 * 
	 * @param canvas
	 *            The searched for WorkareaCanvas.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(WorkareaCanvas canvas)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(
						canvas.getCanvasName()) )
				{
					return true;
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
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(String canvasName)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(
						canvasName) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}




	/**
	 * This function attempts to add the given {@link WidgetObject} to the given {@link WorkareaCanvas} at the {@link Point}
	 * given.
	 * It cleans up the {@link WorkareaCanvas} afterwards, if the boolean is true and the attempt is a success.
	 * This function also goes through the {@link NetworkRules Rules} contained inside the {@link WorkareaCanvas} to check whether
	 * the given {@link Object} violates the {@link NetworkRules rules}. If the {@link Object} violates the rules, a message is
	 * show to the user and the attempt is aborted with no action performed.
	 */
	public static boolean addWidgetToCanvas(WidgetObject newObject,
			Point objectPoint, WorkareaCanvas canvas, boolean withCleanUp,
			boolean rulesCheck)
	{
		if ( newObject != null && objectPoint != null && canvas != null )
		{
			// Gets the Object inside the widget
			Object obj = newObject.getObject();

			if ( obj != null && !obj.isExemptedNetworkRules() )
			{
				// Gets the canvas rules
				NetworkRules rules = canvas.getRules();

				// CAN CONTAIN RULES

				// If the network can not contain a the given object
				if ( !RulesManagment.canContainObject(rules, obj) )
				{
					String errMsg = "";

					// HUB
					if ( obj instanceof Hub )
					{
						errMsg = PrimeMain.texts
								.getString("rulesDoesNotAllowHubMsg");
						log.fine("A Hub was attempted added to a network,"
								+ canvas.getCanvasName()
								+ ", that does not allow Hubs.");
					}
					// WIRELESSROUTER
					else if ( obj instanceof WirelessRouter )
					{
						errMsg = PrimeMain.texts
								.getString("rulesDoesNotAllowWirelessRouterMsg");
						log
								.fine("A WirelessRouter was attempted added to a network,"
										+ canvas.getCanvasName()
										+ ", that does not allow WirelessRouter.");
					}
					// INTERNET
					else if ( obj instanceof Internet )
					{
						errMsg = PrimeMain.texts
								.getString("rulesDoesNotAllowInternetMsg");
						log
								.fine("A Internet object was attempted added to a network,"
										+ canvas.getCanvasName()
										+ ", that does not allow Internet objects.");
					}

					JOptionPane.showMessageDialog(null, errMsg, PrimeMain.texts
							.getString("rulesViolationTitle"),
							JOptionPane.ERROR_MESSAGE);

					return false;
				}


				// HARDWARE RULES

				// If the USB ports on the object violates the rules
				if ( RulesManagment.objectUSBruleViolation(rules, obj, canvas) )
				{
					// Log info
					log.fine("The device attempted added, "
							+ obj.getObjectName()
							+ ",violates the USB rules of the network.");

					String question = PrimeMain.texts
							.getString("rulesUSBviolationMsg")
							+ "\n"
							+ PrimeMain.texts
									.getString("rulesModificationQuestion");

					// Custom button text
					String[] options = { PrimeMain.texts.getString("yes"),
							PrimeMain.texts.getString("no") };


					int i = JOptionPane.showOptionDialog(null, question,
							PrimeMain.texts.getString("confirm"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);

					// If the answer is yes
					if ( i != 0 )
					{
						// Log info
						log
								.fine("The USB ports on the device have been altered to "
										+ "match the allowed USB port and the user has "
										+ "decided to accept the modified device.");
						return false;
					}
				}



				// If the LAN ports on the object violates the rules
				if ( RulesManagment.objectLANruleViolation(rules, obj, canvas) )
				{
					// Log info
					log.fine("The device attempted added, "
							+ obj.getObjectName()
							+ ",violates the LAN(RJ45) rules of the network.");

					String question = PrimeMain.texts
							.getString("rulesLANviolationMsg")
							+ "\n"
							+ PrimeMain.texts
									.getString("rulesModificationQuestion");

					// Custom button text
					String[] options = { PrimeMain.texts.getString("yes"),
							PrimeMain.texts.getString("no") };


					int i = JOptionPane.showOptionDialog(null, question,
							PrimeMain.texts.getString("confirm"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);

					// If the answer is yes
					if ( i != 0 )
					{
						// Log info
						log
								.fine("The integrated LAN ports on the device have been altered to "
										+ "match the allowed USB port and the user has "
										+ "decided to accept the modified device.");
						return false;
					}
				}

			}


			// Add the (possibly modified) object to the given canvas
			WorkareaCanvasActions.addWidgetToCanvas(newObject, objectPoint,
					canvas, withCleanUp);
		}


		return false;
	}



	/**
	 * This function determines whether or not the {@link WorkareaCanvas} given has a any objects open by checking if there is
	 * pointer to the {@link WorkareaCanvas} inside any of the {@link ArrayList} indexes objView in {@link PrimeMain}.
	 */
	public static boolean objectOpenInCanvas(WorkareaCanvas canvas)
	{
		ObjectView[] views = PrimeMain.objView.toArray(new ObjectView[0]);

		for ( int i = 0; i < views.length; i++ )
		{
			if ( views[i].getCanvas().getSerial() == canvas.getSerial() )
			{
				return true;
			}
		}

		return false;
	}



	/**
	 * This function determines whether or not the {@link WorkareaCanvas} given has a any objects open by checking if there is
	 * pointer to the {@link WorkareaCanvas} inside any of the {@link ArrayList} indexes objView in {@link PrimeMain}.
	 */
	public static void bringObjectViewToFront(WorkareaCanvas canvas)
	{
		ObjectView[] views = PrimeMain.objView.toArray(new ObjectView[0]);

		for ( int i = 0; i < views.length; i++ )
		{
			if ( views[i].getCanvas().getSerial() == canvas.getSerial() )
			{
				views[i].toFront();
				return;
			}
		}
	}
}
