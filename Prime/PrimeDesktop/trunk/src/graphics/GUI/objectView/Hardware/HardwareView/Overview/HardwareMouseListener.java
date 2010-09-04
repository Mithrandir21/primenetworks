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
package graphics.GUI.objectView.Hardware.HardwareView.Overview;


import exceptions.MotherboardNotFound;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.MotherboardNewView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import managment.CanvasManagment;
import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.Motherboard;
import widgetManipulation.NetworkRules;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HardwareMouseListener extends MouseAdapter implements
		ActionListener
{
	// This is the main Object
	private Object mainObject;

	// The Object that is to right clicked on
	private Object hardwareObject;

	// The panel where the Popup menu will be shown
	private JPanel panel;


	/**
	 * A constructor that takes a JPanel for the properties display, a main
	 * {@link Object} and the {@link Hardware}.
	 */
	public HardwareMouseListener(JPanel hardwarePanel, Object mainObj,
			Object hardObj)
	{
		panel = hardwarePanel;
		mainObject = mainObj;
		hardwareObject = hardObj;
	}




	/**
	 * Creates and pops up the JMenu coordinates gotten from the MouseEvent.
	 * This menu is shown when right clicked on a Hardware Object in the
	 * HardwareView of an Object on the {@link WorkareaCanvas}.
	 * 
	 * @param e
	 */
	private void myPopupEvent(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();

		JPopupMenu itemPopup = new JPopupMenu();


		JMenuItem deleteHardware = new JMenuItem(
				PrimeMain.texts.getString("hwTabDeleteHWactionLabel"));
		deleteHardware.setActionCommand("Delete Hardware");
		deleteHardware.addActionListener(this);


		JMenuItem editHardware = new JMenuItem(
				PrimeMain.texts.getString("hwTabEditHWactionLabel"));
		editHardware.setActionCommand("Edit Hardware");
		editHardware.addActionListener(this);



		itemPopup.add(deleteHardware);
		itemPopup.add(editHardware);

		itemPopup.show(panel, x, y);
	}


	/**
	 * MUST HAVE BOTH THESE METHODES BECAUSE THE POPUPTRIGGERS ARE DIFFERENT IN
	 * DIFFERENT OPERATING SYSTEMS.
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("Delete Hardware") )
		{
			// Finds the canvas that contains the main object
			WorkareaCanvas canvas = CanvasManagment.findCanvas(mainObject,
					PrimeMain.canvases);

			if ( canvas != null )
			{
				// If the Hardware Object selected is not a Motherboard
				if ( !(hardwareObject instanceof Motherboard) )
				{
					// Asks the user to confirm the deletion
					int answer = JOptionPane.showConfirmDialog(panel,
							PrimeMain.texts
									.getString("hwTabDeleteHWquestionMsg"),
							PrimeMain.texts.getString("confirm"),
							JOptionPane.YES_NO_OPTION);

					// If the user verifies the choice
					if ( answer == JOptionPane.YES_OPTION )
					{
						if ( canvas != null )
						{
							// Attempts to remove the component
							try
							{
								ComponentsManagment.removeComponent(canvas,
										mainObject, hardwareObject);

								// Updates the views of the object to correctly
								// show the current info.
								ObjectView view = PrimeMain
										.getObjectView(mainObject);
								if ( view != null )
								{
									view.updateViewInfo();
								}
								// If no view is returned, then the standard
								// object view
								// is open and that should be updated.
								else if ( PrimeMain.stdObjView != null )
								{
									PrimeMain.stdObjView.getSplitView()
											.getObjView().getHardStdObjView()
											.updateTabInfo();
								}
							}
							catch ( MotherboardNotFound e1 )
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
				else
				{
					// Asks the user to confirm the deletion
					int answer = JOptionPane.showConfirmDialog(panel,
							PrimeMain.texts
									.getString("hwTabDeleteHWreplaceMBmsg"),
							PrimeMain.texts.getString("confirm"),
							JOptionPane.YES_NO_OPTION);

					if ( answer == JOptionPane.YES_OPTION )
					{
						// Creates a new Motherboard object
						Motherboard mbObj = PrimeMain.standard_internal_components
								.getSt_MB();

						// VALIDATES THE USB/LAN PORTS AGAINS THE NETWORK RULES

						// If the object is not exempted the network rules
						if ( !mainObject.isExemptedNetworkRules() )
						{
							NetworkRules rules = canvas.getRules();

							// USB
							// If USB is allowed
							if ( !rules.isUSBnotAllowed() )
							{
								// If the new number of ports is greater then
								// the maximum number of allowed ports.
								if ( rules.getUSBportsAllowed() != -1
										&& mbObj.getMaxUSBs() > rules
												.getUSBportsAllowed() )
								{
									mbObj.setMaxUSBs(rules.getUSBportsAllowed());
									mbObj.setUSBPortsAvailable(mbObj
											.getMaxUSBs());

									String msg = PrimeMain.texts
											.getString("rulesNewMBwithUSBviolationMsg")
											+ "\n"
											+ PrimeMain.texts
													.getString("rulesNewMBCorrectionMsg");

									JOptionPane.showMessageDialog(null, msg,
											PrimeMain.texts.getString("error"),
											JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								mbObj.setMaxUSBs(0);
								mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs());

								String msg = PrimeMain.texts
										.getString("rulesUSBnotAllowedMsg")
										+ "\n"
										+ PrimeMain.texts
												.getString("rulesNewMBCorrectionMsg");

								JOptionPane.showMessageDialog(null, msg,
										PrimeMain.texts.getString("error"),
										JOptionPane.ERROR_MESSAGE);
							}


							// LAN
							// If LAN is allowed
							if ( !rules.isLANnotAllowed() )
							{
								// If the new number of ports is greater then
								// the maximum number of allowed ports.
								if ( rules.getLANportsAllowed() != -1
										&& mbObj.getMaxIntegLANs() > rules
												.getLANportsAllowed() )
								{
									mbObj.setMaxIntegratedLANs(rules
											.getLANportsAllowed());
									mbObj.setIntegLANPortsAvailable(mbObj
											.getMaxIntegLANs());

									String msg = PrimeMain.texts
											.getString("rulesNewMBwithLANviolationMsg")
											+ "\n"
											+ PrimeMain.texts
													.getString("rulesNewMBCorrectionMsg");

									JOptionPane.showMessageDialog(null, msg,
											PrimeMain.texts.getString("error"),
											JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								mbObj.setMaxIntegratedLANs(0);
								mbObj.setIntegLANPortsAvailable(mbObj
										.getMaxIntegLANs());

								String msg = PrimeMain.texts
										.getString("rulesLANnotAllowedMsg")
										+ "\n"
										+ PrimeMain.texts
												.getString("rulesNewMBCorrectionMsg");

								JOptionPane.showMessageDialog(null, msg,
										PrimeMain.texts.getString("error"),
										JOptionPane.ERROR_MESSAGE);
							}
						}

						// Creates a new motherboard editor where the user can
						// save a new Motherboard
						new MotherboardNewView(mainObject, mbObj);
					}
				}
			}
		}
		else if ( e.getActionCommand().equals("Edit Hardware") )
		{
			// Gets the Object View which will contain the hardware editor view
			// of each hardware object
			ObjectView objView = PrimeMain.objView.get(0);

			// Call for a new Hardware editor to be create and then sets the tab
			// focus to the hardware object the user
			// selected to be edited.
			objView.getObjectView().getHardwareEditor()
					.createNewHardwareEditor(mainObject)
					.setTabFocus((Hardware) hardwareObject);
		}
	}
}
