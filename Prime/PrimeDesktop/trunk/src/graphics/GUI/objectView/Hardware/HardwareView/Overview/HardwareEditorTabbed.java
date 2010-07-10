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
package graphics.GUI.objectView.Hardware.HardwareView.Overview;


import exceptions.MotherboardNotFound;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Views.CPUView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.DiscDriveView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.ExternaNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.GraphicsCardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HDDView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.InternalNICView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.MotherboardView;
import graphics.GUI.objectView.Hardware.HardwareView.Views.RAMView;

import java.awt.Component;

import javax.swing.JTabbedPane;

import managment.ComponentsManagment;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HardwareEditorTabbed extends JTabbedPane
{

	private Object mainobj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public HardwareEditorTabbed(Object obj)
	{
		mainobj = obj;
		populateTabs(obj);
	}


	/**
	 * Creates and adds tabs to this JTabbedPane instance based on the class of
	 * the internal components of the given object, such as Motherboard, CPU or
	 * RAM.
	 * 
	 * @param obj
	 *            The object that holds the internal components which in turn
	 *            are the basis for the creation of the hardware views.
	 */
	public void populateTabs(Object obj)
	{
		Object[] components = obj.getComponents();


		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] instanceof Motherboard )
			{
				this.addTab(PrimeMain.texts.getString("motherboard"), null,
						new MotherboardView(obj, (Motherboard) components[i]),
						PrimeMain.texts.getString("hwTabMBtabDescription"));
			}
			else if ( components[i] instanceof CPU )
			{
				this.addTab(PrimeMain.texts.getString("cpu"), null,
						new CPUView(obj, (CPU) components[i]), PrimeMain.texts
								.getString("hwTabCPUtabDescription"));
			}
			else if ( components[i] instanceof HDD )
			{
				this.addTab(PrimeMain.texts.getString("hdd"), null,
						new HDDView(obj, (HDD) components[i]), PrimeMain.texts
								.getString("hwTabHDDtabDescription"));
			}
			else if ( components[i] instanceof Ram )
			{
				this.addTab(PrimeMain.texts.getString("ram"), null,
						new RAMView(obj, (Ram) components[i]), PrimeMain.texts
								.getString("hwTabRAMtabDescription"));
			}
			else if ( components[i] instanceof Discdrive )
			{
				this.addTab(PrimeMain.texts.getString("discdrive"), null,
						new DiscDriveView(obj, (Discdrive) components[i]),
						PrimeMain.texts.getString("hwTabDVDRWtabDescription"));
			}
			else if ( components[i] instanceof GraphicsCard )
			{
				this
						.addTab(PrimeMain.texts.getString("graphicalCard"),
								null, new GraphicsCardView(obj,
										(GraphicsCard) components[i]),
								PrimeMain.texts
										.getString("hwTabGPUtabDescription"));
			}
			else if ( components[i] instanceof InternalNetworksCard )
			{
				this.addTab(PrimeMain.texts.getString("internalNetworkCard"),
						null, new InternalNICView(obj,
								(InternalNetworksCard) components[i]),
						PrimeMain.texts.getString("hwTabIntNICtabDescription"));
			}
			else if ( components[i] instanceof ExternalNetworksCard )
			{
				this.addTab(PrimeMain.texts.getString("externalNetworkCard"),
						null, new ExternaNICView(obj,
								(ExternalNetworksCard) components[i]),
						PrimeMain.texts.getString("hwTabExtNICtabDescription"));
			}
		}
	}



	/**
	 * This method calls the save methods on all the different HardwareViews and
	 * if the boolean given is true, calls also the validation methods on all
	 * views. If any of the validations fail, none off the save methods will be
	 * called.
	 * 
	 * @param verify
	 *            Boolean saying if validation should be run on the data.
	 * @return If the save methods in each of the views does not return false,
	 *         which would mean that it did not save, the method will return
	 *         true. Else it will return false;
	 */
	public boolean save(boolean verify)
	{
		boolean validationFailed = false;


		// If verify is set, the views will be validated.
		if ( verify )
		{
			// Boolean array that contains the validation status of each view.
			boolean[] verified = new boolean[this.getComponentCount()];

			// FIXME
			/**
			 * Goes through all the views and gets the validation status of each
			 * one and places that boolean in the validation array.
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				verified[i] = ((HardwareViewInterface) comp)
						.validateNecessaryData();
			}

			/**
			 * If any of the validation function in any of the views return
			 * false the loop will end and none of the values for any of the
			 * views will be saved. The JFrame will also not exit so the user
			 * has a chance to change the value.
			 */
			for ( int i = 0; i < verified.length; i++ )
			{
				if ( verified[i] == false )
				{
					validationFailed = true;
					i = verified.length;
				}
			}
		}
		// Checks if any of the views failed its validation.
		if ( validationFailed == false )
		{
			boolean close = true;

			/**
			 * Goes through all the views and saves the values since none of the
			 * views failed its validation.
			 */
			for ( int i = 0; i < this.getComponentCount(); i++ )
			{
				Component comp = this.getComponent(i);

				if ( ((HardwareViewInterface) comp).save() == false )
				{
					close = false;
				}
			}


			try
			{
				ComponentsManagment.processAllChanges(mainobj);
			}
			catch ( MotherboardNotFound e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			// Updates the views of the object to correctly show the
			// current info.
			ObjectView view = PrimeMain.getObjectView(mainobj);
			if ( view != null )
			{
				view.updateViewInfo();
			}

			// Returns a boolean showing that everything i saved.
			return close;
		}
		// At least one of the validations have failed.
		else
		{
			/**
			 * Shows that at least one of the validations have failed and that
			 * nothing has been saved.
			 */
			return false;
		}
	}
}
