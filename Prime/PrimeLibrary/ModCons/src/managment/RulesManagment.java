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


import java.util.logging.Level;

import logistical.LibraryLogging;
import logistical.cleanup;
import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.WirelessRouter;
import objects.rackUnits.Rack;
import widgetManipulation.NetworkRules;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.ConnectionUtils;
import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;




/**
 * This class contained functions that check, verify and validate
 * {@link WorkareaCanvas WorkareaCanvases} against {@link NetworkRules}.
 * 
 * @author Bahram Malaekeh
 */
public class RulesManagment
{
	/**
	 * This function calls all the different rules checking methods.
	 */
	public static boolean processRulesChange(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			usbRuleViolation(canvas);

			lanRuleViolation(canvas);

			canContainObjectWithClassRuleViolation(canvas, Internet.class,
					canvas.getRules().isCanConnectToInternet());

			canContainObjectWithClassRuleViolation(canvas, Hub.class, canvas
					.getRules().isCanContainHub());

			canContainObjectWithClassRuleViolation(canvas,
					WirelessRouter.class, canvas.getRules()
							.isCanContainWirelessRouter());


			canvas.cleanUp();

			// Deletes all the undoable actions of the canvas
			canvas.getUndoManager().discardAllEdits();

			return true;
		}


		return false;
	}


	// HARDWARE RULES


	/**
	 * This function is meant as a reaction to the altering of the number of USB
	 * ports available for the given network.
	 * It sets the USB port for any device with a higher max USB ports to the
	 * network rule.
	 * It also removed the device that no longer can be connected because of the
	 * no available ports.
	 */
	public static boolean usbRuleViolation(WorkareaCanvas canvas)
	{
		boolean ruleViolation = false;

		if ( canvas != null )
		{
			NetworkRules rules = canvas.getRules();

			// Gets all the objects on the scene, ie in the network
			Object[] objects = canvas.getObjectsOnTheScene();

			if ( objects != null )
			{
				// Goes through all the objects
				for ( int i = 0; i < objects.length; i++ )
				{
					// If the object is not exempted the network rules
					if ( !objects[i].isExemptedNetworkRules() )
					{
						// Checks whether the object violates the rules
						boolean violation = objectUSBruleViolation(rules,
								objects[i], canvas);

						if ( violation )
						{
							ruleViolation = true;
						}
					}
				}
			}
		}

		return ruleViolation;
	}


	/**
	 * This function checks the number of USB ports available for the given
	 * network.
	 * It sets the USB port of the given device if it has a higher max USB ports
	 * then the network rule.
	 * It also removed the device that no longer can be connected because of the
	 * no available ports.
	 */
	public static boolean objectUSBruleViolation(NetworkRules rules,
			Object object, WorkareaCanvas canvas)
	{
		if ( object != null && rules != null
				&& !object.isExemptedNetworkRules() )
		{
			boolean usbNotAllowed = rules.isUSBnotAllowed();

			// The number of allowed ports
			int allowedUSBports = -1;


			// If USB ports are not allowed or (USB allowed and port number
			// equals 0)
			if ( usbNotAllowed
					|| (!(usbNotAllowed) && rules.getUSBportsAllowed() == 0) )
			{
				allowedUSBports = 0;
			}
			// If USB ports are allowed and allowed USB ports are set to higher
			// then -1, not unlimited
			else if ( rules.getUSBportsAllowed() > -1 )
			{
				allowedUSBports = rules.getUSBportsAllowed();
			}

			// If the allowed ports are not -1,unlimited
			if ( allowedUSBports != -1 )
			{
				// Since any object only has one motherboard this is a safe bet.
				try
				{
					// Gets the object motherboard
					Motherboard objectMotherboard = (Motherboard) object
							.getSpesificComponents(Motherboard.class)[0];

					// If the motherboards USB ports are higher then the allowed
					// USB ports
					if ( objectMotherboard.getMaxUSBs() > allowedUSBports )
					{
						// Sets the motherboard USB ports and removes
						// appropriate connected devices
						ComponentsManagment.USBportsValidation(object,
								objectMotherboard, allowedUSBports, canvas);

						return true;
					}
				}
				catch ( ObjectNotFoundException e )
				{
					System.out
							.println("RulesManagment - objectUSBruleViolation - Motherboard not found for object: "
									+ object.getObjectName());
				}
			}
		}

		return false;
	}


	/**
	 * This function is meant as a reaction to the altering of the number of LAN
	 * ports available for the given network.
	 * It sets the LAN port for any device with a higher max LAN ports to the
	 * network rule.
	 * It also removed the device that no longer can be connected because of the
	 * no available ports.
	 * 
	 * It also removes any {@link InternalNetworksCard} and
	 * {@link ExternalNetworksCard} that is above the maximum amount of
	 * ports, staring by {@link ExternalNetworksCard}.
	 */
	public static boolean lanRuleViolation(WorkareaCanvas canvas)
	{
		boolean ruleViolation = false;

		if ( canvas != null )
		{
			NetworkRules rules = canvas.getRules();

			Object[] objects = canvas.getObjectsOnTheScene();

			for ( int i = 0; i < objects.length; i++ )
			{
				// If the object is not exempted the network rules
				if ( !objects[i].isExemptedNetworkRules() )
				{
					boolean violation = objectLANruleViolation(rules,
							objects[i], canvas);

					if ( violation )
					{
						ruleViolation = true;
					}
				}
			}
		}
		return ruleViolation;
	}



	/**
	 * This function will return False for any object that is an instance of
	 * {@link Infrastructure}.
	 * 
	 * This function checks the number of LAN ports available for the given
	 * network.
	 * It sets the LAN port of the given device if it has a higher max LAN ports
	 * then the network rule.
	 * It also removed the device that no longer can be connected because of the
	 * no available ports.
	 * 
	 * It also removes any {@link InternalNetworksCard} and
	 * {@link ExternalNetworksCard} that is above the maximum amount of
	 * ports, staring by {@link ExternalNetworksCard}.
	 */
	public static boolean objectLANruleViolation(NetworkRules rules,
			Object object, WorkareaCanvas canvas)
	{
		boolean ruleViolation = false;

		// If the canvas is not null and it is not an infrastructure device and
		// the object is not exempted the network rules.
		if ( object != null && canvas != null
				&& !(object instanceof Infrastructure)
				&& !(object instanceof Rack)
				&& !object.isExemptedNetworkRules() )
		{
			boolean lanNotAllowed = rules.isLANnotAllowed();

			// The number of allowed ports
			int allowedLANports = -1;

			// If LAN ports are not allowed or (LAN allowed and port number
			// equals 0)
			if ( lanNotAllowed
					|| (!(lanNotAllowed) && rules.getLANportsAllowed() == 0) )
			{
				allowedLANports = 0;
			}
			// If LAN ports are allowed and allowed LAN ports are set to higher
			// then -1, not unlimited
			else if ( rules.getLANportsAllowed() > -1 )
			{
				allowedLANports = rules.getLANportsAllowed();
			}

			// If the allowed ports are not -1,unlimited
			if ( allowedLANports != -1 )
			{
				// Since any object only has one motherboard this is a safe bet.
				try
				{
					// Gets the object motherboard
					Motherboard objectMotherboard = ComponentsManagment
							.getObjectMotherboard(object);

					// The number of integrated LAN ports
					int maxIntegLan = objectMotherboard
							.getInstalledNICsAvailable(ConnectionUtils.RJ45);

					// Gets the both the number of internal NICs and the how
					// many they are
					int internalNICcount = 0;
					Object[] internalNICs = null;
					try
					{
						// Gets a temporary objects arrray
						Object[] tempInternalNICs = object
								.getSpesificComponents(InternalNetworksCard.class);


						// If the array is not null
						if ( tempInternalNICs != null )
						{
							// Creates a new array with the length of the
							// temporary array
							internalNICs = new Object[tempInternalNICs.length];

							// Goes through the entire temporary array
							for ( int i = 0; i < tempInternalNICs.length; i++ )
							{
								if ( tempInternalNICs[i] != null )
								{
									InternalNetworksCard nic = (InternalNetworksCard) tempInternalNICs[i];

									// If the connection type is the same as
									// RJ45
									if ( nic.getConnectionType().equals(
											ConnectionUtils.RJ45) )
									{
										internalNICs[i] = tempInternalNICs[i];
									}
								}
							}

							// Cleans the external nics up
							internalNICs = cleanup
									.cleanObjectArray(internalNICs);

							internalNICcount = internalNICs.length;
						}
					}
					catch ( ObjectNotFoundException e )
					{
						// Does nothing
					}



					// Gets the both the number of external NICs and the how
					// many they are
					int externalNICcount = 0;
					Object[] externalNICs = null;
					try
					{
						// Gets a temporary objects arrray
						Object[] tempExternalNICs = object
								.getSpesificComponents(ExternalNetworksCard.class);


						// If the array is not null
						if ( tempExternalNICs != null )
						{
							// Creates a new array with the length of the
							// temporary array
							externalNICs = new Object[tempExternalNICs.length];

							// Goes through the entire temporary array
							for ( int i = 0; i < tempExternalNICs.length; i++ )
							{
								if ( tempExternalNICs[i] != null )
								{
									ExternalNetworksCard nic = (ExternalNetworksCard) tempExternalNICs[i];

									// If the connection type is the same as
									// RJ45
									if ( nic.getConnectionType().equals(
											ConnectionUtils.RJ45) )
									{
										externalNICs[i] = tempExternalNICs[i];
									}
								}
							}

							// Cleans the external nics up
							externalNICs = cleanup
									.cleanObjectArray(externalNICs);

							externalNICcount = externalNICs.length;
						}
					}
					catch ( ObjectNotFoundException e )
					{
						// Does nothing
					}



					// The total number of NICs(internal and external)
					int totalNICs = internalNICcount + externalNICcount;
					// The total number of Lan ports on the device
					int totalLANs = maxIntegLan + totalNICs;

					/**
					 * If the number of integrated LAN ports is greater or equal
					 * to the allowedLANports, then all the
					 * NICs(internal and external) must be removed since there
					 * is no room for them.
					 */
					if ( maxIntegLan >= allowedLANports )
					{
						// If there are any internal NICs
						if ( internalNICcount > 0 )
						{
							for ( int j = internalNICs.length - 1; j > -1; j-- )
							{
								// Removes the NIC and if the NIC is connected
								// to an object, the connection is broken.
								ComponentsManagment.removeInternalNIC(canvas,
										object,
										(InternalNetworksCard) internalNICs[j]);
								ruleViolation = true;
							}
						}

						// If there are any external NICs
						if ( externalNICcount > 0 )
						{
							for ( int j = externalNICs.length - 1; j > -1; j-- )
							{
								ComponentsManagment.removeExternalNIC(canvas,
										object,
										(ExternalNetworksCard) externalNICs[j]);

								ruleViolation = true;
							}
						}


						if ( maxIntegLan > allowedLANports )
						{
							/**
							 * Sets the motherboard LAN ports and removes
							 * appropriate connected devices after all the
							 * Internal and
							 * External NICs have been removed.
							 */
							ComponentsManagment.portsValidation(object,
									objectMotherboard, allowedLANports,
									ConnectionUtils.RJ45, canvas);

							ruleViolation = true;
						}
					}
					/**
					 * If the number of allowed LANs is lower then the total
					 * number of LANs ports, which means that the number
					 * of integrated LAN ports is lower then the number of
					 * allowed ports. This means that some of the NICs can
					 * stay.
					 */
					else
					{
						// If there are any external NICs
						if ( externalNICcount > 0 )
						{
							for ( int j = externalNICs.length - 1; j > -1; j-- )
							{
								// If the totalLANs is above the allowedLANports
								if ( totalLANs > allowedLANports )
								{
									ComponentsManagment
											.removeExternalNIC(
													canvas,
													object,
													(ExternalNetworksCard) externalNICs[j]);

									totalLANs--;

									ruleViolation = true;
								}
							}
						}



						// If the totalLANs is above the allowedLANports and
						// there are any internal NICs
						if ( totalLANs > allowedLANports
								&& internalNICcount > 0 )
						{
							for ( int j = internalNICs.length - 1; j > -1; j-- )
							{
								// If the totalLANs is above the allowedLANports
								if ( totalLANs > allowedLANports )
								{
									// Removes the NIC and if the NIC is
									// connected to an object, the connection is
									// broken.
									ComponentsManagment
											.removeInternalNIC(
													canvas,
													object,
													(InternalNetworksCard) internalNICs[j]);

									// Removed one of the total LANs
									totalLANs--;

									ruleViolation = true;
								}
							}
						}
					}
				}
				catch ( MotherboardNotFound e )
				{
					LibraryLogging.libraryLog.logp(
							Level.SEVERE,
							"RulesManagment",
							"objectLANruleViolation",
							"The Object '" + object.getObjectName()
									+ "' in the network '"
									+ canvas.getCanvasName()
									+ "' does not contain a Motherboard.");

					if ( Settings.debug )
					{
						e.printStackTrace();
					}
				}
			}


			// Will set the IntegLAN setting on the motherboard.
			try
			{
				// Gets the object motherboard
				Motherboard objectMotherboard = ComponentsManagment
						.getObjectMotherboard(object);

				// The number of integrated LAN ports
				int maxIntegLan = objectMotherboard
						.getInstalledNICsAvailable(ConnectionUtils.RJ45);

				// If there are any integrated LAN ports
				// objectMotherboard.setIntegLANcard(maxIntegLan);
			}
			catch ( MotherboardNotFound e )
			{
				LibraryLogging.libraryLog.logp(Level.INFO, "RulesManagment",
						"objectLANruleViolation",
						"The Object '" + object.getObjectName()
								+ "' in the network '" + canvas.getCanvasName()
								+ "' does not contain a Motherboard.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
			}
		}





		return ruleViolation;
	}

	// SOFTWARE RULES





	// INFRASTRUCTURE RULES


	/**
	 * This function determines if the rules of the {@link WorkareaCanvas}
	 * allowed class type objects. If the {@link WorkareaCanvas} does not allow
	 * it, those class type objects are removed.
	 */
	public static boolean canContainObjectWithClassRuleViolation(
			WorkareaCanvas canvas, Class classType, boolean canContain)
	{
		boolean ruleViolation = false;

		// If the network can contain Internet objects
		if ( !canContain )
		{
			// Gets the objects on the canvas
			WidgetObject[] objects = canvas.getWidgetObjectsOnTheScene();

			for ( int i = 0; i < objects.length; i++ )
			{
				// If the object is not exempted the network rules
				if ( !objects[i].getObject().isExemptedNetworkRules() )
				{
					if ( objects[i].getObject().getClass().equals(classType) )
					{
						// Removes all connection to the WidgetObject
						WorkareaCanvasActions.removeAllConnectionsToFromObject(
								canvas, objects[i].getObject());

						// Removes the WidgetObject from the canvas
						canvas.getMainLayer().removeChild(objects[i]);

						canvas.subtractFromNumberOgWidgetsOnTheCanvas();

						ruleViolation = true;
					}
				}
			}
		}

		return ruleViolation;
	}



	/**
	 * This method determines whether the given {@link NetworkRules} allows the
	 * given {@link Object}.
	 */
	public static boolean canContainObject(NetworkRules rules, Object obj)
	{

		// If the object is not exempted the network rules
		if ( !obj.isExemptedNetworkRules() )
		{
			// HUB// If the object is a Hub object
			if ( obj instanceof Hub )
			{
				// If the network can not contain a Hub object
				if ( !rules.isCanContainHub() )
				{
					return false;
				}
			}


			// WIRELESSROUTER
			// If the object is a Hub object
			if ( obj instanceof WirelessRouter )
			{
				// If the network can not contain a WirelessRouter object
				if ( !rules.isCanContainWirelessRouter() )
				{
					return false;
				}
			}


			// INTERNET
			// If the object is a Hub object
			if ( obj instanceof Internet )
			{
				// If the network can not contain a Internet object
				if ( !rules.isCanConnectToInternet() )
				{
					return false;
				}
			}
		}

		return true;
	}
}
