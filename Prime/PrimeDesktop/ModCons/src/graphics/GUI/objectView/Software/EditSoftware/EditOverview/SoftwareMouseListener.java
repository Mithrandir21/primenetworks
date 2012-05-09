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
package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import logistical.cleanup;
import managment.DesktopSoftwareManagment;
import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.OperatingSystem;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SoftwareMouseListener extends MouseAdapter implements
		ActionListener
{
	// This is the main Object
	private Object mainObject;

	// The Object that is to right clicked on
	private Object softwareObject;

	// The panel where the Popup menu will be shown
	private JPanel panel;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public SoftwareMouseListener(JPanel hardwarePanel, Object mainObj,
			Object softObj)
	{
		panel = hardwarePanel;
		mainObject = mainObj;
		softwareObject = softObj;
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
				PrimeMain.texts.getString("swTabRemoveSoftwareButtonLabel"));
		deleteHardware.setActionCommand("Delete Software");
		deleteHardware.addActionListener(this);


		JMenuItem editHardware = new JMenuItem(
				PrimeMain.texts.getString("swTabEditSoftwareButtonLabel"));
		editHardware.setActionCommand("Edit Software");
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
		if ( e.getActionCommand().equals("Delete Software") )
		{
			int answer = -1;
			// If the Hardware Object selected is not a Motherboard
			if ( !(softwareObject instanceof OperatingSystem) )
			{
				// Asks the user to confirm the deletion
				answer = JOptionPane.showConfirmDialog(panel,
						PrimeMain.texts.getObject("swTabDeleteSWquestionMsg"),
						PrimeMain.texts.getString("verify"),
						JOptionPane.YES_NO_OPTION);
			}
			else
			{
				// Asks the user to confirm the deletion
				answer = JOptionPane.showConfirmDialog(panel,
						PrimeMain.texts.getString("osViewRemovalQuestionText"),
						PrimeMain.texts.getString("confirm"),
						JOptionPane.YES_NO_OPTION);
			}

			// If the user verifies the choice
			if ( answer == JOptionPane.YES_OPTION )
			{
				// Removes the OS from the software array of the main object
				DesktopSoftwareManagment.removeSoftware(mainObject,
						(Software) softwareObject);

				// All the software of the main obj(without the OS)
				Software[] software = mainObject.getSoftware();

				// Goes through all the software
				for ( int i = 0; i < software.length; i++ )
				{

					// The test does not include instances of Operating
					// system
					if ( !(software[i] instanceof OperatingSystem) )
					{
						// Checks whether or not the given software is still
						// compatible
						if ( !(SoftwareManagment.validateSoftware(software[i],
								mainObject)) )
						{
							// If the software is not compatible the index
							// of
							// that software will be set to null
							software[i] = null;
						}
					}
				}

				// Removes all the null pointers in an array
				software = cleanup.cleanObjectArray(software);

				// Sets the remaining software as the software of the main
				// object
				mainObject.setSoftware(software);


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain.getObjectView(mainObject);
				if ( view != null )
				{
					view.updateViewInfo();
				}
				// If no view is returned, then the standard object view is
				// open and that should be updated.
				else if ( PrimeMain.stdObjView != null )
				{
					PrimeMain.stdObjView.getSplitView().getObjView()
							.getSoftStdObjView().updateTabInfo();
				}
			}
		}
		else if ( e.getActionCommand().equals("Edit Software") )
		{
			// Gets the Object View which will contain the software editor view
			// of each software object
			ObjectView objView = PrimeMain.objView.get(0);

			// Call for a new Software editor to be create and then sets the tab
			// focus to the software object the user
			// selected to be edited.
			objView.getObjectView().getSoftwareEditor()
					.createNewSoftwareEditor(mainObject)
					.setTabFocus((Software) softwareObject);
		}
	}

}
